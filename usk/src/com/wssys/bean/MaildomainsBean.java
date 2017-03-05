package com.wssys.bean;

public class MaildomainsBean {
	private Integer id;

	private String domainName;

	Integer domainDaylimit;

	private Integer domainMinutelimit;

	private String remark;
	private Integer domainDayuse;

	private int sid;
	private String sendmailaddress;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public Integer getDomainDaylimit() {
		return domainDaylimit;
	}

	public void setDomainDaylimit(Integer domainDaylimit) {
		this.domainDaylimit = domainDaylimit;
	}

	public Integer getDomainMinutelimit() {
		return domainMinutelimit;
	}

	public void setDomainMinutelimit(Integer domainMinutelimit) {
		this.domainMinutelimit = domainMinutelimit;
	}

	public String getRemark() {
		return remark;
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

	public String getSendmailaddress() {
		return sendmailaddress;
	}

	public void setSendmailaddress(String sendmailaddress) {
		this.sendmailaddress = sendmailaddress;
	}



}
