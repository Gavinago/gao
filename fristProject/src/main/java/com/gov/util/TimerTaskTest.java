package com.gov.util;

import java.util.TimerTask;

public class TimerTaskTest extends TimerTask {
	private volatile boolean falg;
	public boolean isFalg() {
		return falg;
	}
	public void setFalg(boolean falg) {
		this.falg = falg;
	}
	@Override
	public void run() {
		if(falg){
			
			SyncWeather.weather().getWeather("");
		}
	}
}
