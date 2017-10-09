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

import com.bqhx.yyb.dao.InformationVOMapper;
import com.bqhx.yyb.util.ExcelUtil;
import com.bqhx.yyb.vo.ConstantVO;
import com.bqhx.yyb.vo.InformationVO;

@RestController
@RequestMapping("/")
public class ExcelController {
	
	@Autowired
	private InformationVOMapper informationVOMapper;
	
	@RequestMapping("/downloadexcel")
	protected String downloadExcel() throws Exception {
		InformationVO record = new InformationVO();
		record.setManagerStatus(ConstantVO.FLAG_ONE);
		List<InformationVO> list = informationVOMapper.selectAll(record);
		for(InformationVO inf : list){
			String paymentDate_s = inf.getPaymentDate();
			if(paymentDate_s != null && paymentDate_s != ""){
				inf.setPaymentDate(paymentDate_s.substring(0, 19));
			}
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("title", ConstantVO.SUMMARYTABLETITLE);
		map.put("date", getDate());
		map.put("total", list.size() + " 条");
		String out = "D:/业绩日报总表_" + getDate() + ".xls"; 
		ExcelUtil.getInstance().exportObj2ExcelByTemplate(map, ConstantVO.EXCELTEMPLATE,
				new FileOutputStream(out), list, InformationVO.class, true);
		return "success";
	}

	private String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		return sdf.format(new Date());
	}
}
