package com.bqhx.yyb.controller;

import java.math.BigDecimal;
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
import com.bqhx.yyb.dao.MovableCheckMapper;
import com.bqhx.yyb.dao.OrganizationMapper;
import com.bqhx.yyb.dao.PrincipalMapper;
import com.bqhx.yyb.dao.TypeMapper;
import com.bqhx.yyb.util.ConditionUtil;
import com.bqhx.yyb.util.DateUtil;
import com.bqhx.yyb.vo.CertificateVO;
import com.bqhx.yyb.vo.ConditionVO;
import com.bqhx.yyb.vo.DqVO;
import com.bqhx.yyb.vo.FgsVO;
import com.bqhx.yyb.vo.InformationVO;
import com.bqhx.yyb.vo.MessageVO;
import com.bqhx.yyb.vo.MovableCheckVO;
import com.bqhx.yyb.vo.OrganizationConditionVO;
import com.bqhx.yyb.vo.OrganizationResultVO;
import com.bqhx.yyb.vo.PrincipalVO;
import com.bqhx.yyb.vo.ResultTypeVO;
import com.bqhx.yyb.vo.TypeVO;
import com.bqhx.yyb.vo.UserVO;
import com.bqhx.yyb.vo.YybVO;


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
	@Autowired
	private MovableCheckMapper movableCheckMapper;
	@Autowired
	private PrincipalMapper principalMapper;
	@Autowired
	private OrganizationMapper organizationMapper;
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
	MessageVO insertSelective(ConditionVO condition, UserVO user) {
		MessageVO messageVO = new MessageVO();
		InformationVO informationVO = informationVOMapper.selectByPrimaryKey(condition);
		if (informationVO != null) {
			messageVO.setCode(Constant.FLAG_ZERO);
			messageVO.setMessage(Constant.FAILED);
		} else {
			String insDate = DateUtil.formatDate(new Date(), Constant.PATTERN_HMS);
			if (insDate != null && insDate != "") {
				condition.setInsDate(insDate);
			}
			condition.setInsUser(user.getName());
			informationVOMapper.insertSelective(condition);
			//插入付息表
			insertCertificate(condition);
			//插入还本表
			insertPrincipal(condition);
			messageVO.setCode(Constant.FLAG_ONE);
			messageVO.setMessage(Constant.SUCCESS);
		}
		return messageVO;
	}

	@RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.POST)
	InformationVO selectByPrimaryKey(ConditionVO condition) throws ParseException {
		InformationVO informationVO = informationVOMapper.selectByPrimaryKey(condition);
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
	List<InformationVO> selectByCondition(ConditionVO conditionVO, UserVO user) {
		ConditionVO condition = ConditionUtil.getConditionVOByRole(conditionVO,user);
		List<InformationVO> informationVOList = informationVOMapper.selectByCondition(condition);
		if (informationVOList != null) {
			for (int i = 0; i < informationVOList.size(); i++) {
				InformationVO informationVO = informationVOList.get(i);
				//插入和更新时间
				String insDate = DateUtil.convertDate(informationVO.getInsDate(), Constant.PATTERN_HMS);
				String updDate = DateUtil.convertDate(informationVO.getUpdDate(), Constant.PATTERN_HMS);
				if (insDate != null && insDate != "") {
					informationVO.setInsDate(insDate);
				}
				if (updDate != null && updDate != "") {
					informationVO.setUpdDate(updDate);
				}
				//架构信息显示name
				OrganizationConditionVO orcon = new OrganizationConditionVO();
				orcon.setDelFlg(Constant.FLAG_ZERO);
				//syb
				if(informationVO.getSyb() != null && !"".equals(informationVO.getSyb()) && !"A001".equals(informationVO.getSyb())){
					orcon.setD_ID(informationVO.getSyb());
					OrganizationResultVO syb = organizationMapper.selectSybByCondition(orcon);
					if(syb != null){
						informationVO.setSybname(syb.getDname());
//						informationVO.setSybManager(syb.getDmanager());
					}else{
						informationVO.setSybname("");
					}
				}else{
					orcon.setD_ID("A001");
					informationVO.setSybname("");
				}
				//dq
				if(informationVO.getDq() != null && !"".equals(informationVO.getDq()) && !"B001".equals(informationVO.getDq())){
					orcon.setP_ID(informationVO.getDq());
					DqVO dq = organizationMapper.selectDqByCondition(orcon);
					if(dq != null){
						informationVO.setDqname(dq.getPname());
					}else{
						informationVO.setDqname("");
					}
				}else{
					orcon.setP_ID("B001");
					informationVO.setDqname("");
				}
				//fgs
				if(informationVO.getFgs() != null && !"".equals(informationVO.getFgs()) && !"C001".equals(informationVO.getFgs())){
					orcon.setF_ID(informationVO.getFgs());
					FgsVO fgs = organizationMapper.selectFgsByCondition(orcon);
					if(fgs != null){
						informationVO.setFgsname(fgs.getFname());
					}else{
						informationVO.setFgsname("");
					}
				}else{
					orcon.setF_ID("C001");
					informationVO.setFgsname("");
				}
				//yyb
				if(informationVO.getYyb() != null && !"".equals(informationVO.getYyb()) && !"D001".equals(informationVO.getYyb())){
					orcon.setY_ID(informationVO.getYyb());
					YybVO yyb = organizationMapper.selectYybByCondition(orcon);	
					if(yyb != null){
						informationVO.setYybname(yyb.getYname());
					}else{
						informationVO.setYybname("");
					}
				}else{
					orcon.setY_ID("D001");
					informationVO.setYybname("");
				}
			}
		}
		return informationVOList;
	}
	/**
	 * 
	 * 人力与业绩
	 */
	@RequestMapping(value = "/selectHumanAndPerformanceByCondition", method = RequestMethod.POST)
	List<ResultTypeVO> selectHumanAndPerformanceByCondition(ConditionVO conditionVO, UserVO user) {
		ConditionVO condition = ConditionUtil.getConditionVOByRole(conditionVO,user);
		condition.setJxAchievement(new BigDecimal(12500.00));
		List<ResultTypeVO> informationVOList = informationVOMapper.selectHumanAndPerformanceByCondition(condition);
		if (informationVOList != null) {
			for (int i = 0; i < informationVOList.size(); i++) {
               //insDate and updDate
				ResultTypeVO informationVO = informationVOList.get(i);
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
	 * 插入付息凭证表
	 * @param record
	 * insertCertificate
	 */
	void insertCertificate(ConditionVO condition){
		CertificateVO certificateVO = new CertificateVO();
		String startDate = condition.getStartDate();
		TypeVO typeVO = typeMapper.selectTypeByPrimaryKey(condition.getType());
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
		insertMovableCheck(condition);
	}
	
	/**
	 * 插入还本信息表
	 * @param record
	 * insertCertificate
	 */
	void insertPrincipal(ConditionVO condition){
		PrincipalVO principalVO = new PrincipalVO();
		principalVO.setContract(condition.getContract());
		principalVO.setMoney(condition.getMoney());
		TypeVO typeVO = typeMapper.selectTypeByPrimaryKey(condition.getType());
		principalVO.setTypeName(typeVO.getTypeName());
		principalVO.setLcManager(condition.getLcManager());
		principalVO.setTmanager(condition.getTmanager());
		principalVO.setYyb(condition.getYyb());
		principalVO.setYybManager(condition.getYybManager());
		principalVO.setFgs(condition.getFgs());
		principalVO.setFgsManager(condition.getFgsManager());
		principalVO.setDq(condition.getDq());
		principalVO.setDqManager(condition.getDqManager());
		principalVO.setSyb(condition.getSyb());
		principalVO.setSybManager(condition.getSybManager());
		principalVO.setStartDate(condition.getStartDate());
		principalVO.setEndDate(condition.getEndDate());
		principalVO.setTenderName(condition.getTenderName());
		principalMapper.insertPrincipal(principalVO);
		//插入移动支票表
		condition.setPayFlg(Constant.FLAG_ZERO);
		insertMovableCheck(condition);
	}
	
	/**
	 * 插入移动支票表
	 * @param record
	 * insertCertificate
	 */
	void insertMovableCheck(ConditionVO condition){
		MovableCheckVO movableCheckVO = new MovableCheckVO();
		
		String contract = condition.getContract();
		String payFlg = condition.getPayFlg();
		String startDate = condition.getStartDate();
		String inCardNo = condition.getInCardNo();
		String inCardName = condition.getInCardName();
		String inBranch = condition.getInBank();
		String cardLine = condition.getCardLine();
		
		movableCheckVO.setContract(contract);
		movableCheckVO.setPayFlg(payFlg);
		movableCheckVO.setStartDate(startDate);
		movableCheckVO.setInCardNo(inCardNo);
		movableCheckVO.setInCardName(inCardName);
		movableCheckVO.setInBranch(inBranch);
		movableCheckVO.setCardLine(cardLine);
		//付方账号
		movableCheckVO.setCardNo(Constant.CARDNO);
		//金额上限&附言
		String postscript = "";
		if(payFlg.equals("1")){//付息
			movableCheckVO.setAmountLimit(condition.getInterestMonth());
			postscript = condition.getBeginDate() + "-" + condition.getTerminateDate() + Constant.PROFIT;
		}else{//还本
			movableCheckVO.setAmountLimit(new BigDecimal(condition.getMoney()));
			postscript = Constant.POSTSCRIPT + contract;
		}
		movableCheckVO.setPostscript(postscript);
		//生效日期
		movableCheckVO.setEffectiveDates(startDate.replaceAll("-", ""));
		//失效日期
		String invalidDate = DateUtil.convertDay(startDate, +Constant.INVALIDTIME);
		System.out.println("失效日期: " + invalidDate.replaceAll("-", ""));
		movableCheckVO.setInvalidDates(invalidDate.replaceAll("-", ""));
		//支票权限
		movableCheckVO.setCheckAuthority(Constant.CHECKAUTHORITY);
		//授权使用人
		movableCheckVO.setAuthorizedUser(Constant.AUTHORIZEDUSER);
		//收方信息填写类型
		movableCheckVO.setReceiverType(Constant.RECIEVERTYPE);
		//汇路类型
		if(inBranch.equals("招商银行")){
			movableCheckVO.setRemitType(Constant.REMITTYPEMERCHANTSBANK);
		}else if(movableCheckVO.getAmountLimit() != null){
			if(movableCheckVO.getAmountLimit().compareTo(Constant.AMOUNTLIMIT) <= 0){
				movableCheckVO.setRemitType(Constant.REMITTYPEREALTIME);
			}else{
				movableCheckVO.setRemitType(Constant.REMITTYPECOMMON);
			}
		}
		//收方行地址
		String inCardAddress = condition.getCardProvince() + condition.getCardCity();
		movableCheckVO.setInCardAddress(inCardAddress);
		movableCheckMapper.insertMovableCheck(movableCheckVO);
	}
	
}
