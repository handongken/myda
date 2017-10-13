package com.bqhx.yyb.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 常量类
 *
 */
public final class Constant {
	/**
	 * pattern:"yyyy-MM-dd HH:mm:ss"
	 */
	public static final String PATTERN_HMS = "yyyy-MM-dd HH:mm:ss";
	/**
	 * pattern:"yyyy-MM-dd"
	 */
	public static final String PATTERN = "yyyy-MM-dd";
	/**
	 * 3
	 */
	public static final String FLAG_THREE = "3";
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
	/**
	 * summary table's name
	 */
	public static final String SUMMARYTABLE = "D:/业绩日报总表_";
	/**
	 * redeemedtable's tilte
	 */
	public static final String REDEEMEDTABLETITLE = "已赎回名单";
	/**
	 * redeemedtable's name
	 */
	public static final String REDEEMEDTABLE = "D:/已赎回名单_";
	/**
	 * redeemabletable's tilte
	 */
	public static final String REDEEMABLETABLETITLE = "申请提前赎回";
	/**
	 * redeemabletable's name
	 */
	public static final String REDEEMABLETABLE = "D:/申请提前赎回_";
	/**
	 * investmenttable's tilte
	 */
	public static final String INVESTMENTTABLETITLE = "续投业绩";
	/**
	 * investmenttable's name
	 */
	public static final String INVESTMENTTABLE = "D:/续投业绩_";
	/**
	 * getTypeValue
	 */
	/*public static final String getTypeValue(String key){
		Map<String, String> typeMap = new HashMap<String, String>();
		String value = null;
		typeMap.put("0", "聚惠丰");
		typeMap.put("1", "聚惠盈");
		typeMap.put("2", "聚惠享");
		typeMap.put("3", "聚惠丰(50)");
		typeMap.put("4", "聚惠盈(50)");
		typeMap.put("5", "聚惠享(50)");
		typeMap.put("6", "聚惠丰(100)");
		typeMap.put("7", "聚惠盈(100)");
		typeMap.put("8", "聚惠享(100)");
		typeMap.put("9", "聚惠福");
		typeMap.put("10", "聚惠鑫");
		typeMap.put("11", "聚惠福(50)");
		typeMap.put("12", "聚惠福(100)");
		typeMap.put("13", "聚惠鑫(50)");
		typeMap.put("14", "聚惠鑫(100)");
		typeMap.put("15", "惠添利");
		typeMap.put("16", "惠添金");
		typeMap.put("17", "惠添利(50)");
		typeMap.put("18", "惠添利(100)");
		typeMap.put("19", "惠添金(50)");
		typeMap.put("20", "惠添金(100)");
		if(typeMap.containsKey(key)){
			value = typeMap.get(key);
		}
		return value;
	}
	*//**
	 * getStatusValue
	 *//*
	public static final String getStatusValue(String key){
		Map<String, String> statusMap = new HashMap<String, String>();
		String value = null;
		statusMap.put("0", "正常");
		statusMap.put("1", "已赎回");
		statusMap.put("2", "即将到期");
		statusMap.put("3", "申请提前赎回");
		if(statusMap.containsKey(key)){
			value = statusMap.get(key);
		}
		return value;
	}
	*//**
	 * getContinueFlgValue
	 *//*
	public static final String getContinueFlgValue(String key){
		Map<String, String> continueFlgMap = new HashMap<String, String>();
		String value = null;
		continueFlgMap.put("0", "续投");
		continueFlgMap.put("1", "非续投");
		if(continueFlgMap.containsKey(key)){
			value = continueFlgMap.get(key);
		}
		return value;
	}*/
}
