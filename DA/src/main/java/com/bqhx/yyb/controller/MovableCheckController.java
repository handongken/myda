package com.bqhx.yyb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bqhx.yyb.constant.Constant;
import com.bqhx.yyb.dao.MovableCheckMapper;
import com.bqhx.yyb.vo.ConditionVO;
import com.bqhx.yyb.vo.MovableCheckVO;
@RestController
@RequestMapping("/")
public class MovableCheckController {
	@Autowired
	private MovableCheckMapper movableCheckMapper;
	
	/**
	 * 根据条件查询还本信息
	 * @param conditionVO
	 * @return InformationVOList
	 */
	@RequestMapping(value = "/selectMovableCheckByCondition", method = RequestMethod.POST)
	List<MovableCheckVO> selectMovableCheckByCondition(ConditionVO conditionVO){
		conditionVO.setDelFlg(Constant.FLAG_ZERO);
		List<MovableCheckVO> movableCheckList = movableCheckMapper.selectMovableCheckByCondition(conditionVO);
		return movableCheckList;
	}
	
}
