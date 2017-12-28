package com.bqhx.yyb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bqhx.yyb.dao.TypeMapper;
import com.bqhx.yyb.service.TypeService;
import com.bqhx.yyb.vo.TypeVO;

@Service("TypeServiceImpl")
public class TypeServiceImpl implements TypeService{
	@Autowired
	private TypeMapper typeMapper;
	
	@Override
	public TypeVO selectTypeByCondition(TypeVO type) {
		TypeVO typeVO = typeMapper.selectTypeByCondition(type);
		return typeVO;
	}

}
