package com.bqhx.yyb.dao;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bqhx.yyb.vo.ConditionVO;
import com.bqhx.yyb.vo.InformationVO;
@Mapper
public interface InformationVOMapper {
	int deleteByPrimaryKey(InformationVO record);

	int insert(InformationVO record);

	int insertSelective(InformationVO record);

	List<InformationVO> selectByCondition(ConditionVO condition);
	
	InformationVO selectByPrimaryKey(InformationVO record);
   
    List<InformationVO> selectAll(InformationVO record);
    
    int updateByPrimaryKeySelective(InformationVO record);

    int updateByPrimaryKey(InformationVO record);
    
}