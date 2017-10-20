package com.bqhx.yyb.util;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

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
    public final static String SER_NUM = "sernums";
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
    *  默认类型
    */
    private int defaultType;
    /**
     * 存储某一方所对于的样式
     */
    private Map<Integer,CellStyle> styles;
    /**
     * 序号的列
     */
    private int serColIndex;
    
    public Workbook getWb() {
		return wb;
	}
	private ExcelTemplate(){

    }
    public static ExcelTemplate getInstance() {
        return et;
    }

    /**
     * 从classpath路径下读取相应的模板文件
     * @param path
     * @return
     */
    public ExcelTemplate readTemplateByClasspath(String path) {
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
     * 将文件写到相应的路径下
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
            throw new RuntimeException("写入数据失败:"+e.getMessage());
        } finally {
            try {
                if(fos!=null) fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 将文件写到某个输出流中
     * @param os
     */
    public void wirteToStream(OutputStream os) {
        try {
            wb.write(os);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("写入流失败:"+e.getMessage());
        }
    }
    /**
     * 从某个路径来读取模板
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
     * @param value
     */
    public void createCell(String value) {
        Cell c = curRow.createCell(curColIndex);
        setCellStyle(c);
        c.setCellType(defaultType);
        setCellValue(c,value);
        curColIndex++;
    }
    
    /**
     * 根据cellType创建单元格
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
     * 在单元格填充数据
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
        c.setCellValue((int)value);
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
        c.setCellValue(value==null?0:value.intValue());
        curColIndex++;
    }
    /**
     * 设置某个元素的样式
     * @param c
     */
    private void setCellStyle(Cell c) {
        if(styles.containsKey(curColIndex)) {
            c.setCellStyle(styles.get(curColIndex));
        } else {
            c.setCellStyle(defaultStyle);
        }
    }
    /**
     * 创建新行，在使用时只要添加完一行，需要调用该方法创建
     */
    public void createNewRow() {
        if(lastRowIndex>curRowIndex&&curRowIndex!=initRowIndex) {
            sheet.shiftRows(curRowIndex, lastRowIndex, 1,true,true);
            lastRowIndex++;
        }
        curRow = sheet.createRow(curRowIndex);
        curRow.setHeightInPoints(rowHeight);
        curRowIndex++;
        curColIndex = initColIndex;
    }

    /**
     * 创建新行，在使用时只要添加完一行，需要调用该方法创建
     */
    public void copyAndCreateNewRow() {
        if(lastRowIndex>curRowIndex&&curRowIndex!=initRowIndex) {
            sheet.shiftRows(curRowIndex, lastRowIndex, 1,true,true);
//            sheet.setForceFormulaRecalculation(true);
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
        for(int i=initRowIndex;i<curRowIndex;i++) {
            row = sheet.getRow(i);
            c = row.createCell(serColIndex);
            setCellStyle(c);
            c.setCellValue(index++);
        }
    }
    /**
     * 根据map替换相应的常量，通过Map中的值来替换$开头的值
     * @param datas
     */
    public void replaceFinalData(Map<String,String> datas) {
        if(datas==null) return;
        String str = null;
        for(Row row:sheet) {
            for(Cell c:row) {
                if(c.getCellType()!=Cell.CELL_TYPE_STRING) continue;
            	str = c.getStringCellValue().trim();
                if(str.startsWith("$")) {
                    if(datas.containsKey(str)) {
                        c.setCellValue(datas.get(str));
                    }
                }
            }
        }
    }
    /**
     * 基于Properties的替换，依然也是替换#开始的
     * @param prop
     */
    public void replaceFinalData(Properties prop) {
        if(prop==null) return;
        for(Row row:sheet) {
            for(Cell c:row) {
//                if(c.getCellType()!=Cell.CELL_TYPE_STRING) continue;
                String str = c.getStringCellValue().trim();
                if(str.startsWith("#")) {
                    if(prop.containsKey(str.substring(1))) {
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
         for(Row row:sheet) {
            if(findData) break;
            for(Cell c:row) {
            	if(c.getCellType()!=Cell.CELL_TYPE_STRING){
            		continue;
            	}
                String str = c.getStringCellValue().trim();
                if(str.equals(SER_NUM)) {
                    serColIndex = c.getColumnIndex();
                    findSer = true;
                }
                if(str.startsWith("#")){
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
        if(!findSer) {
            initSer();
        }
    }
    /**
     * 初始化序号位置
     */
    private void initSer() {
        for(Row row:sheet) {
            for(Cell c:row) {
            	String str = null;
            	if(c.getCellType()!=Cell.CELL_TYPE_STRING){
            		continue;
            	}
            	str = c.getStringCellValue().trim();
                if(str.equals(SER_NUM)) {
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
        for(Row row:sheet) {
            for(Cell c:row) {
            	String str = null;
            	if(c.getCellType()!=Cell.CELL_TYPE_STRING){
            		continue;
            	}
            	str = c.getStringCellValue().trim();
                if(str.equals(DEFAULT_STYLE)) {
                    defaultStyle = c.getCellStyle();
                }
                if(str.equals(STYLE)) {
                    styles.put(c.getColumnIndex(), c.getCellStyle());
                }
            }
        }
}
    
    public void initStyles(int rowNum){
    	styles = new HashMap<Integer, CellStyle>();
        for(Row row:sheet) {
        	if(row.getRowNum() == rowNum){
        		for(Cell c:row) {
                	String str = null;
                	if(c.getCellType()!=Cell.CELL_TYPE_STRING){
                		continue;
                	}
                	str = c.getStringCellValue().trim();
                    if(str.equals(DEFAULT_STYLE)) {
                        defaultStyle = c.getCellStyle();
                    }
                    if(str.equals(STYLE)) {
                        styles.put(c.getColumnIndex(), c.getCellStyle());
                    }
                }
        	}
        }
    }
    
    /**
     * 从0开始根据模板中获取当前数据行数，默认是2
     * @return 当前数据行数
     */
    public int getCurDataRowNum() {
    	int curDataRowNum = 2;
         for(Row row:sheet) {
            for(Cell c:row) {
            	if(c.getCellType()!=Cell.CELL_TYPE_STRING){
            		continue;
            	}
                String str = c.getStringCellValue().trim();
                if(str.startsWith("@")){
                	curDataRowNum = row.getRowNum();
                return curDataRowNum;
                }
            }
        }
        return curDataRowNum;
    }
    
    public void addCurColIndex(){
    	this.curColIndex++;
    }
}
