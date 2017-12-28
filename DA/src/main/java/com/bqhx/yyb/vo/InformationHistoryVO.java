package com.bqhx.yyb.vo;

import java.math.BigDecimal;
/**
 * 数据总表历史VO
 * @author Administrator
 *
 */
public class InformationHistoryVO {
	private static final long serialVersionUID = 1L;
	/**主键ID  */ 
	private String infoId;
	/**change_flg  */ 
	private String changeInfo;
	/**合同编号  */ 
	private String contract;
	/**出借金额(投资金额) */ 
	private Integer money;
	/**产品名称  */ 
	private String type;  
	/**折标系数  */ 
	private BigDecimal zbRatio;
	/**绩效业绩  */ 
	private BigDecimal jxAchievement;
	/**客户经理  */ 
	private String lcManager;
	/**理财经理id  */ 
	private String lcId;
	/**团队经理  */ 
	private String tmanager;
	/**团队name  */ 
	private String tname;
	/**营业部id  */ 
	private String yyb;
	/**营业部经理  */ 
	private String yybManager;
	/**营业部name  */ 
	private String yybname;
	/**分公司id  */ 
	private String fgs;
	/**分公司经理  */ 
	private String fgsManager;
	/**分公司name  */ 
	private String fgsname;
	/**大区id */ 
	private String dq;
	/**大区经理  */ 
	private String dqManager;
	/**大区name  */ 
	private String dqname;
	/**事业部id  */ 
	private String syb;
	/**事业部经理  */ 
	private String sybManager;
	/**事业部name  */ 
	private String sybname;
	/**期数  */ 
	private Integer periods;
	/**年化收益（预期年化收益率）  */ 
	private BigDecimal rate;
	/**利息总额（投资收益总额）  */ 
	private BigDecimal interestAll;
	/**月付利息（月付投资收益）  */
    private BigDecimal interestMonth;
    /**划扣日期  */
	private String paymentDate;
	/**初始出借日期  */ 
	private String startDate;
	/**到期日  */ 
	private String endDate;
	/**账单日  */ 
	private String statementDate;
	/**即将到期天数  */ 
	private String surplusDate;
	/**状态  */ 
	private String status;
	/**pos机终端号  */ 
	private String posNo;
	/**出借人  */ 
	private String tenderName;
	/**证件类型  */ 
	private String idType;
	/**身份证号  */ 
	private String idNo;
	/**非续投/续投  */ 
	private String continueFlg;
	/**联系方式  */ 
	private String tel;
	/**推广渠道  */ 
	private String spreadType;
	/**汇入银行（精确到支行）  */ 
	private String bank;
	/**银行支行名称  */ 
	private String branch;
	/**账号  */ 
	private String cardNo;
	/**开户人姓名  */ 
	private String cardName;
	/**开卡省份  */ 
	private String cardProvince;
	/**开卡城市  */ 
	private String cardCity;
	/**银行行号  */ 
	private String cardLine;
	/**回款银行  */ 
	private String inBank;
	/**银行支行名称  */ 
	private String inBranch;
	/**回款账号  */ 
	private String inCardNo;
	/**回款开户人姓名  */ 
	private String inCardName;
	/**回款开卡省份  */ 
	private String inCardProvince;
	/**回款开卡城市  */ 
	private String inCardCity;
	/**出借人地址  */ 
	private String borrowAddress;
	/**紧急联系人  */ 
	private String contactName;
	/**紧急联系人电话  */ 
	private String contactTel;
	/**与出借人关系  */ 
	private String contactRelationship;
	/**备注  */ 
	private String remark;
	/**审批者  */ 
	private String managerNo;
	/**审批状态  */ 
	private String managerStatus;
	/**插入用户ID  */ 
	private String insUser;
	/**插入时间  */ 
	private String insDate;
	/**更新用户ID  */ 
	private String updUser;
	/**更新时间  */ 
	private String updDate;
	public String getInfoId() {
		return infoId;
	}
	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}
	public String getChangeInfo() {
		return changeInfo;
	}
	public void setChangeInfo(String changeInfo) {
		this.changeInfo = changeInfo;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public BigDecimal getZbRatio() {
		return zbRatio;
	}
	public void setZbRatio(BigDecimal zbRatio) {
		this.zbRatio = zbRatio;
	}
	public BigDecimal getJxAchievement() {
		return jxAchievement;
	}
	public void setJxAchievement(BigDecimal jxAchievement) {
		this.jxAchievement = jxAchievement;
	}
	public String getLcManager() {
		return lcManager;
	}
	public void setLcManager(String lcManager) {
		this.lcManager = lcManager;
	}
	public String getLcId() {
		return lcId;
	}
	public void setLcId(String lcId) {
		this.lcId = lcId;
	}
	public String getTmanager() {
		return tmanager;
	}
	public void setTmanager(String tmanager) {
		this.tmanager = tmanager;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
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
	public String getYybname() {
		return yybname;
	}
	public void setYybname(String yybname) {
		this.yybname = yybname;
	}
	public String getFgs() {
		return fgs;
	}
	public void setFgs(String fgs) {
		this.fgs = fgs;
	}
	public String getFgsManager() {
		return fgsManager;
	}
	public void setFgsManager(String fgsManager) {
		this.fgsManager = fgsManager;
	}
	public String getFgsname() {
		return fgsname;
	}
	public void setFgsname(String fgsname) {
		this.fgsname = fgsname;
	}
	public String getDq() {
		return dq;
	}
	public void setDq(String dq) {
		this.dq = dq;
	}
	public String getDqManager() {
		return dqManager;
	}
	public void setDqManager(String dqManager) {
		this.dqManager = dqManager;
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
	public String getSybManager() {
		return sybManager;
	}
	public void setSybManager(String sybManager) {
		this.sybManager = sybManager;
	}
	public String getSybname() {
		return sybname;
	}
	public void setSybname(String sybname) {
		this.sybname = sybname;
	}
	public Integer getPeriods() {
		return periods;
	}
	public void setPeriods(Integer periods) {
		this.periods = periods;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public BigDecimal getInterestAll() {
		return interestAll;
	}
	public void setInterestAll(BigDecimal interestAll) {
		this.interestAll = interestAll;
	}
	public BigDecimal getInterestMonth() {
		return interestMonth;
	}
	public void setInterestMonth(BigDecimal interestMonth) {
		this.interestMonth = interestMonth;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStatementDate() {
		return statementDate;
	}
	public void setStatementDate(String statementDate) {
		this.statementDate = statementDate;
	}
	public String getSurplusDate() {
		return surplusDate;
	}
	public void setSurplusDate(String surplusDate) {
		this.surplusDate = surplusDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPosNo() {
		return posNo;
	}
	public void setPosNo(String posNo) {
		this.posNo = posNo;
	}
	public String getTenderName() {
		return tenderName;
	}
	public void setTenderName(String tenderName) {
		this.tenderName = tenderName;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getContinueFlg() {
		return continueFlg;
	}
	public void setContinueFlg(String continueFlg) {
		this.continueFlg = continueFlg;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getSpreadType() {
		return spreadType;
	}
	public void setSpreadType(String spreadType) {
		this.spreadType = spreadType;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getCardProvince() {
		return cardProvince;
	}
	public void setCardProvince(String cardProvince) {
		this.cardProvince = cardProvince;
	}
	public String getCardCity() {
		return cardCity;
	}
	public void setCardCity(String cardCity) {
		this.cardCity = cardCity;
	}
	public String getCardLine() {
		return cardLine;
	}
	public void setCardLine(String cardLine) {
		this.cardLine = cardLine;
	}
	public String getInBank() {
		return inBank;
	}
	public void setInBank(String inBank) {
		this.inBank = inBank;
	}
	public String getInBranch() {
		return inBranch;
	}
	public void setInBranch(String inBranch) {
		this.inBranch = inBranch;
	}
	public String getInCardNo() {
		return inCardNo;
	}
	public void setInCardNo(String inCardNo) {
		this.inCardNo = inCardNo;
	}
	public String getInCardName() {
		return inCardName;
	}
	public void setInCardName(String inCardName) {
		this.inCardName = inCardName;
	}
	public String getInCardProvince() {
		return inCardProvince;
	}
	public void setInCardProvince(String inCardProvince) {
		this.inCardProvince = inCardProvince;
	}
	public String getInCardCity() {
		return inCardCity;
	}
	public void setInCardCity(String inCardCity) {
		this.inCardCity = inCardCity;
	}
	public String getBorrowAddress() {
		return borrowAddress;
	}
	public void setBorrowAddress(String borrowAddress) {
		this.borrowAddress = borrowAddress;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactTel() {
		return contactTel;
	}
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
	public String getContactRelationship() {
		return contactRelationship;
	}
	public void setContactRelationship(String contactRelationship) {
		this.contactRelationship = contactRelationship;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getManagerNo() {
		return managerNo;
	}
	public void setManagerNo(String managerNo) {
		this.managerNo = managerNo;
	}
	public String getManagerStatus() {
		return managerStatus;
	}
	public void setManagerStatus(String managerStatus) {
		this.managerStatus = managerStatus;
	}
	public String getInsUser() {
		return insUser;
	}
	public void setInsUser(String insUser) {
		this.insUser = insUser;
	}
	public String getInsDate() {
		return insDate;
	}
	public void setInsDate(String insDate) {
		this.insDate = insDate;
	}
	public String getUpdUser() {
		return updUser;
	}
	public void setUpdUser(String updUser) {
		this.updUser = updUser;
	}
	public String getUpdDate() {
		return updDate;
	}
	public void setUpdDate(String updDate) {
		this.updDate = updDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
