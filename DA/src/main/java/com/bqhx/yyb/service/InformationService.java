package com.bqhx.yyb.service;

import com.bqhx.yyb.vo.UserVO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bqhx.yyb.vo.ConditionVO;
import com.bqhx.yyb.vo.InformationHistoryVO;
import com.bqhx.yyb.vo.InformationVO;
import com.bqhx.yyb.vo.ResultTypeVO;

public interface InformationService {
	
	ConditionVO selectByPrimaryKey(ConditionVO condition);
	
	int insertSelective(ConditionVO condition,UserVO user);
	
	int insertInfoHistory(ConditionVO record);
	
	List<InformationHistoryVO> selectInfoHistoryByCondition(ConditionVO condition);
	
	List<InformationVO> selectByCondition(ConditionVO condition,UserVO user);
	
	InformationVO selectByContract(ConditionVO condition);
	/** 人力与业绩总表*/
	List<ResultTypeVO> selectHumanAndPerformanceByCondition(ConditionVO condition,UserVO user);
	
	int updateByPrimaryKeySelective(ConditionVO conditionVO,UserVO user);
	
	int insertBatch(@Param("conlist")List<ConditionVO> conlist);
	
}
