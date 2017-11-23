package com.bqhx.yyb.web;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.bq.vo.UserVO;



@FeignClient(name="USER-SERVICE", fallback = UserServiceFallback.class)
public interface UserService {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    UserVO login(@RequestBody UserVO user) ;

    @RequestMapping(value = "/hello5", method = RequestMethod.POST)
    UserVO getUserByid(@RequestParam("id") String id);

}