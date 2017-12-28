package com.bqhx.yyb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bqhx.yyb.constant.Constant;
import com.bqhx.yyb.dao.OrganizationMapper;
import com.bqhx.yyb.dao.UserHistoryMapper;
import com.bqhx.yyb.dao.UserMapper;
import com.bqhx.yyb.service.UserService;
import com.bqhx.yyb.util.DateUtil;
import com.bqhx.yyb.vo.DqVO;
import com.bqhx.yyb.vo.FgsVO;
import com.bqhx.yyb.vo.OrganizationConditionVO;
import com.bqhx.yyb.vo.OrganizationResultVO;
import com.bqhx.yyb.vo.TdVO;
import com.bqhx.yyb.vo.UserConditionVO;
import com.bqhx.yyb.vo.UserHistoryVO;
import com.bqhx.yyb.vo.UserVO;
import com.bqhx.yyb.vo.YybVO;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper userMapper;
	@Autowired
	OrganizationMapper organizationMapper;
	@Autowired
	UserHistoryMapper userHistoryMapper; 
	
	/**
	 * 新增用户
	 */
	@Override
	public boolean insertUserSelective(UserConditionVO condition) {
		boolean flag = false;
		if(flag != true){
		String insDate = DateUtil.formatDate(new Date(), Constant.PATTERN_HMS);
		if (insDate != "") {
			condition.setInsDate(insDate);
		}
		condition.setInsUser(condition.getLocalUserId());
		userMapper.insertUserSelective(condition);
		//插入历史表
		condition.setUserChangeInfo(Constant.INSERT);
		//架构name
		OrganizationConditionVO orcon = new OrganizationConditionVO();
		//sname
		orcon.setVlevel(Constant.FLAG_ZERO);
		if(condition.getSid() == null || "".equals(condition.getSid())){
			orcon.setD_ID("A001");
		}else{
			orcon.setD_ID(condition.getSid());
			OrganizationResultVO syb = organizationMapper.selectSybByCondition(orcon);
			if(syb != null){
				condition.setSname(syb.getDname());
				condition.setSmname(syb.getDmanager());
			}
		}
		//dname
		if(condition.getDid() == null || "".equals(condition.getDid())){
			orcon.setP_ID("B001");
		}else{
			orcon.setP_ID(condition.getDid());
			DqVO dq = organizationMapper.selectDqByCondition(orcon);
			if(dq != null){
				condition.setDname(dq.getPname());
				condition.setDmname(dq.getPmanager());
			}
		}
		//fname
		if(condition.getFid() == null || "".equals(condition.getFid())){
			orcon.setF_ID("C001");
		}else{
			orcon.setF_ID(condition.getFid());
			FgsVO fgs = organizationMapper.selectFgsByCondition(orcon);
			if(fgs != null){
				condition.setFname(fgs.getFname());
				condition.setFmname(fgs.getFmanager());
			}
		}
		//yname
		if(condition.getYid() == null || "".equals(condition.getYid())){
			orcon.setY_ID("D001");
		}else{
			orcon.setY_ID(condition.getYid());
			YybVO yyb = organizationMapper.selectYybByCondition(orcon);
			if(yyb != null){
				condition.setYname(yyb.getYname());
				condition.setYmname(yyb.getYmanager());
			}
		}
		//tname
		orcon.setT_ID(condition.getTid());
		TdVO td = organizationMapper.selectTdByCondition(orcon);
		if(td != null){
			condition.setTname(td.getTname());
			condition.setTmname(td.getTmanager());
		}
		insertUserHistory(condition);
		flag = true;
	}
		return flag;
	}
	
	/**
	 * 查询用户
	 */
	@Override
	public List<UserVO> selectUserByCondition(UserConditionVO condition) {
		List<UserVO> list = userMapper.selectUserByCondition(condition);
		for(UserVO userVO : list){
			String insDate = DateUtil.convertDate(userVO.getInsDate(), Constant.PATTERN_HMS);
			if (insDate != "") {
				userVO.setInsDate(insDate);
			}
			OrganizationConditionVO organizationConditionVO = new OrganizationConditionVO();
			organizationConditionVO.setVlevel(Constant.FLAG_ZERO);
			//syb
			if(userVO.getSid() != null && !"".equals(userVO.getSid()) && !"A001".equals(userVO.getSid())){
				organizationConditionVO.setD_ID(userVO.getSid());
				OrganizationResultVO syb = organizationMapper.selectSybByCondition(organizationConditionVO);
				if(syb != null){
					userVO.setSname(syb.getDname());
				}else{
					userVO.setSname("");
				}
			}else{
				organizationConditionVO.setD_ID("A001");
				userVO.setSname("");
			}
			//dq
			if(userVO.getDid() != null && !"".equals(userVO.getDid()) && !"B001".equals(userVO.getDid())){
				organizationConditionVO.setP_ID(userVO.getDid());
				DqVO dq = organizationMapper.selectDqByCondition(organizationConditionVO);
				if(dq != null){
					userVO.setDname(dq.getPname());
				}else{
					userVO.setDname("");
				}
			}else{
				organizationConditionVO.setP_ID("B001");
				userVO.setDname("");
			}
			//fgs
			if(userVO.getFid() != null && !"".equals(userVO.getFid()) && !"C001".equals(userVO.getFid())){
				organizationConditionVO.setF_ID(userVO.getFid());
				FgsVO fgs = organizationMapper.selectFgsByCondition(organizationConditionVO);
				if(fgs != null){
					userVO.setFname(fgs.getFname());
				}else{
					userVO.setFname("");
				}
			}else{
				organizationConditionVO.setF_ID("C001");
				userVO.setFname("");
			}
			//yyb
			if(userVO.getYid() != null && !"".equals(userVO.getYid()) && !"D001".equals(userVO.getYid())){
				organizationConditionVO.setY_ID(userVO.getYid());
				YybVO yyb = organizationMapper.selectYybByCondition(organizationConditionVO);	
				if(yyb != null){
					userVO.setYname(yyb.getYname());
				}else{
					userVO.setYname("");
				}
			}else{
				organizationConditionVO.setY_ID("D001");
				userVO.setYname("");
			}
			//td
			if(userVO.getTid() != null && !"".equals(userVO.getTid())){
				organizationConditionVO.setT_ID(userVO.getTid());
				TdVO td = organizationMapper.selectTdByCondition(organizationConditionVO);
				if(td != null){
					userVO.setTname(td.getTname());
				}else{
					userVO.setTname("");
				}
			}else{
				userVO.setTname("");
			}
		}
		return list;
	}

	@Override
	public UserVO selectUserByPrimaryKey(UserConditionVO condition) {
		UserVO vo = userMapper.selectUserByPrimaryKey(condition);
		return vo;
	}

	/**
	 * 更新
	 */
	@Override
	public int updateUserByPrimaryKeySelective(UserConditionVO condition, String localUserId) {
		String updDate = DateUtil.formatDate(new Date(), Constant.PATTERN_HMS);
		condition.setUpdDate(updDate);
		condition.setUpdUser(localUserId);
		userMapper.updateUserByPrimaryKeySelective(condition);
		//插入历史表
		//删除
		if(Constant.FLAG_ONE.equals(condition.getDelFlg())){
			condition.setUserChangeInfo(Constant.DELETE);
		}
		else{
			condition.setUserChangeInfo(Constant.UPDATE);
		}
		// 架构显示name
		OrganizationConditionVO orcon = new OrganizationConditionVO();
		// sname
		orcon.setVlevel(Constant.FLAG_ZERO);
		if (condition.getSid() == null || "".equals(condition.getSid())) {
			orcon.setD_ID("A001");
		} else {
			orcon.setD_ID(condition.getSid());
			OrganizationResultVO syb = organizationMapper.selectSybByCondition(orcon);
			if (syb != null) {
				condition.setSname(syb.getDname());
				condition.setSmname(syb.getDmanager());
			}
		}
		// dname
		if (condition.getDid() == null || "".equals(condition.getDid())) {
			orcon.setP_ID("B001");
		} else {
			orcon.setP_ID(condition.getDid());
			DqVO dq = organizationMapper.selectDqByCondition(orcon);
			if (dq != null) {
				condition.setDname(dq.getPname());
				condition.setDmname(dq.getPmanager());
			}
		}
		// fname
		if (condition.getFid() == null || "".equals(condition.getFid())) {
			orcon.setF_ID("C001");
		} else {
			orcon.setF_ID(condition.getFid());
			FgsVO fgs = organizationMapper.selectFgsByCondition(orcon);
			if (fgs != null) {
				condition.setFname(fgs.getFname());
				condition.setFmname(fgs.getFmanager());
			}
		}
		// yname
		orcon.setY_ID(condition.getYid());
		if (condition.getYid() == null || "".equals(condition.getYid())) {
			orcon.setY_ID("D001");
		} else {
			orcon.setY_ID(condition.getYid());
			YybVO yyb = organizationMapper.selectYybByCondition(orcon);
			if (yyb != null) {
				condition.setYname(yyb.getYname());
				condition.setYmname(yyb.getYmanager());
			}
		}
		// tname
		orcon.setT_ID(condition.getTid());
		TdVO td = organizationMapper.selectTdByCondition(orcon);
		if (td != null) {
			condition.setTname(td.getTname());
			condition.setTmname(td.getTmanager());
		}
		insertUserHistory(condition);
		return 1;
	}

	@Override
	public int insertUserHistory(UserConditionVO record) {
		userHistoryMapper.insertUserHistory(record);
		return 1;
	}

	@Override
	public List<UserHistoryVO> selectUserHistoryByCondition(UserConditionVO condition) {
		List<UserHistoryVO> uhlist = userHistoryMapper.selectUserHistoryByCondition(condition);
		return uhlist;
	}

}
