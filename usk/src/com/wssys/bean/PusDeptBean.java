package com.wssys.bean;

import java.sql.Timestamp;

public class PusDeptBean {
	private Integer id;
	private String name;
	private Integer pid;
	private Integer deptgrade;
	private String description;
	private Timestamp createtime;
	private Integer userid;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer getDeptgrade() {
		return deptgrade;
	}
	public void setDeptgrade(Integer deptgrade) {
		this.deptgrade = deptgrade;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	
	
	
}
