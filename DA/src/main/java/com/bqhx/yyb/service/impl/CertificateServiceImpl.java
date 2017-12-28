package com.bqhx.yyb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bqhx.yyb.constant.Constant;
import com.bqhx.yyb.dao.CertificateMapper;
import com.bqhx.yyb.dao.TypeMapper;
import com.bqhx.yyb.service.CertificateService;
import com.bqhx.yyb.service.MovableCheckService;
import com.bqhx.yyb.util.DateUtil;
import com.bqhx.yyb.vo.CertificateVO;
import com.bqhx.yyb.vo.ConditionVO;
import com.bqhx.yyb.vo.TypeVO;

@Service("CertificateServiceImpl")
public class CertificateServiceImpl implements CertificateService {
	@Autowired
	TypeMapper typeMapper;
	@Autowired
	CertificateMapper certificateMapper;
	@Autowired
	@Qualifier("MovableCheckServiceImpl")
	MovableCheckService movableCheckService;
	/**
	 * 插入付息凭证表
	 * @param record
	 * insertCertificate
	 */
	@Override
	public void insertCertificate(ConditionVO condition) {
		CertificateVO certificateVO = new CertificateVO();
		String startDate = condition.getStartDate();
		TypeVO typeVO = typeMapper.selectTypeByPrimaryKey(condition.getType(),Constant.FLAG_ZERO);
		String typeName = typeVO.getTypeName();
		int returnInterval = typeVO.getReturnInterval();
		String terminateDate = DateUtil.convertDay(startDate,-Constant.ONE);
		String beginDate = DateUtil.convertMonth(-returnInterval, startDate);
		String tenderName = condition.getTenderName();
		String tel = condition.getTel();
		certificateVO.setStartDate(startDate);
		certificateVO.setBeginDate(beginDate);
		certificateVO.setTerminateDate(terminateDate);
		certificateVO.setContract(condition.getContract());
		certificateVO.setInBank(condition.getInBank());
		certificateVO.setInCardName(condition.getInCardName());
		certificateVO.setInCardNo(condition.getInCardNo());
		certificateVO.setInterestMonth(condition.getInterestMonth());
		certificateVO.setMoney(condition.getMoney());
		certificateVO.setTypeName(typeName);
		certificateVO.setReturnInterval(returnInterval);
		certificateVO.setTenderName(tenderName);
		certificateVO.setTel(tel);
		certificateMapper.insertCertificate(certificateVO);
		//插入移动支票表
		condition.setPayFlg(Constant.FLAG_ONE);
		condition.setBeginDate(beginDate);
		condition.setTerminateDate(terminateDate);
		movableCheckService.insertMovableCheck(condition);
	}
	@Override
	public List<CertificateVO> selectCertificateByCondition(ConditionVO condition) {
		List<CertificateVO> certificateVOList = certificateMapper.selectCertificateByCondition(condition);
		return certificateVOList;
	}

}
