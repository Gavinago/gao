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
 * PusDept entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "pus_dept")
public class PusDept implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Integer pid;
	private Integer deptgrade;
	private String description;
	@Column(name="createtime",updatable=false,insertable=false)private Timestamp createtime;

	
	//@Fetch(FetchMode.JOIN)： 始终立刻加载，使用外连(outer join)查询的同时加载关联对象，忽略FetchType.LAZY设定。
	//@Fetch(FetchMode.SELECT) ：默认懒加载(除非设定关联属性lazy=false)，当访问每一个关联对象时加载该对象，会累计产生N+1条sql语句
	//@Fetch(FetchMode.SUBSELECT)  默认懒加载(除非设定关联属性lazy=false),在访问第一个关联对象时加载所有的关联对象。会累计产生两条sql语句。且FetchType设定有效
	
	@ManyToOne
	@Fetch(FetchMode.JOIN)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "pid", insertable = false, updatable = false, referencedColumnName = "id")
	private PusDept pd;

	// Constructors

	public PusDept getPd() {
		return pd;
	}

	public void setPd(PusDept pd) {
		this.pd = pd;
	}

	/** default constructor */
	public PusDept() {
	}

	/** minimal constructor */
	public PusDept(Timestamp createtime) {
		this.createtime = createtime;
	}

	/** full constructor */
	public PusDept(String name, Integer pid, Integer deptgrade,
			String description, Timestamp createtime) {
		this.name = name;
		this.pid = pid;
		this.deptgrade = deptgrade;
		this.description = description;
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

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getDeptgrade() {
		return this.deptgrade;
	}

	public void setDeptgrade(Integer deptgrade) {
		this.deptgrade = deptgrade;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

}