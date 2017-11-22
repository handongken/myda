package com.bqhx.yyb.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bqhx.yyb.constant.Constant;
import com.bqhx.yyb.dao.OrganizationMapper;
import com.bqhx.yyb.dao.UserMapper;
import com.bqhx.yyb.util.DateUtil;
import com.bqhx.yyb.vo.DqVO;
import com.bqhx.yyb.vo.FgsVO;
import com.bqhx.yyb.vo.MessageVO;
import com.bqhx.yyb.vo.OrganizationConditionVO;
import com.bqhx.yyb.vo.OrganizationResultVO;
import com.bqhx.yyb.vo.TdVO;
import com.bqhx.yyb.vo.UserConditionVO;
import com.bqhx.yyb.vo.UserVO;
import com.bqhx.yyb.vo.YybVO;

@CrossOrigin
@RestController
@RequestMapping("/")
public class UserController {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private OrganizationMapper organizationMapper;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	List<UserVO> home(UserConditionVO condition) {
		List<UserVO> list = userMapper.selectUserByCondition(condition);
		return list;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	UserVO login(UserConditionVO condition) {
		UserVO vo = userMapper.selectUserByPrimaryKey(condition);
		if(vo == null) {
			vo = new UserVO();
			vo.setMessage("用户不存在");
		} else if(!condition.getPassword().equals(vo.getPassword())) {
			vo = new UserVO();
			vo.setMessage("密码错误");
		}
		
		return vo;
	}
	
	@RequestMapping(value = "/insertUserSelective", method = RequestMethod.POST)
	MessageVO insertUserSelective(UserConditionVO condition, UserVO user){
		MessageVO messageVO = new MessageVO();
		UserVO userVO = userMapper.selectUserByPrimaryKey(condition);
		if (userVO != null) {
			messageVO.setCode(Constant.FLAG_ZERO);
			messageVO.setMessage(Constant.FAILED);
		} else {
			String insDate = DateUtil.formatDate(new Date(), Constant.PATTERN_HMS);
			if (insDate != null && insDate != "") {
				condition.setInsDate(insDate);
			}
			condition.setInsUser(user.getName());
			userMapper.insertUserSelective(condition);
			messageVO.setCode(Constant.FLAG_ONE);
			messageVO.setMessage(Constant.SUCCESS);
		}
		return messageVO;
	}
	
	@RequestMapping(value = "/updateUserByPrimaryKey", method = RequestMethod.POST)
	MessageVO updateUserByPrimaryKey(UserConditionVO condition){
		String updDate = DateUtil.formatDate(new Date(), Constant.PATTERN_HMS);
		if (updDate != null && updDate != "") {
			condition.setUpdDate(updDate);
		}
		condition.setUpdUser(condition.getName());
		int code = userMapper.updateUserByPrimaryKey(condition);
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

	@RequestMapping(value = "/deleteUserByPrimaryKey", method = RequestMethod.POST)
	MessageVO deleteUserByPrimaryKey(UserConditionVO condition){
		condition.setDelFlg(Constant.FLAG_ONE);
		MessageVO message = updateUserByPrimaryKeySelective(condition);
		return message;
	}
	
	@RequestMapping(value = "/updateUserByPrimaryKeySelective", method = RequestMethod.POST)
	MessageVO updateUserByPrimaryKeySelective(UserConditionVO condition) {
		String updDate = DateUtil.formatDate(new Date(), Constant.PATTERN_HMS);
		if (updDate != null && updDate != "") {
			condition.setUpdDate(updDate);
		}
		condition.setUpdUser(condition.getName());
		int code = userMapper.updateUserByPrimaryKeySelective(condition);
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
	
	@RequestMapping(value = "/selectUserByCondition", method = RequestMethod.POST)
	List<UserVO> selectUserByCondition(UserConditionVO condition){
		condition.setDelFlg(Constant.FLAG_ZERO);
		List<UserVO> userList = userMapper.selectUserByCondition(condition);
		for(UserVO userVO : userList){
			String insDate = DateUtil.convertDate(userVO.getInsDate(), Constant.PATTERN_HMS);
			if (insDate != null && insDate != "") {
				userVO.setInsDate(insDate);
			}
			OrganizationConditionVO organizationConditionVO = new OrganizationConditionVO();
			organizationConditionVO.setDelFlg(Constant.FLAG_ZERO);
			organizationConditionVO.setVlevel(Constant.FLAG_ZERO);
			//syb
			if(userVO.getSid() != null && !"".equals(userVO.getSid()) && !"A001".equals(userVO.getSid())){
				organizationConditionVO.setD_ID(userVO.getSid());
				OrganizationResultVO syb = organizationMapper.selectSybByCondition(organizationConditionVO);
				if(syb != null){
					userVO.setSname(syb.getDname());
				}else{
					userVO.setSname("");
				}
			}else{
				organizationConditionVO.setD_ID("A001");
				userVO.setSname("");
			}
			//dq
			if(userVO.getDid() != null && !"".equals(userVO.getDid()) && !"B001".equals(userVO.getDid())){
				organizationConditionVO.setP_ID(userVO.getDid());
				DqVO dq = organizationMapper.selectDqByCondition(organizationConditionVO);
				if(dq != null){
					userVO.setDname(dq.getPname());
				}else{
					userVO.setDname("");
				}
			}else{
				organizationConditionVO.setP_ID("B001");
				userVO.setDname("");
			}
			//fgs
			if(userVO.getFid() != null && !"".equals(userVO.getFid()) && !"C001".equals(userVO.getFid())){
				organizationConditionVO.setF_ID(userVO.getFid());
				FgsVO fgs = organizationMapper.selectFgsByCondition(organizationConditionVO);
				if(fgs != null){
					userVO.setFname(fgs.getFname());
				}else{
					userVO.setFname("");
				}
			}else{
				organizationConditionVO.setF_ID("C001");
				userVO.setFname("");
			}
			//yyb
			if(userVO.getYid() != null && !"".equals(userVO.getYid()) && !"D001".equals(userVO.getYid())){
				organizationConditionVO.setY_ID(userVO.getYid());
				YybVO yyb = organizationMapper.selectYybByCondition(organizationConditionVO);	
				if(yyb != null){
					userVO.setYname(yyb.getYname());
				}else{
					userVO.setYname("");
				}
			}else{
				organizationConditionVO.setY_ID("D001");
				userVO.setYname("");
			}
			//td
			if(userVO.getTid() != null && !"".equals(userVO.getTid())){
				organizationConditionVO.setT_ID(userVO.getTid());
				TdVO td = organizationMapper.selectTdByCondition(organizationConditionVO);
				if(td != null){
					userVO.setTname(td.getTname());
				}else{
					userVO.setTname("");
				}
			}else{
				userVO.setTname("");
			}
		}
		return userList;
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping("/now")
	String getTime() {
		return "Current time: " + (new Date()).toLocaleString();
	}
}
