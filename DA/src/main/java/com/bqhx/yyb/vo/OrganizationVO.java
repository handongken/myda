package com.bqhx.yyb.vo;

public class OrganizationVO {
	/**主键ID*/
	private String oidOrgid;
	/**事业部ID*/
	private String did;
	/**大区ID*/
	private String pid;
	/**分公司ID*/
	private String fid;
	/**营业部ID*/
	private String yid;
	/**团队ID*/
	private String tid;
	/**delflg*/
	private String delFlg;
	/**levelType*/
	private String levelType;
	/**typeid*/
	private String typeId;
	
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getYid() {
		return yid;
	}
	public void setYid(String yid) {
		this.yid = yid;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getDelFlg() {
		return delFlg;
	}
	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg;
	}
	public String getOidOrgid() {
		return oidOrgid;
	}
	public void setOidOrgid(String oidOrgid) {
		this.oidOrgid = oidOrgid;
	}
	public String getLevelType() {
		return levelType;
	}
	public void setLevelType(String levelType) {
		this.levelType = levelType;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	
}
