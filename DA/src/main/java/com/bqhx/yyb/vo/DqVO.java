package com.bqhx.yyb.vo;

import java.util.List;

public class DqVO {
	/**分公司list*/
	private List<FgsVO> fgsList;
	/**大区ID*/
	private String pid;
	/**大区name*/
	private String pname;
	/**大区经理*/
	private String pmanager;
	/**所在事业部ID*/
	private String did;
	
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

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPmanager() {
		return pmanager;
	}

	public void setPmanager(String pmanager) {
		this.pmanager = pmanager;
	}

	public List<FgsVO> getFgsList() {
		return fgsList;
	}

	public void setFgsList(List<FgsVO> fgsList) {
		this.fgsList = fgsList;
	}
	
	
}
