package com.bqhx.yyb.vo;

import java.util.List;

public class OrganizationResultVO {
	/**事业部ID*/
	private String did;
	/**事业部name*/
	private String dname;
	/**事业部经理*/
	private String dmanager;
	/**大区ID*/
	private String pid;
	/**大区name*/
	private String pname;
	/**大区经理*/
	private String pmanager;
	/**分公司ID*/
	private String fid;
	/**分公司name*/
	private String fname;
	/**分公司经理*/
	private String fmanager;
	/**营业部ID*/
	private String yid;
	/**营业部name*/
	private String yname;
	/**营业部经理*/
	private String ymanager;
	/**团队ID*/
	private String tid;
	/**团队name*/
	private String tname;
	/**团队经理*/
	private String tmanager;
	/**delflg*/
	private String delFlg;
	/**大区list*/
	private List<DqVO> dqList;
	
	public List<DqVO> getDqList() {
		return dqList;
	}
	public void setDqList(List<DqVO> dqList) {
		this.dqList = dqList;
	}
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
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
	public String getYid() {
		return yid;
	}
	public void setYid(String yid) {
		this.yid = yid;
	}
	public String getYname() {
		return yname;
	}
	public void setYname(String yname) {
		this.yname = yname;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getDelFlg() {
		return delFlg;
	}
	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg;
	}
	public String getDmanager() {
		return dmanager;
	}
	public void setDmanager(String dmanager) {
		this.dmanager = dmanager;
	}
	public String getPmanager() {
		return pmanager;
	}
	public void setPmanager(String pmanager) {
		this.pmanager = pmanager;
	}
	public String getFmanager() {
		return fmanager;
	}
	public void setFmanager(String fmanager) {
		this.fmanager = fmanager;
	}
	public String getYmanager() {
		return ymanager;
	}
	public void setYmanager(String ymanager) {
		this.ymanager = ymanager;
	}
	public String getTmanager() {
		return tmanager;
	}
	public void setTmanager(String tmanager) {
		this.tmanager = tmanager;
	}
	
}
