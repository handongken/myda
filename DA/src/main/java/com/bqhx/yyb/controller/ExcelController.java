package com.bqhx.yyb.controller;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bqhx.yyb.constant.Constant;
import com.bqhx.yyb.dao.InformationVOMapper;
import com.bqhx.yyb.dao.TypeMapper;
import com.bqhx.yyb.util.ExcelUtil;
import com.bqhx.yyb.vo.InformationVO;
import com.bqhx.yyb.vo.TypeVO;
import com.bqhx.yyb.constant.*;

@RestController
@RequestMapping("/")
public class ExcelController {
	
	@Autowired
	private InformationVOMapper informationVOMapper;
	@Autowired
	private TypeMapper typeMapper;
	/**
	 * 下载总表
	 *
	 */
	@RequestMapping("/downloadSummaryTable")
	protected String downloadSummaryTable() throws Exception {
		InformationVO record = new InformationVO();
		record.setDelFlg(Constant.FLAG_ZERO);
		List<InformationVO> list = informationVOMapper.selectAll(record);
		for(int i = 0;i < list.size();i++){
			InformationVO info = list.get(i);
			//type
			String type = info.getType();
			TypeVO typeVO = typeMapper.selectTypeByPrimaryKey(type);
			String typeName = typeVO.getTypeName();
			if(typeName != null && typeName != ""){
				info.setType(typeName);
			}
			//status
			String status = info.getStatus();
			String statusValue = StatusEnum.getValue(status);
			if(statusValue != null && statusValue != ""){
				info.setStatus(statusValue);
			}
			//continueFlg
			String continueFlg = info.getContinueFlg();
			String continueFlgValue = ContinueFlgEnum.getValue(continueFlg);
			if(continueFlgValue != null && continueFlgValue != ""){
				info.setContinueFlg(continueFlgValue);
			}
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("${totalCount}", list.size() + " 条");
		map.put("${date}", getDate());
		map.put("${title}", Constant.SUMMARYTABLETITLE);
		String out = Constant.SUMMARYTABLE + getDate() + ".xls";
		ExcelUtil.getInstance().exportObj2ExcelByTemplate(map, Constant.TOTALTEMPLATE,
				new FileOutputStream(out), list, InformationVO.class, true);
		return "success";
	}

	/**
	 * 下载已赎回表
	 * status="1"
	 */
	@RequestMapping("/downloadRedeemedTable")
	protected String downloadRedeemedTable() throws Exception {
		InformationVO record = new InformationVO();
		record.setDelFlg(Constant.FLAG_ZERO);
		record.setStatus(Constant.FLAG_ONE);
		List<InformationVO> list = informationVOMapper.selectAll(record);
		for(int i = 0;i < list.size();i++){
			InformationVO info = list.get(i);
			//type
			String type = info.getType();
			TypeVO typeVO = typeMapper.selectTypeByPrimaryKey(type);
			String typeName = typeVO.getTypeName();
			if(typeName != null && typeName != ""){
				info.setType(typeName);
			}
			//status
			String status = info.getStatus();
			String statusValue = StatusEnum.getValue(status);
			if(statusValue != null && statusValue != ""){
				info.setStatus(statusValue);
			}
			//continueFlg
			String continueFlg = info.getContinueFlg();
			String continueFlgValue = ContinueFlgEnum.getValue(continueFlg);
			if(continueFlgValue != null && continueFlgValue != ""){
				info.setContinueFlg(continueFlgValue);
			}
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("${title}", Constant.REDEEMEDTABLETITLE);
		map.put("${totalCount}", list.size() + " 条");
		map.put("${date}", getDate());
		String out = Constant.REDEEMEDTABLE + getDate() + ".xls";
		ExcelUtil.getInstance().exportObj2ExcelByTemplate(map, Constant.TOTALTEMPLATE,
				new FileOutputStream(out), list, InformationVO.class, true);
		return "success";
	}
	
	/**
	 * 下载提前赎回表
	 * status="3"
	 */
	@RequestMapping("/downloadRedeemableTable")
	protected String downloadRedeemableTable() throws Exception {
		InformationVO record = new InformationVO();
		record.setDelFlg(Constant.FLAG_ZERO);
		record.setStatus(Constant.FLAG_THREE);
		List<InformationVO> list = informationVOMapper.selectAll(record);
		for(int i = 0;i < list.size();i++){
			InformationVO info = list.get(i);
			//type
			String type = info.getType();
			TypeVO typeVO = typeMapper.selectTypeByPrimaryKey(type);
			String typeName = typeVO.getTypeName();
			if(typeName != null && typeName != ""){
				info.setType(typeName);
			}
			//status
			String status = info.getStatus();
			String statusValue = StatusEnum.getValue(status);
			if(statusValue != null && statusValue != ""){
				info.setStatus(statusValue);
			}
			//continueFlg
			String continueFlg = info.getContinueFlg();
			String continueFlgValue = ContinueFlgEnum.getValue(continueFlg);
			if(continueFlgValue != null && continueFlgValue != ""){
				info.setContinueFlg(continueFlgValue);
			}
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("${title}", Constant.REDEEMABLETABLETITLE);
		map.put("${totalCount}", list.size() + " 条");
		map.put("${date}", getDate());
		String out = Constant.REDEEMABLETABLE + getDate() + ".xls";
		ExcelUtil.getInstance().exportObj2ExcelByTemplate(map, Constant.TOTALTEMPLATE,
				new FileOutputStream(out), list, InformationVO.class, true);
		return "success";
	}
	
	/**
	 * 下载续投业绩表
	 * continueFlg="0"
	 */
	@RequestMapping("/downloadInvestmentTable")
	protected String downloadInvestmentTable() throws Exception {
		InformationVO record = new InformationVO();
		record.setDelFlg(Constant.FLAG_ZERO);
		record.setContinueFlg(Constant.FLAG_ZERO);
		List<InformationVO> list = informationVOMapper.selectAll(record);
		for(int i = 0;i < list.size();i++){
			InformationVO info = list.get(i);
			//type
			String type = info.getType();
			TypeVO typeVO = typeMapper.selectTypeByPrimaryKey(type);
			String typeName = typeVO.getTypeName();
			if(typeName != null && typeName != ""){
				info.setType(typeName);
			}
			//status
			String status = info.getStatus();
			String statusValue = StatusEnum.getValue(status);
			if(statusValue != null && statusValue != ""){
				info.setStatus(statusValue);
			}
			//continueFlg
			String continueFlg = info.getContinueFlg();
			String continueFlgValue = ContinueFlgEnum.getValue(continueFlg);
			if(continueFlgValue != null && continueFlgValue != ""){
				info.setContinueFlg(continueFlgValue);
			}
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("${title}", Constant.INVESTMENTTABLETITLE);
		map.put("${totalCount}", list.size() + " 条");
		map.put("${date}", getDate());
		String out = Constant.INVESTMENTTABLE + getDate() + ".xls";
		ExcelUtil.getInstance().exportObj2ExcelByTemplate(map, Constant.TOTALTEMPLATE,
				new FileOutputStream(out), list, InformationVO.class, true);
		return "success";
	}
	
	private String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat(Constant.PATTERN);
		return sdf.format(new Date());
	}
}
