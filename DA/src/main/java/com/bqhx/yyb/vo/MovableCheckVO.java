package com.bqhx.yyb.vo;

import java.math.BigDecimal;
/**
 * @author Administrator
 * 移动支票VO000
 */
public class MovableCheckVO {
	private static final long serialVersionUID = 1L;
	/**合同编号  */ 
	private String contract;
	/**付方账号  */ 
	private String cardNo;
	/**金额上限  */
	private BigDecimal amountLimit;
	/**初始出借日期  */ 
	private String startDate;
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
	/**收方账号（回款账号）  */ 
	private String inCardNo;
	/**收方户名（回款开户人姓名）  */ 
	private String inCardName;
	/**汇路类型  */ 
	private String remitType;
	/**收方行名称 （银行支行名称） */ 
	private String inBranch;
	/**收方行行号  */ 
	private String cardLine;
	/**收方行地址 */ 
	private String inCardAddress;
	/**附言  */ 
	private String postscript;
	/**收款人手机号码  */ 
	private String inTel;
	/**付息or还本,1or0  */ 
	private String payFlg;
	
	
	public String getPayFlg() {
		return payFlg;
	}
	public void setPayFlg(String payFlg) {
		this.payFlg = payFlg;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEffectiveDates() {
		return effectiveDates;
	}
	public void setEffectiveDates(String effectiveDates) {
		this.effectiveDates = effectiveDates;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public BigDecimal getAmountLimit() {
		return amountLimit;
	}
	public void setAmountLimit(BigDecimal amountLimit) {
		this.amountLimit = amountLimit;
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
	public String getRemitType() {
		return remitType;
	}
	public void setRemitType(String remitType) {
		this.remitType = remitType;
	}
	public String getInBranch() {
		return inBranch;
	}
	public void setInBranch(String inBranch) {
		this.inBranch = inBranch;
	}
	public String getCardLine() {
		return cardLine;
	}
	public void setCardLine(String cardLine) {
		this.cardLine = cardLine;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getContract() {
		return contract;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}

}
