package com.bqhx.yyb.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bqhx.yyb.vo.FileVO;

@Mapper
public interface FileMapper {
	int insertFilePath(FileVO fileVO);
	
	FileVO selectFilePathByKey(FileVO fileVO);
	
	List<FileVO> selectFileByCondition(FileVO file);
	
	void updateFileByPrimaryKey(FileVO file);
}
