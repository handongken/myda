package com.bqhx.yyb.dao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bqhx.yyb.vo.TypeVO;

@Mapper
public interface TypeMapper {

	TypeVO selectTypeByPrimaryKey(@Param("type")String type);

}