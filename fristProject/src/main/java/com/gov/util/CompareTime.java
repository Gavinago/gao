package com.gov.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sun.jmx.snmp.internal.SnmpAccessControlSubSystem;

import ch.qos.logback.core.net.SyslogOutputStream;

public class CompareTime {
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	public CompareTime(){
		
	}
	/**
	 * 和当前时间比较
	 * @param time
	 * @return 大于等于当前时间为true小于为false
	 */
	public boolean compareCurrentTime(String time){
		String currentTime = getCurrentTime();
		long times = getTimeMillisecond(time);
		long currentTimes =getTimeMillisecond(currentTime);
		if(times>=currentTimes){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 获得当前时间 
	 * @return yyyy-MM-dd hh:mm:ss
	 */
	public String getCurrentTime(){
		
		Date date = new Date();
		String currentTime =sdf.format(date);
		return currentTime;
	}
	/**
	 * 将当前字符串时间转换成毫秒数
	 * @param time
	 * @return
	 */
	public long getTimeMillisecond(String time){
		long mm = 0;
		try {
			Date d = sdf.parse(time);
			mm = d.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return mm;
	}
	/**
	 * 将毫秒数转换成时间字符串
	 * @return yyyy-MM-dd hh:mm:ss
	 */
	public String getTimeString(long millisecond){
		Date date = new Date(millisecond);
		return sdf.format(date);
	}
	/**
	 * 比较两个时间大小
	 * @param str1 时间1 yyyy-MM-dd HH:mm:ss
	 * @param str2 时间2 yyyy-MM-dd HH:mm:ss
	 * @return str1>=str2 true;
	 */
	public boolean compareTime(String str1,String str2){
		long s1 = getTimeMillisecond(str1);
		long s2 = getTimeMillisecond(str2);
		if(s1>=s2){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 比较两个时间相差多长时间
	 * @param str1
	 * @param str2
	 * @return str1和str2 相差时间(分钟)
	 */
	public double comparefarTime(String str1,String str2){
		long s1 = getTimeMillisecond(str1);
		long s2 = getTimeMillisecond(str2);
		long mm = s1-s2;
		DecimalFormat df = new DecimalFormat("#.00");
		double time = Double.valueOf(df.format(mm/1000/60));
		return time;
	}
	/**
	 * 获得当前时间以前多少天的时间
	 * @param date 天
	 * @return 当前时间以前的时间
	 */
	public String BeforeTheTime(double date){
		String time =null;
		if(date>=0){
			long mm = dayTransToMillisecond(date);
			long currentMm = System.currentTimeMillis();
			time = getTimeString(currentMm-mm);
		}
		return time;
	}
	/**
	 * 把天数转换成毫秒数
	 * @param date
	 * @return
	 */
	public long dayTransToMillisecond(double date){
		long time=0;
		if(date>=0){
			time = (long) (date*24*60*60*1000);
		}
		return time;
	}
}
