package com.gov.util;

import java.util.Date;
import java.util.Timer;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListener implements ServletContextListener{
	
	TimerTaskTest ttk = new TimerTaskTest();
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ttk.setFalg(true);
		Timer timer = new Timer();
		Date date = new Date();
		date.setTime(date.getTime()+1000*60);
		timer.scheduleAtFixedRate(ttk, date,1000*60*30);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ttk.setFalg(false);
	}

}
