package com.wssys.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PusSysUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="pus_sys_user")
public class PusSysUser implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	@Column(name="account",updatable=false)private String account;
	@Column(name="pwd",updatable=false)private String pwd;
	private Integer sex;
	private String phone;
	private String mobile;
	private String email;
	private String address;
	private Integer state;
	private Integer isAcceptedSms;
	private String description;
	private Short age;
	private Integer isAcceptedDk;
	private Integer isAcceptedCk;
	@Column(name="createtime",updatable=false,insertable=false) private Timestamp createtime;

	// Constructors

	
	
	
	public Timestamp getCreatetime() {
		return createtime;
	}

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

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	/** default constructor */
	public PusSysUser() {
	}

	public PusSysUser(PusSysUser u) {
		this.id=u.getId();
		this.name = u.getName();
		this.account = u.getAccount();
		this.pwd = u.getPwd();
		this.sex = u.getSex();
		this.phone = u.getPhone();
		this.mobile = u.getMobile();
		this.email = u.getEmail();
		this.address = u.getAddress();
		this.state = u.getState();
		this.description = u.getDescription();
		this.age = u.getAge();
		this.createtime=u.getCreatetime();
	}
	/** minimal constructor */
	public PusSysUser(String account, Integer state) {
		this.account = account;
		this.state = state;
	}

	/** full constructor */
	public PusSysUser(String name, String account, String pwd, Integer sex,
			String phone, String mobile, String email, String address,
			Integer state, String description, Short age,Timestamp createtime) {
		this.name = name;
		this.account = account;
		this.pwd = pwd;
		this.sex = sex;
		this.phone = phone;
		this.mobile = mobile;
		this.email = email;
		this.address = address;
		this.state = state;
		this.description = description;
		this.age = age;
		this.createtime=createtime;
	}

	// Property accessors

	
	public Integer getIsAcceptedSms() {
		return isAcceptedSms;
	}

	public void setIsAcceptedSms(Integer isAcceptedSms) {
		this.isAcceptedSms = isAcceptedSms;
	}
	
	public Integer getId() {
		return this.id;
	}



	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Short getAge() {
		return this.age;
	}

	public void setAge(Short age) {
		this.age = age;
	}

}