package com.wssys.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DeptReDwh entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="dept_re_dwh")
public class DeptReDwh implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer deptid;
	private Integer dwhid;
	private Integer type;
	@Column(name="createtime",updatable=false,insertable=false)private Timestamp createtime;

	// Constructors

	/** default constructor */
	public DeptReDwh() {
	}

	/** minimal constructor */
	public DeptReDwh(Integer deptid, Integer dwhid, Timestamp createtime) {
		this.deptid = deptid;
		this.dwhid = dwhid;
		this.createtime = createtime;
	}

	/** full constructor */
	public DeptReDwh(Integer deptid, Integer dwhid, Integer type,
			Timestamp createtime) {
		this.deptid = deptid;
		this.dwhid = dwhid;
		this.type = type;
		this.createtime = createtime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDeptid() {
		return this.deptid;
	}

	public void setDeptid(Integer deptid) {
		this.deptid = deptid;
	}

	public Integer getDwhid() {
		return this.dwhid;
	}

	public void setDwhid(Integer dwhid) {
		this.dwhid = dwhid;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

}