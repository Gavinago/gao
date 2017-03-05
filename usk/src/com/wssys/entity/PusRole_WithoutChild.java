package com.wssys.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pus_role")
public class PusRole_WithoutChild {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String type;
	private String code;
	private Integer pid;
	private String remark;
	private Integer state;
	@Column(name="createtime",updatable=false,insertable=false) private Timestamp createtime;
	
	
	
	/** default constructor */
	public PusRole_WithoutChild() {
	}

	/** minimal constructor */
	public PusRole_WithoutChild(String name, Integer state) {
		this.name = name;
		this.state = state;
	}

	/** full constructor */
	public PusRole_WithoutChild(String name, String type, String code, Integer pid,
			String remark, Integer state) {
		this.name = name;
		this.type = type;
		this.code = code;
		this.pid = pid;
		this.remark = remark;
		this.state = state;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	
	
	
}
