package com.wssys.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Dwh entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="dwh")
public class Dwh implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String remark;
	@Column(name="createtime",updatable=false,insertable=false)private Timestamp createtime;

	// Constructors

	/** default constructor */
	public Dwh() {
	}

	/** minimal constructor */
	public Dwh(Timestamp createtime) {
		this.createtime = createtime;
	}

	/** full constructor */
	public Dwh(String name, String remark, Timestamp createtime) {
		this.name = name;
		this.remark = remark;
		this.createtime = createtime;
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

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

}