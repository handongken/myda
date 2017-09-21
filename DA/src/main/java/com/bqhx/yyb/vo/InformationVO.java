package com.bqhx.yyb.vo;

import java.math.BigDecimal;
import java.util.Date;

public class InformationVO {
    private String contract;

    private Integer money;

    private String type;

    private BigDecimal zbRatio;

    private BigDecimal jxAchievement;

    private String lcManager;

    private String lcId;

    private String tManager;

    private String yyb;

    private String yybManager;

    private String fgs;

    private String fgsManager;

    private String dq;

    private String dqManager;

    private String syb;

    private String sybManager;

    private Integer periods;

    private BigDecimal rate;

    private BigDecimal interestAll;

    private BigDecimal interestMonth;

    private Date paymentDate;

    private Date startDate;

    private Date endDate;

    private String statementDate;

    private Integer surplusDate;

    private String status;

    private String posNo;

    private String tenderName;

    private String idType;

    private String idNo;

    private String continueFlg;

    private String tel;

    private String spreadType;

    private String bank;

    private String branch;

    private String cardNo;

    private String cardName;

    private String cardProvince;

    private String cardCity;

    private String cardLine;

    private String inBank;

    private String inBranch;

    private String inCardNo;

    private String inCardName;

    private String inCardProvince;

    private String inCardCity;

    private String borrowAddress;

    private String contactName;

    private String contactTel;

    private String contactRelationship;

    private String remark;

    private String managerNo;

    private String managerStatus;   

    private String delFlg;

    private String insUser;

    private Date insDate;

    private String updUser;

    private Date updDate;

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

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
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

    public String getInsUser() {
        return insUser;
    }

    public void setInsUser(String insUser) {
        this.insUser = insUser == null ? null : insUser.trim();
    }

    public Date getInsDate() {
        return insDate;
    }

    public void setInsDate(Date insDate) {
        this.insDate = insDate;
    }

    public String getUpdUser() {
        return updUser;
    }

    public void setUpdUser(String updUser) {
        this.updUser = updUser == null ? null : updUser.trim();
    }

    public Date getUpdDate() {
        return updDate;
    }

    public void setUpdDate(Date updDate) {
        this.updDate = updDate;
    }
}