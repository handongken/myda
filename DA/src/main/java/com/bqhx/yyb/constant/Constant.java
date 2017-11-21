package com.bqhx.yyb.constant;

import java.math.BigDecimal;

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
	 * 数字1
	 */
	public static final int ONE = 1; 
	/**
	 * 4
	 */
	public static final String FLAG_FOUR = "4";
	/**
	 * 3
	 */
	public static final String FLAG_THREE = "3";
	/**
	 * 2
	 */
	public static final String FLAG_TWO = "2";
	/**
	 * 1
	 */
	public static final String FLAG_ONE = "1";
	/**
	 * 0
	 */
	public static final String FLAG_ZERO = "0";
	/**
	 * A
	 */
	public static final String FLAG_A = "A";
	/**
	 * B
	 */
	public static final String FLAG_B = "B";
	/**
	 * C
	 */
	public static final String FLAG_C = "C";
	/**
	 * D
	 */
	public static final String FLAG_D = "D";
	/**
	 * E
	 */
	public static final String FLAG_E = "E";
	/**
	 * F
	 */
	public static final String FLAG_F = "F";
	/**
	 * success
	 */
	public static final String SUCCESS = "success";
	/**
	 * failed
	 */
	public static final String FAILED = "failed";
	/**
	 * totaltemplate
	 */
	public static final String TOTALTEMPLATE = "info-template.xlsx";
	/**
	 * marketAndBudgettemplate
	 */
	public static final String MBTEMPLATE = "marketAndBudget-template.xlsx";
	/**
	 * interesttemplate
	 */
	public static final String INTERESTTEMPLATE = "interest-template.xlsx";
	/**
	 * movableChecktemplate
	 */
	public static final String MOVABLECHECKTEMPLATE = "movableCheck-template.xlsx";
	/**
	 * SMSInteresttemplate
	 */
	public static final String SMSINTERESTTEMPLATE = "SMSInterest-template.xlsx";
	/**
	 * SMSCapitaltemplate
	 */
	public static final String SMSCAPITALTEMPLATE = "SMSCapital-template.xlsx";
	/**
	 * principaltemplate
	 */
	public static final String PRINCIPALTEMPLATE = "principal-template.xlsx";
	/**
	 * nationalsummarytemplate
	 */
	public static final String NATIONALSUMMARYTEMPLATE = "nationalSummary-template.xlsx";
	/**
	 * humanAndPerformancetemplate
	 */
	public static final String HUMANANDPERFORMANCETEMPLATE = "humanAndPerformance-template.xlsx";
	/**
	 * actualTimetemplate
	 */
	public static final String ACTUALTIMETEMPLATE = "actualTime-template.xlsx";
	/**
	 * performancePDtemplate
	 */
	public static final String PERFORMANCEPDTEMPLATE = "performancePD-template.xlsx";
	/**
	 * codingtemplate
	 */
	public static final String CODINGTEMPLATE = "coding-template.xlsx";
	/**
	 * 业绩日报总表标题
	 */
	public static final String SUMMARYTABLETITLE = "业绩日报总表";
	/**
	 * 业绩日报总表名
	 */
	public static final String SUMMARYNAME = "业绩日报总表_";
	/**
	 * 已赎回表标题
	 */
	public static final String REDEEMEDTABLETITLE = "已赎回名单";
	/**
	 * 已赎回表名
	 */
	public static final String REDEEMEDNAME = "已赎回名单_";
	/**
	 * 提前赎回表标题
	 */
	public static final String REDEEMABLETABLETITLE = "申请提前赎回";
	/**
	 * 提前赎回表名
	 */
	public static final String REDEEMABLENAME = "申请提前赎回_";
	/**
	 * 续投业绩表标题
	 */
	public static final String INVESTMENTTABLETITLE = "续投业绩";
	/**
	 * 续投业绩表名
	 */
	public static final String INVESTMENTNAME = "续投业绩_";
	/**
	 * 付息凭证表标题
	 */
	public static final String INTERESTCERTIFICATETITLE = "受让方收益发放凭证(付息申请)";
	/**
	 * 付息凭证表名
	 */
	public static final String INTERESTCERTIFICATENAME = "付息凭证_";
	/**
	 * 还本凭证表标题
	 */
	public static final String RELEASECERTIFICATETITLE = "受让方本金发放凭证(付本申请)";
	/**
	 * 还本凭证表名
	 */
	public static final String RELEASECERTIFICATENAME = "还本凭证_";
	/**
	 * 还本信息表标题
	 */
	public static final String PRINCIPALTITLE = "还本信息";
	/**
	 * 还本信息表名
	 */
	public static final String PRINCIPALNAME = "还本大表_";
	/**
	 * 移动支票表标题
	 */
	public static final String MOVABLECHECKTITLE = "移动支票";
	/**
	 * 移动支票表名
	 */
	public static final String MOVABLECHECKNAME = "移动支票_";
	/**
	 * 银行短信回息表标题
	 */
	public static final String SMSINTERESTTITLE = "银行短信回息表";
	/**
	 * 银行短信回息表名
	 */
	public static final String SMSINTERESTNAME = "银行短信回息表_";
	/**
	 * 银行短信回本表标题
	 */
	public static final String SMSCAPITALTITLE = "银行短信回本表";
	/**
	 * 银行短信回本表名
	 */
	public static final String SMSCAPITALNAME = "银行短信回本表_";
	/**
	 * 全国汇总表名
	 */
	public static final String NATIONALSUMMARYNAME = "全国汇总表_";
	/**
	 * 全国基本架构表名
	 */
	public static final String HUMANANDPERFORMANCENAME = "全国基本架构_";
	/**
	 * 人力与业绩表标题
	 */
	public static final String HUMANANDPERFORMANCETITLE = "人力与业绩表";
	/**
	 * 实时业绩表名
	 */
	public static final String ACTUALTIMENAME = "惠信实时业绩表_";
	/**
	 * 实时业绩表标题
	 */
	public static final String ACTUALTIMETITLE = "实时业绩表";
	/**
	 * 每日业绩分表名
	 */
	public static final String PERFORMANCEPDNAME = "每日业绩分表_";
	/**
	 * 每日业绩分表标题
	 */
	public static final String PERFORMANCEPDTITLE = "规模业绩表";
	/**
	 * 日报编码汇总_
	 */
	public static final String CODINGTEMPLATENAME = "日报编码汇总_";
	/** 
	 * 付方账号
	 */ 
	public static final String CARDNO = "411906450710918";
	/**
	 * 失效时间：5天
	 */
	public static final int INVALIDTIME = 5;
	/**
	 * 支票权限  
	 */ 
	public static final String CHECKAUTHORITY = "可支付、不可转让";
	/** 
	 * 授权使用人
	 */ 
	public static final String AUTHORIZEDUSER = "博琪融资经办";
	/** 
	 * 收方信息填写类型
	 */ 
	public static final String RECIEVERTYPE = "预先录入(支付时不可修改)";
	/** 
	 * 金额上限50000
	 */ 
	public static final BigDecimal AMOUNTLIMIT = new BigDecimal(50000.00);
	/** 
	 * 汇路类型:招商银行
	 */ 
	public static final String REMITTYPEMERCHANTSBANK = "招商银行";
	/**
	 * 汇路类型:他行实时
	 */
	public static final String REMITTYPEREALTIME = "他行实时";
	/**
	 * 汇路类型:他行普通
	 */
	public static final String REMITTYPECOMMON = "他行普通";
	/**
	 * 收益
	 */
	public static final String PROFIT = "收益";
	/**
	 * 附言：还款，合同号
	 */
	public static final String POSTSCRIPT = "还款，合同号";
	/**
	 * 人力与业绩汇总表标题
	 */
	public static final String HUMANANDRESOURCETITLE = "人力与业绩汇总表";
	/**
	 * 市场和预算权限typeid
	 */
	public static final String MB = "2";
	/**
	 * 客户经理权限typeid
	 */
	public static final String LCMANAGER = "3";
	/**
	 * 团队经理权限typeid
	 */
	public static final String TMANAGER = "4";
	/**
	 * 营业部经理权限typeid
	 */
	public static final String YYBMANAGER = "5";
	/**
	 * 分公司经理权限typeid
	 */
	public static final String FGSMANAGER = "6";
	/**
	 * 大区经理权限typeid
	 */
	public static final String DQMANAGER = "7";
	/**
	 * 事业部经理权限typeid
	 */
	public static final String SYBMANAGER = "8";
	/**
	 * 事业部内勤权限typeid
	 */
	public static final String SYBOFFICE = "10";
	/**
	 * 大区内勤权限typeid
	 */
	public static final String DQOFFICE = "11";
	/**
	 * 分公司内勤权限typeid
	 */
	public static final String FGSOFFICE = "12";
	/**
	 * 营业部内勤权限typeid
	 */
	public static final String YYBOFFICE = "13";
	
}
