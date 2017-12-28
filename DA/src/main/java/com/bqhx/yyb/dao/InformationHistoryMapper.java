package com.bqhx.yyb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bqhx.yyb.vo.ConditionVO;
import com.bqhx.yyb.vo.InformationHistoryVO;

@Mapper
public interface InformationHistoryMapper {

	void insertInfoHistory(ConditionVO record);

	List<InformationHistoryVO> selectInfoHistoryByCondition(ConditionVO condition);

}