package com.bqhx.yyb.web;

import org.springframework.stereotype.Component;
import com.bq.vo.UserVO;

/**
 * Created by Administrator on 2016/9/16.
 */
@Component
public class UserServiceFallback implements UserService {


	@Override
	public UserVO login(UserVO user) {
		UserVO vo = new UserVO();
		vo.setMessage("error");
		return vo;
	}

	@Override
	public UserVO getUserByid(String id) {
		UserVO vo = new UserVO();
		vo.setMessage("error");
		return vo;
	}

}
