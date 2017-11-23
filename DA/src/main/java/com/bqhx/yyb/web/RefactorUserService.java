package com.bqhx.yyb.web;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value = "USER-SERVICE")
public interface RefactorUserService extends UserService {


}