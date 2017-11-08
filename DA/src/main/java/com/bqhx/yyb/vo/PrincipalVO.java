package com.bqhx.yyb.vo;

import java.io.Serializable;

/**
 * 还本信息
 */
public class PrincipalVO implements Serializable{ 
	private static final long serialVersionUID = 1L;
	/**合同号  */ 
	private String contract;
	/**出借金额  */ 
	private Integer money;
	/**产品名称  */ 
	private String typeName;
	/**客户经理  */ 
	private String lcManager;
	/**团队经理  */ 
	private String tmanager;
	/**营业部名称  */ 
	private String yyb;
	/**营业部经理  */ 
	private String yybManager;
	/**分公司名称  */ 
	private String fgs;
	/**初始出借日期  */ 
	private String startDate;
	/**到期日  */ 
	private String endDate;
	/**出借人  */ 
	private String tenderName;
	/**删除flg  */ 
	private String delFlg;
	
	public String getDelFlg() {
		return delFlg;
	}
	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg;
	}
	public String getLcManager() {
		return lcManager;
	}
	public void setLcManager(String lcManager) {
		this.lcManager = lcManager;
	}
	public String getTmanager() {
		return tmanager;
	}
	public void setTmanager(String tmanager) {
		this.tmanager = tmanager;
	}
	public String getYyb() {
		return yyb;
	}
	public void setYyb(String yyb) {
		this.yyb = yyb;
	}
	public String getYybManager() {
		return yybManager;
	}
	public void setYybManager(String yybManager) {
		this.yybManager = yybManager;
	}
	public String getFgs() {
		return fgs;
	}
	public void setFgs(String fgs) {
		this.fgs = fgs;
	}
	public String getTenderName() {
		return tenderName;
	}
	public void setTenderName(String tenderName) {
		this.tenderName = tenderName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getContract() {
		return contract;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
}
