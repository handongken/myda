package com.bqhx.yyb.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bqhx.yyb.constant.Constant;
import com.bqhx.yyb.service.UserService;
import com.bqhx.yyb.vo.MessageVO;
import com.bqhx.yyb.vo.UserConditionVO;
import com.bqhx.yyb.vo.UserHistoryVO;
import com.bqhx.yyb.vo.UserVO;

@CrossOrigin
@RestController
@RequestMapping("/")
public class UserController {

	@Autowired
	@Qualifier("UserServiceImpl") 
	private UserService userService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	List<UserVO> home(UserConditionVO condition) {
		List<UserVO> list = userService.selectUserByCondition(condition);
		return list;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	UserVO login(UserConditionVO condition) {
		UserVO vo = userService.selectUserByPrimaryKey(condition);
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
	MessageVO insertUserSelective(UserConditionVO condition){
		MessageVO messageVO = new MessageVO();
		UserVO userVO = userService.selectUserByPrimaryKey(condition);
		if (userVO != null) {
			messageVO.setCode(Constant.FLAG_ZERO);
			messageVO.setMessage(Constant.FAILED);
		} else {
			boolean flag = userService.insertUserSelective(condition);
			if(flag == true){
				messageVO.setCode(Constant.FLAG_ONE);
				messageVO.setMessage(Constant.SUCCESS);
			}else{
				messageVO.setCode(Constant.FLAG_ZERO);
				messageVO.setMessage(Constant.FAILED);
			}
		}
		return messageVO;
	}
	
	@RequestMapping(value = "/deleteUserByPrimaryKey", method = RequestMethod.POST)
	MessageVO deleteUserByPrimaryKey(UserConditionVO condition){
		condition.setDelFlg(Constant.FLAG_ONE);
		String localUserId = condition.getLocalUserId();
		int code = userService.updateUserByPrimaryKeySelective(condition,localUserId);
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
	 * 更新
	 * @param condition
	 * @return
	 */
	@RequestMapping(value = "/updateUserByPrimaryKeySelective", method = RequestMethod.POST)
	MessageVO updateUserByPrimaryKeySelective(UserConditionVO condition) {
		String localUserId = condition.getLocalUserId();
		int code = userService.updateUserByPrimaryKeySelective(condition,localUserId);
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
		List<UserVO> userList = userService.selectUserByCondition(condition);
		return userList;
	}
	
	/**
	 * 
	 * @param condition
	 * @return
	 */
	@RequestMapping(value = "/selectUserHistoryByCondition", method = RequestMethod.POST)
	List<UserHistoryVO> selectUserHistoryByCondition(UserConditionVO condition){
		List<UserHistoryVO> userList = userService.selectUserHistoryByCondition(condition);
		return userList;
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping("/now")
	String getTime() {
		return "Current time: " + (new Date()).toLocaleString();
	}
}
