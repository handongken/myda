package com.bqhx.yyb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bqhx.yyb.vo.CertificateVO;
import com.bqhx.yyb.vo.ConditionVO;

@Mapper
public interface CertificateMapper {
	void insertCertificate(CertificateVO record);

	List<CertificateVO> selectCertificateByCondition(ConditionVO condition);

}