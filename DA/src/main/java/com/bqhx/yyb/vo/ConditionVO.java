package com.bqhx.yyb.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.bqhx.yyb.util.ExcelResources;


public class ConditionVO extends BaseVO implements Serializable{

	private static final long serialVersionUID = 1L;
	/**合同编号  */ 
	private String contract;
	/**出借金额 */ 
	private Integer money;
	/**产品名称  */ 
	private String type;  
	/**折标系数  */ 
	private BigDecimal zbRatio;
	/**绩效业绩  */ 
	private BigDecimal jxAchievement;
	/**理财经理  */ 
	private String lcManager;
	/**理财经理id  */ 
	private String lcId;
	/**团队经理  */ 
	private String tmanager;
	/**营业部  */ 
	private String yyb;
	/**营业部经理  */ 
	private String yybManager;
	/**分公司  */ 
	private String fgs;
	/**分公司经理  */ 
	private String fgsManager;
	/**大区  */ 
	private String dq;
	/**大区经理  */ 
	private String dqManager;
	/**事业部  */ 
	private String syb;
	/**事业部经理  */ 
	private String sybManager;
	/**期数  */ 
	private Integer periods;
	/**年化收益  */ 
	private BigDecimal rate;
	/**利息总额  */ 
	private BigDecimal interestAll;
	/**月付利息  */
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
	private Integer surplusDate;
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
	/**汇入银行  */ 
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
	/**账号  */ 
	private String inCardNo;
	/**开户人姓名  */ 
	private String inCardName;
	/**开卡省份  */ 
	private String inCardProvince;
	/**开卡城市  */ 
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
	/**删除flg  */ 
	private String delFlg;
	/** startTime */
	private String startTime;
	/** endTime */
	private String endTime;
	/** multyCon */
	private String multyCon;
	/** multyFlg */
	private String multyFlg;
	/** 发放期间 */
	private StringBuffer issueDate;
	/** 下载路径 */
	private String downloadPath;
	/**开始发放日期  */ 
	private String beginDate;
	/**结束发放日期  */ 
	private String terminateDate;
	/**金额上限  */
	private BigDecimal amountLimit;
	/**生效日期（初始出借日期String）  */ 
	private String effectiveDates;
	/**失效日期  */ 
	private String invalidDates;
	/**支票权限  */ 
	private String checkAuthority;
	/**授权使用人  */ 
	private String authorizedUser;
	/**收方信息填写类型  */ 
	private String receiverType;
	/**汇路类型  */ 
	private String remitType;
	/**收方行地址 */ 
	private String inCardAddress;
	/**附言  */ 
	private String postscript;
	/**收款人手机号码  */ 
	private String inTel;
	/**付息or还本,1or0  */ 
	private String payFlg;
	
	public BigDecimal getAmountLimit() {
		return amountLimit;
	}

	public void setAmountLimit(BigDecimal amountLimit) {
		this.amountLimit = amountLimit;
	}

	public String getEffectiveDates() {
		return effectiveDates;
	}

	public void setEffectiveDates(String effectiveDates) {
		this.effectiveDates = effectiveDates;
	}

	public String getInvalidDates() {
		return invalidDates;
	}

	public void setInvalidDates(String invalidDates) {
		this.invalidDates = invalidDates;
	}

	public String getCheckAuthority() {
		return checkAuthority;
	}

	public void setCheckAuthority(String checkAuthority) {
		this.checkAuthority = checkAuthority;
	}

	public String getAuthorizedUser() {
		return authorizedUser;
	}

	public void setAuthorizedUser(String authorizedUser) {
		this.authorizedUser = authorizedUser;
	}

	public String getReceiverType() {
		return receiverType;
	}

	public void setReceiverType(String receiverType) {
		this.receiverType = receiverType;
	}

	public String getRemitType() {
		return remitType;
	}

	public void setRemitType(String remitType) {
		this.remitType = remitType;
	}

	public String getInCardAddress() {
		return inCardAddress;
	}

	public void setInCardAddress(String inCardAddress) {
		this.inCardAddress = inCardAddress;
	}

	public String getPostscript() {
		return postscript;
	}

	public void setPostscript(String postscript) {
		this.postscript = postscript;
	}

	public String getInTel() {
		return inTel;
	}

	public void setInTel(String inTel) {
		this.inTel = inTel;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getTerminateDate() {
		return terminateDate;
	}

	public void setTerminateDate(String terminateDate) {
		this.terminateDate = terminateDate;
	}

	public String getPayFlg() {
		return payFlg;
	}

	public void setPayFlg(String payFlg) {
		this.payFlg = payFlg;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDownloadPath() {
		return downloadPath;
	}

	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
	}

	@ExcelResources(title="合同编号",order=1)
	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract == null ? null : contract.trim();
	}

	@ExcelResources(title="出借金额",order=2)
	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	@ExcelResources(title="产品名称",order=3)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	@ExcelResources(title="折标系数",order=4)
	public BigDecimal getZbRatio() {
		return zbRatio;
	}

	public void setZbRatio(BigDecimal zbRatio) {
		this.zbRatio = zbRatio;
	}

	@ExcelResources(title="绩效业绩",order=5)
	public BigDecimal getJxAchievement() {
		return jxAchievement;
	}

	public void setJxAchievement(BigDecimal jxAchievement) {
		this.jxAchievement = jxAchievement;
	}

	@ExcelResources(title="理财经理",order=6)
	public String getLcManager() {
		return lcManager;
	}

	public void setLcManager(String lcManager) {
		this.lcManager = lcManager == null ? null : lcManager.trim();
	}

	@ExcelResources(title="员工编号",order=7)
	public String getLcId() {
		return lcId;
	}

	public void setLcId(String lcId) {
		this.lcId = lcId == null ? null : lcId.trim();
	}

	@ExcelResources(title="团队经理",order=8)
	public String getTmanager() {
		return tmanager;
	}

	public void setTmanager(String tmanager) {
		this.tmanager = tmanager;
	}

	@ExcelResources(title="营业部名称",order=9)
	public String getYyb() {
		return yyb;
	}

	public void setYyb(String yyb) {
		this.yyb = yyb == null ? null : yyb.trim();
	}

	@ExcelResources(title="营业部经理",order=10)
	public String getYybManager() {
		return yybManager;
	}

	public void setYybManager(String yybManager) {
		this.yybManager = yybManager == null ? null : yybManager.trim();
	}

	@ExcelResources(title="分公司名称",order=11)
	public String getFgs() {
		return fgs;
	}

	public void setFgs(String fgs) {
		this.fgs = fgs == null ? null : fgs.trim();
	}

	@ExcelResources(title="分公司经理",order=12)
	public String getFgsManager() {
		return fgsManager;
	}

	public void setFgsManager(String fgsManager) {
		this.fgsManager = fgsManager == null ? null : fgsManager.trim();
	}
	
	@ExcelResources(title="大区名称",order=13)
	public String getDq() {
		return dq;
	}

	public void setDq(String dq) {
		this.dq = dq == null ? null : dq.trim();
	}

	@ExcelResources(title="大区经理",order=14)
	public String getDqManager() {
		return dqManager;
	}

	public void setDqManager(String dqManager) {
		this.dqManager = dqManager == null ? null : dqManager.trim();
	}

	@ExcelResources(title="事业部名称",order=15)
	public String getSyb() {
		return syb;
	}

	public void setSyb(String syb) {
		this.syb = syb == null ? null : syb.trim();
	}

	@ExcelResources(title="事业部经理",order=16)
	public String getSybManager() {
		return sybManager;
	}

	public void setSybManager(String sybManager) {
		this.sybManager = sybManager == null ? null : sybManager.trim();
	}

	@ExcelResources(title="期数",order=17)
	public Integer getPeriods() {
		return periods;
	}

	public void setPeriods(Integer periods) {
		this.periods = periods;
	}

	@ExcelResources(title="年化收益率",order=18)
	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	@ExcelResources(title="利息总额",order=19)
	public BigDecimal getInterestAll() {
		return interestAll;
	}

	public void setInterestAll(BigDecimal interestAll) {
		this.interestAll = interestAll;
	}

	@ExcelResources(title="月付利息",order=20)
	public BigDecimal getInterestMonth() {
		return interestMonth;
	}

	public void setInterestMonth(BigDecimal interestMonth) {
		this.interestMonth = interestMonth;
	}

	@ExcelResources(title="划扣日期",order=21)
	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	@ExcelResources(title="初始出借日期 ",order=22)
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	@ExcelResources(title="到期日",order=23)
	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@ExcelResources(title="账单日",order=24)
	public String getStatementDate() {
		return statementDate;
	}

	public void setStatementDate(String statementDate) {
		this.statementDate = statementDate == null ? null : statementDate.trim();
	}

	@ExcelResources(title="即将到期天数",order=25)
	public Integer getSurplusDate() {
		return surplusDate;
	}

	public void setSurplusDate(Integer surplusDate) {
		this.surplusDate = surplusDate;
	}

	@ExcelResources(title="状态",order=26)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	@ExcelResources(title="pos机终端号",order=27)
	public String getPosNo() {
		return posNo;
	}

	public void setPosNo(String posNo) {
		this.posNo = posNo == null ? null : posNo.trim();
	}

	@ExcelResources(title="出借人姓名",order=28)
	public String getTenderName() {
		return tenderName;
	}

	public void setTenderName(String tenderName) {
		this.tenderName = tenderName == null ? null : tenderName.trim();
	}

	@ExcelResources(title="证件类型",order=29)
	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType == null ? null : idType.trim();
	}

	@ExcelResources(title="身份证号",order=30)
	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo == null ? null : idNo.trim();
	}

	@ExcelResources(title="非续投/续投",order=31)
	public String getContinueFlg() {
		return continueFlg;
	}

	public void setContinueFlg(String continueFlg) {
		this.continueFlg = continueFlg == null ? null : continueFlg.trim();
	}

	@ExcelResources(title="联系方式",order=32)
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel == null ? null : tel.trim();
	}

	@ExcelResources(title="推广渠道",order=33)
	public String getSpreadType() {
		return spreadType;
	}

	public void setSpreadType(String spreadType) {
		this.spreadType = spreadType == null ? null : spreadType.trim();
	}

	@ExcelResources(title="汇入银行",order=34)
	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank == null ? null : bank.trim();
	}

	@ExcelResources(title="银行支行名称",order=35)
	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch == null ? null : branch.trim();
	}

	@ExcelResources(title="账号",order=36)
	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo == null ? null : cardNo.trim();
	}

	@ExcelResources(title="开户人姓名",order=37)
	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName == null ? null : cardName.trim();
	}

	@ExcelResources(title="开卡省份",order=38)
	public String getCardProvince() {
		return cardProvince;
	}

	public void setCardProvince(String cardProvince) {
		this.cardProvince = cardProvince == null ? null : cardProvince.trim();
	}

	@ExcelResources(title="开卡城市",order=39)
	public String getCardCity() {
		return cardCity;
	}

	public void setCardCity(String cardCity) {
		this.cardCity = cardCity == null ? null : cardCity.trim();
	}

	@ExcelResources(title="银行行号",order=40)
	public String getCardLine() {
		return cardLine;
	}

	public void setCardLine(String cardLine) {
		this.cardLine = cardLine == null ? null : cardLine.trim();
	}

	@ExcelResources(title="汇款银行",order=41)
	public String getInBank() {
		return inBank;
	}

	public void setInBank(String inBank) {
		this.inBank = inBank == null ? null : inBank.trim();
	}

	@ExcelResources(title="银行支行名称",order=42)
	public String getInBranch() {
		return inBranch;
	}

	public void setInBranch(String inBranch) {
		this.inBranch = inBranch == null ? null : inBranch.trim();
	}

	@ExcelResources(title="账号",order=43)
	public String getInCardNo() {
		return inCardNo;
	}

	public void setInCardNo(String inCardNo) {
		this.inCardNo = inCardNo == null ? null : inCardNo.trim();
	}

	@ExcelResources(title="开户人姓名",order=44)
	public String getInCardName() {
		return inCardName;
	}

	public void setInCardName(String inCardName) {
		this.inCardName = inCardName == null ? null : inCardName.trim();
	}

	@ExcelResources(title="开卡省份",order=45)
	public String getInCardProvince() {
		return inCardProvince;
	}

	public void setInCardProvince(String inCardProvince) {
		this.inCardProvince = inCardProvince == null ? null : inCardProvince.trim();
	}

	@ExcelResources(title="开卡城市",order=46)
	public String getInCardCity() {
		return inCardCity;
	}

	public void setInCardCity(String inCardCity) {
		this.inCardCity = inCardCity == null ? null : inCardCity.trim();
	}

	@ExcelResources(title="出借人地址",order=47)
	public String getBorrowAddress() {
		return borrowAddress;
	}

	public void setBorrowAddress(String borrowAddress) {
		this.borrowAddress = borrowAddress == null ? null : borrowAddress.trim();
	}

	@ExcelResources(title="紧急联系人",order=48)
	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName == null ? null : contactName.trim();
	}

	@ExcelResources(title="紧急联系人电话",order=49)
	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel == null ? null : contactTel.trim();
	}

	@ExcelResources(title="与出借人关系",order=50)
	public String getContactRelationship() {
		return contactRelationship;
	}

	public void setContactRelationship(String contactRelationship) {
		this.contactRelationship = contactRelationship == null ? null : contactRelationship.trim();
	}

	@ExcelResources(title="备注",order=51)
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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getMultyCon() {
		return multyCon;
	}

	public void setMultyCon(String multyCon) {
		this.multyCon = multyCon;
	}

	public String getMultyFlg() {
		return multyFlg;
	}

	public void setMultyFlg(String multyFlg) {
		this.multyFlg = multyFlg;
	}

	public StringBuffer getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(StringBuffer issueDate) {
		this.issueDate = issueDate;
	}


}