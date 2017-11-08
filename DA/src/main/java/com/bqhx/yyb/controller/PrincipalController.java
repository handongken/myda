package com.bqhx.yyb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bqhx.yyb.constant.Constant;
import com.bqhx.yyb.dao.PrincipalMapper;
import com.bqhx.yyb.vo.CertificateVO;
import com.bqhx.yyb.vo.ConditionVO;
import com.bqhx.yyb.vo.PrincipalVO;
@RestController
@RequestMapping("/")
public class PrincipalController {
	@Autowired
	private PrincipalMapper principalVOMapper;
	
	/**
	 * 根据条件查询还本信息
	 * @param conditionVO
	 * @return InformationVOList
	 */
	@RequestMapping(value = "/selectPrincipalByCondition", method = RequestMethod.POST)
	List<PrincipalVO> selectPrincipalByCondition(ConditionVO conditionVO){
		conditionVO.setDelFlg(Constant.FLAG_ZERO);
		List<PrincipalVO> principalList = principalVOMapper.selectPrincipalByCondition(conditionVO);
		return principalList;
	}
	
}
