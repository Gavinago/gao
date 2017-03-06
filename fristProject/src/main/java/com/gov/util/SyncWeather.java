package com.gov.util;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.bag.SynchronizedSortedBag;

import cn.com.WebXml.WeatherWebServiceSoap;
import cn.com.WebXml.WeatherWebServiceSoapProxy;

public class SyncWeather  {
	private static final WeatherWebServiceSoap wwss = new WeatherWebServiceSoapProxy();
	/**
	 * 创建一个SyncWeather对象
	 */
	private static final SyncWeather weather = new SyncWeather();
	/**
	 * 私有化构造方法
	 */
	private SyncWeather(){
		
	}
	/**
	 * 获得唯一的对象
	 * @return
	 */
    public static SyncWeather weather(){
       return weather;
    }
    /**
     * 获得WeatherWebServiceSoap对象
     * @return
     */
    public static WeatherWebServiceSoap getWwss(){
    	return wwss;
    }
	/**
	 * 根据城市或地区名称查询获得未来三天内天气情况、现在的天气实况、天气和生活指数
	 *
	 *	调用方法如下：输入参数：theCityName = 城市中文名称(国外城市可用英文)或城市代码(不输入默认为上海市)，如：上海 或 58367，如有城市名称重复请使用城市代码查询(可通过 getSupportCity 或 getSupportDataSet 获得)；返回数据： 一个一维数组 String(22)，共有23个元素。
	 *	String(0) 到 String(4)：省份，城市，城市代码，城市图片名称，最后更新时间。String(5) 到 String(11)：当天的 气温，概况，风向和风力，天气趋势开始图片名称(以下称：图标一)，天气趋势结束图片名称(以下称：图标二)，现在的天气实况，天气和生活指数。String(12) 到 String(16)：第二天的 气温，概况，风向和风力，图标一，图标二。String(17) 到 String(21)：第三天的 气温，概况，风向和风力，图标一，图标二。String(22) 被查询的城市或地区的介绍 
     *
     *
	 * @param theCityName
	 * @return
	 */
	@SuppressWarnings("unused")
	public List<String> getWeather(String theCityName){
		
		List<String> list = new ArrayList<String>();
		String[] str = null;
		if(theCityName.trim()==""||null==theCityName){
			theCityName = "西安";
		}
		try {
			System.out.println(theCityName);
			str = wwss.getWeatherbyCityName(theCityName);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		if(str.length>0){
		for(int i=0;i<str.length;i++){
			list.add(str[i]);
		}
		System.out.println(new Date()+"---"+str[4]+"--------- "+str[5]);
		}
		return list;
	}
}
