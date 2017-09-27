package com.bqhx.yyb.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class InformationVO extends BaseVO implements Serializable{

	private static final long serialVersionUID = 1L;
	// 合同编号
	private String contract;
	// 出借金额
	private Integer money;
	// 产品名称
	private String type;
	// 折标系数
	private BigDecimal zbRatio;
	// 绩效业绩
	private BigDecimal jxAchievement;
	// 理财经理
	private String lcManager;
	// 理财经理id
	private String lcId;
	// 团队经理
	private String tManager;
	// 营业部
	private String yyb;
	// 营业部经理
	private String yybManager;
	// 分公司
	private String fgs;
	// 分公司经理
	private String fgsManager;
	// 大区
	private String dq;
	// 大区经理
	private String dqManager;
	// 事业部
	private String syb;
	// 事业部经理
	private String sybManager;
	// 期数
	private Integer periods;
	// 年化收益
	private BigDecimal rate;
	// 利息总额
	private BigDecimal interestAll;
	//月付利息
    private BigDecimal interestMonth;
    //划扣日期
	private String paymentDate;
	// 初始出借日期
	private String startDate;
	// 到期日
	private String endDate;
	// 账单日
	private String statementDate;
	// 即将到期天数
	private Integer surplusDate;
	// 状态
	private String status;
	// pos机终端号
	private String posNo;
	// 出借人
	private String tenderName;
	// 证件类型
	private String idType;
	// 身份证号
	private String idNo;
	// 非续投/续投
	private String continueFlg;
	// 联系方式
	private String tel;
	// 推广渠道
	private String spreadType;
	// 汇入银行
	private String bank;
	// 银行支行名称
	private String branch;
	// 账号
	private String cardNo;
	// 开户人姓名
	private String cardName;
	// 开卡省份
	private String cardProvince;
	// 开卡城市
	private String cardCity;
	// 银行行号
	private String cardLine;
	// 回款银行
	private String inBank;
	// 银行支行名称
	private String inBranch;
	// 账号
	private String inCardNo;
	// 开户人姓名
	private String inCardName;
	// 开卡省份
	private String inCardProvince;
	// 开卡城市
	private String inCardCity;
	// 出借人地址
	private String borrowAddress;
	// 紧急联系人
	private String contactName;
	// 紧急联系人电话
	private String contactTel;
	// 与出借人关系
	private String contactRelationship;
	// remark
	private String remark;
	// 审批者
	private String managerNo;
	// 审批状态
	private String managerStatus;
	// 删除flg
	private String delFlg;

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract == null ? null : contract.trim();
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
		this.type = type == null ? null : type.trim();
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
		this.lcManager = lcManager == null ? null : lcManager.trim();
	}

	public String getLcId() {
		return lcId;
	}

	public void setLcId(String lcId) {
		this.lcId = lcId == null ? null : lcId.trim();
	}

	public String gettManager() {
		return tManager;
	}

	public void settManager(String tManager) {
		this.tManager = tManager == null ? null : tManager.trim();
	}

	public String getYyb() {
		return yyb;
	}

	public void setYyb(String yyb) {
		this.yyb = yyb == null ? null : yyb.trim();
	}

	public String getYybManager() {
		return yybManager;
	}

	public void setYybManager(String yybManager) {
		this.yybManager = yybManager == null ? null : yybManager.trim();
	}

	public String getFgs() {
		return fgs;
	}

	public void setFgs(String fgs) {
		this.fgs = fgs == null ? null : fgs.trim();
	}

	public String getFgsManager() {
		return fgsManager;
	}

	public void setFgsManager(String fgsManager) {
		this.fgsManager = fgsManager == null ? null : fgsManager.trim();
	}

	public String getDq() {
		return dq;
	}

	public void setDq(String dq) {
		this.dq = dq == null ? null : dq.trim();
	}

	public String getDqManager() {
		return dqManager;
	}

	public void setDqManager(String dqManager) {
		this.dqManager = dqManager == null ? null : dqManager.trim();
	}

	public String getSyb() {
		return syb;
	}

	public void setSyb(String syb) {
		this.syb = syb == null ? null : syb.trim();
	}

	public String getSybManager() {
		return sybManager;
	}

	public void setSybManager(String sybManager) {
		this.sybManager = sybManager == null ? null : sybManager.trim();
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
		this.statementDate = statementDate == null ? null : statementDate.trim();
	}

	public Integer getSurplusDate() {
		return surplusDate;
	}

	public void setSurplusDate(Integer surplusDate) {
		this.surplusDate = surplusDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getPosNo() {
		return posNo;
	}

	public void setPosNo(String posNo) {
		this.posNo = posNo == null ? null : posNo.trim();
	}

	public String getTenderName() {
		return tenderName;
	}

	public void setTenderName(String tenderName) {
		this.tenderName = tenderName == null ? null : tenderName.trim();
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType == null ? null : idType.trim();
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo == null ? null : idNo.trim();
	}

	public String getContinueFlg() {
		return continueFlg;
	}

	public void setContinueFlg(String continueFlg) {
		this.continueFlg = continueFlg == null ? null : continueFlg.trim();
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel == null ? null : tel.trim();
	}

	public String getSpreadType() {
		return spreadType;
	}

	public void setSpreadType(String spreadType) {
		this.spreadType = spreadType == null ? null : spreadType.trim();
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank == null ? null : bank.trim();
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch == null ? null : branch.trim();
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo == null ? null : cardNo.trim();
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName == null ? null : cardName.trim();
	}

	public String getCardProvince() {
		return cardProvince;
	}

	public void setCardProvince(String cardProvince) {
		this.cardProvince = cardProvince == null ? null : cardProvince.trim();
	}

	public String getCardCity() {
		return cardCity;
	}

	public void setCardCity(String cardCity) {
		this.cardCity = cardCity == null ? null : cardCity.trim();
	}

	public String getCardLine() {
		return cardLine;
	}

	public void setCardLine(String cardLine) {
		this.cardLine = cardLine == null ? null : cardLine.trim();
	}

	public String getInBank() {
		return inBank;
	}

	public void setInBank(String inBank) {
		this.inBank = inBank == null ? null : inBank.trim();
	}

	public String getInBranch() {
		return inBranch;
	}

	public void setInBranch(String inBranch) {
		this.inBranch = inBranch == null ? null : inBranch.trim();
	}

	public String getInCardNo() {
		return inCardNo;
	}

	public void setInCardNo(String inCardNo) {
		this.inCardNo = inCardNo == null ? null : inCardNo.trim();
	}

	public String getInCardName() {
		return inCardName;
	}

	public void setInCardName(String inCardName) {
		this.inCardName = inCardName == null ? null : inCardName.trim();
	}

	public String getInCardProvince() {
		return inCardProvince;
	}

	public void setInCardProvince(String inCardProvince) {
		this.inCardProvince = inCardProvince == null ? null : inCardProvince.trim();
	}

	public String getInCardCity() {
		return inCardCity;
	}

	public void setInCardCity(String inCardCity) {
		this.inCardCity = inCardCity == null ? null : inCardCity.trim();
	}

	public String getBorrowAddress() {
		return borrowAddress;
	}

	public void setBorrowAddress(String borrowAddress) {
		this.borrowAddress = borrowAddress == null ? null : borrowAddress.trim();
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName == null ? null : contactName.trim();
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel == null ? null : contactTel.trim();
	}

	public String getContactRelationship() {
		return contactRelationship;
	}

	public void setContactRelationship(String contactRelationship) {
		this.contactRelationship = contactRelationship == null ? null : contactRelationship.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getManagerNo() {
		return managerNo;
	}

	public void setManagerNo(String managerNo) {
		this.managerNo = managerNo == null ? null : managerNo.trim();
	}

	public String getManagerStatus() {
		return managerStatus;
	}

	public void setManagerStatus(String managerStatus) {
		this.managerStatus = managerStatus == null ? null : managerStatus.trim();
	}

	public String getDelFlg() {
		return delFlg;
	}

	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg == null ? null : delFlg.trim();
	}

}