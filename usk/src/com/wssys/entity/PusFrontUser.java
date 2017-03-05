package com.wssys.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * PusFrontUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "pus_front_user")
public class PusFrontUser implements java.io.Serializable {

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
	private String description;
	private Short age;
	@Column(name="createtime",updatable=false,insertable=false)private Timestamp createtime;

	
//	@OneToOne(cascade={CascadeType.ALL})
//	@JoinColumn(name="address_id")
//	public Companyinfors;
	
	// Constructors

	/** default constructor */
	public PusFrontUser() {
	}

	/** minimal constructor */
	public PusFrontUser(String account, Integer state) {
		this.account = account;
		this.state = state;
	}

	/** full constructor */
	public PusFrontUser(String name, String account, String pwd, Integer sex,
			String phone, String mobile, String email, String address,
			Integer state, String description, Short age, Timestamp createtime) {
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
		this.createtime = createtime;
	}

	// Property accessors

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

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

}