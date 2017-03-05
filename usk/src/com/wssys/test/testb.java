package com.wssys.test;

import java.util.Calendar;

public class testb {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		// System.out.println("开始了");
		// Thread.sleep(5000L); // 休眠到明天
		// System.out.println("结束了");
		// Long t_start = System.currentTimeMillis();
		// System.out.println(t_start);
		//
		// java.util.Date myDate = new java.util.Date();
		// long myTime = (myDate.getTime() / 1000) - 60 * 60 * 24 * 365;
		// myDate = new java.util.Date();
		// myTime = (myDate.getTime() / 1000) + 60 * 60 * 24;
		// System.out.println((myDate.getTime() / 1000));

		// myDate.setTime(myTime * 1000);
		// mDate=formatter.format(myDate);
		// System.out.println(myDate.getTime());

		
		 Long t_start = System.currentTimeMillis();
		 System.out.println(t_start);
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		System.out.println(cal.getTime());
		System.out.println(cal.getTimeInMillis());
		
		System.out.println("--------------------------------");
		
		Calendar cal24 = Calendar.getInstance(); 
		cal24.set(Calendar.HOUR_OF_DAY, 24); 
		cal24.set(Calendar.SECOND, 0); 
		cal24.set(Calendar.MINUTE, 0); 
		cal24.set(Calendar.MILLISECOND, 0); 
		//return (int) (cal24.getTimeInMillis()/1000); 
		System.out.println(cal24.getTime());
		System.out.println(cal24.getTimeInMillis());
		
		String email="651947105@qq.com";
		System.out.println(email.substring(0, email.indexOf("@")));
		
	}

}
