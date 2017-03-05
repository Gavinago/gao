package com.wssys.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.persistence.JoinColumn;

/**
 * Receivingmail entity. @author MyEclipse Persistence Tools
 */

@Entity
@Table(name="receivingmail")
public class Receivingmail implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")private Integer id;
	@Column(name="mail_address")private String mailAddress;
	@Column(name="mail_remark")private String mailRemark;
	@Column(name="createtime",updatable=false)private Timestamp createtime;
	@Column(name="cid") private Integer cid;
	
	@ManyToOne
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "cid", insertable=false, updatable=false,referencedColumnName = "id") 
	private Mailclient mc;
	

	// Constructors


	public Mailclient getMc() {
		return mc;
	}

	public void setMc(Mailclient mc) {
		this.mc = mc;
	}

	/** default constructor */
	public Receivingmail() {
	}

	/** full constructor */
	public Receivingmail(String mailAddress, String mailRemark,
			Timestamp createtime,Integer cid) {
		this.mailAddress = mailAddress;
		this.mailRemark = mailRemark;
		this.createtime = createtime;
		this.cid = cid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMailAddress() {
		return this.mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getMailRemark() {
		return this.mailRemark;
	}

	public void setMailRemark(String mailRemark) {
		this.mailRemark = mailRemark;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

}