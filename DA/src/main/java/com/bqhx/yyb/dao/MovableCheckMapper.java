package com.bqhx.yyb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bqhx.yyb.vo.ConditionVO;
import com.bqhx.yyb.vo.MovableCheckVO;

@Mapper
public interface MovableCheckMapper {

	void insertMovableCheck(MovableCheckVO record);

	List<MovableCheckVO> selectMovableCheckByCondition(ConditionVO condition);

}