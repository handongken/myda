package com.bqhx.yyb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bqhx.yyb.vo.OrganizationCodeVO;

@Mapper
public interface OrganizationCodeMapper {

	void insertOrganizationCode(OrganizationCodeVO record);

	OrganizationCodeVO selectOrganizationCodeByOid(@Param(value = "oid") String oid,@Param(value = "delFlg") String delFlg);

}