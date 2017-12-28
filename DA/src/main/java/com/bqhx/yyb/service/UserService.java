package com.bqhx.yyb.service;

import java.util.List;

import com.bqhx.yyb.vo.UserConditionVO;
import com.bqhx.yyb.vo.UserHistoryVO;
import com.bqhx.yyb.vo.UserVO;

public interface UserService {
	boolean insertUserSelective(UserConditionVO condition);
	
	List<UserVO> selectUserByCondition(UserConditionVO condition);
	
	UserVO selectUserByPrimaryKey(UserConditionVO condition);
	/**更新 */
	int updateUserByPrimaryKeySelective(UserConditionVO condition,String localUserId);
	
	int insertUserHistory(UserConditionVO record);
	
	List<UserHistoryVO> selectUserHistoryByCondition(UserConditionVO condition);
}
