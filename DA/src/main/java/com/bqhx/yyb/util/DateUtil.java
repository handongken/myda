package com.bqhx.yyb.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	/**
	 * @param date 转换的对象
	 * @param pattern 日期时间格式的模式
	 * @return 参数null的时候返回null
	 */
	public static String convertDate(String date,String pattern) {
		String date_s = null;
		if (date == null || "".equals(date)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			Date date_d = sdf.parse(date);
			date_s = sdf.format(date_d);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date_s;
	}
	
	/**
	 * @param date 转换的对象
	 * @return 参数null的时候返回null,string
	 */
	public static String formatDate(Date date,String pattern) {
		String date_s = null;
		if (date == null || "".equals(date)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			date_s = sdf.format(date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date_s;
	}
}
