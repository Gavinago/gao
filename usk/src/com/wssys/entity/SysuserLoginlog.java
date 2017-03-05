package com.wssys.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SysuserLoginlog entity. @author MyEclipse Persistence Tools
 * 用户登录日志
 */
@Entity
@Table(name="sysuser_loginlog")
public class SysuserLoginlog implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String useraccount;
	private Integer type;
	private String ip;
	@Column(name="createtime",updatable=false)private Timestamp createtime;

	// Constructors

	/** default constructor */
	public SysuserLoginlog() {
	}

	/** full constructor */
	public SysuserLoginlog(String useraccount, Integer type, String ip,
			Timestamp createtime) {
		this.useraccount = useraccount;
		this.type = type;
		this.ip = ip;
		this.createtime = createtime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUseraccount() {
		return this.useraccount;
	}

	public void setUseraccount(String useraccount) {
		this.useraccount = useraccount;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

}