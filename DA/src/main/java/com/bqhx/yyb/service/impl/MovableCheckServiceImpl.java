package com.bqhx.yyb.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bqhx.yyb.constant.Constant;
import com.bqhx.yyb.dao.MovableCheckMapper;
import com.bqhx.yyb.service.MovableCheckService;
import com.bqhx.yyb.util.DateUtil;
import com.bqhx.yyb.vo.ConditionVO;
import com.bqhx.yyb.vo.MovableCheckVO;

@Service("MovableCheckServiceImpl")
public class MovableCheckServiceImpl implements MovableCheckService {
	
	@Autowired
	private MovableCheckMapper movableCheckMapper;
	/**
	 * 插入移动支票表
	 * @param record
	 * insertCertificate
	 */
	@Override
	public void insertMovableCheck(ConditionVO condition) {

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
//		logger.info("失效日期: " + invalidDate.replaceAll("-", ""));
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
	@Override
	public List<MovableCheckVO> selectMovableCheckByCondition(ConditionVO condition) {
		List<MovableCheckVO> movableCheckList = movableCheckMapper.selectMovableCheckByCondition(condition);
		return movableCheckList;
	}

}
