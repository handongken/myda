package com.bqhx.yyb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bqhx.yyb.constant.Constant;
import com.bqhx.yyb.dao.PrincipalMapper;
import com.bqhx.yyb.dao.TypeMapper;
import com.bqhx.yyb.service.MovableCheckService;
import com.bqhx.yyb.service.PrincipalService;
import com.bqhx.yyb.vo.ConditionVO;
import com.bqhx.yyb.vo.PrincipalVO;
import com.bqhx.yyb.vo.TypeVO;

@Service("PrincipalServiceImpl")
public class PrincipalServiceImpl implements PrincipalService {

	@Autowired
	private PrincipalMapper principalMapper;
	@Autowired
	private TypeMapper typeMapper;
	@Autowired
	@Qualifier("MovableCheckServiceImpl")
	MovableCheckService movableCheckService;
	/**
	 * 插入还本信息表
	 * @param record
	 * insertCertificate
	 */
	@Override
	public void insertPrincipal(ConditionVO condition) {
		PrincipalVO principalVO = new PrincipalVO();
		principalVO.setContract(condition.getContract());
		principalVO.setMoney(condition.getMoney());
		TypeVO typeVO = typeMapper.selectTypeByPrimaryKey(condition.getType(),Constant.FLAG_ZERO);
		principalVO.setTypeName(typeVO.getTypeName());
		principalVO.setLcManager(condition.getLcManager());
		principalVO.setTmanager(condition.getTmanager());
		principalVO.setYyb(condition.getYyb());
		principalVO.setYybManager(condition.getYybManager());
		principalVO.setFgs(condition.getFgs());
		principalVO.setFgsManager(condition.getFgsManager());
		principalVO.setDq(condition.getDq());
		principalVO.setDqManager(condition.getDqManager());
		principalVO.setSyb(condition.getSyb());
		principalVO.setSybManager(condition.getSybManager());
		principalVO.setStartDate(condition.getStartDate());
		principalVO.setEndDate(condition.getEndDate());
		principalVO.setTenderName(condition.getTenderName());
		principalMapper.insertPrincipal(principalVO);
		//插入移动支票表
		condition.setPayFlg(Constant.FLAG_ZERO);
		movableCheckService.insertMovableCheck(condition);
	
	}
	@Override
	public List<PrincipalVO> selectPrincipalByCondition(ConditionVO condition) {
		List<PrincipalVO> principalList = principalMapper.selectPrincipalByCondition(condition);
		return principalList;
	}

}
