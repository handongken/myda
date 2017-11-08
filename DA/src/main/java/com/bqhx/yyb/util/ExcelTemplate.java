package com.bqhx.yyb.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.*;
public class ExcelTemplate {
	/**
	 * 数据行标识
	 */
	public final static String DATA_LINE = "datas";
	/**
	 * 默认样式标识
	 */
	public final static String DEFAULT_STYLE = "defaultStyles";
	/**
	 * 行样式标识
	 */
	public final static String STYLE = "styles";
	/**
	 * 插入序号样式标识
	 */
	// public final static String SER_NUM = "sernums";
	public final static String SER_NUM = "sernum";
	private static ExcelTemplate et = new ExcelTemplate();
	private Workbook wb;
	private Sheet sheet;
	/**
	 * 数据的初始化列数
	 */
	private int initColIndex;
	/**
	 * 数据的初始化行数
	 */
	private int initRowIndex;
	/**
	 * 当前列数
	 */
	private int curColIndex;
	/**
	 * 特殊当前列数
	 */
	private int specialColIndex;
	/**
	 * 当前行数
	 */
	private int curRowIndex;
	/**
	 * 当前行对象
	 */
	private Row curRow;
	/**
	 * 最后一行的数据
	 */
	private int lastRowIndex;
	/**
	 * 默认样式
	 */
	private CellStyle defaultStyle;
	/**
	 * 默认行高
	 */
	private float rowHeight;
	/**
	 * 默认类型
	 */
	private int defaultType;
	/**
	 * 存储某一方所对于的样式
	 */
	private Map<Integer, CellStyle> styles;
	/**
	 * 序号的列
	 */
	private int serColIndex;
	
	
	public Workbook getWb() {
		return wb;
	}

	private ExcelTemplate() {

	}

	public static ExcelTemplate getInstance() {
		return et;
	}

	/**
	 * 从classpath路径下读取相应的模板文件
	 * 
	 * @param path
	 * @return
	 */
	public ExcelTemplate readTemplateByClasspath(String path) {
		try {
			if(path.contains("xlsx")){
				wb = new XSSFWorkbook(TemplateFileUtil.getTemplates(path));
			}else{
				wb = new HSSFWorkbook(TemplateFileUtil.getTemplates(path));
			}
			initTemplate();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("读取模板不存在！请检查");
		}
		return this;
	}

	/**
	 * 从classpath路径下读取相应的模板文件
	 * 
	 * @param path
	 * @return
	 */
	public ExcelTemplate readTemplateByClasspathWithDate(String path) {
		try {
			if(path.contains("xlsx")){
				wb = new XSSFWorkbook(TemplateFileUtil.getTemplates(path));
			}else{
				wb = new HSSFWorkbook(TemplateFileUtil.getTemplates(path));
			}
			initTemplateWithDate();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("读取模板不存在！请检查");
		}
		return this;
	}
	
	/**
	 * 将文件写到相应的路径下
	 * 
	 * @param filepath
	 */
	public void writeToFile(String filepath) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filepath);
			wb.write(fos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("写入的文件不存在");
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("写入数据失败:" + e.getMessage());
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
	 * 将文件写到某个输出流中
	 * 
	 * @param os
	 */
	public void wirteToStream(OutputStream os) {
		try {
			wb.write(os);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("写入流失败:" + e.getMessage());
		}
	}

	/**
	 * 从某个路径来读取模板
	 * 
	 * @param path
	 * @return
	 */
	public ExcelTemplate readTemplateByPath(String path) {
		try {
			wb = new HSSFWorkbook(TemplateFileUtil.getTemplates(path));
			initTemplate();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("读取模板不存在！请检查");
		}
		return this;
	}

	/**
	 * 创建单元格
	 * 
	 * @param value
	 */
	public void createCell(String value) {
		Cell c = curRow.createCell(curColIndex);
		setCellStyle(c);
		c.setCellType(defaultType);
		setCellValue(c, value);
		curColIndex++;
	}

	/**
	 * 在指定列创建单元格
	 * 
	 * @param value
	 */
	public void createCellByCol(int colNum,String value) {
		Cell c = curRow.createCell(colNum);
		setCellStyle(c);
		c.setCellType(defaultType);
		setCellValue(c, value);
		specialColIndex++;
	}
	
	/**
	 * 根据cellType创建单元格
	 * 
	 * @param cellType
	 * @param value
	 */
	public void createCell(int cellType, String value) {
		Cell c = curRow.createCell(curColIndex);
		setCellStyle(c);
		c.setCellType(cellType);
		c.setCellFormula(value);
		curColIndex++;
	}

	/**
	 * 创建当前行列单元格
	 * 
	 * @param cellType
	 * @param value
	 */
	public void createCurCell(int cellType, String value, int curColIndex) {
		Cell c = curRow.createCell(curColIndex);
		setCellStyle(c);
		c.setCellType(cellType);
		c.setCellFormula(value);
		curColIndex++;
	}

	/**
	 * 替换除数据之外的公式
	 * 
	 * @param cellType
	 * @param value
	 */
	/*
	 * public void createCell(int cellType, int row, String value) { Cell c =
	 * curRow.createCell(curColIndex); setCellStyle(c); c.setCellType(cellType);
	 * c.setCellFormula(value); curColIndex++; }
	 */

	/**
	 * 在单元格填充数据
	 * 
	 * @param cell
	 * @param param
	 */
	public void setCellValue(Cell cell, String param) {
		if (cell != null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_FORMULA:
				try {
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue(Double.parseDouble(param));
				} catch (IllegalStateException e) {
					// value = String.valueOf(cell.getRichStringCellValue());
				}
				break;
			case Cell.CELL_TYPE_NUMERIC:
				cell.setCellValue(Double.parseDouble(param));
				// value = String.valueOf(cell.getNumericCellValue());
				break;
			case Cell.CELL_TYPE_STRING:
				cell.setCellValue(param);
				// value = String.valueOf(cell.getRichStringCellValue());
				break;
			}
		}
	}

	public void createCell(int value) {
		Cell c = curRow.createCell(curColIndex);
		setCellStyle(c);
		c.setCellValue((int) value);
		curColIndex++;
	}

	public void createCell(Date value) {
		Cell c = curRow.createCell(curColIndex);
		setCellStyle(c);
		c.setCellValue(value);
		curColIndex++;
	}

	public void createCell(double value) {
		Cell c = curRow.createCell(curColIndex);
		setCellStyle(c);
		c.setCellValue(value);
		curColIndex++;
	}

	public void createCell(boolean value) {
		Cell c = curRow.createCell(curColIndex);
		setCellStyle(c);
		c.setCellValue(value);
		curColIndex++;
	}

	public void createCell(Calendar value) {
		Cell c = curRow.createCell(curColIndex);
		setCellStyle(c);
		c.setCellValue(value);
		curColIndex++;
	}

	public void createCell(BigInteger value) {
		Cell c = curRow.createCell(curColIndex);
		setCellStyle(c);
		c.setCellValue(value == null ? 0 : value.intValue());
		curColIndex++;
	}

	/**
	 * 设置某个元素的样式
	 * 
	 * @param c
	 */
	private void setCellStyle(Cell c) {
		if (styles.containsKey(curColIndex)) {
			c.setCellStyle(styles.get(curColIndex));
		} else {
			c.setCellStyle(defaultStyle);
		}
	}

	/**
	 * 创建新行，在使用时只要添加完一行，需要调用该方法创建
	 */
	public void createNewRow() {
		if (lastRowIndex >= curRowIndex && curRowIndex != initRowIndex) {
			sheet.shiftRows(curRowIndex, lastRowIndex, 1, true, true);
			lastRowIndex++;
		}
		curRow = sheet.createRow(curRowIndex);
		curRow.setHeightInPoints(rowHeight);
		curRowIndex++;
		curColIndex = initColIndex;
	}

	/**
	 * 根据行、列创建新行
	 */
	public void createNewRow(int rowNum,int colNum) {
		if (lastRowIndex >= rowNum && rowNum != initRowIndex) {
			sheet.shiftRows(rowNum, lastRowIndex, 1, true, true);
			lastRowIndex++;
		}
		curRow = sheet.createRow(rowNum);
		curRow.setHeightInPoints(rowHeight);
		curRowIndex++;
		specialColIndex = colNum;
	}
	
	/**
	 * 创建新行，在使用时只要添加完一行，需要调用该方法创建
	 */
	public void copyAndCreateNewRow() {
		if (lastRowIndex > curRowIndex && curRowIndex != initRowIndex) {
			sheet.shiftRows(curRowIndex, lastRowIndex, 1, true, true);
			// sheet.setForceFormulaRecalculation(true);
			lastRowIndex++;
		}
		curRow = sheet.createRow(curRowIndex);
		curRow.setHeightInPoints(rowHeight);
		curRowIndex++;
		curColIndex = initColIndex;
	}

	/**
	 * 插入序号，会自动找相应的序号标示的位置完成插入
	 */
	public void insertSer() {
		int index = 1;
		Row row = null;
		Cell c = null;
		for (int i = initRowIndex; i < curRowIndex; i++) {
			row = sheet.getRow(i);
			c = row.createCell(serColIndex);
			setCellStyle(c);
			c.setCellValue(index++);
		}
	}

	/**
	 * 根据map替换相应的常量，通过Map中的值来替换$开头的值
	 * 
	 * @param datas
	 */
	public void replaceFinalData(Map<String, String> datas) {
		if (datas == null)
			return;
		String str = null;
		for (Row row : sheet) {
			for (Cell c : row) {
				if (c.getCellType() != Cell.CELL_TYPE_STRING)
					continue;
				str = c.getStringCellValue().trim();
				if (str.contains("$")) {
					int begin = str.indexOf("$");
					if (begin != 0) {
						int end = str.indexOf("}");
						String st = c.getStringCellValue().trim().substring(begin, end + 1);
						if (datas.containsKey(st)) {
							c.setCellValue(getReplacedValue(str, datas.get(st), begin, end));
						}
					} else {
						String st = c.getStringCellValue().trim();
						if (datas.containsKey(st)) {
							c.setCellValue(datas.get(st));
						}
					}
				} else if (str.contains("@") && !str.startsWith("@")) {
					if(str.contains("SUM") || str.contains("COUNT")){
						String formula = getCurColFormula(str, getFormulaRowNum());
//						System.out.println("公式： " + formula);
						int curFormulaColNum = getFormulaColNum(getFormulaRowNum(),str);
						createFormulaCell(getFormulaRowNum(),curFormulaColNum , formula);
					}else{
						String formula = getCurColFormula(str, getFormulaRowNum()+1);
//						System.out.println("公式： " + formula);
						int curFormulaColNum = getFormulaColNum(getFormulaRowNum(),str);
						createFormulaCell(getFormulaRowNum(),curFormulaColNum , formula);
					}
				}
			}
		}
	}

	/**
	 * 设置合并单元格样式(合计)
	 */
	public void setSumRegionStyle(Sheet sheet, CellRangeAddress region) {
		CellStyle cs = wb.createCellStyle();  
		//设置边框
		cs.setBorderBottom(CellStyle.BORDER_THIN); //下边框    
		cs.setBorderLeft(CellStyle.BORDER_THIN);//左边框    
		cs.setBorderTop(CellStyle.BORDER_THIN);//上边框    
		cs.setBorderRight(CellStyle.BORDER_THIN);//右边框 
		//设置颜色
		cs.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cs.setFillForegroundColor(IndexedColors.LEMON_CHIFFON.getIndex());
		//设置垂直居中
		cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cs.setAlignment(CellStyle.ALIGN_CENTER);
        for (int i = region.getFirstRow(); i <= region.getLastRow(); i++) {
            Row row = sheet.getRow(i);
            for (int j = region.getFirstColumn(); j <= region.getLastColumn(); j++) {
                Cell cell = row.getCell(j);
                if (cell == null) {
                    cell = row.createCell(j);
                    cell.setCellValue("");
                }
                cell.setCellStyle(cs);

            }
        }
    }
	
	/**
	 * 设置合并单元格样式
	 */
	public void setRegionStyle(Sheet sheet, CellRangeAddress region) {
		CellStyle cs = wb.createCellStyle();  
		//设置边框
		cs.setBorderBottom(CellStyle.BORDER_THIN); //下边框    
		cs.setBorderLeft(CellStyle.BORDER_THIN);//左边框    
		cs.setBorderTop(CellStyle.BORDER_THIN);//上边框    
		cs.setBorderRight(CellStyle.BORDER_THIN);//右边框 
		//设置垂直居中
		cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cs.setAlignment(CellStyle.ALIGN_CENTER);
        for (int i = region.getFirstRow(); i <= region.getLastRow(); i++) {
            Row row = sheet.getRow(i);
            for (int j = region.getFirstColumn(); j <= region.getLastColumn(); j++) {
                Cell cell = row.getCell(j);
                if (cell == null) {
                    cell = row.createCell(j);
                    cell.setCellValue("");
                }
                cell.setCellStyle(cs);

            }
        }
    }
	
	/**
	 * 设置每日业绩表样式
	 */
	public void setPDStyle(Sheet sheet) {
		CellStyle cs = wb.createCellStyle();
		//日期行数
		int dateRowNum = 2;
		//规模行数
		int moneyRowNum = 4;
		String str = "";
		//设置边框
		cs.setBorderBottom(CellStyle.BORDER_THIN); //下边框    
		cs.setBorderLeft(CellStyle.BORDER_THIN);//左边框    
		cs.setBorderTop(CellStyle.BORDER_THIN);//上边框    
		cs.setBorderRight(CellStyle.BORDER_THIN);//右边框 
		//设置垂直居中
		cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cs.setAlignment(CellStyle.ALIGN_CENTER);
		//设置字体
		Font font = wb.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 14);//设置字体大小
		cs.setFont(font);
		//sheet所有行
		int rowNum = sheet.getLastRowNum();
//		System.out.println("sheet所有行数: " + rowNum);
		for(int i = 0; i < rowNum+1; i++){
			Row row = sheet.getRow(i);
			//遍历一行中的所有的单元格  
		     for (Cell cell : row) {  
		        if(cell.getCellType() != Cell.CELL_TYPE_STRING){
		        	continue;
		        }
		        str = cell.getStringCellValue();
		        //记录三种行数
		        if(str.equals("日期")){
		        	dateRowNum = i;
		        	cell.setCellStyle(cs);
		        }
		        if(str.equals("星期")){
		        	cell.setCellStyle(cs);
		        }
		        if(str.equals("规模")){
		        	moneyRowNum = i;
		        	cell.setCellStyle(cs);
		        }
		        //日期行样式
		        if(i == dateRowNum){
		        	//设置颜色
		    		cs.setFillPattern(CellStyle.SOLID_FOREGROUND);
		    		cs.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		    		//设置字体
		    		font.setBoldweight(Font.BOLDWEIGHT_BOLD);//粗体显示
		    		cs.setFont(font);
		    		cell.setCellStyle(cs);
		        }//规模行样式
		        else if(i == moneyRowNum){
		        	if(!str.equals("规模") && !str.equals("")){
		        		double moneyValue = Double.valueOf(str);
		        		if(moneyValue > 0 && moneyValue < 100){
		        			CellStyle cellStyle = wb.createCellStyle();
		        			//设置边框
		        			cellStyle.setBorderBottom(CellStyle.BORDER_THIN); //下边框    
		        			cellStyle.setBorderLeft(CellStyle.BORDER_THIN);//左边框    
		        			cellStyle.setBorderTop(CellStyle.BORDER_THIN);//上边框    
		        			cellStyle.setBorderRight(CellStyle.BORDER_THIN);//右边框 
		        			//设置垂直居中
		        			cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		        			cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		        			//设置字体
		        			Font font_m = wb.createFont();
		        			font_m.setFontName("宋体");
		        			font_m.setFontHeightInPoints((short) 14);//设置字体大小
		        			cellStyle.setFont(font);
			        		//设置颜色（黄）
		        			cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		        			cellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
				    		cell.setCellStyle(cellStyle);
			        	}else if(moneyValue > 200){
			        		CellStyle cellStyle = wb.createCellStyle();
		        			//设置边框
		        			cellStyle.setBorderBottom(CellStyle.BORDER_THIN); //下边框    
		        			cellStyle.setBorderLeft(CellStyle.BORDER_THIN);//左边框    
		        			cellStyle.setBorderTop(CellStyle.BORDER_THIN);//上边框    
		        			cellStyle.setBorderRight(CellStyle.BORDER_THIN);//右边框 
		        			//设置垂直居中
		        			cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		        			cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		        			//设置字体
		        			Font font_m = wb.createFont();
		        			font_m.setFontName("宋体");
		        			font_m.setFontHeightInPoints((short) 14);//设置字体大小
		        			cellStyle.setFont(font);
			        		//设置颜色（橘）
		        			cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		        			cellStyle.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex()); 
				    		cell.setCellStyle(cellStyle);
			        	}
		        	}
		        }
		     }
		}
    }
	
	/**
	 * 从模板中获取的常量，通过Map中的值来替换$开头的值
	 * 
	 * @param datas
	 */
	public String getReplacedValue(String str, String st, int in, int end) {
		String s = str.substring(0, in);
		String t = str.substring(end + 1);
		return s + st + t;
	}

	/**
	 * 基于Properties的替换，依然也是替换#开始的
	 * 
	 * @param prop
	 */
	public void replaceFinalData(Properties prop) {
		if (prop == null)
			return;
		for (Row row : sheet) {
			for (Cell c : row) {
				// if(c.getCellType()!=Cell.CELL_TYPE_STRING) continue;
				String str = c.getStringCellValue().trim();
				if (str.startsWith("#")) {
					if (prop.containsKey(str.substring(1))) {
						c.setCellValue(prop.getProperty(str.substring(1)));
					}
				}
			}
		}
	}

	private void initTemplate() {
		sheet = wb.getSheetAt(0);
		initConfigData();
		lastRowIndex = sheet.getLastRowNum();
	}

	/**
	 * 初始化数据信息
	 */
	private void initConfigData() {
		boolean findData = false;
		boolean findSer = false;
		for (Row row : sheet) {
			if (findData)
				break;
			for (Cell c : row) {
				if (c.getCellType() != Cell.CELL_TYPE_STRING) {
					continue;
				}
				String str = c.getStringCellValue().trim();
				if (str.equals(SER_NUM)) {
					serColIndex = c.getColumnIndex();// 0
					findSer = true;
				}
				if (str.startsWith("#")) {
					initColIndex = c.getColumnIndex();
					initRowIndex = row.getRowNum();
					curColIndex = initColIndex;
					curRowIndex = initRowIndex;
					findData = true;
					defaultStyle = c.getCellStyle();
					defaultType = c.getCellType();
					rowHeight = row.getHeightInPoints();
					initStyles();
					break;
				}
			}
		}
		if (!findSer) {
			initSer();
		}
	}

	private void initTemplateWithDate() {
		sheet = wb.getSheetAt(0);
		initConfigDataWithDate();
		lastRowIndex = sheet.getLastRowNum();
	}
	
	/**
	 * 初始化日期信息
	 */
	private void initConfigDataWithDate() {
		boolean findData = false;
		boolean findSer = false;
		for (Row row : sheet) {
			if (findData)
				break;
			for (Cell c : row) {
				if (c.getCellType() != Cell.CELL_TYPE_STRING) {
					continue;
				}
				String str = c.getStringCellValue().trim();
				if (str.equals(SER_NUM)) {
					serColIndex = c.getColumnIndex();// 0
					findSer = true;
				}
				if (str.startsWith("日期")) {
					initColIndex = c.getColumnIndex();
					initRowIndex = row.getRowNum();
					curColIndex = initColIndex;
					curRowIndex = initRowIndex;
					findData = true;
					defaultStyle = c.getCellStyle();
					defaultType = c.getCellType();
					rowHeight = row.getHeightInPoints();
					initStyles();
					break;
				}
			}
		}
		if (!findSer) {
			initSer();
		}
	}
	
	/**
	 * 初始化序号位置
	 */
	private void initSer() {
		for (Row row : sheet) {
			for (Cell c : row) {
				String str = null;
				if (c.getCellType() != Cell.CELL_TYPE_STRING) {
					continue;
				}
				str = c.getStringCellValue().trim();
				if (str.equals(SER_NUM)) {
					serColIndex = c.getColumnIndex();
				}
			}
		}
	}

	/**
	 * 初始化样式信息
	 */
	private void initStyles() {
		styles = new HashMap<Integer, CellStyle>();
		for (Row row : sheet) {
			for (Cell c : row) {
				String str = null;
				if (c.getCellType() != Cell.CELL_TYPE_STRING) {
					continue;
				}
				str = c.getStringCellValue().trim();
				if (str.equals(DEFAULT_STYLE)) {
					defaultStyle = c.getCellStyle();
				}
				if (str.equals(STYLE)) {
					styles.put(c.getColumnIndex(), c.getCellStyle());
				}
			}
		}
	}

	public void initStyles(int rowNum) {
		styles = new HashMap<Integer, CellStyle>();
		for (Row row : sheet) {
			if (row.getRowNum() == rowNum) {
				for (Cell c : row) {
					String str = null;
					if (c.getCellType() != Cell.CELL_TYPE_STRING) {
						continue;
					}
					str = c.getStringCellValue().trim();
					if (str.equals(DEFAULT_STYLE)) {
						defaultStyle = c.getCellStyle();
					}
					if (str.equals(STYLE)) {
						styles.put(c.getColumnIndex(), c.getCellStyle());
					}
				}
			}
		}
	}

	/**
	 * 从0开始根据模板中获取#开头数据行数，默认是2
	 * 
	 * @return 当前数据行数
	 */
	public int getDataRowNum() {
		int curDataRowNum = 2;
		for (Row row : sheet) {
			for (Cell c : row) {
				if (c.getCellType() != Cell.CELL_TYPE_STRING) {
					continue;
				}
				String str = c.getStringCellValue().trim();
				if (str.startsWith("#")) {
					curDataRowNum = row.getRowNum();
					return curDataRowNum;
				}
			}
		}
		return curDataRowNum;
	}

	/**
	 * 从0开始根据模板中获取"日期"开头数据行数，默认是2
	 * 
	 * @return 当前数据行数
	 */
	public int getDataRowNumWithDate() {
		int curDataRowNum = 2;
		for (Row row : sheet) {
			for (Cell c : row) {
				if (c.getCellType() != Cell.CELL_TYPE_STRING) {
					continue;
				}
				String str = c.getStringCellValue().trim();
				if (str.startsWith("日期")) {
					curDataRowNum = row.getRowNum();
					return curDataRowNum;
				}
			}
		}
		return curDataRowNum;
	}
	
	/**
	 * 从模板中获取带公式的行
	 * 
	 * @return 带公式的行
	 */
	public int getFormulaRowNum() {
		int formulaRowNum = 2;
		for (Row row : sheet) {
			for (Cell c : row) {
				if (c.getCellType() != Cell.CELL_TYPE_STRING) {
					continue;
				}
				String str = c.getStringCellValue().trim();
				if (str.contains("@")) {
					formulaRowNum = row.getRowNum();
					return formulaRowNum;
				}
			}
		}
		return formulaRowNum;
	}

	/**
	 * 从模板中获取带公式的列
	 * 
	 * @return 带公式的列
	 */
	public int getFormulaColNum(int formulaRowNum,String str) {
		int formulaColNum = 0;
		Row row = sheet.getRow(formulaRowNum);
		if (row != null) {
//			int cells = row.getPhysicalNumberOfCells();// 是获取不为空的列个数。 
			int cellNum = row.getLastCellNum();// 是获取最后一个不为空的列是第几个。 
//			System.out.println("总列数: " + cellNum);
			for (int j = 0; j < cellNum; j++) {
				// 获取到列的值
				Cell c = row.getCell(j);
				if(c == null){
					continue;
				}
				if (c.getCellType() != Cell.CELL_TYPE_STRING) {
					continue;
				}
				String st = c.getStringCellValue().trim();
				if (st.contains("@") && st.equals(str)) {
					formulaColNum = j;
					return formulaColNum;
				}
			}
		}
		return formulaColNum;
	}

	/**
	 * 获取当前行公式
	 * 
	 * @param formula
	 * @param rowNum
	 * @return 当前行公式
	 */
	private String getCurColFormula(String formula, int rowNum) {
		String formulaRepalce = null;
		if (formula.indexOf("@") != -1) {
			formulaRepalce = formula.replaceAll("@", String.valueOf(rowNum));
		}
		return formulaRepalce;
	}

	/**
	 * 创建带公式单元格
	 * 
	 * @return 带公式单元格
	 */
	public void createFormulaCell(int formulaRowNum, int formulaColNum, String formula) {
		Row row = sheet.getRow(formulaRowNum);
		if (row != null) {
			Cell cell = row.createCell(formulaColNum);
			setCellStyle(cell);
			cell.setCellType(Cell.CELL_TYPE_FORMULA);
			cell.setCellFormula(formula);
		}
	}

	public int getLastRowIndex() {
		return lastRowIndex;
	}

	public void addCurColIndex() {
		this.curColIndex++;
	}

	public int getCurRowIndex() {
		return curRowIndex;
	}

	public int getCurColIndex() {
		return curColIndex;
	}

	public float getRowHeight() {
		return rowHeight;
	}


}
