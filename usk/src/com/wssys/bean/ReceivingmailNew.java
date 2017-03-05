package com.wssys.bean;

import java.sql.Timestamp;


public class ReceivingmailNew {
	private Integer id;
	private String mailAddress;
	private String mailRemark;
	private Timestamp createtime;
	private Integer cid;
	private String clientname;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getMailRemark() {
		return mailRemark;
	}

	public void setMailRemark(String mailRemark) {
		this.mailRemark = mailRemark;
	}

	public Timestamp getCreatetime() {
		return createtime;
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

	public String getClientname() {
		return clientname;
	}

	public void setClientname(String clientname) {
		this.clientname = clientname;
	}

}
