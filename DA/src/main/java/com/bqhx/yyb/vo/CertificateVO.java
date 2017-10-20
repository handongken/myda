package com.bqhx.yyb.vo;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 付息及发放凭证 
 */
public class CertificateVO implements Serializable{
	 
	private static final long serialVersionUID = 1L;
	/**合同号  */ 
	private String contract;
	/**返息间隔  */ 
	private Integer returnInterval;
	/**投资金额  */ 
	private Integer money;
	/**开户人姓名  */ 
	private String inCardName;
	/**银行账号  */ 
	private String inCardNo;
	/**发放金额  */ 
	private BigDecimal interestMonth;
	/**银行名称  */ 
	private String inBank;
	/**产品名称  */ 
	private String typeName;
	/**初始出借日期  */ 
	private String startDate;
	/**开始发放日期  */ 
	private String beginDate;
	/**结束发放日期  */ 
	private String endDate;
	
	public String getContract() {
		return contract;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
	public Integer getReturnInterval() {
		return returnInterval;
	}
	public void setReturnInterval(Integer returnInterval) {
		this.returnInterval = returnInterval;
	}
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	public String getInCardName() {
		return inCardName;
	}
	public void setInCardName(String inCardName) {
		this.inCardName = inCardName;
	}
	public String getInCardNo() {
		return inCardNo;
	}
	public void setInCardNo(String inCardNo) {
		this.inCardNo = inCardNo;
	}
	public BigDecimal getInterestMonth() {
		return interestMonth;
	}
	public void setInterestMonth(BigDecimal interestMonth) {
		this.interestMonth = interestMonth;
	}
	public String getInBank() {
		return inBank;
	}
	public void setInBank(String inBank) {
		this.inBank = inBank;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
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
