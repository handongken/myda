package com.bqhx.yyb.service;

import java.util.List;

import com.bqhx.yyb.vo.CertificateVO;
import com.bqhx.yyb.vo.ConditionVO;

public interface CertificateService {
	void insertCertificate(ConditionVO condition);
	
	List<CertificateVO> selectCertificateByCondition(ConditionVO condition);
}
