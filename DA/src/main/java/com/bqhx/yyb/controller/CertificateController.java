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
	
	/**
	 * 银行短信（回息）
	 */
	@RequestMapping(value = "/selectSMSInterestByCondition", method = RequestMethod.POST)
	List<CertificateVO> selectSMSInterestByCondition(ConditionVO conditionVO){
		List<CertificateVO> certificateVOList = certificateMapper.selectCertificateByCondition(conditionVO);
		for(int i = 0;i < certificateVOList.size();i++){
			CertificateVO certificateVO = certificateVOList.get(i);
			String str = certificateVO.getInCardNo();
			String inCardNo = str.substring(str.length() - 4);//银行卡后四位
			certificateVO.setInCardNo(inCardNo);
		}
		return certificateVOList;
	}
	
	/**
	 * 银行短信（回本）
	 */
	@RequestMapping(value = "/selectSMSCapitalByCondition", method = RequestMethod.POST)
	List<CertificateVO> selectSMSCapitalByCondition(ConditionVO conditionVO){
		List<CertificateVO> certificateVOList = certificateMapper.selectCertificateByCondition(conditionVO);
		for(int i = 0;i < certificateVOList.size();i++){
			CertificateVO certificateVO = certificateVOList.get(i);
			String str = certificateVO.getInCardNo();
			String inCardNo = str.substring(str.length() - 4);//银行卡后四位
			certificateVO.setInCardNo(inCardNo);
		}
		return certificateVOList;
	}
}
