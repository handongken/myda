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
	 * totaltemplate
	 */
	public static final String TOTALTEMPLATE = "info-template.xlsx";
	/**
	 * interesttemplate
	 */
	public static final String INTERESTTEMPLATE = "interest-template.xlsx";
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
}
