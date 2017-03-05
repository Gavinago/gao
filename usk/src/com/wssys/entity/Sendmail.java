package com.wssys.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Sendmail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="sendmail")
public class Sendmail implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name="id") private Integer id;
	@Column(name="mail_address") private String mailAddress;
	@Column(name="mail_remark") private String mailRemark;
	@Column(name="createtime",updatable=false) private Timestamp createtime;
	@Column(name="mail_pwd") private String mailPwd;
	@Column(name="mail_port") private Integer mailPort;
	@Column(name="mai_smtp_address") private String maiSmtpAddress;
	@Column(name="dayuse_limit") private Integer dayuseLimit;
	@Column(name="domain_ids") private String domainids;
	@Column(name="sendcount")private int sendcount;
	@Column(name="taskid",nullable=false)private int taskid;
	
	
//	 @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="smail")
//	 private List<Maildomains> listMaildomains;
//	
//
//	public List<Maildomains> getListExamUserAns() {
//		return listMaildomains;
//	}
//
//	public void setListExamUserAns(List<Maildomains> listMaildomains) {
//		this.listMaildomains = listMaildomains;
//	}

	public int getTaskid() {
		return taskid;
	}

	public void setTaskid(int taskid) {
		this.taskid = taskid;
	}

	public int getSendcount() {
		return sendcount;
	}

	public void setSendcount(int sendcount) {
		this.sendcount = sendcount;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	// Constructors

	/** default constructor */
	public Sendmail() {
	}

	/** full constructor */
	public Sendmail(String mailAddress, String mailRemark,
			Timestamp createtime, String mailPwd, Integer mailPort,
			String maiSmtpAddress, Integer dayuseLimit,String domainids) {
		this.mailAddress = mailAddress;
		this.mailRemark = mailRemark;
		this.createtime = createtime;
		this.mailPwd = mailPwd;
		this.mailPort = mailPort;
		this.maiSmtpAddress = maiSmtpAddress;
		this.dayuseLimit = dayuseLimit;
		this.domainids = domainids;
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

	public String getMailPwd() {
		return this.mailPwd;
	}

	public void setMailPwd(String mailPwd) {
		this.mailPwd = mailPwd;
	}

	public Integer getMailPort() {
		return this.mailPort;
	}

	public void setMailPort(Integer mailPort) {
		this.mailPort = mailPort;
	}

	public String getMaiSmtpAddress() {
		return this.maiSmtpAddress;
	}

	public void setMaiSmtpAddress(String maiSmtpAddress) {
		this.maiSmtpAddress = maiSmtpAddress;
	}

	public Integer getDayuseLimit() {
		return this.dayuseLimit;
	}

	public void setDayuseLimit(Integer dayuseLimit) {
		this.dayuseLimit = dayuseLimit;
	}
	
	public String getDomainids() {
		return domainids;
	}

	public void setDomainids(String domainids) {
		this.domainids = domainids;
	}

}