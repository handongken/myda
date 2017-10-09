package com.bqhx.yyb.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bqhx.yyb.dao.InformationVOMapper;
import com.bqhx.yyb.vo.InformationVO;
import com.bqhx.yyb.vo.MessageVO;
import com.bqhx.yyb.vo.UserVO;

/**
 * @author Administrator InformationController
 */

@RestController
@RequestMapping("/")
public class InformationController {

	@Autowired
	private InformationVOMapper informationVOMapper;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/** 
	 * 
	 * @param record 
	 * @return messageVO
	 * delete
	 */
	@RequestMapping(value = "/deleteByPrimaryKey", method = RequestMethod.POST)
	MessageVO deleteByPrimaryKey(InformationVO record) {
		record.setDelFlg("1");
		String code = updateByPrimaryKeySelective(record);
		MessageVO messageVO = new MessageVO();
		if(code.equals("1")){
			messageVO.setCode(code);
			messageVO.setMessage("success");
		}else{
			messageVO.setCode("0");
			messageVO.setMessage("failed");
		}				
		return messageVO;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	InformationVO insert(InformationVO record) {
		int code = informationVOMapper.insert(record);
		code = 1;
		if(code == 1){
			record.setMessage("1");
		}else{
			record.setMessage("0");
		}
		return record;
	}

	/** 
	 * 
	 * @param record 
	 * @return messageVO
	 * insert
	 */
	@RequestMapping(value = "/insertSelective", method = RequestMethod.POST)
	MessageVO insertSelective(InformationVO record,UserVO user) {
		MessageVO messageVO = new MessageVO();
		InformationVO informationVO = informationVOMapper.selectByPrimaryKey(record);
		if(informationVO != null){
			messageVO.setCode("0");
			messageVO.setMessage("failed");
		}else{
			record.setInsDate(sdf.format(new Date()));
			record.setInsUser(user.getName());
			informationVOMapper.insertSelective(record);
			messageVO.setCode("1");
			messageVO.setMessage("success");
		}
		return messageVO;
	}

	@RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.POST)
	InformationVO selectByPrimaryKey(InformationVO record) {
		InformationVO informationVO = informationVOMapper.selectByPrimaryKey(record);
			String paymentDate_s = informationVO.getPaymentDate();
			String insDate_s = informationVO.getInsDate();
			String updDate_s = informationVO.getUpdDate();
			if(paymentDate_s != null && paymentDate_s != "" && paymentDate_s.length() > 19){
				informationVO.setPaymentDate(paymentDate_s.substring(0, 19));
			}
			if(insDate_s != null && insDate_s != "" && insDate_s.length() > 19){
				informationVO.setInsDate(insDate_s.substring(0, 19));
			}
			if(updDate_s != null && updDate_s != "" && updDate_s.length() > 19){
				informationVO.setUpdDate(updDate_s.substring(0, 19));
			}
		return informationVO;
	}
	
	@RequestMapping(value = "/selectByCondition", method = RequestMethod.POST)
	List<InformationVO> selectByCondition(InformationVO record) {
		record.setDelFlg("0");
		List<InformationVO> informationVOList = informationVOMapper.selectByCondition(record);
		for(int i=0;i<informationVOList.size();i++){
			InformationVO informationVO = informationVOList.get(i);
			String paymentDate_s = informationVO.getPaymentDate();
			String insDate_s = informationVO.getInsDate();
			String updDate_s = informationVO.getUpdDate();
			if(paymentDate_s != null && paymentDate_s != "" && paymentDate_s.length() > 19){
				informationVO.setPaymentDate(paymentDate_s.substring(0, 19));
			}
			if(insDate_s != null && insDate_s != "" && insDate_s.length() > 19){
				informationVO.setInsDate(insDate_s.substring(0, 19));
			}
			if(updDate_s != null && updDate_s != "" && updDate_s.length() > 19){
				informationVO.setUpdDate(updDate_s.substring(0, 19));
			}
		}
		return informationVOList;
	}
	
	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	List<InformationVO> selectAll() {
		InformationVO record = new InformationVO();
		record.setManagerStatus("1");
//		String managerStatus = "1";
		List<InformationVO> informationVOList = informationVOMapper.selectAll(record);
		for(InformationVO informationVO : informationVOList){
			String paymentDate_s = informationVO.getPaymentDate();
			String insDate_s = informationVO.getInsDate();
			String updDate_s = informationVO.getUpdDate();
			if(paymentDate_s != null && paymentDate_s != "" && paymentDate_s.length() > 19){
				informationVO.setPaymentDate(paymentDate_s.substring(0, 19));
			}
			if(insDate_s != null && insDate_s != "" && insDate_s.length() > 19){
				informationVO.setInsDate(insDate_s.substring(0, 19));
			}
			if(updDate_s != null && updDate_s != "" && updDate_s.length() > 19){
				informationVO.setUpdDate(updDate_s.substring(0, 19));
			}
		}
		return informationVOList;
	}
	
	@RequestMapping(value = "/updateByPrimaryKeySelective", method = RequestMethod.POST)
	String updateByPrimaryKeySelective(InformationVO record) {
		informationVOMapper.updateByPrimaryKeySelective(record);
		return "1";
	}

	/** 
	 * 
	 * @param record 
	 * @return record
	 * update
	 */
	@RequestMapping(value = "/updateByPrimaryKey", method = RequestMethod.POST)
	MessageVO updateByPrimaryKey(InformationVO record,UserVO user) {
		record.setUpdDate(sdf.format(new Date()));
		record.setUpdUser(user.getName());
		int code = informationVOMapper.updateByPrimaryKey(record);
		code = 1;
		MessageVO messageVO = new MessageVO();
		if(code ==1){
			messageVO.setCode("1");
			messageVO.setMessage("success");
		}else{
			messageVO.setCode("0");
			messageVO.setMessage("failed");
		}				
		return messageVO;
	}

	/** 
	 * 
	 * @param record 
	 * @return record
	 * approve
	 */
	@RequestMapping(value = "/approve", method = RequestMethod.POST)
	MessageVO approve(InformationVO record) {
		MessageVO messageVO = new MessageVO();  
		String managerStatus = record.getManagerStatus();
		if(managerStatus.equals("1")){
			informationVOMapper.updateByPrimaryKeySelective(record);
		}else{
			informationVOMapper.updateByPrimaryKeySelective(record);
		}		
		messageVO.setCode("1");
		messageVO.setMessage("success");
		return messageVO;
	}
	
	/**
	 * @author Administrator 获取绩效
	 *//*
	@RequestMapping(value = "/getPerformance", method = RequestMethod.GET)
	InformationVO getPerformance(String contract) {
		InformationVO information = informationVOMapper.getPerformance(contract);
		// int money = information.getMoney();

		return information;
	}

	*//**
	 * @author Administrator 更新利息
	 *//*
	@RequestMapping(value = "/getInterest", method = RequestMethod.POST)
	InformationVO updateInterest(String contract) {
		InformationVO information_before = informationVOMapper.getInterest(contract);
		int periods = information_before.getPeriods();
		BigDecimal rate = information_before.getRate();
		BigDecimal interest_all = rate.multiply(new BigDecimal(periods));
		BigDecimal interest_month = information_before.getInterestMonth();
		if (periods != 0) {
			interest_month = interest_all.divide(new BigDecimal(periods));
		}
		informationVOMapper.updateInterest(contract, interest_all, interest_month);
		InformationVO information_after = informationVOMapper.getInterest(contract);
		return information_after;
	}

	*//**
	 * @author Administrator 获取利息
	 *//*
	InformationVO getTnterest(String contract) {
		InformationVO information = informationVOMapper.getInterest(contract);
		return information;
	}

	*//**
	 * @author Administrator 获取银行信息
	 *//*
	@RequestMapping(value = "/getBank", method = RequestMethod.GET)
	InformationVO getBank(String contract) {
		InformationVO information = informationVOMapper.getBank(contract);
		return information;
	}*/
	
	/*private String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
		return sdf.format(new Date());
	}*/
}
