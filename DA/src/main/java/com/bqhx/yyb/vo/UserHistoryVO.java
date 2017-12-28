package com.bqhx.yyb.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 付息及发放凭证 
 */
public class UserHistoryVO extends BaseVO implements Serializable{
	 
	private static final long serialVersionUID = 1L;
	/**user_his_id  */ 
	private String userHisId;
	/**user_change_info  */ 
	private String userChangeInfo;
	/**user_id  */ 
	private Integer userId;
	/**password  */ 
	private String password;
	/**name  */ 
	private String name;
	/**tel  */ 
	private BigDecimal tel;
	/**type_id  */ 
	private String typeId;
	/**s_id  */ 
	private String sid;
	/**s_name  */ 
	private String sname;
	/**s_mname  */ 
	private String smname;
	/**d_id  */ 
	private String did;
	/**d_name  */ 
	private String dname;
	/**d_mname  */ 
	private String dmname;
	/**f_id  */ 
	private String fid;
	/**f_name  */ 
	private String fname;
	/**f_mname  */ 
	private String fmname;
	/**y_id  */ 
	private String yid;
	/**y_name  */ 
	private String yname;
	/**y_mname  */ 
	private String ymname;
	/**t_id  */ 
	private String tid;
	/**t_name  */ 
	private String tname;
	/**t_mname  */ 
	private String tmname;
	/**id_card  */ 
	private String idCard;
	/**del_flg  */ 
	private String delFlg;
	
	public String getDelFlg() {
		return delFlg;
	}
	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg;
	}
	public String getUserHisId() {
		return userHisId;
	}
	public void setUserHisId(String userHisId) {
		this.userHisId = userHisId;
	}
	public String getUserChangeInfo() {
		return userChangeInfo;
	}
	public void setUserChangeInfo(String userChangeInfo) {
		this.userChangeInfo = userChangeInfo;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getTel() {
		return tel;
	}
	public void setTel(BigDecimal tel) {
		this.tel = tel;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSmname() {
		return smname;
	}
	public void setSmname(String smname) {
		this.smname = smname;
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
	public String getDmname() {
		return dmname;
	}
	public void setDmname(String dmname) {
		this.dmname = dmname;
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
	public String getFmname() {
		return fmname;
	}
	public void setFmname(String fmname) {
		this.fmname = fmname;
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
	public String getYmname() {
		return ymname;
	}
	public void setYmname(String ymname) {
		this.ymname = ymname;
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
	public String getTmname() {
		return tmname;
	}
	public void setTmname(String tmname) {
		this.tmname = tmname;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
