package com.bqhx.yyb.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bqhx.yyb.constant.Constant;
import com.bqhx.yyb.service.CertificateService;
import com.bqhx.yyb.service.InformationService;
import com.bqhx.yyb.service.PrincipalService;
import com.bqhx.yyb.util.ConditionUtil;
import com.bqhx.yyb.vo.ConditionVO;
import com.bqhx.yyb.vo.InformationVO;
import com.bqhx.yyb.vo.MessageVO;
import com.bqhx.yyb.vo.ResultTypeVO;
import com.bqhx.yyb.vo.UserVO;


/**
 * @author Administrator InformationController
 */

@RestController
@RequestMapping("/")
public class InformationController {

	@Autowired
	@Qualifier("InformationServiceImpl") 
	private InformationService informationService;
	@Autowired
	@Qualifier("CertificateServiceImpl") 
	private CertificateService certificateService;
	@Autowired
	@Qualifier("PrincipalServiceImpl") 
	private PrincipalService principalService;
	/**
	 * 
	 * @param record
	 * @return messageVO delete
	 */
	@RequestMapping(value = "/deleteByPrimaryKey", method = RequestMethod.POST)
	MessageVO deleteByPrimaryKey(ConditionVO condition,UserVO user) {
		MessageVO messageVO = new MessageVO();
		condition.setDelFlg(Constant.FLAG_ONE);
		int code = informationService.updateByPrimaryKeySelective(condition,user);
		//插入历史表
		condition.setChangeInfo(Constant.DELETE);
		informationService.insertInfoHistory(condition);
		if(code == 1){
			messageVO.setCode(Constant.FLAG_ONE);
			messageVO.setMessage(Constant.SUCCESS);
		}else{
			messageVO.setCode(Constant.FLAG_ZERO);
			messageVO.setMessage(Constant.FAILED);
		}
		return messageVO;
	}

	/**
	 * 
	 * @param record
	 * @return messageVO insert
	 */
	@RequestMapping(value = "/insertSelective", method = RequestMethod.POST)
	MessageVO insertSelective(ConditionVO condition, UserVO user) {
		MessageVO messageVO = new MessageVO();
		ConditionVO information = informationService.selectByPrimaryKey(condition);
		if (information != null) {
			messageVO.setCode(Constant.FLAG_ZERO);
			messageVO.setMessage(Constant.FAILED);
		} else {
			int code = informationService.insertSelective(condition,user);
			//插入付息表
			certificateService.insertCertificate(condition);
			//插入还本表
			principalService.insertPrincipal(condition);
			//插入历史表
			condition.setChangeInfo(Constant.INSERT);
			informationService.insertInfoHistory(condition);
			if(code == 1){
				messageVO.setCode(Constant.FLAG_ONE);
				messageVO.setMessage(Constant.SUCCESS);
			}else{
				messageVO.setCode(Constant.FLAG_ZERO);
				messageVO.setMessage(Constant.FAILED);
			}
		}
		return messageVO;
	}

	@RequestMapping(value = "/selectByCondition", method = RequestMethod.POST)
	List<InformationVO> selectByCondition(ConditionVO conditionVO, UserVO user) {
		ConditionVO condition = ConditionUtil.getConditionVOByRole(conditionVO,user);
		List<InformationVO> informationVOList = informationService.selectByCondition(condition,user);
		return informationVOList;
	}
	
	@RequestMapping(value = "/selectByContract", method = RequestMethod.POST)
	InformationVO selectByContract(ConditionVO conditionVO, UserVO user) {
		ConditionVO condition = ConditionUtil.getConditionVOByRole(conditionVO,user);
		InformationVO informationVO = informationService.selectByContract(condition);
		return informationVO;
	}
	
	/**
	 * 
	 * 人力与业绩
	 */
	@RequestMapping(value = "/selectHumanAndPerformanceByCondition", method = RequestMethod.POST)
	List<ResultTypeVO> selectHumanAndPerformanceByCondition(ConditionVO conditionVO, UserVO user) {
		ConditionVO condition = ConditionUtil.getConditionVOByRole(conditionVO,user);
		condition.setJxAchievement(new BigDecimal(12500.00));
		List<ResultTypeVO> informationVOList = informationService.selectHumanAndPerformanceByCondition(condition,user);
		return informationVOList;
	}
	
	@RequestMapping(value = "/updateByPrimaryKeySelective", method = RequestMethod.POST)
	MessageVO updateByPrimaryKeySelective(ConditionVO conditionVO, UserVO user) {
		MessageVO messageVO = new MessageVO();
		int code = informationService.updateByPrimaryKeySelective(conditionVO,user);
		//插入历史表
		conditionVO.setChangeInfo(Constant.UPDATE);
		informationService.insertInfoHistory(conditionVO);
		if (code == 1) {
			messageVO.setCode(Constant.FLAG_ONE);
			messageVO.setMessage(Constant.SUCCESS);
		} else {
			messageVO.setCode(Constant.FLAG_ZERO);
			messageVO.setMessage(Constant.FAILED);
		}
		return messageVO;
	}

	/**
	 * 
	 * @param record
	 * @return record approve
	 */
	@RequestMapping(value = "/approve", method = RequestMethod.POST)
	MessageVO approve(ConditionVO condition,UserVO user) {
		MessageVO messageVO = new MessageVO();
		informationService.updateByPrimaryKeySelective(condition,user);
		//插入历史表
		String mstatus = condition.getManagerStatus();
		//审批通过
		if("1".equals(mstatus)){
			condition.setChangeInfo(Constant.APPROVEONE);
		}//审批失败
		else if("2".equals(mstatus)){
			condition.setChangeInfo(Constant.APPROVETWO);
		}
		informationService.insertInfoHistory(condition);
		messageVO.setCode(Constant.FLAG_ONE);
		messageVO.setMessage(Constant.SUCCESS);
		return messageVO;
	}

}
