package com.wssys.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Twodimensioncode entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="twodimensioncode")
public class Twodimensioncode implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String imagepath;
	@Column(name="createtime",updatable=false,insertable=false)private Timestamp createtime;
	private Integer state;
	private Integer goodsid;

	// Constructors

	/** default constructor */
	public Twodimensioncode() {
	}

	/** minimal constructor */
	public Twodimensioncode(Timestamp createtime) {
		this.createtime = createtime;
	}

	/** full constructor */
	public Twodimensioncode(String imagepath, Timestamp createtime,
			Integer state, Integer goodsid) {
		this.imagepath = imagepath;
		this.createtime = createtime;
		this.state = state;
		this.goodsid = goodsid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImagepath() {
		return this.imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getGoodsid() {
		return this.goodsid;
	}

	public void setGoodsid(Integer goodsid) {
		this.goodsid = goodsid;
	}

}