package com.wssys.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * PusRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="pus_role")
public class PusRole implements java.io.Serializable {

	// Fields
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
	// Constructors
	
	@ManyToOne
	@Fetch(FetchMode.JOIN)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "pid", insertable=false, updatable=false,referencedColumnName = "id") 
	private PusRole pr;
	
	

	public PusRole getPr() {
		return pr;
	}

	public void setPr(PusRole pr) {
		this.pr = pr;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	/** default constructor */
	public PusRole() {
	}

	/** minimal constructor */
	public PusRole(String name, Integer state) {
		this.name = name;
		this.state = state;
	}

	/** full constructor */
	public PusRole(String name, String type, String code, Integer pid,
			String remark, Integer state) {
		this.name = name;
		this.type = type;
		this.code = code;
		this.pid = pid;
		this.remark = remark;
		this.state = state;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}