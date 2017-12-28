package com.bqhx.yyb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bqhx.yyb.vo.UserConditionVO;
import com.bqhx.yyb.vo.UserVO;

@Mapper
public interface UserMapper {

	List<UserVO> selectUserByCondition(UserConditionVO condition);

	int insertUserSelective(UserConditionVO condition);
	
	UserVO selectUserByPrimaryKey(UserConditionVO condition);

	int updateUserByPrimaryKeySelective(UserConditionVO condition);

}