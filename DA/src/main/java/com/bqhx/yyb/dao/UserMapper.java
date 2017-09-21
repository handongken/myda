<<<<<<< HEAD
package com.bqhx.yyb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bqhx.yyb.vo.UserVO;

@Mapper
public interface UserMapper {

	List<UserVO> getAll();

	UserVO getOne(String userId);

	void addUser(@Param("userId") String userId, @Param("name") String name, @Param("password") String password,
			@Param("tel") String tel);

	void updateUser(@Param("userId") String userId, @Param("name") String name, @Param("password") String password,
			@Param("tel") String tel);

	void deleteUserById(String userId);
=======
package com.bqhx.yyb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bqhx.yyb.vo.UserVO;

@Mapper
public interface UserMapper {

	List<UserVO> getAll();

	UserVO getOne(String userId);

	void addUser(@Param("userId") String userId, @Param("name") String name, @Param("password") String password,
			@Param("tel") String tel);

	void updateUser(@Param("userId") String userId, @Param("name") String name, @Param("password") String password,
			@Param("tel") String tel);

	void deleteUserById(String userId);
>>>>>>> refs/remotes/origin/master
}