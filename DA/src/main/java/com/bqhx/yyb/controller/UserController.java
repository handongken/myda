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
import org.springframework.web.servlet.ModelAndView;

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
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	ModelAndView addUser(String userId,String name,String password,String tel,ModelAndView mv){
		userMapper.addUser(userId,name, password,tel);
		mv.setViewName("Success");
		return mv;
	}
	
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	ModelAndView updateUser(String userId,String name,String password,String tel,ModelAndView mv){
		userMapper.updateUser(userId,name, password,tel);
		mv.setViewName("Success");
		return mv;
	}

	@RequestMapping(value = "/deleteUserById", method = RequestMethod.POST)
	ModelAndView deleteUserById(String userId,ModelAndView mv){
		userMapper.deleteUserById(userId);
		mv.setViewName("Success");
		return mv;
	}
	
	@RequestMapping(value = "/getOne", method = RequestMethod.GET)
	ModelAndView getOne(String userId,ModelAndView mv){
		UserVO user = userMapper.getOne(userId);
		mv.addObject("user",user);
		mv.setViewName("ShowAllUser");
		return mv;
	}
	
	@RequestMapping(value = "/findUserList", method = RequestMethod.GET)
	ModelAndView getAll(ModelAndView mv){
		List<UserVO> userList = userMapper.getAll();
		mv.addObject("userList",userList);
		mv.setViewName("ShowAllUser");
		return mv;
	}
	
	@RequestMapping(value = "/findOne", method = RequestMethod.GET)
	ModelAndView findOne(String userId,ModelAndView mv){
		UserVO user = userMapper.getOne(userId);
		mv.addObject("user",user);
		mv.setViewName("EditUser");
		return mv;
	}
}
