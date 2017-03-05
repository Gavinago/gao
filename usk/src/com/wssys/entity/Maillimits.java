package com.wssys.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Maillimits entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="maillimits")
public class Maillimits implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id") private Integer id;
	@Column(name="smail_id")private String smailId;
	@Column(name="subject")private String subject;
	@Column(name="rmail_id")private String rmailId;
	@Column(name="is_first_open")private Integer isFirstOpen;
	@Column(name="opentime")private Timestamp opentime;
	@Column(name="ip")private String ip;
	@Column(name="batchnumber")private String batchnumber;
	@Column(name="sendtime")private Timestamp sendtime;

	// Constructors

	/** default constructor */
	public Maillimits() {
	}

	/** full constructor */
	public Maillimits(String smailId, String subject, String rmailId,
			Integer isFirstOpen, Timestamp opentime, String ip,
			String batchnumber, Timestamp sendtime) {
		this.smailId = smailId;
		this.subject = subject;
		this.rmailId = rmailId;
		this.isFirstOpen = isFirstOpen;
		this.opentime = opentime;
		this.ip = ip;
		this.batchnumber = batchnumber;
		this.sendtime = sendtime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSmailId() {
		return this.smailId;
	}

	public void setSmailId(String smailId) {
		this.smailId = smailId;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getRmailId() {
		return this.rmailId;
	}

	public void setRmailId(String rmailId) {
		this.rmailId = rmailId;
	}

	public Integer getIsFirstOpen() {
		return this.isFirstOpen;
	}

	public void setIsFirstOpen(Integer isFirstOpen) {
		this.isFirstOpen = isFirstOpen;
	}

	public Timestamp getOpentime() {
		return this.opentime;
	}

	public void setOpentime(Timestamp opentime) {
		this.opentime = opentime;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getBatchnumber() {
		return this.batchnumber;
	}

	public void setBatchnumber(String batchnumber) {
		this.batchnumber = batchnumber;
	}

	public Timestamp getSendtime() {
		return this.sendtime;
	}

	public void setSendtime(Timestamp sendtime) {
		this.sendtime = sendtime;
	}

}