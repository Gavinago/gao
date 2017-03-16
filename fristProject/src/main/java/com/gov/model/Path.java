package com.gov.model;

import java.io.Serializable;

public class Path implements Serializable{
	private static final long serialVersionUID = 1L;
	private String path;
	private String pathName;
	private String url;
	private Integer state;
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getPathName() {
		return pathName;
	}
	public void setPathName(String pathName) {
		this.pathName = pathName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Path [path=" + path + ", pathName=" + pathName + ", url=" + url + ", state=" + state + "]";
	}
}
