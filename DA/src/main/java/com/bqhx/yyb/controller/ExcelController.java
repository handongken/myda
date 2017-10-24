package com.bqhx.yyb.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bqhx.yyb.constant.Constant;
import com.bqhx.yyb.dao.CertificateMapper;
import com.bqhx.yyb.dao.InformationVOMapper;
import com.bqhx.yyb.dao.MovableCheckMapper;
import com.bqhx.yyb.dao.PrincipalMapper;
import com.bqhx.yyb.dao.TypeMapper;
import com.bqhx.yyb.util.ExcelUtil;
import com.bqhx.yyb.vo.CertificateVO;
import com.bqhx.yyb.vo.ConditionVO;
import com.bqhx.yyb.vo.InformationVO;
import com.bqhx.yyb.vo.MovableCheckVO;
import com.bqhx.yyb.vo.PrincipalVO;
import com.bqhx.yyb.vo.TypeVO;
import com.bqhx.yyb.constant.*;

@RestController
@RequestMapping("/")
public class ExcelController {
	
	@Autowired
	private InformationVOMapper informationVOMapper;
	@Autowired
	private CertificateMapper certificateMapper;
	@Autowired
	private TypeMapper typeMapper;
	@Autowired
	private PrincipalMapper principalVOMapper;
	@Autowired
	private MovableCheckMapper movableCheckMapper;
	/**
	 * 下载总表
	 *
	 */
	@RequestMapping(value="/downloadSummaryTable", method = RequestMethod.POST)
	protected void downloadSummaryTable(HttpServletResponse res) throws Exception {
		InformationVO record = new InformationVO();
		record.setDelFlg(Constant.FLAG_ZERO);
		String fileName = Constant.SUMMARYNAME + getDate() + ".xlsx"; 
		String excelName = new String(fileName.getBytes("gb2312") , "ISO8859-1");
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
		ExcelUtil.getInstance().exportObj2ExcelByTemplate(map, Constant.TOTALTEMPLATE,
				getOutputStreamByCondition(excelName,res), list, InformationVO.class, true);
	}

	/**
	 * 下载已赎回表
	 * status="1"
	 */
	@RequestMapping(value="/downloadRedeemedTable", method = RequestMethod.POST)
	protected void downloadRedeemedTable(HttpServletResponse res) throws Exception {
		InformationVO record = new InformationVO();
		record.setDelFlg(Constant.FLAG_ZERO);
		record.setStatus(Constant.FLAG_ONE);
		String fileName = Constant.REDEEMEDNAME + getDate() + ".xlsx"; 
		String excelName = new String(fileName.getBytes("gb2312") , "ISO8859-1");
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
		ExcelUtil.getInstance().exportObj2ExcelByTemplate(map, Constant.TOTALTEMPLATE,
				getOutputStreamByCondition(excelName,res), list, InformationVO.class, true);
	}
	
	/**
	 * 下载提前赎回表
	 * status="3"
	 */
	@RequestMapping(value="/downloadRedeemableTable", method = RequestMethod.POST)
	protected void downloadRedeemableTable(HttpServletResponse res) throws Exception {
		InformationVO record = new InformationVO();
		record.setDelFlg(Constant.FLAG_ZERO);
		record.setStatus(Constant.FLAG_THREE);
		String fileName = Constant.REDEEMABLENAME + getDate() + ".xlsx"; 
		String excelName = new String(fileName.getBytes("gb2312") , "ISO8859-1");
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
		ExcelUtil.getInstance().exportObj2ExcelByTemplate(map, Constant.TOTALTEMPLATE,
				getOutputStreamByCondition(excelName,res), list, InformationVO.class, true);
	}
	
	/**
	 * 下载续投业绩表
	 * continueFlg="0"
	 */
	@RequestMapping(value="/downloadInvestmentTable", method = RequestMethod.POST)
	protected void downloadInvestmentTable(HttpServletResponse res) throws Exception {
		InformationVO record = new InformationVO();
		record.setDelFlg(Constant.FLAG_ZERO);
		record.setContinueFlg(Constant.FLAG_ZERO);
		String fileName = Constant.INVESTMENTNAME + getDate() + ".xlsx"; 
		String excelName = new String(fileName.getBytes("gb2312") , "ISO8859-1");
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
//		String out = Constant.INVESTMENTTABLE + getDate() + ".xls";new FileOutputStream(out)
		ExcelUtil.getInstance().exportObj2ExcelByTemplate(map, Constant.TOTALTEMPLATE,
				getOutputStreamByCondition(excelName,res), list, InformationVO.class, true);
	}
	
	/**
	 * 付息凭证表
	 *
	 */
	@RequestMapping(value = "/downloadInterestCertificate", method = RequestMethod.POST)
	protected void downloadInterestCertificate(ConditionVO condition,HttpServletResponse res) throws Exception {
		String fileName = Constant.INTERESTCERTIFICATENAME + getDate() + ".xlsx";
		String excelName = new String(fileName.getBytes("gb2312") , "ISO8859-1");
		String startTime = condition.getStartTime();
		List<CertificateVO> certificateList = certificateMapper.selectCertificateByCondition(condition);
		Map<String, String> map = new HashMap<String, String>();
		map.put("${title}", Constant.INTERESTCERTIFICATETITLE);
		map.put("${date}", getDate());
		map.put("${startTime}", startTime);
		ExcelUtil.getInstance().exportObj2ExcelByTemplate(map, Constant.INTERESTTEMPLATE,
				getOutputStreamByCondition(excelName,res), certificateList, CertificateVO.class, true);
	}
	
	/**
	 * 发放凭证表
	 *
	 */
	@RequestMapping(value = "/downloadReleaseCertificate", method = RequestMethod.POST)
	protected void downloadReleaseCertificate(ConditionVO condition,HttpServletResponse res) throws Exception {
		String fileName = Constant.RELEASECERTIFICATENAME + getDate() + ".xlsx";
		String excelName = new String(fileName.getBytes("gb2312") , "ISO8859-1");
		String startTime = condition.getStartTime();
		List<CertificateVO> certificateList = certificateMapper.selectCertificateByCondition(condition);
		Map<String, String> map = new HashMap<String, String>();
		map.put("${title}", Constant.RELEASECERTIFICATETITLE);
		map.put("${date}", getDate());
		map.put("${startTime}", startTime);
		ExcelUtil.getInstance().exportObj2ExcelByTemplate(map, Constant.INTERESTTEMPLATE,
				getOutputStreamByCondition(excelName,res), certificateList, CertificateVO.class, true);
	}
	
	/**
	 * 还本信息表
	 *
	 */
	@RequestMapping(value = "/downloadPrincipal", method = RequestMethod.POST)
	protected void downloadPrincipal(ConditionVO condition,HttpServletResponse res) throws Exception {
		String fileName = Constant.PRINCIPALNAME + getDate() + ".xlsx";
		String excelName = new String(fileName.getBytes("gb2312") , "ISO8859-1");
		String startTime = condition.getStartTime();
		List<PrincipalVO> principalList = principalVOMapper.selectPrincipalByCondition(condition);
		Map<String, String> map = new HashMap<String, String>();
		map.put("${title}", Constant.PRINCIPALTITLE);
		map.put("${date}", getDate());
		map.put("${startTime}", startTime);
		ExcelUtil.getInstance().exportObj2ExcelByTemplate(map, Constant.INTERESTTEMPLATE,
				getOutputStreamByCondition(excelName,res), principalList, CertificateVO.class, true);
	}
	
	/**
	 * 移动支票表
	 *
	 */
	@RequestMapping(value = "/downloadMovableCheck", method = RequestMethod.POST)
	protected void downloadMovableCheck(ConditionVO condition,HttpServletResponse res) throws Exception {
		String fileName = Constant.MOVABLECHECKNAME + getDate() + ".xlsx";
		String excelName = new String(fileName.getBytes("gb2312") , "ISO8859-1");
		String startTime = condition.getStartTime();
		List<MovableCheckVO> movableCheckList = movableCheckMapper.selectMovableCheckByCondition(condition);
		Map<String, String> map = new HashMap<String, String>();
		map.put("${title}", Constant.MOVABLECHECKTITLE);
		map.put("${date}", getDate());
		map.put("${startTime}", startTime);
		ExcelUtil.getInstance().exportObj2ExcelByTemplate(map, Constant.INTERESTTEMPLATE,
				getOutputStreamByCondition(excelName,res), movableCheckList, CertificateVO.class, true);
	}
	
	/**
	 * 银行短信回息表
	 *
	 */
	@RequestMapping(value = "/downloadSMSInterest", method = RequestMethod.POST)
	protected void downloadSMSInterest(ConditionVO condition,HttpServletResponse res) throws Exception {
		String fileName = Constant.SMSINTERESTNAME + getDate() + ".xlsx";
		String excelName = new String(fileName.getBytes("gb2312") , "ISO8859-1");
		String startTime = condition.getStartTime();
		List<CertificateVO> certificateList = certificateMapper.selectCertificateByCondition(condition);
		Map<String, String> map = new HashMap<String, String>();
		map.put("${title}", Constant.SMSINTERESTTITLE);
		map.put("${date}", getDate());
		map.put("${startTime}", startTime);
		ExcelUtil.getInstance().exportObj2ExcelByTemplate(map, Constant.INTERESTTEMPLATE,
				getOutputStreamByCondition(excelName,res), certificateList, CertificateVO.class, true);
	}
	
	/**
	 * 银行短信回本表
	 *
	 */
	@RequestMapping(value = "/downloadSMSCapital", method = RequestMethod.POST)
	protected void downloadSMSCapital(ConditionVO condition,HttpServletResponse res) throws Exception {
		String fileName = Constant.SMSCAPITALNAME + getDate() + ".xlsx";
		String excelName = new String(fileName.getBytes("gb2312") , "ISO8859-1");
		String startTime = condition.getStartTime();
		List<CertificateVO> certificateList = certificateMapper.selectCertificateByCondition(condition);
		Map<String, String> map = new HashMap<String, String>();
		map.put("${title}", Constant.SMSCAPITALTITLE);
		map.put("${date}", getDate());
		map.put("${startTime}", startTime);
		ExcelUtil.getInstance().exportObj2ExcelByTemplate(map, Constant.INTERESTTEMPLATE,
				getOutputStreamByCondition(excelName,res), certificateList, CertificateVO.class, true);
	}
	
	protected OutputStream getOutputStreamByCondition(String fileName,HttpServletResponse res){
		res.setHeader("content-type", "application/octet-stream");
	    res.setContentType("application/octet-stream");
	    res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
	    OutputStream os = null;
		      try {
				os = res.getOutputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    return os;
	}
	
	private String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat(Constant.PATTERN);
		return sdf.format(new Date());
	}
}
