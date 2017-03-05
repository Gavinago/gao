package com.wssys.bean;

import org.springframework.web.multipart.MultipartFile;

public class ComPanyForm {
	private Integer id;
	private String name;
	private String legalrep;
	private Integer nob;
	private MultipartFile cbl; // 公司营业执照
	private MultipartFile taxtaxreg; // 国税地税登记
	private MultipartFile orgstrucode; // 组织结构代码证
	private String bank;
	private String bankaccount;
	private String address;
	private Integer zipcode;
	private String description;
	private Integer type;
	
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getBankaccount() {
		return bankaccount;
	}

	public void setBankaccount(String bankaccount) {
		this.bankaccount = bankaccount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLegalrep() {
		return legalrep;
	}

	public void setLegalrep(String legalrep) {
		this.legalrep = legalrep;
	}

	public Integer getNob() {
		return nob;
	}

	public void setNob(Integer nob) {
		this.nob = nob;
	}

	public MultipartFile getCbl() {
		return cbl;
	}

	public void setCbl(MultipartFile cbl) {
		this.cbl = cbl;
	}

	public MultipartFile getTaxtaxreg() {
		return taxtaxreg;
	}

	public void setTaxtaxreg(MultipartFile taxtaxreg) {
		this.taxtaxreg = taxtaxreg;
	}

	public MultipartFile getOrgstrucode() {
		return orgstrucode;
	}

	public void setOrgstrucode(MultipartFile orgstrucode) {
		this.orgstrucode = orgstrucode;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}



	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	

	public Integer getZipcode() {
		return zipcode;
	}

	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
