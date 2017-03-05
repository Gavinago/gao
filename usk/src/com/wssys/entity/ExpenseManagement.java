package com.wssys.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ExpenseManagement entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="expense_management")
public class ExpenseManagement implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String expensename;
	private Double price;
	private String unit;
	private Timestamp createtime;

	// Constructors

	/** default constructor */
	public ExpenseManagement() {
	}

	/** full constructor */
	public ExpenseManagement(String expensename, Double price, String unit,
			Timestamp createtime) {
		this.expensename = expensename;
		this.price = price;
		this.unit = unit;
		this.createtime = createtime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getExpensename() {
		return this.expensename;
	}

	public void setExpensename(String expensename) {
		this.expensename = expensename;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

}