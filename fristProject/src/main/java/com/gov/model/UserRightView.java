package com.gov.model;

import java.io.Serializable;

public class UserRightView implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String username;
	private String name;
	private String rolename;
	private String rightname;
	private String rightcode;
	
	public UserRightView() {
		super();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getRightname() {
		return rightname;
	}
	public void setRightname(String rightname) {
		this.rightname = rightname;
	}
	public String getRightcode() {
		return rightcode;
	}
	public void setRightcode(String rightcode) {
		this.rightcode = rightcode;
	}
	@Override
	public String toString() {
		return "UserRightView [username=" + username + ", name=" + name + ", rolename=" + rolename + ", rightname="
				+ rightname + ", rightcode=" + rightcode + "]";
	}
	

}
