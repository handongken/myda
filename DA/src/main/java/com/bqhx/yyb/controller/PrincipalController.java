package com.bqhx.yyb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bqhx.yyb.service.PrincipalService;
import com.bqhx.yyb.vo.ConditionVO;
import com.bqhx.yyb.vo.PrincipalVO;
@RestController
@RequestMapping("/")
public class PrincipalController {
	@Autowired
	@Qualifier("PrincipalServiceImpl") 
	private PrincipalService principalService;
	/**
	 * 根据条件查询还本信息
	 * @param conditionVO
	 * @return InformationVOList
	 */
	@RequestMapping(value = "/selectPrincipalByCondition", method = RequestMethod.POST)
	List<PrincipalVO> selectPrincipalByCondition(ConditionVO conditionVO){
		List<PrincipalVO> principalList = principalService.selectPrincipalByCondition(conditionVO);
		return principalList;
	}
	
}
