package com.bqhx.yyb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bqhx.yyb.vo.ConditionVO;
import com.bqhx.yyb.vo.InformationVO;
import com.bqhx.yyb.vo.ResultTypeVO;
@Mapper
public interface InformationVOMapper {
	int deleteByPrimaryKey(InformationVO record);

	int insert(InformationVO record);

	int insertSelective(ConditionVO condition);

	List<InformationVO> selectByCondition(ConditionVO condition);
	
	List<InformationVO> selectRePaymentByCondition(ConditionVO condition);
	
	List<ResultTypeVO> selectHumanAndPerformanceByCondition(ConditionVO condition);
	Integer testSelect();
	InformationVO selectByPrimaryKey(ConditionVO condition);
   
    List<InformationVO> selectAll();
    
    int updateByPrimaryKeySelective(InformationVO record);

    int updateByPrimaryKey(InformationVO record);
    
}