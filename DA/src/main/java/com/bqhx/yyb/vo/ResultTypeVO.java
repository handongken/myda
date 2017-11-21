package com.bqhx.yyb.vo;

import java.math.BigDecimal;

public class ResultTypeVO {
	/**事业部  */ 
	private String syb;
	/**事业部name */ 
	private String sybname;
	/**大区  */ 
	private String dq;
	/**大区  */ 
	private String dqname;
	/**分公司  */ 
	private String fgs;
	/**分公司  */ 
	private String fgsname;
	/**出借金额 */ 
	private Integer money;
	/**绩效业绩  */ 
	private BigDecimal jxAchievement;
	/**理财经理id  */ 
	private String lcId;
	 /**划扣日期  */
	private String paymentDate;
	/**删除flg  */ 
	private String delFlg;
	/**开单人数  */ 
	private Integer lcNum;
	/**规模业绩  */ 
	private Integer moneySum;
	/**绩效业绩  */ 
	private BigDecimal jxSum;
	
	private String insDate;
	private String insUser;
	private String updDate;
	private String updUser;	
	
	public String getSybname() {
		return sybname;
	}
	public void setSybname(String sybname) {
		this.sybname = sybname;
	}
	public String getDqname() {
		return dqname;
	}
	public void setDqname(String dqname) {
		this.dqname = dqname;
	}
	public String getFgsname() {
		return fgsname;
	}
	public void setFgsname(String fgsname) {
		this.fgsname = fgsname;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getInsDate() {
		return insDate;
	}
	public void setInsDate(String insDate) {
		this.insDate = insDate;
	}
	public String getInsUser() {
		return insUser;
	}
	public void setInsUser(String insUser) {
		this.insUser = insUser;
	}
	public String getUpdDate() {
		return updDate;
	}
	public void setUpdDate(String updDate) {
		this.updDate = updDate;
	}
	public String getUpdUser() {
		return updUser;
	}
	public void setUpdUser(String updUser) {
		this.updUser = updUser;
	}
	public String getSyb() {
		return syb;
	}
	public void setSyb(String syb) {
		this.syb = syb;
	}
	
	public String getDq() {
		return dq;
	}
	public void setDq(String dq) {
		this.dq = dq;
	}
	public String getFgs() {
		return fgs;
	}
	public void setFgs(String fgs) {
		this.fgs = fgs;
	}
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	public BigDecimal getJxAchievement() {
		return jxAchievement;
	}
	public void setJxAchievement(BigDecimal jxAchievement) {
		this.jxAchievement = jxAchievement;
	}
	public String getLcId() {
		return lcId;
	}
	public void setLcId(String lcId) {
		this.lcId = lcId;
	}
	public String getDelFlg() {
		return delFlg;
	}
	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg;
	}
	public Integer getLcNum() {
		return lcNum;
	}
	public void setLcNum(Integer lcNum) {
		this.lcNum = lcNum;
	}
	public Integer getMoneySum() {
		return moneySum;
	}
	public void setMoneySum(Integer moneySum) {
		this.moneySum = moneySum;
	}
	public BigDecimal getJxSum() {
		return jxSum;
	}
	public void setJxSum(BigDecimal jxSum) {
		this.jxSum = jxSum;
	}
	
	
}
