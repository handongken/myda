package com.bqhx.yyb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bqhx.yyb.constant.Constant;
import com.bqhx.yyb.service.InformationService;
import com.bqhx.yyb.service.OrganizationService;
import com.bqhx.yyb.vo.ConditionVO;
import com.bqhx.yyb.vo.DqVO;
import com.bqhx.yyb.vo.FgsVO;
import com.bqhx.yyb.vo.InformationHistoryVO;
import com.bqhx.yyb.vo.OrganizationConditionVO;
import com.bqhx.yyb.vo.OrganizationResultVO;
import com.bqhx.yyb.vo.YybVO;
@RestController
@RequestMapping("/")
public class InformationHistoryController {
	@Autowired
	@Qualifier("InformationServiceImpl") 
	private InformationService informationService;
	@Autowired
	@Qualifier("OrganizationServiceImpl") 
	private OrganizationService organizationService;
	/**
	 * 根据条件查询还本信息
	 * @param conditionVO
	 * @return InformationVOList
	 */
	@RequestMapping(value = "/selectInfoHistoryByCondition", method = RequestMethod.POST)
	List<InformationHistoryVO> selectInfoHistoryByCondition(ConditionVO condition){
		List<InformationHistoryVO> infoHistoryList = informationService.selectInfoHistoryByCondition(condition);
		for (int i = 0; i < infoHistoryList.size(); i++) {
			InformationHistoryVO infoHistory = infoHistoryList.get(i);
			//架构信息显示name
			OrganizationConditionVO orcon = new OrganizationConditionVO();
			orcon.setVlevel(Constant.FLAG_ZERO);
			//syb
			if(infoHistory.getSyb() != null && !"".equals(infoHistory.getSyb()) && !"A001".equals(infoHistory.getSyb())){
				orcon.setD_ID(infoHistory.getSyb());
				OrganizationResultVO syb = organizationService.selectSybByCondition(orcon);
				if(syb != null){
					infoHistory.setSybname(syb.getDname());
//					informationVO.setSybManager(syb.getDmanager());
				}else{
					infoHistory.setSybname("");
				}
			}else{
				orcon.setD_ID("A001");
				infoHistory.setSybname("");
			}
			//dq
			if(infoHistory.getDq() != null && !"".equals(infoHistory.getDq()) && !"B001".equals(infoHistory.getDq())){
				orcon.setP_ID(infoHistory.getDq());
				DqVO dq = organizationService.selectDqByCondition(orcon);
				if(dq != null){
					infoHistory.setDqname(dq.getPname());
				}else{
					infoHistory.setDqname("");
				}
			}else{
				orcon.setP_ID("B001");
				infoHistory.setDqname("");
			}
			//fgs
			if(infoHistory.getFgs() != null && !"".equals(infoHistory.getFgs()) && !"C001".equals(infoHistory.getFgs())){
				orcon.setF_ID(infoHistory.getFgs());
				FgsVO fgs = organizationService.selectFgsByCondition(orcon);
				if(fgs != null){
					infoHistory.setFgsname(fgs.getFname());
				}else{
					infoHistory.setFgsname("");
				}
			}else{
				orcon.setF_ID("C001");
				infoHistory.setFgsname("");
			}
			//yyb
			if(infoHistory.getYyb() != null && !"".equals(infoHistory.getYyb()) && !"D001".equals(infoHistory.getYyb())){
				orcon.setY_ID(infoHistory.getYyb());
				YybVO yyb = organizationService.selectYybByCondition(orcon);	
				if(yyb != null){
					infoHistory.setYybname(yyb.getYname());
				}else{
					infoHistory.setYybname("");
				}
			}else{
				orcon.setY_ID("D001");
				infoHistory.setYybname("");
			}
		}
		return infoHistoryList;
	}
	
}
