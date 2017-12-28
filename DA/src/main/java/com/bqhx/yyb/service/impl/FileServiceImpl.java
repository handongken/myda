package com.bqhx.yyb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bqhx.yyb.dao.FileMapper;
import com.bqhx.yyb.service.FileService;
import com.bqhx.yyb.vo.FileVO;

@Service("FileServiceImpl")
public class FileServiceImpl implements FileService {
	@Autowired
	private FileMapper fileMapper;
	
	@Override
	public FileVO selectFilePathByKey(FileVO fileVO) {
		FileVO file = fileMapper.selectFilePathByKey(fileVO);
		return file;
	}

	@Override
	public int insertFilePath(FileVO fileVO) {
		fileMapper.insertFilePath(fileVO);
		return 1;
	}

	@Override
	public List<FileVO> selectFileByCondition(FileVO file) {
		List<FileVO> fileList = fileMapper.selectFileByCondition(file);
		return fileList;
	}

	@Override
	public int updateFileByPrimaryKey(FileVO file) {
		fileMapper.updateFileByPrimaryKey(file);
		return 1;
	}

}
