package com.wssys.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Mailclient entity. @author MyEclipse Persistence Tools
 * linux下 表名 要小写
 */
@Entity
@Table(name="mailclient")
public class Mailclient implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")private Integer id;
	@Column(name="clintname")private String clintname;
	@Column(name="clientaddress")private String clientaddress;
	@Column(name="clinttel")private String clinttel;
	@Column(name="remark")private String remark;
	@Column(name="createtime",insertable=false,updatable=false)private Timestamp createtime;

	// Constructors



	/** default constructor */
	public Mailclient() {
	}

	/** full constructor */
	public Mailclient(String clintname, String clientaddress, String clinttel,
			String remark) {
		this.clintname = clintname;
		this.clientaddress = clientaddress;
		this.clinttel = clinttel;
		this.remark = remark;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClintname() {
		return this.clintname;
	}

	public void setClintname(String clintname) {
		this.clintname = clintname;
	}

	public String getClientaddress() {
		return this.clientaddress;
	}

	public void setClientaddress(String clientaddress) {
		this.clientaddress = clientaddress;
	}

	public String getClinttel() {
		return this.clinttel;
	}

	public void setClinttel(String clinttel) {
		this.clinttel = clinttel;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
}