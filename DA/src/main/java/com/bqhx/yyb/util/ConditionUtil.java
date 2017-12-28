package com.bqhx.yyb.util;

import com.bqhx.yyb.constant.Constant;
import com.bqhx.yyb.vo.ConditionVO;
import com.bqhx.yyb.vo.UserVO;

public class ConditionUtil {
	/**
	 * 根据用户角色处理查询条件
	 */
	public static ConditionVO getConditionVOByRole(ConditionVO condition, UserVO user){
		String userId = user.getUserId();
		String typeId = user.getTypeId();
		if(typeId != null && !typeId.equals("")){
			if(typeId.equals(Constant.LCMANAGER)){
				condition.setLcManager(userId);
			}else if(typeId.equals(Constant.TMANAGER)){
				condition.setTmanager(userId);
			}else if(typeId.equals(Constant.YYBMANAGER)){
				condition.setYybManager(userId);
			}else if(typeId.equals(Constant.FGSMANAGER)){
				condition.setFgsManager(userId);
			}else if(typeId.equals(Constant.DQMANAGER)){
				condition.setDqManager(userId);
			}else if(typeId.equals(Constant.SYBMANAGER)){
				condition.setSybManager(userId);
			}
			if(typeId.equals(Constant.SYBOFFICE)){
				condition.setSyb(user.getSid());
			}else if(typeId.equals(Constant.DQOFFICE)){
				condition.setDq(user.getDid());
			}else if(typeId.equals(Constant.FGSOFFICE)){
				condition.setFgs(user.getFid());
			}else if(typeId.equals(Constant.YYBOFFICE)){
				condition.setYyb(user.getYid());
			}
		}
		return condition;
	}
}
