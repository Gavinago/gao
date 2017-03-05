package com.gov.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gov.model.Weather;

public interface WeatherMapper {

	public List<Weather>selectWeatherByDataId(@Param(value="DataId")String DataId,@Param(value="city")String city);
}
