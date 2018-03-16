package com.lvdao.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lvdao.common.CommonConst;

public class DateUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);
	
	public final static String DATE_FORMAT_YEAR_MONTH_DAY = "yyyyMMdd";
	public final static String DATE_FORMAT_DAY = "yyyy-MM-dd";
	public static final String DATE_FORMAT_MIN = "yyyy-MM-dd HH:mm";
	public static final String DATE_FORMAT_MILLS = "yyyy-MM-dd HH:mm:ss SSS";
	public static final String ORDER_DATE_FORMAT = "yyMMddHHmmssSSS";
	public static final String DATE_FORMAT_SECOND = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT_YEAR_MILLS = "yy-MM-dd HH:mm";
	public static final String DATE_FORMAT_HOURS_MIN = "HH:mm";
	
	/** 年月日时分秒(无下划线) yyMMddHHmmss */
    public static final String DATE_FORMAT = "yyMMddHHmmssSSS";
	
	public final static String FIRST_TIME = "00:00:00";
	public final static String LAST_TIME = "23:59:59";
	
	public static String format(Date date, String pattern) {
		if (null == date) {
			return null;
		}
		return (new SimpleDateFormat(pattern)).format(date);
	}
	
	public static long getInterval(final String startDate, final String endDate) {
		 
    	SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_MILLS);
    	
    	Calendar calStart = Calendar.getInstance(); 
    	Calendar calEnd = Calendar.getInstance();
    	
		try {
			calStart.setTime(df.parse(startDate));
			calEnd.setTime(df.parse(endDate));
			
			long ms = calEnd.getTimeInMillis() - calStart.getTimeInMillis();
	    	return ms;
		} catch (ParseException e) {
		    LOGGER.error("toDD error:", e);
			return CommonConst.DIGIT_ZERO;
		} 
	}
	
	public static long toDay(long ms) {
    	return ms/1000/3600/24;
    }
	
	public static int getDateInterval(Date startDate, Date endDate) {
		
		 Calendar beginTime = Calendar.getInstance(); 
	     Calendar endTime = Calendar.getInstance();
	     beginTime.setTime(startDate);
	     beginTime.set(Calendar.HOUR, CommonConst.DIGIT_ZERO);
	     beginTime.set(Calendar.MINUTE, CommonConst.DIGIT_ZERO);
	     beginTime.set(Calendar.SECOND, CommonConst.DIGIT_ZERO);
	     
	     endTime.setTime(endDate);
	     endTime.set(Calendar.HOUR, CommonConst.DIGIT_ZERO);
	     endTime.set(Calendar.MINUTE, CommonConst.DIGIT_ZERO);
	     endTime.set(Calendar.SECOND, CommonConst.DIGIT_ZERO);
	     endTime.add(Calendar.DATE, CommonConst.DIGIT_ONE);
	     
		long ms = endTime.getTimeInMillis() - beginTime.getTimeInMillis();
		
		return (int)toDay(ms);
	 }
	
	public static Date parseDate(String date, String format) {
		
		Date formatDate = null;
		
		if(null == date || date.trim().equals(CommonConst.PUNCTUATION_DOUBLE_QUOTATION_MARKS)) {
			return null;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		
		try {
			formatDate = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return formatDate;
	}
	
	public static void main(String[] args) {
		Date parseDate = parseDate("1992-03-24",DATE_FORMAT_DAY);
		int age = -1;
		try {
			age = getCurrentAgeByBirthdate(parseDate);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(age);
	}
	
	public static Date addDate(Date date, int day) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, day);
		return cal.getTime();
	}
	
	public static Date getFirstOfDay(Date date, int day) {
		String tempDate = DateUtils.format(date, DateUtils.DATE_FORMAT_DAY);
		tempDate = tempDate + CommonConst.PUNCTUATION_DOUBLE_QUOTATION_MARKS_BACKSPACE + FIRST_TIME;
		date = DateUtils.parseDate(tempDate, DateUtils.DATE_FORMAT_MIN);
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, day);
		return calendar.getTime();
	}
	
	public static Date getLastOfDay(Date date, int day) {
		String tempDate = DateUtils.format(date, DateUtils.DATE_FORMAT_DAY);
		tempDate = tempDate + CommonConst.PUNCTUATION_DOUBLE_QUOTATION_MARKS_BACKSPACE + LAST_TIME;
		date = DateUtils.parseDate(tempDate, DateUtils.DATE_FORMAT_MIN);
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, day);
		return cal.getTime();
	}
	
	public static String addDay(String DateStr, String dateFormat, int day) {
		
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date endTime = null;
		try {
			endTime = sdf.parse(DateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(endTime);
		cal.add(Calendar.DATE, day);
		endTime = cal.getTime();
		return sdf.format(endTime);
	}
	
	public static Date getFirstOfMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, CommonConst.DIGIT_ONE);
		return getFirstOfDay(cal.getTime(), CommonConst.DIGIT_ZERO);
	}
	
	public static Date getWeekSunday(Date date) {    
        Calendar cal = Calendar.getInstance();    
        cal.setTime(date);    
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);    
        cal.add(Calendar.WEEK_OF_YEAR, CommonConst.DIGIT_ONE);
        return getLastOfDay(cal.getTime(), CommonConst.DIGIT_ZERO);   
    }
	
	public static Date getForstOfMonth(Date date, int month) {
		Calendar cal = Calendar.getInstance();   
		cal.setTime(date);
		cal.set(Calendar.DATE, CommonConst.DIGIT_ONE);
		cal.add(Calendar.MONTH, month);
		return getLastOfDay(cal.getTime(), CommonConst.DIGIT_ZERO);  
    } 
	
	public static Date getLastOfMonth(Date date){   
		Calendar cal = Calendar.getInstance();   
		cal.setTime(date);
		cal.set(Calendar.DATE, CommonConst.DIGIT_ONE);  
		cal.add(Calendar.MONTH, CommonConst.DIGIT_ONE);
		cal.add(Calendar.DATE, CommonConst.DIGIT_MINUS_ONE);
		return getLastOfDay(cal.getTime(), CommonConst.DIGIT_ZERO);  
    }
	
	/**
	 * date转string
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date){   
		SimpleDateFormat sdf=new SimpleDateFormat(DATE_FORMAT_DAY);  
		return sdf.format(date);   
    }
	
	/**
	  * @author hexiang
	  * @param brithday
	  * @return
	  * @throws ParseException
	  *             根据生日获取年龄;
	  */
	@SuppressWarnings("deprecation")
	public static int getCurrentAgeByBirthdate(Date brithday) throws ParseException, Exception {
		try {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formatDate = new SimpleDateFormat(DATE_FORMAT_DAY);
			String currentTime = formatDate.format(calendar.getTime());
			Date today = formatDate.parse(currentTime);

			return today.getYear() - brithday.getYear();
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static String getTimeStamp() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}
	
	public static String getDatePoor(Date endDate, Date startDate) {
		long nd = 1000 * 24 * 60 * 60;
		long nh = 1000 * 60 * 60;
		long nm = 1000 * 60;
		// long ns = 1000;
		// 获得两个时间的毫秒时间差异
		long diff = endDate.getTime() - startDate.getTime();
		// 计算差多少天
		long day = diff / nd;
		// 计算差多少小时
		long hour = diff % nd / nh;
		// 计算差多少分钟
		long min = diff % nd % nh / nm;
		// 计算差多少秒//输出结果
		// long sec = diff % nd % nh % nm / ns;
		if(day == 0) {
			if(hour == 0) {
				return min + "分钟";
			}
			return hour + "小时" + min + "分钟";
		}
		return day + "天" + hour + "小时" + min + "分钟";
	}
	/**
	 * 计算两个日期之间相差的天数
	 * @param smdate 较小的时间 
	 * @param bdate  较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate,Date bdate) throws ParseException    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    }  
	
	public static String getWXTimeStamp() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}
}
