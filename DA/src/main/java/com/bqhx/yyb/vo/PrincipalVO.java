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
	/**营业部id  */ 
	private String yyb;
	/**营业部名称  */ 
	private String yybname;
	/**营业部经理  */ 
	private String yybManager;
	/**分公司id  */ 
	private String fgs;
	/**分公司名称  */ 
	private String fgsname;
	/**分公司经理  */ 
	private String fgsManager;
	/**大区id  */ 
	private String dq;
	/**大区名称  */ 
	private String dqname;
	/**大区经理  */ 
	private String dqManager;
	/**事业部id  */ 
	private String syb;
	/**事业部名称  */ 
	private String sybname;
	/**事业部经理  */ 
	private String sybManager;
	/**初始出借日期  */ 
	private String startDate;
	/**到期日  */ 
	private String endDate;
	/**出借人  */ 
	private String tenderName;
	/**删除flg  */ 
	private String delFlg;
	
	public String getFgsManager() {
		return fgsManager;
	}
	public void setFgsManager(String fgsManager) {
		this.fgsManager = fgsManager;
	}
	public String getDqManager() {
		return dqManager;
	}
	public void setDqManager(String dqManager) {
		this.dqManager = dqManager;
	}
	public String getSybManager() {
		return sybManager;
	}
	public void setSybManager(String sybManager) {
		this.sybManager = sybManager;
	}
	public String getDq() {
		return dq;
	}
	public void setDq(String dq) {
		this.dq = dq;
	}
	public String getDqname() {
		return dqname;
	}
	public void setDqname(String dqname) {
		this.dqname = dqname;
	}
	public String getSyb() {
		return syb;
	}
	public void setSyb(String syb) {
		this.syb = syb;
	}
	public String getSybname() {
		return sybname;
	}
	public void setSybname(String sybname) {
		this.sybname = sybname;
	}
	public String getYyb() {
		return yyb;
	}
	public void setYyb(String yyb) {
		this.yyb = yyb;
	}
	public String getFgs() {
		return fgs;
	}
	public void setFgs(String fgs) {
		this.fgs = fgs;
	}
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
	public String getYybManager() {
		return yybManager;
	}
	public void setYybManager(String yybManager) {
		this.yybManager = yybManager;
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
	public String getYybname() {
		return yybname;
	}
	public void setYybname(String yybname) {
		this.yybname = yybname;
	}
	public String getFgsname() {
		return fgsname;
	}
	public void setFgsname(String fgsname) {
		this.fgsname = fgsname;
	}
	
}
