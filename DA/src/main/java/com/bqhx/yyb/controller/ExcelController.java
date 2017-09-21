package com.bqhx.yyb.controller;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bqhx.yyb.util.ExcelUtil;
import com.bqhx.yyb.vo.DataVO;

@RestController
@RequestMapping("/")
public class ExcelController {

	@RequestMapping("/downloadexcel")
	protected ModelAndView downloadExcel(ModelAndView mv) throws Exception {
		List<DataVO> list = new ArrayList<DataVO>();
		list.add(new DataVO("鑫", "http://www.xin.com", "admin", "111111", 111));
		list.add(new DataVO("惠", "http://www.hui.com", "admin", "111111", 222));
		list.add(new DataVO("网", "http://www.wang.com", "admin", "111111", 333));

		Map<String, String> map = new HashMap<String, String>();
		map.put("title", "网站信息表");
		map.put("total", list.size() + " 条");
		map.put("date", getDate());
		ExcelUtil.getInstance().exportObj2ExcelByTemplate(map, "web-info-template.xls",
				new FileOutputStream("D:/temp/out.xls"), list, DataVO.class, true);
		mv.setViewName("Success");
		return mv; 
	}

	private String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		return sdf.format(new Date());
	}
}
