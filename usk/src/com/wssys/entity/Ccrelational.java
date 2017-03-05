package com.wssys.entity;

/**
 * Ccrelational entity. @author MyEclipse Persistence Tools
 */

public class Ccrelational implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer contactid;
	private Integer companyid;

	// Constructors

	/** default constructor */
	public Ccrelational() {
	}

	/** full constructor */
	public Ccrelational(Integer contactid, Integer companyid) {
		this.contactid = contactid;
		this.companyid = companyid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getContactid() {
		return this.contactid;
	}

	public void setContactid(Integer contactid) {
		this.contactid = contactid;
	}

	public Integer getCompanyid() {
		return this.companyid;
	}

	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}

}