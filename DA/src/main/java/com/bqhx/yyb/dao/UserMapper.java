package com.bqhx.yyb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bqhx.yyb.vo.UserVO;

@Mapper
public interface UserMapper {

    List<UserVO> getAll();

    UserVO getOne(String userId);
}