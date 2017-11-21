package com.bqhx.yyb.vo;

import com.bqhx.yyb.util.ExcelResources;

public class DataVO {

    //序号
    private String serialNo;
    
    //事业部
    private String syb;
    
    //大区
    private String dq;
    
    //分公司
    private String fgs;
    
    //营业部
    private String yyb;
    
    //团队
    private String td;
    
    //团队经理
    private String lcManager;
    
    //居间人员
    private String mediacyPerson;
    
    //职能岗
    private String function;
    
    //合计
    private String totalPerson;
    
    //开单人数
    private String billingNo;
    
    //开单率
    private String billingRate;
    
    //人均产能（规模）
    private String perScale;
    
    //人均产能（绩效）
    private String perPerformance;
    
    //规模业绩
    private String scale;
    
    //绩效业绩
    private String performance;
    
    public DataVO(String serialNo, String syb, String dq, String fgs, String yyb, String td, String lcManager,
			String mediacyPerson, String function, String totalPerson, String billingNo, String billingRate,
			String perScale, String perPerformance, String scale, String performance) {
		super();
		this.serialNo = serialNo;
		this.syb = syb;
		this.dq = dq;
		this.fgs = fgs;
		this.yyb = yyb;
		this.td = td;
		this.lcManager = lcManager;
		this.mediacyPerson = mediacyPerson;
		this.function = function;
		this.totalPerson = totalPerson;
		this.billingNo = billingNo;
		this.billingRate = billingRate;
		this.perScale = perScale;
		this.perPerformance = perPerformance;
		this.scale = scale;
		this.performance = performance;
	}

	public DataVO() {}

    @Override
	public String toString() {
		return "DataVO [serialNo=" + serialNo + ", syb=" + syb + ", dq=" + dq + ", fgs=" + fgs + ", yyb=" + yyb
				+ ", td=" + td + ", lcManager=" + lcManager + ", mediacyPerson=" + mediacyPerson + ", function="
				+ function + ", totalPerson=" + totalPerson + ", billingNo=" + billingNo + ", billingRate="
				+ billingRate + ", perScale=" + perScale + ", perPerformance=" + perPerformance + ", scale=" + scale
				+ ", performance=" + performance + "]";
	}

	@ExcelResources(title="序号",order=1)
	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	@ExcelResources(title="事业部",order=2)
	public String getSyb() {
		return syb;
	}

	public void setSyb(String syb) {
		this.syb = syb;
	}

	@ExcelResources(title="大区",order=3)
	public String getDq() {
		return dq;
	}

	public void setDq(String dq) {
		this.dq = dq;
	}

	@ExcelResources(title="分公司",order=4)
	public String getFgs() {
		return fgs;
	}

	public void setFgs(String fgs) {
		this.fgs = fgs;
	}

	@ExcelResources(title="营业部",order=5)
	public String getYyb() {
		return yyb;
	}

	public void setYyb(String yyb) {
		this.yyb = yyb;
	}

	@ExcelResources(title="团队",order=6)
	public String getTd() {
		return td;
	}

	public void setTd(String td) {
		this.td = td;
	}

	@ExcelResources(title="理财经理",order=7)
	public String getLcManager() {
		return lcManager;
	}

	public void setLcManager(String lcManager) {
		this.lcManager = lcManager;
	}

	@ExcelResources(title="居间人员",order=8)
	public String getMediacyPerson() {
		return mediacyPerson;
	}

	public void setMediacyPerson(String mediacyPerson) {
		this.mediacyPerson = mediacyPerson;
	}

	@ExcelResources(title="职能岗",order=9)
	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	@ExcelResources(title="合计",order=10)
	public String getTotalPerson() {
		return totalPerson;
	}

	public void setTotalPerson(String totalPerson) {
		this.totalPerson = totalPerson;
	}

	@ExcelResources(title="开单人数",order=11)
	public String getBillingNo() {
		return billingNo;
	}

	public void setBillingNo(String billingNo) {
		this.billingNo = billingNo;
	}

	@ExcelResources(title="开单率",order=12)
	public String getBillingRate() {
		return billingRate;
	}

	public void setBillingRate(String billingRate) {
		this.billingRate = billingRate;
	}

	@ExcelResources(title="人均产能（规模）",order=13)
	public String getPerScale() {
		return perScale;
	}

	public void setPerScale(String perScale) {
		this.perScale = perScale;
	}

	@ExcelResources(title="人均产能（绩效）",order=14)
	public String getPerPerformance() {
		return perPerformance;
	}

	public void setPerPerformance(String perPerformance) {
		this.perPerformance = perPerformance;
	}

	@ExcelResources(title="规模业绩（万元）",order=15)
	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	@ExcelResources(title="绩效业绩（万元）",order=16)
	public String getPerformance() {
		return performance;
	}

	public void setPerformance(String performance) {
		this.performance = performance;
	}
    
}
