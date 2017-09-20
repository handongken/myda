package com.bqhx.yyb.dao;

import com.bqhx.yyb.vo.InformationVO;

public interface InformationVOMapper {
    int deleteByPrimaryKey(String contract);

    int insert(InformationVO record);

    int insertSelective(InformationVO record);

    InformationVO selectByPrimaryKey(String contract);

    int updateByPrimaryKeySelective(InformationVO record);

    int updateByPrimaryKey(InformationVO record);
}