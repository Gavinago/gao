package com.wssys.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pus_menu")
public class PusMenuSingle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Integer menupid;
	private String description;
	private String pageurl;
	private Short type;
	private Short state;
	@Column(name="createtime",updatable=false,insertable=false) private Timestamp createtime;
//	private Integer _parentId;	//业务逻辑处理字段方便 treegrid 显示
//	
//	public Integer get_parentId() {
//		return menupid;
//	}
//	public void set_parentId(Integer _parentId) {
//		this._parentId = menupid;
//	}
	/** default constructor */
	public PusMenuSingle() {
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
	public Integer getMenupid() {
		return menupid;
	}
	public void setMenupid(Integer menupid) {
		this.menupid = menupid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPageurl() {
		return pageurl;
	}
	public void setPageurl(String pageurl) {
		this.pageurl = pageurl;
	}
	public Short getType() {
		return type;
	}
	public void setType(Short type) {
		this.type = type;
	}
	public Short getState() {
		return state;
	}
	public void setState(Short state) {
		this.state = state;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	
	
}
