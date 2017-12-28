package com.bqhx.yyb.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bqhx.yyb.constant.Constant;
import com.bqhx.yyb.constant.ContinueFlgEnum;
import com.bqhx.yyb.constant.StatusEnum;
import com.bqhx.yyb.dao.InformationHistoryMapper;
import com.bqhx.yyb.dao.InformationVOMapper;
import com.bqhx.yyb.dao.OrganizationMapper;
import com.bqhx.yyb.dao.TypeMapper;
import com.bqhx.yyb.service.InformationService;
import com.bqhx.yyb.util.ConditionUtil;
import com.bqhx.yyb.util.DateUtil;
import com.bqhx.yyb.vo.ConditionVO;
import com.bqhx.yyb.vo.DqVO;
import com.bqhx.yyb.vo.FgsVO;
import com.bqhx.yyb.vo.InformationHistoryVO;
import com.bqhx.yyb.vo.InformationVO;
import com.bqhx.yyb.vo.OrganizationConditionVO;
import com.bqhx.yyb.vo.OrganizationResultVO;
import com.bqhx.yyb.vo.ResultTypeVO;
import com.bqhx.yyb.vo.TypeVO;
import com.bqhx.yyb.vo.UserVO;
import com.bqhx.yyb.vo.YybVO;

@Service("InformationServiceImpl")
public class InformationServiceImpl implements InformationService {
	@Autowired
	private InformationVOMapper informationVOMapper;
	@Autowired
	private InformationHistoryMapper informationHistoryMapper;
	@Autowired
	private TypeMapper typeMapper;
	@Autowired
	private OrganizationMapper organizationMapper;
	
	@Override
	public ConditionVO selectByPrimaryKey(ConditionVO condition) {
		ConditionVO information = informationVOMapper.selectByPrimaryKey(condition);
		return information;
	}

	/**
	 * 插入
	 */
	@Override
	public int insertSelective(ConditionVO condition,UserVO user) {
		String insDate = DateUtil.formatDate(new Date(), Constant.PATTERN_HMS);
		if (insDate != "") {
			condition.setInsDate(insDate);
		}
		condition.setInsUser(user.getUserId());
		informationVOMapper.insertSelective(condition);
		
		return 1;
	}

	@Override
	public int insertInfoHistory(ConditionVO record) {
		informationHistoryMapper.insertInfoHistory(record);
		return 1;
	}

	/**
	 * 查询
	 */
	@Override
	public List<InformationVO> selectByCondition(ConditionVO condition,UserVO user) {
		List<InformationVO> infoList = informationVOMapper.selectByCondition(condition);
		if(infoList != null){
			for (int i = 0; i < infoList.size(); i++) {
				InformationVO info = infoList.get(i);
				//插入和更新时间
				String insDate = DateUtil.convertDate(info.getInsDate(), Constant.PATTERN_HMS);
				info.setInsDate(insDate);
				String updDate = DateUtil.convertDate(info.getUpdDate(), Constant.PATTERN_HMS);
				info.setUpdDate(updDate);
				//type
				String type = info.getType();
				TypeVO typeVO = typeMapper.selectTypeByPrimaryKey(type,Constant.FLAG_ZERO);
				String typeName = typeVO.getTypeName();
				if(typeName != null && typeName != ""){
					info.setType(typeName);
				}
				//status
				String status = info.getStatus();
				String statusValue = StatusEnum.getValue(status);
				if(statusValue != ""){
					info.setStatus(statusValue);
				}
				if(user != null){
					String typeId = user.getTypeId();
					if(typeId != null && !typeId.equals("") && !typeId.equals(Constant.MB)){
						//continueFlg
						String continueFlg = info.getContinueFlg();
						String continueFlgValue = ContinueFlgEnum.getValue(continueFlg);
						if(continueFlgValue != null && continueFlgValue != ""){
							info.setContinueFlg(continueFlgValue);
						}
					}
				}
				//架构信息显示name
				OrganizationConditionVO orcon = new OrganizationConditionVO();
				orcon.setVlevel(Constant.FLAG_ZERO);
				//syb
				if(info.getSyb() != null && !"".equals(info.getSyb()) && !"A001".equals(info.getSyb())){
					orcon.setD_ID(info.getSyb());
					OrganizationResultVO syb = organizationMapper.selectSybByCondition(orcon);
					if(syb != null){
						info.setSybname(syb.getDname());
//						informationVO.setSybManager(syb.getDmanager());
					}else{
						info.setSybname("无");
					}
				}else{
					orcon.setD_ID("A001");
					info.setSybname("无");
				}
				//dq
				if(info.getDq() != null && !"".equals(info.getDq()) && !"B001".equals(info.getDq())){
					orcon.setP_ID(info.getDq());
					DqVO dq = organizationMapper.selectDqByCondition(orcon);
					if(dq != null){
						info.setDqname(dq.getPname());
					}else{
						info.setDqname("无");
					}
				}else{
					orcon.setP_ID("B001");
					info.setDqname("无");
				}
				//fgs
				if(info.getFgs() != null && !"".equals(info.getFgs()) && !"C001".equals(info.getFgs())){
					orcon.setF_ID(info.getFgs());
					FgsVO fgs = organizationMapper.selectFgsByCondition(orcon);
					if(fgs != null){
						info.setFgsname(fgs.getFname());
					}else{
						info.setFgsname("无");
					}
				}else{
					orcon.setF_ID("C001");
					info.setFgsname("无");
				}
				//yyb
				if(info.getYyb() != null && !"".equals(info.getYyb()) && !"D001".equals(info.getYyb())){
					orcon.setY_ID(info.getYyb());
					YybVO yyb = organizationMapper.selectYybByCondition(orcon);	
					if(yyb != null){
						info.setYybname(yyb.getYname());
					}else{
						info.setYybname("无");
					}
				}else{
					orcon.setY_ID("D001");
					info.setYybname("无");
				}
			}
		}
		return infoList;
	}

	@Override
	public List<ResultTypeVO> selectHumanAndPerformanceByCondition(ConditionVO conditionVO,UserVO user) {
		ConditionVO condition = ConditionUtil.getConditionVOByRole(conditionVO,user);
		//>12500
		condition.setJxAchievement(new BigDecimal(12500.00));
		List<ResultTypeVO> infoList = informationVOMapper.selectHumanAndPerformanceByCondition(condition);
		if(infoList != null){
			for (int i = 0; i < infoList.size(); i++) {
				//insDate and updDate
				ResultTypeVO info = infoList.get(i);
				String insDate = DateUtil.convertDate(info.getInsDate(), Constant.PATTERN_HMS);
				info.setInsDate(insDate);
				String updDate = DateUtil.convertDate(info.getUpdDate(), Constant.PATTERN_HMS);
				info.setUpdDate(updDate);
				//架构信息显示name
				OrganizationConditionVO orcon = new OrganizationConditionVO();
				orcon.setVlevel(Constant.FLAG_ZERO);
				//syb
				if(info.getSyb() != null && !"".equals(info.getSyb()) && !"A001".equals(info.getSyb())){
					orcon.setD_ID(info.getSyb());
					OrganizationResultVO syb = organizationMapper.selectSybByCondition(orcon);
					if(syb != null){
						info.setSybname(syb.getDname());
//						informationVO.setSybManager(syb.getDmanager());
					}else{
						info.setSybname("无");
					}
				}else{
					orcon.setD_ID("A001");
					info.setSybname("无");
				}
				//dq
				if(info.getDq() != null && !"".equals(info.getDq()) && !"B001".equals(info.getDq())){
					orcon.setP_ID(info.getDq());
					DqVO dq = organizationMapper.selectDqByCondition(orcon);
					if(dq != null){
						info.setDqname(dq.getPname());
					}else{
						info.setDqname("无");
					}
				}else{
					orcon.setP_ID("B001");
					info.setDqname("无");
				}
				//fgs
				if(info.getFgs() != null && !"".equals(info.getFgs()) && !"C001".equals(info.getFgs())){
					orcon.setF_ID(info.getFgs());
					FgsVO fgs = organizationMapper.selectFgsByCondition(orcon);
					if(fgs != null){
						info.setFgsname(fgs.getFname());
					}else{
						info.setFgsname("无");
					}
				}else{
					orcon.setF_ID("C001");
					info.setFgsname("无");
				}
			}
		}
		return infoList;
	}

	@Override
	public int updateByPrimaryKeySelective(ConditionVO conditionVO,UserVO user) {
		String updDate = DateUtil.formatDate(new Date(), Constant.PATTERN_HMS);
		conditionVO.setUpdDate(updDate);
		conditionVO.setUpdUser(user.getUserId());
		informationVOMapper.updateByPrimaryKeySelective(conditionVO);
		return 1;
	}

	@Override
	public InformationVO selectByContract(ConditionVO condition) {
		InformationVO info = informationVOMapper.selectByContract(condition);
		//插入和更新时间
		String insDate = DateUtil.convertDate(info.getInsDate(), Constant.PATTERN_HMS);
		info.setInsDate(insDate);
		String updDate = DateUtil.convertDate(info.getUpdDate(), Constant.PATTERN_HMS);
		info.setUpdDate(updDate);
		//架构信息显示name
		OrganizationConditionVO orcon = new OrganizationConditionVO();
		orcon.setVlevel(Constant.FLAG_ZERO);
		//syb
		if(info.getSyb() != null && !"".equals(info.getSyb()) && !"A001".equals(info.getSyb())){
			orcon.setD_ID(info.getSyb());
			OrganizationResultVO syb = organizationMapper.selectSybByCondition(orcon);
			if(syb != null){
				info.setSybname(syb.getDname());
//				informationVO.setSybManager(syb.getDmanager());
			}else{
				info.setSybname("无");
			}
		}else{
			orcon.setD_ID("A001");
			info.setSybname("无");
		}
		//dq
		if(info.getDq() != null && !"".equals(info.getDq()) && !"B001".equals(info.getDq())){
			orcon.setP_ID(info.getDq());
			DqVO dq = organizationMapper.selectDqByCondition(orcon);
			if(dq != null){
				info.setDqname(dq.getPname());
			}else{
				info.setDqname("无");
			}
		}else{
			orcon.setP_ID("B001");
			info.setDqname("无");
		}
		//fgs
		if(info.getFgs() != null && !"".equals(info.getFgs()) && !"C001".equals(info.getFgs())){
			orcon.setF_ID(info.getFgs());
			FgsVO fgs = organizationMapper.selectFgsByCondition(orcon);
			if(fgs != null){
				info.setFgsname(fgs.getFname());
			}else{
				info.setFgsname("无");
			}
		}else{
			orcon.setF_ID("C001");
			info.setFgsname("无");
		}
		//yyb
		if(info.getYyb() != null && !"".equals(info.getYyb()) && !"D001".equals(info.getYyb())){
			orcon.setY_ID(info.getYyb());
			YybVO yyb = organizationMapper.selectYybByCondition(orcon);	
			if(yyb != null){
				info.setYybname(yyb.getYname());
			}else{
				info.setYybname("无");
			}
		}else{
			orcon.setY_ID("D001");
			info.setYybname("无");
		}
		return info;
	}

	@Override
	public int insertBatch(List<ConditionVO> conlist) {
		informationVOMapper.insertBatch(conlist);
		return 1;
	}

	@Override
	public List<InformationHistoryVO> selectInfoHistoryByCondition(ConditionVO condition) {
		List<InformationHistoryVO> infoHistoryList = informationHistoryMapper.selectInfoHistoryByCondition(condition);
		return infoHistoryList;
	}
	
}
