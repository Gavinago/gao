package com.wssys.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.FetchType;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * PusMenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="pus_menu")
public class PusMenu implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Integer menupid;
	private String description;
	private String pageurl;
	private Short type;
	private Short state;
	private Integer sortfiled;
	@Column(name="createtime",updatable=false,insertable=false) private Timestamp createtime;
	
//	1、FetchType.LAZY：懒加载，加载一个实体时，定义懒加载的属性不会马上从数据库中加载。
//
//	2、FetchType.EAGER：急加载，加载一个实体时，定义急加载的属性会立即从数据库中加载。
	@ManyToOne
	@Fetch(FetchMode.JOIN)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "menupid", insertable=false, updatable=false,referencedColumnName = "id") 
	private PusMenu pm;
	

//	
//	@ManyToOne
//	@Fetch(FetchMode.JOIN)
//	@NotFound(action=NotFoundAction.IGNORE)
//	@JoinColumn(name = "roleid", insertable=false, updatable=false,referencedColumnName = "roleid") 
//	private PusReUserRole pusReUserRole;
	

	// Constructors

//	public PusReUserRole getPusReUserRole() {
//		return pusReUserRole;
//	}
//
//	public void setPusReUserRole(PusReUserRole pusReUserRole) {
//		this.pusReUserRole = pusReUserRole;
//	}
//


	public PusMenu getPm() {
		return pm;
	}

	public void setPm(PusMenu pm) {
		this.pm = pm;
	}

	public Integer getSortfiled() {
		return sortfiled;
	}

	public void setSortfiled(Integer sortfiled) {
		this.sortfiled = sortfiled;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	/** default constructor */
	public PusMenu() {
	}

	/** minimal constructor */
	public PusMenu(String name, Short type, Short state) {
		this.name = name;
		this.type = type;
		this.state = state;
	}

	/** full constructor */
	public PusMenu(String name, Integer menupid, String description,
			String pageurl, Short type, Short state) {
		this.name = name;
		this.menupid = menupid;
		this.description = description;
		this.pageurl = pageurl;
		this.type = type;
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

	public Integer getMenupid() {
		return this.menupid;
	}

	public void setMenupid(Integer menupid) {
		this.menupid = menupid;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPageurl() {
		return this.pageurl;
	}

	public void setPageurl(String pageurl) {
		this.pageurl = pageurl;
	}

	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

}