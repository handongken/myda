package com.bqhx.yyb.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bqhx.yyb.constant.Constant;
import com.bqhx.yyb.dao.CertificateMapper;
import com.bqhx.yyb.dao.InformationVOMapper;
import com.bqhx.yyb.dao.TypeMapper;
import com.bqhx.yyb.util.DateUtil;
import com.bqhx.yyb.vo.CertificateVO;
import com.bqhx.yyb.vo.ConditionVO;
import com.bqhx.yyb.vo.InformationVO;
import com.bqhx.yyb.vo.MessageVO;
import com.bqhx.yyb.vo.TypeVO;
import com.bqhx.yyb.vo.UserVO;


/**
 * @author Administrator InformationController
 */

@RestController
@RequestMapping("/")
public class InformationController {

	@Autowired
	private InformationVOMapper informationVOMapper;
	@Autowired
	private CertificateMapper certificateMapper;
	@Autowired
	private TypeMapper typeMapper;
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
			insertCertificate(record);
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
	 * 
	 * @param record
	 * @return messageVO insert
	 */
	void insertCertificate(InformationVO record){
		CertificateVO certificateVO = new CertificateVO();
		String startDate = record.getStartDate();
		TypeVO typeVO = typeMapper.selectTypeByPrimaryKey(record.getType());
		String typeName = typeVO.getTypeName();
		int returnInterval = typeVO.getReturnInterval();
		String endDate = DateUtil.getEndDate(startDate,Constant.ONE);
		String beginDate = DateUtil.getBeginDate(returnInterval, startDate);
		certificateVO.setStartDate(startDate);
		certificateVO.setEndDate(endDate);
		certificateVO.setBeginDate(beginDate);
		certificateVO.setContract(record.getContract());
		certificateVO.setInBank(record.getInBank());
		certificateVO.setInCardName(record.getInCardName());
		certificateVO.setInCardNo(record.getInCardNo());
		certificateVO.setInterestMonth(record.getInterestMonth());
		certificateVO.setMoney(record.getMoney());
		certificateVO.setTypeName(typeName);
		certificateVO.setReturnInterval(returnInterval);
		certificateMapper.insertCertificate(certificateVO);
	}
	
}
