package com.wssys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Mailtask entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="mailtask")
public class Mailtask implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")private Integer id;
	@Column(name="mailsum")private Integer mailsum;
	@Column(name="sented")private Integer sented;
	@Column(name="percentage")private String percentage;

	// Constructors

	/** default constructor */
	public Mailtask() {
	}

	/** full constructor */
	public Mailtask(Integer mailsum, Integer sented, String percentage) {
		this.mailsum = mailsum;
		this.sented = sented;
		this.percentage = percentage;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMailsum() {
		return this.mailsum;
	}

	public void setMailsum(Integer mailsum) {
		this.mailsum = mailsum;
	}

	public Integer getSented() {
		return this.sented;
	}

	public void setSented(Integer sented) {
		this.sented = sented;
	}

	public String getPercentage() {
		return this.percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

}