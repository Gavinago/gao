package com.wssys.bean;

import java.sql.Timestamp;

public class ConfirmPM {
	private Integer id;
	private String sellcompanyname;	//出卖人
	private String vmname;	//产品规格
	private String omname;	//产地
	private double weight;
	private double addedweight;
	private double addedprice;
	private double realweight;
	private String legalrep;	//法定代表人
	private String bank;	//
	private String bankaccount;	//
	private String address;	//
	private Integer cid;
	private Integer buyersid;
	private String procurementnum;	//采购合同编号
	private String ordernum;
	private Integer gcontactsid;
	private Integer gyscontacts;	//供应商联系人
	private String goodsnum;
	private String dwname;
	private Integer acceptanceid;	//仓库经办人
	private String billoflading;
	private Timestamp rksj;
	private String hwbz;
	private String ccf;
	private String xhf;
	private String rkgbf;
	
	
	
	public String getCcf() {
		return ccf;
	}
	public void setCcf(String ccf) {
		this.ccf = ccf;
	}
	public String getXhf() {
		return xhf;
	}
	public void setXhf(String xhf) {
		this.xhf = xhf;
	}
	public String getRkgbf() {
		return rkgbf;
	}
	public void setRkgbf(String rkgbf) {
		this.rkgbf = rkgbf;
	}
	public double getRealweight() {
		return realweight;
	}
	public void setRealweight(double realweight) {
		this.realweight = realweight;
	}
	public String getHwbz() {
		return hwbz;
	}
	public void setHwbz(String hwbz) {
		this.hwbz = hwbz;
	}
	public Timestamp getRksj() {
		return rksj;
	}
	public void setRksj(Timestamp rksj) {
		this.rksj = rksj;
	}
	public double getAddedweight() {
		return addedweight;
	}
	public void setAddedweight(double addedweight) {
		this.addedweight = addedweight;
	}
	public String getBilloflading() {
		return billoflading;
	}
	public void setBilloflading(String billoflading) {
		this.billoflading = billoflading;
	}
	public Integer getAcceptanceid() {
		return acceptanceid;
	}
	public void setAcceptanceid(Integer acceptanceid) {
		this.acceptanceid = acceptanceid;
	}
	public String getDwname() {
		return dwname;
	}
	public void setDwname(String dwname) {
		this.dwname = dwname;
	}
	public String getGoodsnum() {
		return goodsnum;
	}
	public void setGoodsnum(String goodsnum) {
		this.goodsnum = goodsnum;
	}
	public String getOmname() {
		return omname;
	}
	public void setOmname(String omname) {
		this.omname = omname;
	}
	public Integer getGyscontacts() {
		return gyscontacts;
	}
	public void setGyscontacts(Integer gyscontacts) {
		this.gyscontacts = gyscontacts;
	}
	public Integer getGcontactsid() {
		return gcontactsid;
	}
	public void setGcontactsid(Integer gcontactsid) {
		this.gcontactsid = gcontactsid;
	}
	public String getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}
	public String getProcurementnum() {
		return procurementnum;
	}
	public void setProcurementnum(String procurementnum) {
		this.procurementnum = procurementnum;
	}
	public Integer getBuyersid() {
		return buyersid;
	}
	public void setBuyersid(Integer buyersid) {
		this.buyersid = buyersid;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getLegalrep() {
		return legalrep;
	}
	public void setLegalrep(String legalrep) {
		this.legalrep = legalrep;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getBankaccount() {
		return bankaccount;
	}
	public void setBankaccount(String bankaccount) {
		this.bankaccount = bankaccount;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSellcompanyname() {
		return sellcompanyname;
	}
	public void setSellcompanyname(String sellcompanyname) {
		this.sellcompanyname = sellcompanyname;
	}
	public String getVmname() {
		return vmname;
	}
	public void setVmname(String vmname) {
		this.vmname = vmname;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getAddedprice() {
		return addedprice;
	}
	public void setAddedprice(double addedprice) {
		this.addedprice = addedprice;
	}
	
	
}
