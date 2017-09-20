package com.bqhx.yyb.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bqhx.yyb.dao.InformationVOMapper;
import com.bqhx.yyb.vo.InformationVO;


/**
 * @author Administrator
 * InformationController
 */

@RestController
@RequestMapping("/")
public class InformationController {

	@Autowired
	private InformationVOMapper informationVOMapper;
	/**
	 * @author Administrator
	 * 获取绩效
	 */
	@RequestMapping(value = "/getPerformance", method = RequestMethod.GET)
	InformationVO getPerformance(String contract){
		InformationVO information = informationVOMapper.getPerformance(contract);
//		int money = information.getMoney();
		
		return information;
	}
	
	/**
	 * @author Administrator
	 * 更新利息      
	 */
	@RequestMapping(value = "/getInterest", method = RequestMethod.POST)
	InformationVO updateInterest(String contract){
		InformationVO information_before = informationVOMapper.getInterest(contract);
		int periods = information_before.getPeriods();
		BigDecimal rate = information_before.getRate();
		BigDecimal interest_all = rate.multiply(new BigDecimal(periods));
		BigDecimal interest_month = information_before.getInterestMonth();
		if(periods != 0){		
			interest_month = interest_all.divide(new BigDecimal(periods));
}
		informationVOMapper.updateInterest(contract, interest_all,interest_month);
		InformationVO information_after = informationVOMapper.getInterest(contract);
		return information_after;
	}
	
	/**
	 * @author Administrator
	 * 获取利息
	 */
	InformationVO getTnterest(String contract){
		InformationVO information = informationVOMapper.getInterest(contract);
		return information;
	}
	
	/**
	 * @author Administrator
	 * 获取银行信息
	 */
	@RequestMapping(value = "/getBank", method = RequestMethod.GET)
	InformationVO getBank(String contract){
		InformationVO information = informationVOMapper.getBank(contract);
		return information;
	}
}
