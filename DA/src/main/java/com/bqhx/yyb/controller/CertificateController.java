package com.bqhx.yyb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bqhx.yyb.dao.CertificateMapper;
import com.bqhx.yyb.vo.CertificateVO;
import com.bqhx.yyb.vo.ConditionVO;
@RestController
@RequestMapping("/")
public class CertificateController {
	@Autowired
	private CertificateMapper certificateMapper;
	
	@RequestMapping(value = "/selectCertificateByCondition", method = RequestMethod.POST)
	List<CertificateVO> selectCertificateByCondition(ConditionVO conditionVO){
		List<CertificateVO> certificateVOList = certificateMapper.selectCertificateByCondition(conditionVO);
		return certificateVOList;
	}
}
