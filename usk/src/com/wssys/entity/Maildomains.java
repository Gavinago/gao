package com.wssys.entity;

import javax.persistence.CascadeType;
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

/**
 * Maildomains entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "maildomains")
public class Maildomains implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "domain_name")
	private String domainName;
	@Column(name = "domain_daylimit")
	Integer domainDaylimit;
	@Column(name = "domain_minutelimit")
	private Integer domainMinutelimit;
	@Column(name = "remark")
	private String remark;
	@Column(name = "domain_dayuse")
	private Integer domainDayuse;

	@Column(name = "sid")
	private int sid;
	
	@ManyToOne
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "sid", insertable=false, updatable=false,referencedColumnName = "id") 
	private Sendmail sm;
	
	
//	@ManyToOne(cascade = CascadeType.ALL)
//	 @JoinColumn(name = "sid")
//	private Sendmail smail;
//
//	// Constructors
//
//	public Sendmail getSmail() {
//		return smail;
//	}
//
//	public void setSmail(Sendmail smail) {
//		this.smail = smail;
//	}

	public Sendmail getSm() {
		return sm;
	}

	public void setSm(Sendmail sm) {
		this.sm = sm;
	}

	/** default constructor */
	public Maildomains() {
	}

	/** full constructor */
	public Maildomains(String domainName, Integer domainDaylimit,
			Integer domainMinutelimit, String remark,Integer domainDayuse,int sid) {
		this.domainName = domainName;
		this.domainDaylimit = domainDaylimit;
		this.domainMinutelimit = domainMinutelimit;
		this.remark = remark;
		this.domainDayuse = domainDayuse;
		this.sid = sid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDomainName() {
		return this.domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public Integer getDomainDaylimit() {
		return this.domainDaylimit;
	}

	public void setDomainDaylimit(Integer domainDaylimit) {
		this.domainDaylimit = domainDaylimit;
	}

	public Integer getDomainMinutelimit() {
		return this.domainMinutelimit;
	}

	public void setDomainMinutelimit(Integer domainMinutelimit) {
		this.domainMinutelimit = domainMinutelimit;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getDomainDayuse() {
		return domainDayuse;
	}

	public void setDomainDayuse(Integer domainDayuse) {
		this.domainDayuse = domainDayuse;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

}