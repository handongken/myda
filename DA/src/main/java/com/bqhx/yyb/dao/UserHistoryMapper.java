package com.bqhx.yyb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.bqhx.yyb.vo.UserConditionVO;
import com.bqhx.yyb.vo.UserHistoryVO;

@Mapper
public interface UserHistoryMapper {

	void insertUserHistory(UserConditionVO record);

	List<UserHistoryVO> selectUserHistoryByCondition(UserConditionVO condition);

}