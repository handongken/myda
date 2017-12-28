package com.bqhx.yyb.service;

import java.util.List;

import com.bqhx.yyb.vo.ConditionVO;
import com.bqhx.yyb.vo.PrincipalVO;

public interface PrincipalService {
	void insertPrincipal(ConditionVO condition);
	
	List<PrincipalVO> selectPrincipalByCondition(ConditionVO condition);
}
