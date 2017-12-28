package com.bqhx.yyb.service;

import java.util.List;

import com.bqhx.yyb.vo.FileVO;

public interface FileService {
	FileVO selectFilePathByKey(FileVO fileVO);
	
	int insertFilePath(FileVO fileVO);
	
	List<FileVO> selectFileByCondition(FileVO file);
	
	int updateFileByPrimaryKey(FileVO file);
}
