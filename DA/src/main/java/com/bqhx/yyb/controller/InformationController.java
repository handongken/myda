package com.bqhx.yyb.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bqhx.yyb.dao.InformationVOMapper;
import com.bqhx.yyb.vo.InformationVO;

/**
 * @author Administrator InformationController
 */

@RestController
@RequestMapping("/")
public class InformationController {

	@Autowired
	private InformationVOMapper informationVOMapper;

	@RequestMapping(value = "/deleteByPrimaryKey", method = RequestMethod.POST)
	InformationVO deleteByPrimaryKey(InformationVO record) {
		int code = informationVOMapper.deleteByPrimaryKey(record);
		code = 1;
		if(code == 1){
			record.setMessage("1");
		}else{
			record.setMessage("0");
		}
		return record;
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

	@RequestMapping(value = "/insertSelective", method = RequestMethod.POST)
	InformationVO insertSelective(InformationVO record) {
		int code = informationVOMapper.insertSelective(record);
		code = 1;
		if(code == 1){
			record.setMessage("1");
		}else{
			record.setMessage("0");
		}
		return record;
	}

	@RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.POST)
	List<InformationVO> selectByPrimaryKey(InformationVO record) {
		List<InformationVO> informationVOList = informationVOMapper.selectByPrimaryKey(record);
		return informationVOList;
	}
	
	@RequestMapping(value = "/updateByPrimaryKeySelective", method = RequestMethod.POST)
	InformationVO updateByPrimaryKeySelective(InformationVO record) {
		int code = informationVOMapper.updateByPrimaryKeySelective(record);
		code = 1;
		if(code == 1){
			record.setMessage("1");
		}else{
			record.setMessage("0");
		}
		return record;
	}

	@RequestMapping(value = "/updateByPrimaryKey", method = RequestMethod.POST)
	InformationVO updateByPrimaryKey(InformationVO record) {
		int code = informationVOMapper.updateByPrimaryKey(record);
		code = 1;
		if(code == 1){
			record.setMessage("1");
		}else{
			record.setMessage("0");
		}
		return record;
	}

	/**
	 * @author Administrator 获取绩效
	 */
	@RequestMapping(value = "/getPerformance", method = RequestMethod.GET)
	InformationVO getPerformance(String contract) {
		InformationVO information = informationVOMapper.getPerformance(contract);
		// int money = information.getMoney();

		return information;
	}

	/**
	 * @author Administrator 更新利息
	 */
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

	/**
	 * @author Administrator 获取利息
	 */
	InformationVO getTnterest(String contract) {
		InformationVO information = informationVOMapper.getInterest(contract);
		return information;
	}

	/**
	 * @author Administrator 获取银行信息
	 */
	@RequestMapping(value = "/getBank", method = RequestMethod.GET)
	InformationVO getBank(String contract) {
		InformationVO information = informationVOMapper.getBank(contract);
		return information;
	}
}
