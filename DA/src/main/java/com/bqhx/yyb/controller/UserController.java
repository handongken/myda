package com.bqhx.yyb.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bqhx.yyb.dao.UserMapper;
import com.bqhx.yyb.vo.UserVO;

@CrossOrigin
@RestController
@RequestMapping("/")
public class UserController {

	@Autowired
	private UserMapper userMapper;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	List<UserVO> home() {
		List<UserVO> list = userMapper.getAll();
		return list;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	UserVO login(@RequestParam String userId, @RequestParam String password) {
		
		UserVO vo = userMapper.getOne(userId);
		if(vo == null) {
			vo = new UserVO();
			vo.setMessage("用户不存在");
		} else if(!password.equals(vo.getPassword())) {
			vo = new UserVO();
			vo.setMessage("密码错误");
		}
		
		return vo;
	}

	
	@SuppressWarnings("deprecation")
	@RequestMapping("/now")
	String getTime() {
		return "Current time: " + (new Date()).toLocaleString();
	}
}
