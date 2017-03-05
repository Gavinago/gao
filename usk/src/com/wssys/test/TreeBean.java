package com.wssys.test;
/**
 * 定义数据类
 * 将所查询的数据封装
 *
 *
 */
public class TreeBean {
	private String id;
	private String text;
	private String pid;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public TreeBean(String id, String text, String pid) {
		this.id = id;
		this.text = text;
		this.pid = pid;
	}
	
}
