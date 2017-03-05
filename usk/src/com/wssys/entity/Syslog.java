package com.wssys.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Syslog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="syslog")
public class Syslog implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String ipAddress;
	private String loginName;
	private String methodName;
	private String methodRemark;
	private String operatingcontent;
	@Column(name="createtime",updatable=false,insertable=false)private Timestamp createtime;

	// Constructors

	/** default constructor */
	public Syslog() {
	}

	/** minimal constructor */
	public Syslog(String loginName, Timestamp createtime) {
		this.loginName = loginName;
		this.createtime = createtime;
	}

	/** full constructor */
	public Syslog(String ipAddress, String loginName, String methodName,
			String methodRemark,String operatingcontent, Timestamp createtime) {
		this.ipAddress = ipAddress;
		this.loginName = loginName;
		this.methodName = methodName;
		this.methodRemark = methodRemark;
		this.operatingcontent = operatingcontent;
		this.createtime = createtime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public String getOperatingcontent() {
		return operatingcontent;
	}

	public void setOperatingcontent(String operatingcontent) {
		this.operatingcontent = operatingcontent;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}



	

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getMethodRemark() {
		return methodRemark;
	}

	public void setMethodRemark(String methodRemark) {
		this.methodRemark = methodRemark;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

}