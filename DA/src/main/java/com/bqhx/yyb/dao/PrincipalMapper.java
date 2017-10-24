package com.bqhx.yyb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bqhx.yyb.vo.ConditionVO;
import com.bqhx.yyb.vo.PrincipalVO;

@Mapper
public interface PrincipalMapper {

	void insertPrincipal(PrincipalVO record);

	List<PrincipalVO> selectPrincipalByCondition(ConditionVO condition);

}