package com.gov.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gov.util.SyncWeather;

@Controller
public class WeatherController {
	@RequestMapping(value="getWeather.do")
	@ResponseBody
	public List<String> weather(String cityName){
		List<String> list = null;
		if(cityName.trim()!="" || null!=cityName){
			list = SyncWeather.weather().getWeather(cityName);
		}
		return list;
	}
}
