package com.wssys.bean;

import java.sql.Timestamp;

public class PusDeptTreeBean {

	private Integer id;
	private String name;
	private Integer pid;
	private Integer deptgrade;
	private String description;
	private Timestamp createtime;
	private Integer _parentId;
	private String pidname;
	private String dwname;
	
	
	
	public String getDwname() {
		return dwname;
	}
	public void setDwname(String dwname) {
		this.dwname = dwname;
	}
	public String getPidname() {
		return pidname;
	}
	public void setPidname(String pidname) {
		this.pidname = pidname;
	}
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
	public Integer get_parentId() {
		return _parentId;
	}
	public void set_parentId(Integer _parentId) {
		this._parentId = _parentId;
	}
	
	
	
	
}
