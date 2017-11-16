package com.bqhx.yyb.vo;

import java.util.List;

public class FgsVO {
	/**营业部list*/
	private List<YybVO> yybList;
	/**分公司ID*/
	private String fid;
	/**分公司name*/
	private String fname;
	/**分公司经理*/
	private String fmanager;
	/**所在大区ID*/
	private String pid;
	
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

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getFmanager() {
		return fmanager;
	}

	public void setFmanager(String fmanager) {
		this.fmanager = fmanager;
	}

	public List<YybVO> getYybList() {
		return yybList;
	}

	public void setYybList(List<YybVO> yybList) {
		this.yybList = yybList;
	}
	
}
