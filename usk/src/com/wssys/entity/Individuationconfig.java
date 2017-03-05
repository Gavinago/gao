package com.wssys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Individuationconfig entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="individuationconfig")
public class Individuationconfig implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")private Integer id;
	@Column(name="configkey")private String configkey;
	@Column(name="configvalue")private String configvalue;
	@Column(name="description")private String description;

	// Constructors

	/** default constructor */
	public Individuationconfig() {
	}

	/** full constructor */
	public Individuationconfig(String configkey, String configvalue,
			String description) {
		this.configkey = configkey;
		this.configvalue = configvalue;
		this.description = description;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConfigkey() {
		return this.configkey;
	}

	public void setConfigkey(String configkey) {
		this.configkey = configkey;
	}

	public String getConfigvalue() {
		return this.configvalue;
	}

	public void setConfigvalue(String configvalue) {
		this.configvalue = configvalue;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}