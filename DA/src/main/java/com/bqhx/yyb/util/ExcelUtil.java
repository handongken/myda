package com.bqhx.yyb.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
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
			int readLine = et.getCurDataRowNum();
//			System.out.println("模板中要替换的数据行在： " + readLine + " 行");//3
			String[] datas = getDatasByTemplate(et, clz, readLine);
			// 输出值
			for (int j = 0; j < objs.size(); j++) {
				Object obj = objs.get(j);
				et.createNewRow();
//				System.out.println("表格最后一行：" + et.getLastRowIndex());
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
							if(datas[i].contains("ser")){
								rel = String.valueOf(et.getCurRowIndex()-3);
								et.createCell(rel);
							}else{
								@SuppressWarnings("unchecked")
								Method m = clz.getDeclaredMethod(getMethodName(datas[i]));
								rel = String.valueOf(m.invoke(obj));
								if(m.getReturnType().getName().contains("BigDecimal")){
									et.createCell(new BigDecimal(rel).doubleValue());
									continue;
								}
									et.createCell(rel);
							}
						}
					}
				}
			}
			et.getWb().getSheetAt(0).setForceFormulaRecalculation(true);
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
		/*if(version.equals("2007")){
			XSSFSheet sheet = (XSSFSheet)et.getWb().getSheetAt(0);
			XSSFRow row = sheet.getRow(readLine);
		}else{
			HSSFSheet sheet = (HSSFSheet) et.getWb().getSheetAt(0);
			HSSFRow row = sheet.getRow(readLine);
		}*/
		Sheet sheet = et.getWb().getSheetAt(0);
		Row row = sheet.getRow(readLine);
		// 总列数
		int colNum = row.getPhysicalNumberOfCells();
//		System.out.println("colNum:" + colNum);
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
