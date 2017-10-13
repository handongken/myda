package com.bqhx.yyb.vo;

import java.io.Serializable;

public class ConditionVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/** startTime */
	private String startTime;
	/** endTime */
	private String endTime;
	/** 合同编号 */
	private String contract;
	/** 分公司 */
	private String fgs;
	/** 分公司经理 */
	private String fgsManager;
	/** 大区 */
	private String dq;
	/** 大区经理 */
	private String dqManager;
	/** 事业部 */
	private String syb;
	/** 事业部经理 */
	private String sybManager;
	/** 出借人 */
	private String tenderName;
	/** 联系方式 */
	private String tel;
	/** 状态 */
	private String status;
	/** 划扣日期 */
	private String paymentDate;
	/** 删除flg */
	private String delFlg;
	/** multyCon */
	private String multyCon;
	/** multyFlg */
	private String multyFlg;
	/**非续投/续投  */ 
	private String continueFlg;
	
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

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
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

	public String getTenderName() {
		return tenderName;
	}

	public void setTenderName(String tenderName) {
		this.tenderName = tenderName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getDelFlg() {
		return delFlg;
	}

	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg;
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

	public String getContinueFlg() {
		return continueFlg;
	}

	public void setContinueFlg(String continueFlg) {
		this.continueFlg = continueFlg;
	}

}
