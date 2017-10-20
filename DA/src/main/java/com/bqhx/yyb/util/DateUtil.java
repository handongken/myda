package com.bqhx.yyb.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import com.bqhx.yyb.constant.Constant;

public class DateUtil {

	/**
	 * @param date
	 *            转换的对象
	 * @param pattern
	 *            日期时间格式的模式
	 * @return 参数null的时候返回null
	 */
	public static String convertDate(String date, String pattern) {
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
	 * @param date
	 *            转换的对象
	 * @return 参数null的时候返回null,string
	 */
	public static String formatDate(Date date, String pattern) {
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

	/**
	 * @param String转换成Calendar
	 * @return 参数null的时候返回null
	 */
	public static Calendar StringConvert2Calendar(String date, String pattern) {
		Date date_s = null;
		Calendar calendar = Calendar.getInstance();
		if (date == null || "".equals(date)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			date_s = sdf.parse(date);
			calendar.setTime(date_s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return calendar;
	}

	/**
	 * @param Calendar转换成String
	 * @return 参数null的时候返回null
	 */
	public static String CalendarConvert2String(Calendar date, String pattern) {
		String date_s = null;
		if (date == null || "".equals(date)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			date_s = sdf.format(date.getTime());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date_s;
	}

	/**
	 * 获取初始发放期间
	 */
	public static String getBeginDate(int periods,String startDate) {
		Calendar bCalendar = StringConvert2Calendar(startDate, Constant.PATTERN);
		bCalendar.add(Calendar.MONTH, -periods);
		String beginDate = DateUtil.CalendarConvert2String(bCalendar, Constant.PATTERN);
		return beginDate;
	}
	
	/**
	 * 获取结束发放期间
	 */
	public static String getEndDate(String startDate,int day) {
		Calendar eCalendar = StringConvert2Calendar(startDate, Constant.PATTERN);
		eCalendar.add(Calendar.DAY_OF_YEAR, -day);
		String endDate = DateUtil.CalendarConvert2String(eCalendar, Constant.PATTERN);
		return endDate;
	}
}
