package com.wssys.bean;

import java.sql.Timestamp;

public class PusSysUserBean {
	private Integer id;
	private String account;
	private String name;
	private String pwd;
	private Integer state;
	private Timestamp createtime;
	private String cname;
	private String ccname;
	private String tel;
	private String phone;
	private String ccphone;
	private String rolename;
	private Integer isAcceptedSms;
	private Integer isAcceptedDk;
	private Integer isAcceptedCk;
	
	
	
	
	public Integer getIsAcceptedDk() {
		return isAcceptedDk;
	}
	public void setIsAcceptedDk(Integer isAcceptedDk) {
		this.isAcceptedDk = isAcceptedDk;
	}
	public Integer getIsAcceptedCk() {
		return isAcceptedCk;
	}
	public void setIsAcceptedCk(Integer isAcceptedCk) {
		this.isAcceptedCk = isAcceptedCk;
	}
	public Integer getIsAcceptedSms() {
		return isAcceptedSms;
	}
	public void setIsAcceptedSms(Integer isAcceptedSms) {
		this.isAcceptedSms = isAcceptedSms;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getCcphone() {
		return ccphone;
	}
	public void setCcphone(String ccphone) {
		this.ccphone = ccphone;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCcname() {
		return ccname;
	}
	public void setCcname(String ccname) {
		this.ccname = ccname;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
