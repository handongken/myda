package com.bqhx.yyb.vo;

import java.util.List;

public class YybVO {
	/**团队list*/
	private List<TdVO> tlist;
	/**营业部ID*/
	private String yid;
	/**营业部name*/
	private String yname;
	/**营业部经理*/
	private String ymanager;
	/**所在分公司ID*/
	private String fid;
	
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

	public String getYname() {
		return yname;
	}

	public void setYname(String yname) {
		this.yname = yname;
	}

	public String getYmanager() {
		return ymanager;
	}

	public void setYmanager(String ymanager) {
		this.ymanager = ymanager;
	}

	public List<TdVO> getTlist() {
		return tlist;
	}

	public void setTlist(List<TdVO> tlist) {
		this.tlist = tlist;
	}
	
	
}
