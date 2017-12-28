package com.bqhx.yyb.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.bqhx.yyb.constant.Constant;

public class DateUtil {

	public static String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat(Constant.PATTERN);
		return sdf.format(new Date());
	}
	/**
	 * @param date
	 *            转换的对象
	 * @param pattern
	 *            日期时间格式的模式
	 * @return 参数null的时候返回null
	 */
	public static String convertDate(String date, String pattern) {
		String date_s = "";
		if (date == null || "".equals(date)) {
			return "";
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
	 * string to date
	 */
	public static Date stringToDate(String time, String pattern) {
		Date date = null;
		if (time == null || "".equals(time)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			date = sdf.parse(time);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * date to string
	 */
	public static String dateToString(Date date, String pattern){
		String str = "";
		if(date == null || "".equals(date)){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		str = sdf.format(date);
		return str;
	}
	
	/**
	 * @param date
	 *            转换的对象
	 * @return 参数null的时候返回null,string
	 */
	public static String formatDate(Date date, String pattern) {
		String date_s = "";
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
		String date_s = "";
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
	 * 转换月份
	 */
	public static String convertMonth(int periods, String startDate) {
		Calendar bCalendar = StringConvert2Calendar(startDate, Constant.PATTERN);
		bCalendar.add(Calendar.MONTH, periods);
		String beginDate = DateUtil.CalendarConvert2String(bCalendar, Constant.PATTERN);
		return beginDate;
	}

	/**
	 * 转换天数
	 */
	public static String convertDay(String startDate, int day) {
		Calendar eCalendar = StringConvert2Calendar(startDate, Constant.PATTERN);
		eCalendar.add(Calendar.DAY_OF_YEAR, day);
		String endDate = DateUtil.CalendarConvert2String(eCalendar, Constant.PATTERN);
		return endDate;
	}

	/**
	 * 根据时间获取day
	 */
	public static String getDayByTime(String time) {
		String str = "";
		Calendar calendar = Calendar.getInstance();
		Date date = stringToDate(time, Constant.PATTERN);
		calendar.setTime(date);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		str = String.valueOf(day);
		return str;
	}

	/**
	 * 根据时间获取month
	 */
	public static int getMonthByTime(String time) {
		int month = 1;
		Calendar calendar = Calendar.getInstance();
		Date date = stringToDate(time, Constant.PATTERN);
		calendar.setTime(date);
		month = calendar.get(Calendar.MONTH) + 1; 
		return month;
	}
	
	/**
	 * 根据时间获取week
	 */
	public static String getWeekByTime(String time) {
		String week = "";
		Calendar calendar = Calendar.getInstance();
		Date date = stringToDate(time, Constant.PATTERN);
		calendar.setTime(date);
		int dw = calendar.get(Calendar.DAY_OF_WEEK);
		if (dw == 1) {
			week = "周天";
		} else if (dw == 2) {
			week = "周一";
		} else if (dw == 3) {
			week = "周二";
		} else if (dw == 4) {
			week = "周三";
		} else if (dw == 5) {
			week = "周四";
		} else if (dw == 6) {
			week = "周五";
		} else if (dw == 7) {
			week = "周六";
		}
		return week;
	}
	
	/**
	 * 根据开始时间和结束时间返回时间段内的day集合 
	 */
	public static List<String> getDatesBetweenTwoDate(String startTime, String endTime) { 
		List<String> dateList = new ArrayList<String>();
		dateList.add(startTime);
		Calendar calendar = Calendar.getInstance();
		Date startDate = stringToDate(startTime,Constant.PATTERN);
		Date endDate = stringToDate(endTime,Constant.PATTERN);
		calendar.setTime(startDate);
		boolean bContinue = true;  
        while (bContinue) {  
        	// 根据日历的规则，为给定的日历字段添加或减去指定的时间量  
    		calendar.add(Calendar.DAY_OF_MONTH, 1);  
            // 测试此日期是否在指定日期之后  
    		if(endDate.after(calendar.getTime())){
    			String st = dateToString(calendar.getTime(),Constant.PATTERN);
    			dateList.add(st);
    		} else {  
                break;  
            }
        }
        dateList.add(endTime); 
		return dateList;
	}
	
	/**
	 * 根据开始时间和结束时间返回时间段内的month集合 
	 */
	public static List<String> getMonthBetweenTwoDate(String startTime, String endTime) { 
		List<String> dateList = new ArrayList<String>();
		dateList.add(startTime);
		Calendar calendar = Calendar.getInstance();
		Date startDate = stringToDate(startTime,Constant.PATTERN);
		Date endDate = stringToDate(endTime,Constant.PATTERN);
		calendar.setTime(startDate);
		boolean bContinue = true;  
        while (bContinue) {  
        	// 根据日历的规则，为给定的日历字段添加或减去指定的时间量  
    		calendar.add(Calendar.MONTH, 1);  
            // 测试此日期是否在指定日期之后  
    		if(endDate.after(calendar.getTime())){
    			String st = dateToString(calendar.getTime(),Constant.PATTERN);
    			dateList.add(st);
    		} else {  
                break;  
            }
        }
        dateList.add(endTime); 
		return dateList;
	}
}
