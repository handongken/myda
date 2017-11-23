package com.bqhx.yyb.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bq.vo.UserVO;

@RestController
public class ConsumerController {

    @Autowired
    RefactorUserService refactorUserService;


    @RequestMapping(value = "/feign-login", method = RequestMethod.POST)
    UserVO login(@RequestBody UserVO user) {
        return refactorUserService.login(user);
    }

}