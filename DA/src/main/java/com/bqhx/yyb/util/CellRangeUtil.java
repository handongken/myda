package com.bqhx.yyb.util;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

public class CellRangeUtil {
	/**
	 * 大区合并
	 */
	public void dqCellRange(ExcelTemplate et,Sheet sheetModel,Integer firstRow, int lastRow,int col){
		if(firstRow < lastRow){
			CellRangeAddress crangea_dq = new CellRangeAddress(firstRow, lastRow, col, col);
			sheetModel.addMergedRegion(crangea_dq);
			et.setRegionStyle(sheetModel, crangea_dq);
		}
	}
	
	/**
	 * 大区小计合并
	 */
	public void dqSumCellRange(ExcelTemplate et,Sheet sheetModel,Integer firstRow, int lastRow, int firstCol, int lastCol){
		CellRangeAddress crangea_dqsum = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);
		sheetModel.addMergedRegion(crangea_dqsum);
		et.setSumRegionStyle(sheetModel, crangea_dqsum);
	}
	
	/**
	 * 事业部合并
	 */
	public void sybCellRange(ExcelTemplate et,Sheet sheetModel,Integer firstRow, int lastRow, int col){
		CellRangeAddress crangea_syb = new CellRangeAddress(firstRow, lastRow, 1, 1);
		sheetModel.addMergedRegion(crangea_syb);
		et.setRegionStyle(sheetModel, crangea_syb);
	}
	
	/**
	 * 事业部合计合并
	 */
	public void sybSumCellRange(ExcelTemplate et,Sheet sheetModel,Integer firstRow, int lastRow, int firstCol, int lastCol){
		CellRangeAddress crangea = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);
		sheetModel.addMergedRegion(crangea);
		et.setSumRegionStyle(sheetModel, crangea);
	}
	
	/**
	 * 序号合并
	 */
	public void serCellRange(ExcelTemplate et,Sheet sheetModel,Integer firstRow, int lastRow, int col){
		CellRangeAddress crangea_ser = new CellRangeAddress(firstRow, lastRow, col, col);
		sheetModel.addMergedRegion(crangea_ser);
		et.setRegionStyle(sheetModel, crangea_ser);
	}
	
	/**
	 * 总计合并
	 */
	public void sumCellRange(ExcelTemplate et,Sheet sheetModel,Integer firstRow, int lastRow, int firstCol, int lastCol){
		CellRangeAddress crangea_sum = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);
		sheetModel.addMergedRegion(crangea_sum);
		et.setSumRegionStyle(sheetModel, crangea_sum);
	}
	
	/**
	 * 首行创建cell并合并
	 */
	public void firstRowCreateCellRange(ExcelTemplate et,Sheet sheetModel,String str, int index, int firstRow, int lastRow, int firstCol, int lastCol){
		//大区小计合并
		if(str.contains("大区小计")){
			et.createNewRow(et.getCurRowIndex(),index);
			et.createCellByCol(index,str);
			dqSumCellRange(et, sheetModel, firstRow, lastRow, firstCol, lastCol);
			et.createCellByCol(index+2,"SUM(E4:E@)");
			et.createCellByCol(index+3,"SUM(F4:F@)");
			et.createCellByCol(index+4,"SUM(G4:G@)");
		}//事业部合计合并
		else if(str.contains("合计")){
			String formulastr = "\"<>大区小计\"";
			String eFomula = "SUMIF(C4:C@," + formulastr + ",E4:E@)";
			String fFomula = "SUMIF(C4:C@," + formulastr + ",F4:F@)";
			String gFomula = "SUMIF(C4:C@," + formulastr + ",G4:G@)";
			et.createNewRow(et.getCurRowIndex(),0);
			et.createCellByCol(0,str);
			sybSumCellRange(et, sheetModel, firstRow, lastRow, firstCol, lastCol);
			et.createCellByCol(index+2,eFomula);
			et.createCellByCol(index+3,fFomula);
			et.createCellByCol(index+4,gFomula);
		}
	}
	
	/**
	 * 中间行创建cell并合并
	 */
	public void middleRowCreateCellRange(ExcelTemplate et,Sheet sheetModel,String str, int index, Integer rowNum, int firstRow, int lastRow, int firstCol, int lastCol){
		//大区小计合并
		if(str.contains("大区小计")){
			et.createNewRow(et.getCurRowIndex(),index);
			et.createCellByCol(index,str);
			dqSumCellRange(et, sheetModel, firstRow, lastRow, firstCol, lastCol);
			String efomula = "SUM(E" + rowNum + ":E@)";
			String ffomula = "SUM(F" + rowNum + ":F@)";
			String gfomula = "SUM(G" + rowNum + ":G@)";
			et.createCellByCol(index+2,efomula);
			et.createCellByCol(index+3,ffomula);
			et.createCellByCol(index+4,gfomula);		
		}//事业部合计合并
		else if(str.contains("合计")){
			String formulastr = "\"<>大区小计\"";
			String formula = "SUMIF(C" + rowNum + ":C@,";
			String eFormula = formula + formulastr + ",E" + rowNum + ":E@)";
			String fFormula = formula + formulastr + ",F" + rowNum + ":F@)";
			String gFormula = formula + formulastr + ",G" + rowNum + ":G@)";
			et.createNewRow(et.getCurRowIndex(),0);
			et.createCellByCol(0,str);
			CellRangeAddress crangea = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);
			sheetModel.addMergedRegion(crangea);
			et.setSumRegionStyle(sheetModel, crangea);
			et.createCellByCol(index+2,eFormula);
			et.createCellByCol(index+3,fFormula);
			et.createCellByCol(index+4,gFormula);
		}
	}
	
	/**
	 * 总计创建cell并合并
	 */
	public void totalCreateCellRange(ExcelTemplate et,Sheet sheetModel,String str, int index, int firstRow, int lastRow, int firstCol, int lastCol){
		if(str.contains("总计")){
			et.createNewRow(et.getCurRowIndex(),0);
			et.createCellByCol(0,str);
			CellRangeAddress crangea_sum = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);
			sheetModel.addMergedRegion(crangea_sum);
			et.setSumRegionStyle(sheetModel, crangea_sum);
			String dSum = "COUNTIF(D4:D@," + "\"\u002A\"" + ")-COUNTIF(D4:D@," + "\"\"" + ")";//COUNTIF(D4:D24,"*")-COUNTIF(D4:D24,"")文本型(包括假空)-(真空+假空)
			String eSum = "SUMIF(A4:A@," + "\"\u002A合计\"" + ",E4:E@)";//SUMIF(A4:A@,"*合计",E4:E@)
			String fSum = "SUMIF(A4:A@," + "\"\u002A合计\"" + ",F4:F@)";//SUMIF(A4:A@,"*合计",F4:F@)
			String gSum = "SUMIF(A4:A@," + "\"\u002A合计\"" + ",G4:G@)";//SUMIF(A4:A@,"*合计",G4:G@)
			et.createCellByCol(index+1, dSum);
			et.createCellByCol(index+2,eSum);
			et.createCellByCol(index+3,fSum);
			et.createCellByCol(index+4,gSum);
		}
	}
}
