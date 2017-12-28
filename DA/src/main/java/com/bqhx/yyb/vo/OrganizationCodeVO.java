package com.bqhx.yyb.vo;

public class OrganizationCodeVO {
	private String oid;
	private String oname;
	private String mname;
	private String vlevel;
	private String levelType;
	private String delFlg;
	
	public String getVlevel() {
		return vlevel;
	}
	public void setVlevel(String vlevel) {
		this.vlevel = vlevel;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getOname() {
		return oname;
	}
	public void setOname(String oname) {
		this.oname = oname;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getLevelType() {
		return levelType;
	}
	public void setLevelType(String levelType) {
		this.levelType = levelType;
	}
	public String getDelFlg() {
		return delFlg;
	}
	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg;
	}
	
}
