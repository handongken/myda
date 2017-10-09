package com.bqhx.yyb.vo;

import java.text.SimpleDateFormat;

/**
 * 常量类
 *
 */
public final class ConstantVO {
	/**
	 * dateformat"yyyy-MM-dd HH:mm:ss"
	 */ 
	public static final SimpleDateFormat SDF= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/** 
	 * 1
	 */
	public static final String FLAG_ONE = "1";
	/**
	 * 0
	 */
	public static final String FLAG_ZERO = "0";
	/**
	 * success
	 */
	public static final String SUCCESS = "success";
	/**
	 * failed
	 */
	public static final String FAILED = "failed";
	/**
	 * exceltemplate
	 */
	public static final String EXCELTEMPLATE = "information-template.xls";
	/**
	 * summary table's title
	 */
	public static final String SUMMARYTABLETITLE = "业绩日报总表";
}
