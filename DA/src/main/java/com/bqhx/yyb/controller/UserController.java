package com.bqhx.yyb.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bqhx.yyb.constant.Constant;
import com.bqhx.yyb.dao.UserMapper;
import com.bqhx.yyb.util.DateUtil;
import com.bqhx.yyb.vo.MessageVO;
import com.bqhx.yyb.vo.UserConditionVO;
import com.bqhx.yyb.vo.UserVO;

@CrossOrigin
@RestController
@RequestMapping("/")
public class UserController {
	@Autowired
	private UserMapper userMapper;

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
	MessageVO updateUserByPrimaryKey(UserConditionVO condition, UserVO user){
		String updDate = DateUtil.formatDate(new Date(), Constant.PATTERN_HMS);
		if (updDate != null && updDate != "") {
			condition.setUpdDate(updDate);
		}
		condition.setUpdUser(user.getName());
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
		String code = updateUserByPrimaryKeySelective(condition);
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
	
	@RequestMapping(value = "/updateUserByPrimaryKeySelective", method = RequestMethod.POST)
	String updateUserByPrimaryKeySelective(UserConditionVO condition) {
		userMapper.updateUserByPrimaryKeySelective(condition);
		return Constant.FLAG_ONE;
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
		}
		return userList;
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping("/now")
	String getTime() {
		return "Current time: " + (new Date()).toLocaleString();
	}
}
