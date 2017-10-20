package com.bqhx.yyb.vo;
import java.io.Serializable;

public class TypeVO  implements Serializable{
	private static final long serialVersionUID = 1L;
	/**产品  */ 
	private String type;
	/**产品编号  */ 
	private String typeCode;
	/**产品名称  */ 
	private String typeName;
	/**年化收益  */ 
	private String rate;
	/**期数  */ 
	private Integer periods;
	/**返还方式  */ 
	private String returnMode;
	/**折标系数  */ 
	private String zbRatio;
	/**返息间隔  */ 
	private Integer returnInterval;
	/**返次  */ 
	private Integer returnNo;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public Integer getPeriods() {
		return periods;
	}
	public void setPeriods(Integer periods) {
		this.periods = periods;
	}
	public String getReturnMode() {
		return returnMode;
	}
	public void setReturnMode(String returnMode) {
		this.returnMode = returnMode;
	}
	public String getZbRatio() {
		return zbRatio;
	}
	public void setZbRatio(String zbRatio) {
		this.zbRatio = zbRatio;
	}
	public Integer getReturnInterval() {
		return returnInterval;
	}
	public void setReturnInterval(Integer returnInterval) {
		this.returnInterval = returnInterval;
	}
	public Integer getReturnNo() {
		return returnNo;
	}
	public void setReturnNo(Integer returnNo) {
		this.returnNo = returnNo;
	}
	
}
