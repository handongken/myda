package com.bqhx.yyb.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.Region;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.*;

public class ExcelUtil {
	private static ExcelUtil eu = new ExcelUtil();

	private ExcelUtil() {
	}

	public static ExcelUtil getInstance() {
		return eu;
	}

	/**
	 * 处理对象转换为Excel
	 * 
	 * @param template
	 *            模板路径
	 * @param objs
	 *            输入的对象列表
	 * @param clz
	 *            对象的类型
	 * @param isClasspath
	 *            模板是否在classPath路径下
	 * @return
	 */
	private ExcelTemplate handlerObj2Excel(String template, List objs, Class clz, boolean isClasspath) {
		ExcelTemplate et = ExcelTemplate.getInstance();
		try {
			// 获取模板
			if (isClasspath) {
				et.readTemplateByClasspath(template);
			} else {
				et.readTemplateByPath(template);
			}
			// 获取模板中要替换的数据行
			int readLine = et.getDataRowNum();
			// System.out.println("模板中要替换的数据行在： " + readLine + " 行");//3
			String[] datas = getDatasByTemplate(et, clz, readLine);
			// 输出值
			for (int j = 0; j < objs.size(); j++) {
				Object obj = objs.get(j);
				et.createNewRow();
				// System.out.println("表格最后一行：" + et.getLastRowIndex());
				for (int i = 0; i < datas.length; i++) {
					// 创建带公式单元格
					if (datas[i].contains("@")) {
						et.createCell(Cell.CELL_TYPE_FORMULA, createCurColFormula(datas[i], readLine + j + 1));
						continue;
					}
					// 创建数据单元格
					if (datas[i].startsWith("#")) {
						String rel = "";
						if (datas[i].indexOf("-") != -1) {
							String begin = datas[i].substring(0, datas[i].indexOf("-"));// #{beginDate}
							@SuppressWarnings("unchecked")
							Method beginM = clz.getDeclaredMethod(getMethodName(begin));
							String beginD = String.valueOf(beginM.invoke(obj));
							String end = datas[i].substring(datas[i].indexOf("-") + 1);// #{endDate}
							@SuppressWarnings("unchecked")
							Method endM = clz.getDeclaredMethod(getMethodName(end));
							String endD = String.valueOf(endM.invoke(obj));
							rel = beginD + "-" + endD;
							et.createCell(rel);
						} else {
							if (datas[i].startsWith("#ser")) {
								rel = String.valueOf(et.getCurRowIndex() - readLine);
								et.createCell(rel);
							} else {
								@SuppressWarnings("unchecked")
								Method m = clz.getDeclaredMethod(getMethodName(datas[i]));
								rel = String.valueOf(m.invoke(obj));
								String methodReturnType = m.getReturnType().getName();
								if (methodReturnType.contains("BigDecimal")) {
									et.createCell(new BigDecimal(rel).doubleValue());
									continue;
								} else if (methodReturnType.contains("Integer")) {
									et.createCell(Integer.valueOf(rel));
									continue;
								}
								et.createCell(rel);
							}
						}
					}
				}
			}
			Sheet sheetModel = et.getWb().getSheetAt(0);
			sheetModel.setForceFormulaRecalculation(true);
			int firstrow = sheetModel.getFirstRowNum();
			int lasttrow = sheetModel.getLastRowNum();
			// System.out.println("模板第一个sheet第一行： " + firstrow + " , " +
			// "模板第一个sheet最后一行： " + lasttrow);
			/*
			 * for(int i = 0;i < sheetNames.length;i++){ Sheet newSheet =
			 * et.getWb().createSheet(sheetNames[i]);
			 * copySheet(et.getWb(),sheetModel,newSheet,firstrow,lasttrow); }
			 */

		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return et;
	}

	private ExcelTemplate handlerObj2ExcelHP(String template, List objs, Class clz, boolean isClasspath) {
		ExcelTemplate et = ExcelTemplate.getInstance();
		CellRangeUtil cellRangeUtil = new CellRangeUtil();
		try {
			// 获取模板
			if (isClasspath) {
				et.readTemplateByClasspath(template);
			} else {
				et.readTemplateByPath(template);
			}
			// 获取模板中要替换的数据行
			int readLine = et.getDataRowNum();
			// System.out.println("模板中要替换的数据行在： " + readLine + " 行");//3
			String[] datas = getDatasByTemplate(et, clz, readLine);
			Sheet sheetModel = et.getWb().getSheetAt(0);
			sheetModel.setForceFormulaRecalculation(true);
			Integer ser = 1;
			Integer serRowNum = 1;
			Integer sybRowNum = 1;
			Integer dqRowNum = 1;
			// 输出值
			for (int j = 0; j < objs.size(); j++) {
				Object obj = objs.get(j);
				et.createNewRow();
				// System.out.println("表格最后一行：" + et.getLastRowIndex());
				for (int i = 0; i < datas.length; i++) {
					// dqindex
					int index = getDataIndex(datas, "#{dq}");
					// 创建带公式单元格
					if (datas[i].contains("@")) {
						et.createCell(Cell.CELL_TYPE_FORMULA, createCurColFormula(datas[i], readLine + j + 1));
						continue;
					}
					// 创建数据单元格
					if (datas[i].startsWith("#")) {
						String rel = "";
						// 序号
						if (datas[i].startsWith("#ser")) {
							// 当前syb
							String curSyb = getDataValue(obj, datas[i + 1], clz);
							if (objs.size() > 1) {
								// 首行
								if (j == 0) {
									et.createCell(ser);
									// 下一行syb
									String nextSyb = getDataValue(objs.get(j + 1), datas[i + 1], clz);
									if (!curSyb.equals(nextSyb)) {
										serRowNum = et.getCurRowIndex();
									}
								} // 不是首行也不是最后一行
								else if (j + 1 < objs.size()) {
									// 上一行syb
									String syb = getDataValue(objs.get(j - 1), datas[i + 1], clz);
									// 下一行syb
									String nextSyb = getDataValue(objs.get(j + 1), datas[i + 1], clz);
									if (!curSyb.equals(syb)) {
										ser = ser + 1;
										serRowNum = et.getCurRowIndex();
										et.createCell(ser);
									} else {
										if (!curSyb.equals(nextSyb)) {
										}
										et.createCell(ser);
									}
								} // 最后一行
								else if (j + 1 == objs.size()) {
									// 上一行syb
									String syb = getDataValue(objs.get(j - 1), datas[i + 1], clz);
									if (curSyb.equals(syb)) {
										et.createCell(ser);
									} else {
										ser = ser + 1;
										et.createCell(ser);
										serRowNum = et.getCurRowIndex();
									}
								}
							} else {
								et.createCell(ser);
							}
						} // 除去ser的data
						else {
							@SuppressWarnings("unchecked")
							Method m = clz.getDeclaredMethod(getMethodName(datas[i]));
							rel = String.valueOf(m.invoke(obj));
							String methodReturnType = m.getReturnType().getName();
							if (methodReturnType.contains("String")) {
								//// 记录每个大区首行dqRowNum
								if (datas[i].equals("#{dq}")) {
									et.createCell(rel);
									// 当前dq
									String curDq = getDataValue(obj, datas[i], clz);
									if (objs.size() > 1) {
										// 首行
										if (j == 0) {
											dqRowNum = et.getCurRowIndex();
										} // 不是首行也不是最后一行
										else if (j + 1 < objs.size()) {
											// 上一行dq
											String dq = getDataValue(objs.get(j - 1), datas[i], clz);
											if (!curDq.equals(dq)) {
												dqRowNum = et.getCurRowIndex();
											}
										} // 最后一行
										else if (j + 1 == objs.size()) {
											// 上一行dq
											String dq = getDataValue(objs.get(j - 1), datas[i], clz);
											if (!curDq.equals(dq)) {
												dqRowNum = et.getCurRowIndex();
											}
										}
									} else {
										et.createCell(rel);
									}
								} // 记录每个事业部首行sybRowNum
								else if (datas[i].equals("#{syb}")) {
									et.createCell(rel);
									// 当前syb
									String curSyb = getDataValue(obj, datas[i], clz);
									if (objs.size() > 1) {
										// 首行
										if (j == 0) {
											sybRowNum = et.getCurRowIndex();
										} // 不是首行也不是最后一行
										else if (j + 1 < objs.size()) {
											// 上一行syb
											String syb = getDataValue(objs.get(j - 1), datas[i], clz);
											if (!curSyb.equals(syb)) {
												sybRowNum = et.getCurRowIndex();
											}
										} // 最后一行
										else if (j + 1 == objs.size()) {
											// 上一行syb
											String syb = getDataValue(objs.get(j - 1), datas[i], clz);
											if (!curSyb.equals(syb)) {
												sybRowNum = et.getCurRowIndex();
											}
										}
									} else {
										sybRowNum = et.getCurRowIndex();
									}
								} else {
									et.createCell(rel);
								}
							} else if (methodReturnType.contains("BigDecimal")) {
								double value = new BigDecimal(rel).doubleValue()/10000.0;
								System.out.println("before: " + new BigDecimal(rel).doubleValue() + "after: " + value);
								et.createCell(value);
							} else if (methodReturnType.contains("Integer")) {
								if(datas[i].equals("#{moneySum}")){
									double value = Double.valueOf(rel)/10000.0;
									/*DecimalFormat df = new DecimalFormat("####.####"); 
									String str = df.format(value);
									System.out.println("String: " + rel + " double: " + value + " string: " + str);*/
									et.createCell(value);
								}else{
									et.createCell(Integer.valueOf(rel));
								}
							}
						}
					}
					// 最后data[i]
					if (i == datas.length - 1) {
						// 当前dq
						String curDq = getDataValue(obj, datas[index], clz);
						// 当前syb
						String curSyb = getDataValue(obj, datas[index - 1], clz);
						String dqSum = "大区小计";
						String total = "总计";
						if (objs.size() > 1) {
							// 首行
							if (j == 0) {
								// 下一行dq
								String nextDq = getDataValue(objs.get(j + 1), datas[index], clz);
								if (!curDq.equals(nextDq)) {
									// 大区小计合并
									cellRangeUtil.firstRowCreateCellRange(et, sheetModel, dqSum, index,
											et.getCurRowIndex(), et.getCurRowIndex(), index, index + 1);
								}
								// 下一行syb
								String nextSyb = getDataValue(objs.get(j + 1), datas[index - 1], clz);
								if (!curSyb.equals(nextSyb)) {
									// 事业部合计合并
									String sybSum = curSyb + "合计";
									cellRangeUtil.firstRowCreateCellRange(et, sheetModel, sybSum, index,
											et.getCurRowIndex(), et.getCurRowIndex(), 0, index + 1);
									// 序号合并
									cellRangeUtil.serCellRange(et, sheetModel, sybRowNum - 1, et.getCurRowIndex() - 2,
											0);
									// 事业部合并
									cellRangeUtil.sybCellRange(et, sheetModel, sybRowNum - 1, et.getCurRowIndex() - 2,
											1);
								}
							} // 不是首行也不是最后一行
							else if (j + 1 < objs.size()) {
								// 上一行dq
								String dq = getDataValue(objs.get(j - 1), datas[index], clz);
								// 下一行dq
								String nextDq = getDataValue(objs.get(j + 1), datas[index], clz);
								if (!curDq.equals(nextDq)) {
									// 大区小计合并
									cellRangeUtil.middleRowCreateCellRange(et, sheetModel, dqSum, index, dqRowNum,
											et.getCurRowIndex(), et.getCurRowIndex(), index, index + 1);
									// 大区合并
									cellRangeUtil.dqCellRange(et, sheetModel, dqRowNum - 1, et.getCurRowIndex() - 2,
											index);
								}
								// 上一行syb
								String syb = getDataValue(objs.get(j - 1), datas[index - 1], clz);
								// 下一行syb
								String nextSyb = getDataValue(objs.get(j + 1), datas[index - 1], clz);
								if (!curSyb.equals(nextSyb)) {
									// 事业部合计合并
									String sybSum = curSyb + "合计";
									cellRangeUtil.middleRowCreateCellRange(et, sheetModel, sybSum, index, sybRowNum,
											et.getCurRowIndex(), et.getCurRowIndex(), 0, index + 1);
									// 序号合并
									cellRangeUtil.serCellRange(et, sheetModel, sybRowNum - 1, et.getCurRowIndex() - 2,
											0);
									// 事业部合并
									cellRangeUtil.sybCellRange(et, sheetModel, sybRowNum - 1, et.getCurRowIndex() - 2,
											1);
								}
							} // 最后一行
							else if (j + 1 == objs.size()) {
								// 大区小计合并
								cellRangeUtil.middleRowCreateCellRange(et, sheetModel, dqSum, index, dqRowNum,
										et.getCurRowIndex(), et.getCurRowIndex(), index, index + 1);
								// 大区合并
								cellRangeUtil.dqCellRange(et, sheetModel, dqRowNum - 1, et.getCurRowIndex() - 2, index);
								// 事业部合计合并
								String sybSum = curSyb + "合计";
								cellRangeUtil.middleRowCreateCellRange(et, sheetModel, sybSum, index, sybRowNum,
										et.getCurRowIndex(), et.getCurRowIndex(), 0, index + 1);
								// 序号合并
								cellRangeUtil.serCellRange(et, sheetModel, sybRowNum - 1, et.getCurRowIndex() - 2, 0);
								// 事业部合并
								cellRangeUtil.sybCellRange(et, sheetModel, sybRowNum - 1, et.getCurRowIndex() - 2, 1);
								// 总计
								cellRangeUtil.totalCreateCellRange(et, sheetModel, total, index, et.getCurRowIndex(),
										et.getCurRowIndex(), 0, index);
							}
						} else {// 只有一条数据
							// 大区小计合并
							cellRangeUtil.middleRowCreateCellRange(et, sheetModel, dqSum, index, dqRowNum,
									et.getCurRowIndex(), et.getCurRowIndex(), index, index + 1);
							// 大区合并
							cellRangeUtil.dqCellRange(et, sheetModel, dqRowNum - 1, et.getCurRowIndex() - 2, index);
							// 事业部合计合并
							String sybSum = curSyb + "合计";
							cellRangeUtil.middleRowCreateCellRange(et, sheetModel, sybSum, index, sybRowNum,
									et.getCurRowIndex(), et.getCurRowIndex(), 0, index + 1);
							// 序号合并
							cellRangeUtil.serCellRange(et, sheetModel, sybRowNum - 1, et.getCurRowIndex() - 2, 0);
							// 事业部合并
							cellRangeUtil.sybCellRange(et, sheetModel, sybRowNum - 1, et.getCurRowIndex() - 2, 1);
							// 总计
							cellRangeUtil.totalCreateCellRange(et, sheetModel, total, index, et.getCurRowIndex(),
									et.getCurRowIndex(), 0, index);
						}
					}
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return et;
	}

	/**
	 * 获取模板中大区的index
	 */
	private int getDataIndex(String[] datas, String data) {
		int index = 0;
		boolean isExit = false;
		for (int i = 0; i < datas.length; i++) {
			if (datas[i].equals(data)) {
				index = i;
				isExit = true;
			}
		}
		if (!isExit) {
			System.out.println("模板中未找到: " + data);
		}
		return index;
	}

	/**
	 * 获取值
	 * 
	 */
	private String getDataValue(Object obj, String datas, Class clz) {
		String syb = "";
		// Object obj = objs.get(j);
		try {
			@SuppressWarnings("unchecked")
			Method m = clz.getDeclaredMethod(getMethodName(datas));
			syb = String.valueOf(m.invoke(obj));
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return syb;
	}

	/**
	 * 
	 * @param Excel工作簿对象
	 * @param 模板Sheet页
	 * @param 新建Sheet页
	 * @param 模板页的第一行
	 * @param 模板页的最后一行
	 */
	private static void copySheet(Workbook wb, Sheet sheetModel, Sheet newSheet, int firstrow, int lasttrow) {
		// 复制一个单元格样式到新建单元格
		if ((firstrow == -1) || (lasttrow == -1) || lasttrow < firstrow) {
			return;
		}
		// 复制合并的单元格
		CellRangeAddress region = null;
		for (int i = 0; i < sheetModel.getNumMergedRegions(); i++) {
			region = sheetModel.getMergedRegion(i);
			region.getFirstRow();
			if ((region.getFirstRow() >= firstrow) && (region.getLastRow() <= lasttrow)) {
				newSheet.addMergedRegion(region);
			}
		}
		Row fromRow = null;
		Row newRow = null;
		Cell newCell = null;
		Cell fromCell = null;
		// 设置列宽
		for (int i = firstrow; i < lasttrow; i++) {
			fromRow = sheetModel.getRow(i);
			if (fromRow != null) {
				for (int j = fromRow.getLastCellNum(); j >= fromRow.getFirstCellNum(); j--) {
					int colnum = sheetModel.getColumnWidth((short) j);
					if (colnum > 100) {
						newSheet.setColumnWidth((short) j, (short) colnum);
					}
					if (colnum == 0) {
						newSheet.setColumnHidden((short) j, true);
					} else {
						newSheet.setColumnHidden((short) j, false);
					}
				}
				break;
			}
		}
		// 复制行并填充数据
		for (int i = 0; i < lasttrow; i++) {
			fromRow = sheetModel.getRow(i);
			if (fromRow == null) {
				continue;
			}
			newRow = newSheet.createRow(i - firstrow);
			newRow.setHeight(fromRow.getHeight());
			for (int j = fromRow.getFirstCellNum(); j < fromRow.getPhysicalNumberOfCells(); j++) {
				fromCell = fromRow.getCell((short) j);
				if (fromCell == null) {
					continue;
				}
				newCell = newRow.createCell((short) j);
				newCell.setCellStyle(fromCell.getCellStyle());
				int cType = fromCell.getCellType();
				newCell.setCellType(cType);
				switch (cType) {
				case HSSFCell.CELL_TYPE_STRING:
					newCell.setCellValue(fromCell.getRichStringCellValue());
					break;
				case HSSFCell.CELL_TYPE_NUMERIC:
					newCell.setCellValue(fromCell.getNumericCellValue());
					break;
				case HSSFCell.CELL_TYPE_FORMULA:
					newCell.setCellValue(fromCell.getCellFormula());
					break;
				case HSSFCell.CELL_TYPE_BOOLEAN:
					newCell.setCellValue(fromCell.getBooleanCellValue());
					break;
				case HSSFCell.CELL_TYPE_ERROR:
					newCell.setCellValue(fromCell.getErrorCellValue());
					break;
				default:
					newCell.setCellValue(fromCell.getRichStringCellValue());
					break;
				}
			}
		}
	}

	/**
	 * 根据字段获取方法名
	 * 
	 * @param s
	 * @return
	 */
	private String getMethodName(String s) {
		StringBuffer buffer = new StringBuffer();

		if (s != null && !"".equals(s)) {
			buffer.append("get");
			buffer.append(s.substring(2, 3).toUpperCase());
			buffer.append(s.substring(3, s.length() - 1));

		}
		return buffer.toString();
	}

	/**
	 * 根据标题获取相应的方法名称
	 * 
	 * @param eh
	 * @return
	 */
	private String getMethodName(ExcelHeader eh) {
		String mn = eh.getMethodName().substring(3);
		mn = mn.substring(0, 1).toLowerCase() + mn.substring(1);
		return mn;
	}

	/**
	 * 将对象转换为Excel并且导出，该方法是基于模板的导出，导出到流
	 * 
	 * @param datas
	 *            模板中的替换的常量数据
	 * @param template
	 *            模板路径
	 * @param os
	 *            输出流
	 * @param objs
	 *            输入的对象列表
	 * @param clz
	 *            对象的类型
	 * @param isClasspath
	 *            模板是否在classPath路径下
	 */
	public void exportObj2ExcelByTemplate(Map<String, String> datas, String template, OutputStream os, List objs,
			Class clz, boolean isClasspath) {
		try {
			ExcelTemplate et = handlerObj2Excel(template, objs, clz, isClasspath);
			et.replaceFinalData(datas);
			et.wirteToStream(os);
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void exportObj2ExcelByTemplateHP(Map<String, String> datas, String template, OutputStream os, List objs,
			Class clz, boolean isClasspath) {
		try {
			ExcelTemplate et = handlerObj2ExcelHP(template, objs, clz, isClasspath);
			et.replaceFinalData(datas);
			et.wirteToStream(os);
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将对象转换为Excel并且导出，该方法是基于模板的导出，导出到一个具体的路径中
	 * 
	 * @param datas
	 *            模板中的替换的常量数据
	 * @param template
	 *            模板路径
	 * @param outPath
	 *            输出路径
	 * @param objs
	 *            对象列表
	 * @param clz
	 *            对象的类型
	 * @param isClasspath
	 *            模板是否在classPath路径下
	 */
	public void exportObj2ExcelByTemplate(Map<String, String> datas, String template, String outPath, List objs,
			Class clz, boolean isClasspath) {
		ExcelTemplate et = handlerObj2Excel(template, objs, clz, isClasspath);
		et.replaceFinalData(datas);
		et.writeToFile(outPath);
	}

	/**
	 * 将对象转换为Excel并且导出，该方法是基于模板的导出，导出到流,基于Properties作为常量数据
	 * 
	 * @param prop
	 *            基于Properties的常量数据模型
	 * @param template
	 *            模板路径
	 * @param os
	 *            输出流
	 * @param objs
	 *            对象列表
	 * @param clz
	 *            对象的类型
	 * @param isClasspath
	 *            模板是否在classPath路径下
	 */
	public void exportObj2ExcelByTemplate(Properties prop, String template, OutputStream os, List objs, Class clz,
			boolean isClasspath) {
		ExcelTemplate et = handlerObj2Excel(template, objs, clz, isClasspath);
		et.replaceFinalData(prop);
		et.wirteToStream(os);
	}

	/**
	 * 将对象转换为Excel并且导出，该方法是基于模板的导出，导出到一个具体的路径中,基于Properties作为常量数据
	 * 
	 * @param prop
	 *            基于Properties的常量数据模型
	 * @param template
	 *            模板路径
	 * @param outPath
	 *            输出路径
	 * @param objs
	 *            对象列表
	 * @param clz
	 *            对象的类型
	 * @param isClasspath
	 *            模板是否在classPath路径下
	 */
	public void exportObj2ExcelByTemplate(Properties prop, String template, String outPath, List objs, Class clz,
			boolean isClasspath) {
		ExcelTemplate et = handlerObj2Excel(template, objs, clz, isClasspath);
		et.replaceFinalData(prop);
		et.writeToFile(outPath);
	}

	private Workbook handleObj2Excel(List objs, Class clz) {
		Workbook wb = new HSSFWorkbook();
		try {
			Sheet sheet = wb.createSheet();
			Row r = sheet.createRow(0);
			List<ExcelHeader> headers = getHeaderList(clz);
			Collections.sort(headers);
			// 写标题
			for (int i = 0; i < headers.size(); i++) {
				r.createCell(i).setCellValue(headers.get(i).getTitle());
			}
			// 写数据
			Object obj = null;
			for (int i = 0; i < objs.size(); i++) {
				r = sheet.createRow(i + 1);
				obj = objs.get(i);
				for (int j = 0; j < headers.size(); j++) {
					r.createCell(j).setCellValue(BeanUtils.getProperty(obj, getMethodName(headers.get(j))));
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return wb;
	}

	/**
	 * 导出对象到Excel，不是基于模板的，直接新建一个Excel完成导出，基于路径的导出
	 * 
	 * @param outPath
	 *            导出路径
	 * @param objs
	 *            对象列表
	 * @param clz
	 *            对象类型
	 */
	public void exportObj2Excel(String outPath, List objs, Class clz) {
		Workbook wb = handleObj2Excel(objs, clz);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(outPath);
			wb.write(fos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null)
					fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 导出对象到Excel，不是基于模板的，直接新建一个Excel完成导出，基于流
	 * 
	 * @param os
	 *            输出流
	 * @param objs
	 *            对象列表
	 * @param clz
	 *            对象类型
	 */
	public void exportObj2Excel(OutputStream os, List objs, Class clz) {
		try {
			Workbook wb = handleObj2Excel(objs, clz);
			wb.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 从类路径读取相应的Excel文件到对象列表
	 * 
	 * @param path
	 *            类路径下的path
	 * @param clz
	 *            对象类型
	 * @param readLine
	 *            开始行，注意是标题所在行
	 * @param tailLine
	 *            底部有多少行，在读入对象时，会减去这些行
	 * @return
	 */
	public List<Object> readExcel2ObjsByClasspath(String path, Class clz, int readLine, int tailLine) {
		Workbook wb = null;
		try {
			wb = new HSSFWorkbook(TemplateFileUtil.getTemplates(path));
			return handlerExcel2Objs(wb, clz, readLine, tailLine);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 从文件路径读取相应的Excel文件到对象列表
	 * 
	 * @param path
	 *            文件路径下的path
	 * @param clz
	 *            对象类型
	 * @param readLine
	 *            开始行，注意是标题所在行
	 * @param tailLine
	 *            底部有多少行，在读入对象时，会减去这些行
	 * @return
	 */
	public List<Object> readExcel2ObjsByPath(String path, Class clz, int readLine, int tailLine) {
		Workbook wb = null;
		try {
			wb = new HSSFWorkbook(TemplateFileUtil.getTemplates(path));
			return handlerExcel2Objs(wb, clz, readLine, tailLine);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 从类路径读取相应的Excel文件到对象列表，标题行为0，没有尾行
	 * 
	 * @param path
	 *            路径
	 * @param clz
	 *            类型
	 * @return 对象列表
	 */
	public List<Object> readExcel2ObjsByClasspath(String path, Class clz) {
		return this.readExcel2ObjsByClasspath(path, clz, 0, 0);
	}

	/**
	 * 从文件路径读取相应的Excel文件到对象列表，标题行为0，没有尾行
	 * 
	 * @param path
	 *            路径
	 * @param clz
	 *            类型
	 * @return 对象列表
	 */
	public List<Object> readExcel2ObjsByPath(String path, Class clz) {
		return this.readExcel2ObjsByPath(path, clz, 0, 0);
	}

	private String getCellValue(Cell c) {
		String o = null;
		switch (c.getCellType()) {
		case Cell.CELL_TYPE_BLANK:
			o = "";
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			o = String.valueOf(c.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_FORMULA:
			o = String.valueOf(c.getCellFormula());
			break;
		case Cell.CELL_TYPE_NUMERIC:
			o = String.valueOf(c.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_STRING:
			o = c.getStringCellValue();
			break;
		default:
			o = null;
			break;
		}
		return o;
	}

	private List<Object> handlerExcel2Objs(Workbook wb, Class clz, int readLine, int tailLine) {
		Sheet sheet = wb.getSheetAt(0);
		List<Object> objs = null;
		try {
			Row row = sheet.getRow(readLine);
			objs = new ArrayList<Object>();
			Map<Integer, String> maps = getHeaderMap(row, clz);
			if (maps == null || maps.size() <= 0)
				throw new RuntimeException("要读取的Excel的格式不正确，检查是否设定了合适的行");
			for (int i = readLine + 1; i <= sheet.getLastRowNum() - tailLine; i++) {
				row = sheet.getRow(i);
				Object obj = clz.newInstance();
				for (Cell c : row) {
					int ci = c.getColumnIndex();
					String mn = maps.get(ci).substring(3);
					mn = mn.substring(0, 1).toLowerCase() + mn.substring(1);
					BeanUtils.copyProperty(obj, mn, this.getCellValue(c));
				}
				objs.add(obj);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return objs;
	}

	private List<ExcelHeader> getHeaderList(Class clz) {
		List<ExcelHeader> headers = new ArrayList<ExcelHeader>();
		Method[] ms = clz.getDeclaredMethods();
		for (Method m : ms) {
			String mn = m.getName();
			if (mn.startsWith("get")) {
				if (m.isAnnotationPresent(ExcelResources.class)) {
					ExcelResources er = m.getAnnotation(ExcelResources.class);
					headers.add(new ExcelHeader(er.title(), er.order(), mn));
				}
			}
		}
		return headers;
	}

	@SuppressWarnings("deprecation")
	private String[] getDatasByTemplate(ExcelTemplate et, Class clz, int readLine) {
		/*
		 * if(version.equals("2007")){ XSSFSheet sheet =
		 * (XSSFSheet)et.getWb().getSheetAt(0); XSSFRow row =
		 * sheet.getRow(readLine); }else{ HSSFSheet sheet = (HSSFSheet)
		 * et.getWb().getSheetAt(0); HSSFRow row = sheet.getRow(readLine); }
		 */
		Sheet sheet = et.getWb().getSheetAt(0);
		Row row = sheet.getRow(readLine);
		// 总列数
		int colNum = row.getPhysicalNumberOfCells();
		// System.out.println("colNum:" + colNum);
		String[] data = new String[colNum];
		for (int i = 0; i < colNum; i++) {
			data[i] = getCellFormatValue(row.getCell((short) i));
		}
		return data;
	}

	private Map<Integer, String> getHeaderMap(Row titleRow, Class clz) {
		List<ExcelHeader> headers = getHeaderList(clz);
		Map<Integer, String> maps = new HashMap<Integer, String>();
		for (Cell c : titleRow) {
			String title = c.getStringCellValue();
			for (ExcelHeader eh : headers) {
				if (eh.getTitle().equals(title.trim())) {
					maps.put(c.getColumnIndex(), eh.getMethodName().replace("get", "set"));
					break;
				}
			}
		}
		return maps;
	}

	private String getCellFormatValue(Cell cell) {
		String cellvalue = "";
		if (cell != null) {
			// 判断当前Cell的Type
			switch (cell.getCellType()) {
			// 如果当前Cell的Type为NUMERIC
			case HSSFCell.CELL_TYPE_NUMERIC:
			case HSSFCell.CELL_TYPE_FORMULA: {
				// 判断当前的cell是否为Date
				// if (HSSFDateUtil.isCellDateFormatted(cell)) {
				// 如果是Date类型则，转化为Data格式

				// data格式是带时分秒的：2011-10-12 0:00:00
				// cellvalue = cell.getDateCellValue().toLocaleString();

				// data格式是不带带时分秒的：2011-10-12
				/*
				 * Date date = cell.getDateCellValue(); SimpleDateFormat sdf =
				 * new SimpleDateFormat("yyyy-MM-dd"); cellvalue =
				 * sdf.format(date);
				 */

				// }
				// 如果是纯数字
				// else {
				// 取得当前Cell的数值
				// cellvalue = String.valueOf(cell.getNumericCellValue());
				// }
				cellvalue = String.valueOf(cell.getCellFormula());
				break;
			}
			// 如果当前Cell的Type为STRIN
			case HSSFCell.CELL_TYPE_STRING:
				// 取得当前的Cell字符串
				cellvalue = cell.getRichStringCellValue().getString();
				break;
			// 默认的Cell值
			default:
				cellvalue = " ";
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;

	}

	/**
	 * 创建当前行公式
	 * 
	 * @param formula
	 * @param rowNum
	 * @return 当前行公式
	 */
	private String createCurColFormula(String formula, int rowNum) {
		String formulaRepalce = null;
		if (formula.indexOf("@") != -1) {
			formulaRepalce = formula.replaceAll("@", String.valueOf(rowNum));
		}
		return formulaRepalce;
	}

}
