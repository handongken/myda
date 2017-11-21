package com.bqhx.yyb.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bqhx.yyb.constant.Constant;
import com.bqhx.yyb.dao.OrganizationMapper;
import com.bqhx.yyb.vo.DqVO;
import com.bqhx.yyb.vo.FgsVO;
import com.bqhx.yyb.vo.OrganizationConditionVO;
import com.bqhx.yyb.vo.OrganizationResultVO;
import com.bqhx.yyb.vo.TdVO;
import com.bqhx.yyb.vo.UserConditionVO;
import com.bqhx.yyb.vo.YybVO;

@RestController
@RequestMapping("/")
public class OrganizationController {
	@Autowired
	private OrganizationMapper organizationMapper;
	
	/**
	 * 根据条件查询organization关系
	 * 参数：当前用户'typeId':user.typeId,'sid':user.sid,'did':user.did,s'fid':user.fid,'yid':user.yid
	 */
	@RequestMapping(value = "/selectOrganizationByCondition", method = RequestMethod.POST)
	List<OrganizationResultVO> selectOrganizationByCondition(UserConditionVO userCondition){
		List<OrganizationResultVO> orList = new ArrayList<OrganizationResultVO>();
		//当前登录用户的typeid
		String typeId = userCondition.getTypeId();
		//条件
		OrganizationConditionVO orcondition = new OrganizationConditionVO();
		orcondition.setDelFlg(Constant.FLAG_ZERO);
		
		//用户属于总部(数据和管理员)
		if("1".equals(typeId) || "system".equals(typeId)){
			//所有事业部
			List<OrganizationResultVO> sybList = organizationMapper.selectAllsybOrganization(orcondition);
			if(sybList != null){
				return sybList;	
			}else{
				return new ArrayList<OrganizationResultVO>();
			}
		}//事业部内勤
		else if("10".equals(typeId)){
			String did = userCondition.getSid();
			orcondition.setD_ID(did);
			//所在事业部
			OrganizationResultVO syb = organizationMapper.selectSybByCondition(orcondition);
			if(syb != null){
				orList.add(syb);
			}else{
				OrganizationResultVO newsyb = new OrganizationResultVO();
				orList.add(newsyb);
			}
		}
		//大区内勤
		else if("11".equals(typeId)){
			String pid = userCondition.getDid();
			orcondition.setD_ID(userCondition.getSid());
			orcondition.setP_ID(pid);
			//大区
			DqVO dq = organizationMapper.selectDqByCondition(orcondition);
			if(dq != null){
				//大区list
				List<DqVO> dqList = new ArrayList<DqVO>();
				dqList.add(dq);
				//所在事业部
				orcondition.setD_ID(dq.getDid());
				OrganizationResultVO syb = organizationMapper.selectSybByCondition(orcondition);
				if(syb != null){
					syb.setDqList(dqList);
					orList.add(syb);
				}else{
					OrganizationResultVO newsyb = new OrganizationResultVO();
					newsyb.setDqList(dqList);
					orList.add(newsyb);
				}
			}
		}//分公司内勤
		else if("12".equals(typeId)){
			String fid = userCondition.getFid();
			orcondition.setD_ID(userCondition.getSid());
			orcondition.setP_ID(userCondition.getDid());
			orcondition.setF_ID(fid);
			//分公司
			FgsVO fgs = organizationMapper.selectFgsByCondition(orcondition);
			if(fgs != null){
				//分公司list
				List<FgsVO> fgsList = new ArrayList<FgsVO>();
				fgsList.add(fgs);
				//所在大区
				orcondition.setP_ID(fgs.getPid());
				DqVO dq = organizationMapper.selectDqByCondition(orcondition);
				if(dq != null){
					List<DqVO> dqList = new ArrayList<DqVO>();
					dq.setFgsList(fgsList);
					dqList.add(dq);
					//所在事业部
					orcondition.setD_ID(dq.getDid());
					OrganizationResultVO syb = organizationMapper.selectSybByCondition(orcondition);
					if(syb != null){
						syb.setDqList(dqList);
						orList.add(syb);
					}//事业部为空
					else{
						OrganizationResultVO newsyb = new OrganizationResultVO();
						newsyb.setDqList(dqList);
						orList.add(newsyb);
					}
				}//大区为空
				else{
					OrganizationResultVO newsyb = new OrganizationResultVO();
					List<DqVO> newdqList = new ArrayList<DqVO>();
					DqVO newdq = new DqVO();
					newdq.setFgsList(fgsList);
					newdqList.add(newdq);
					newsyb.setDqList(newdqList);
					orList.add(newsyb);
				}
			}
		}//营业部内勤
		else if("13".equals(typeId)){
			String yid = userCondition.getYid();
			orcondition.setD_ID(userCondition.getSid());
			orcondition.setP_ID(userCondition.getDid());
			orcondition.setF_ID(userCondition.getFid());
			orcondition.setY_ID(yid);
			//营业部
			YybVO yyb = organizationMapper.selectYybByCondition(orcondition);
			if(yyb != null){
				List<YybVO> yybList = new ArrayList<YybVO>();
				yybList.add(yyb);
				//所在分公司
				orcondition.setF_ID(yyb.getFid());
				FgsVO fgs = organizationMapper.selectFgsByCondition(orcondition);
				if(fgs != null){
					List<FgsVO> fgsList = new ArrayList<FgsVO>();
					fgs.setYybList(yybList);
					fgsList.add(fgs);
					//所在大区
					orcondition.setP_ID(fgs.getPid());
					DqVO dq = organizationMapper.selectDqByCondition(orcondition);
					if(dq != null){
						List<DqVO> dqList = new ArrayList<DqVO>();
						dq.setFgsList(fgsList);
						dqList.add(dq);
						//所在事业部
						orcondition.setD_ID(dq.getDid());
						OrganizationResultVO syb = organizationMapper.selectSybByCondition(orcondition);
						if(syb != null){
							syb.setDqList(dqList);
							orList.add(syb);
						}//事业部为空
						else{
							OrganizationResultVO newsyb = new OrganizationResultVO();
							newsyb.setDqList(dqList);
							orList.add(newsyb);
						}
					}//大区为空
					else{
						OrganizationResultVO newsyb = new OrganizationResultVO();
						List<DqVO> newdqList = new ArrayList<DqVO>();
						DqVO newdq = new DqVO();
						newdq.setFgsList(fgsList);
						newdqList.add(newdq);
						newsyb.setDqList(newdqList);
						orList.add(newsyb);
					}
				}//分公司为空
				else{
					OrganizationResultVO newsyb = new OrganizationResultVO();
					List<DqVO> newdqList = new ArrayList<DqVO>();
					DqVO newdq = new DqVO();
					FgsVO newfgs = new FgsVO();
					List<FgsVO> newfgsList = new ArrayList<FgsVO>();
					newfgs.setYybList(yybList);
					newfgsList.add(newfgs);
					newdq.setFgsList(newfgsList);
					newdqList.add(newdq);
					newsyb.setDqList(newdqList);
					orList.add(newsyb);
				}
			}
		}//团队
		else if("4".equals(typeId)){
			String tid = userCondition.getTid();
			orcondition.setD_ID(userCondition.getSid());
			orcondition.setP_ID(userCondition.getDid());
			orcondition.setF_ID(userCondition.getFid());
			orcondition.setY_ID(userCondition.getYid());
			orcondition.setT_ID(tid);
			//团队
			TdVO td = organizationMapper.selectTdByCondition(orcondition);
			if(td != null){
				List<TdVO> tdList = new ArrayList<TdVO>();
				tdList.add(td);
				//所在营业部
				orcondition.setY_ID(td.getYid());
				YybVO yyb = organizationMapper.selectYybByCondition(orcondition);
				if(yyb != null){
					List<YybVO> yybList = new ArrayList<YybVO>();
					yyb.setTlist(tdList);
					yybList.add(yyb);
					//所在分公司
					orcondition.setF_ID(yyb.getFid());
					FgsVO fgs = organizationMapper.selectFgsByCondition(orcondition);
					if(fgs != null){
						List<FgsVO> fgsList = new ArrayList<FgsVO>();
						fgs.setYybList(yybList);
						fgsList.add(fgs);
						//所在大区
						orcondition.setP_ID(fgs.getPid());
						DqVO dq = organizationMapper.selectDqByCondition(orcondition);
						if(dq != null){
							List<DqVO> dqList = new ArrayList<DqVO>();
							dq.setFgsList(fgsList);
							dqList.add(dq);
							//所在事业部
							orcondition.setD_ID(dq.getDid());
							OrganizationResultVO syb = organizationMapper.selectSybByCondition(orcondition);
							if(syb != null){
								syb.setDqList(dqList);
								orList.add(syb);
							}//事业部为空
							else{
								OrganizationResultVO newsyb = new OrganizationResultVO();
								newsyb.setDqList(dqList);
								orList.add(newsyb);
							}
						}//大区为空
						else{
							OrganizationResultVO newsyb = new OrganizationResultVO();
							List<DqVO> newdqList = new ArrayList<DqVO>();
							DqVO newdq = new DqVO();
							newdq.setFgsList(fgsList);
							newdqList.add(newdq);
							newsyb.setDqList(newdqList);
							orList.add(newsyb);
						}
					}//分公司为空
					else{
						OrganizationResultVO newsyb = new OrganizationResultVO();
						List<DqVO> newdqList = new ArrayList<DqVO>();
						DqVO newdq = new DqVO();
						FgsVO newfgs = new FgsVO();
						List<FgsVO> newfgsList = new ArrayList<FgsVO>();
						newfgs.setYybList(yybList);
						newfgsList.add(newfgs);
						newdq.setFgsList(newfgsList);
						newdqList.add(newdq);
						newsyb.setDqList(newdqList);
						orList.add(newsyb);
					}
				}//营业部为空
				else{
					OrganizationResultVO newsyb = new OrganizationResultVO();
					List<DqVO> newdqList = new ArrayList<DqVO>();
					DqVO newdq = new DqVO();
					FgsVO newfgs = new FgsVO();
					List<FgsVO> newfgsList = new ArrayList<FgsVO>();
					YybVO newyyb = new YybVO();
					List<YybVO> newyybList = new ArrayList<YybVO>();
					newyyb.setTlist(tdList);
					newyybList.add(newyyb);
					newfgs.setYybList(newyybList);
					newfgsList.add(newfgs);
					newdq.setFgsList(newfgsList);
					newdqList.add(newdq);
					newsyb.setDqList(newdqList);
					orList.add(newsyb);
				}
			}
		}
		return orList;
	}
	
	/***
	 * 获取某事业部下所有大区
	 */
	@RequestMapping(value = "/getDqListByCondition", method = RequestMethod.POST)
	List<DqVO> getDqListByCondition(OrganizationConditionVO orConditionVO){
		//事业部
		if(orConditionVO.getD_ID() != null && !"".equals(orConditionVO.getD_ID())){
			List<DqVO> dqList = organizationMapper.selectAlldqOrganization(orConditionVO);
			return dqList;
		}else{
			return new ArrayList<DqVO>();
		}
	}
	
	/***
	 * 获取某大区下所有分公司
	 */
	@RequestMapping(value = "/getFgsListByCondition", method = RequestMethod.POST)
	List<FgsVO> getFgsListByCondition(OrganizationConditionVO orConditionVO){
		//大区
		if(orConditionVO.getP_ID() != null && !"".equals(orConditionVO.getP_ID())){
			List<FgsVO> fgsList = organizationMapper.selectAllfgsOrganization(orConditionVO);
			return fgsList;
		}else{
			return new ArrayList<FgsVO>();
		}
	}
	
	/***
	 * 获取某分公司下所有营业部
	 */
	@RequestMapping(value = "/getYybListByCondition", method = RequestMethod.POST)
	List<YybVO> getYybListByCondition(OrganizationConditionVO orConditionVO){
		//分公司
		if(orConditionVO.getF_ID() != null && !"".equals(orConditionVO.getF_ID())){
			List<YybVO> yybList = organizationMapper.selectAllyybOrganization(orConditionVO);
			return yybList;
		}else{
			return new ArrayList<YybVO>();
		}
	}
}
