package com.bqhx.yyb.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bqhx.yyb.constant.Constant;
import com.bqhx.yyb.constant.TypeEnum;
import com.bqhx.yyb.dao.InformationVOMapper;
import com.bqhx.yyb.util.DateUtil;
import com.bqhx.yyb.vo.ConditionVO;
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

	/**
	 * 
	 * @param record
	 * @return messageVO delete
	 */
	@RequestMapping(value = "/deleteByPrimaryKey", method = RequestMethod.POST)
	MessageVO deleteByPrimaryKey(InformationVO record) {
		record.setDelFlg(Constant.FLAG_ONE);
		String code = updateByPrimaryKeySelective(record);
		MessageVO messageVO = new MessageVO();
		if (code.equals("1")) {
			messageVO.setCode(code);
			messageVO.setMessage(Constant.SUCCESS);
		} else {
			messageVO.setCode(Constant.FLAG_ZERO);
			messageVO.setMessage(Constant.FAILED);
		}
		return messageVO;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	InformationVO insert(InformationVO record) {
		int code = informationVOMapper.insert(record);
		code = Integer.parseInt(Constant.FLAG_ONE);
		if (code == 1) {
			record.setMessage(Constant.FLAG_ONE);
		} else {
			record.setMessage(Constant.FLAG_ZERO);
		}
		return record;
	}

	/**
	 * 
	 * @param record
	 * @return messageVO insert
	 */
	@RequestMapping(value = "/insertSelective", method = RequestMethod.POST)
	MessageVO insertSelective(InformationVO record, UserVO user) {
		MessageVO messageVO = new MessageVO();
		InformationVO informationVO = informationVOMapper.selectByPrimaryKey(record);
		if (informationVO != null) {
			messageVO.setCode(Constant.FLAG_ZERO);
			messageVO.setMessage(Constant.FAILED);
		} else {
			String insDate = DateUtil.formatDate(new Date(), Constant.PATTERN_HMS);
			if (insDate != null && insDate != "") {
				record.setInsDate(insDate);
			}
			record.setInsUser(user.getName());
			informationVOMapper.insertSelective(record);
			messageVO.setCode(Constant.FLAG_ONE);
			messageVO.setMessage(Constant.SUCCESS);
		}
		return messageVO;
	}

	@RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.POST)
	InformationVO selectByPrimaryKey(InformationVO record) throws ParseException {
		InformationVO informationVO = informationVOMapper.selectByPrimaryKey(record);
		String insDate = DateUtil.convertDate(informationVO.getInsDate(), Constant.PATTERN_HMS);
		String updDate = DateUtil.convertDate(informationVO.getUpdDate(), Constant.PATTERN_HMS);
		if (insDate != null && insDate != "") {
			informationVO.setInsDate(insDate);
		}
		if (updDate != null && updDate != "") {
			informationVO.setUpdDate(updDate);
		}
		return informationVO;
	}

	@RequestMapping(value = "/selectByCondition", method = RequestMethod.POST)
	List<InformationVO> selectByCondition(ConditionVO condition) {
		condition.setDelFlg(Constant.FLAG_ZERO);
		List<InformationVO> informationVOList = informationVOMapper.selectByCondition(condition);
		if (informationVOList != null) {
			for (int i = 0; i < informationVOList.size(); i++) {

				InformationVO informationVO = informationVOList.get(i);
				String insDate = DateUtil.convertDate(informationVO.getInsDate(), Constant.PATTERN_HMS);
				String updDate = DateUtil.convertDate(informationVO.getUpdDate(), Constant.PATTERN_HMS);

				if (insDate != null && insDate != "") {
					informationVO.setInsDate(insDate);
				}
				if (updDate != null && updDate != "") {
					informationVO.setUpdDate(updDate);
				}
			}
		}
		TypeEnum t = new TypeEnum();
		return informationVOList;
	}

	@RequestMapping(value = "/updateByPrimaryKeySelective", method = RequestMethod.POST)
	String updateByPrimaryKeySelective(InformationVO record) {
		informationVOMapper.updateByPrimaryKeySelective(record);
		return Constant.FLAG_ONE;
	}

	/**
	 * 
	 * @param record
	 * @return record update
	 */
	@RequestMapping(value = "/updateByPrimaryKey", method = RequestMethod.POST)
	MessageVO updateByPrimaryKey(InformationVO record, UserVO user) {
		String updDate = DateUtil.formatDate(new Date(), Constant.PATTERN_HMS);
		if (updDate != null && updDate != "") {
			record.setUpdDate(updDate);
		}
		record.setUpdUser(user.getName());
		int code = informationVOMapper.updateByPrimaryKey(record);
		code = 1;
		MessageVO messageVO = new MessageVO();
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
	MessageVO approve(InformationVO record) {
		MessageVO messageVO = new MessageVO();
		String managerStatus = record.getManagerStatus();
		if (managerStatus.equals("1")) {
			informationVOMapper.updateByPrimaryKeySelective(record);
		} else {
			informationVOMapper.updateByPrimaryKeySelective(record);
		}
		messageVO.setCode(Constant.FLAG_ONE);
		messageVO.setMessage(Constant.SUCCESS);
		return messageVO;
	}

	/**
	 * @author Administrator 获取绩效
	 */
	/*
	 * @RequestMapping(value = "/getPerformance", method = RequestMethod.GET)
	 * InformationVO getPerformance(String contract) { InformationVO information
	 * = informationVOMapper.getPerformance(contract); // int money =
	 * information.getMoney();
	 * 
	 * return information; }
	 * 
	 *//**
		 * @author Administrator 更新利息
		 */
	/*
	 * @RequestMapping(value = "/getInterest", method = RequestMethod.POST)
	 * InformationVO updateInterest(String contract) { InformationVO
	 * information_before = informationVOMapper.getInterest(contract); int
	 * periods = information_before.getPeriods(); BigDecimal rate =
	 * information_before.getRate(); BigDecimal interest_all = rate.multiply(new
	 * BigDecimal(periods)); BigDecimal interest_month =
	 * information_before.getInterestMonth(); if (periods != 0) { interest_month
	 * = interest_all.divide(new BigDecimal(periods)); }
	 * informationVOMapper.updateInterest(contract, interest_all,
	 * interest_month); InformationVO information_after =
	 * informationVOMapper.getInterest(contract); return information_after; }
	 * 
	 *//**
		 * @author Administrator 获取利息
		 */
	/*
	 * InformationVO getTnterest(String contract) { InformationVO information =
	 * informationVOMapper.getInterest(contract); return information; }
	 * 
	 *//**
		 * @author Administrator 获取银行信息
		 *//*
		 * @RequestMapping(value = "/getBank", method = RequestMethod.GET)
		 * InformationVO getBank(String contract) { InformationVO information =
		 * informationVOMapper.getBank(contract); return information; }
		 */

	/*
	 * private String getDate() { SimpleDateFormat sdf = new
	 * SimpleDateFormat("yyyy-MM-dd hh:MM:ss"); return sdf.format(new Date()); }
	 */
}
