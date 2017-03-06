package com.gov.model;

import java.io.Serializable;
import java.util.Date;

public class Weather implements Serializable{
	private static final long serialVersionUID = 1L;
	private String DataId;
	private String city;
	private String temperature;
	private String picture1;
	private String picture2;
	private Date updateTime;
	private Date createTime;
	
	public Weather() {
		super();
	}
	public String getDataId() {
		return DataId;
	}
	public void setDataId(String dataId) {
		DataId = dataId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getPicture1() {
		return picture1;
	}
	public void setPicture1(String picture1) {
		this.picture1 = picture1;
	}
	public String getPicture2() {
		return picture2;
	}
	public void setPicture2(String picture2) {
		this.picture2 = picture2;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "Weather [DataId=" + DataId + ", city=" + city + ", temperature=" + temperature + ", picture1="
				+ picture1 + ", picture2=" + picture2 + ", updateTime=" + updateTime + ", createTime=" + createTime
				+ "]";
	}
	
	

}
