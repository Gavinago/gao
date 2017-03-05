package com.wssys.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Mailreceipt entity. @author MyEclipse Persistence Tools
 */

@Entity
@Table(name="mailreceipt")
public class Mailreceipt implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")private Integer id;
	@Column(name="rmail_id")private Integer rmailId;
	@Column(name="sendtime",updatable=false)private Timestamp sendtime;
	@Column(name="smail_id")private Integer smailId;
	@Column(name="status")private Integer status;
	@Column(name="remark")private String remark;
	@Column(name="createtime",insertable=false)private Timestamp createtime;
	@Column(name="rmail_address")private String rmailaddress;
	@Column(name="smail_address")private String smailaddress;
	@Column(name="batchnumber")private String batchnumber;
	@Column(name="subject")private String subject;
	@Column(name="uniquemailid") private String uniquemailid;
	

	// Constructors

	public String getUniquemailid() {
		return uniquemailid;
	}

	public void setUniquemailid(String uniquemailid) {
		this.uniquemailid = uniquemailid;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBatchnumber() {
		return batchnumber;
	}

	public void setBatchnumber(String batchnumber) {
		this.batchnumber = batchnumber;
	}

	public String getSmailaddress() {
		return smailaddress;
	}

	public void setSmailaddress(String smailaddress) {
		this.smailaddress = smailaddress;
	}

	public String getRmailaddress() {
		return rmailaddress;
	}

	public void setRmailaddress(String rmailaddress) {
		this.rmailaddress = rmailaddress;
	}

	/** default constructor */
	public Mailreceipt() {
	}

	/** full constructor */
	public Mailreceipt(Integer rmailId, Timestamp sendtime, Integer smailId,
			Integer status, String remark, Timestamp createtime,String rmailaddress,String smailaddress) {
		this.rmailId = rmailId;
		this.sendtime = sendtime;
		this.smailId = smailId;
		this.status = status;
		this.remark = remark;
		this.createtime = createtime;
		this.rmailaddress = rmailaddress;
		this.smailaddress = smailaddress;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRmailId() {
		return this.rmailId;
	}

	public void setRmailId(Integer rmailId) {
		this.rmailId = rmailId;
	}

	public Timestamp getSendtime() {
		return this.sendtime;
	}

	public void setSendtime(Timestamp sendtime) {
		this.sendtime = sendtime;
	}

	public Integer getSmailId() {
		return this.smailId;
	}

	public void setSmailId(Integer smailId) {
		this.smailId = smailId;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

}