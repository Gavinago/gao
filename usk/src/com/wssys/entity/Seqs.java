package com.wssys.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Seqs entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "seqs")
public class Seqs implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String goodsnum;
	private String cknum;
	private String cgnum;

	// Constructors

	/** default constructor */
	public Seqs() {
	}

	// Property accessors

	public String getGoodsnum() {
		return this.goodsnum;
	}

	public String getCgnum() {
		return cgnum;
	}

	public void setCgnum(String cgnum) {
		this.cgnum = cgnum;
	}

	public String getCknum() {
		return cknum;
	}

	public void setCknum(String cknum) {
		this.cknum = cknum;
	}

	public void setGoodsnum(String goodsnum) {
		this.goodsnum = goodsnum;
	}

}