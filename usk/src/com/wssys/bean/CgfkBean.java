package com.wssys.bean;

import java.sql.Timestamp;


public class CgfkBean {
	private Integer id;
	private String num;
	private String ordernum;
	private String pdfpath;
	private Integer state;
	private Integer status;
	private Timestamp createtime;
	private Integer isExceedWeight;
	
	private Double weight;
	private Double addedprice;
	private Integer vmid;
	private Integer  goodsid;
	private String vmname;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}
	public String getPdfpath() {
		return pdfpath;
	}
	public void setPdfpath(String pdfpath) {
		this.pdfpath = pdfpath;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	public Integer getIsExceedWeight() {
		return isExceedWeight;
	}
	public void setIsExceedWeight(Integer isExceedWeight) {
		this.isExceedWeight = isExceedWeight;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Double getAddedprice() {
		return addedprice;
	}
	public void setAddedprice(Double addedprice) {
		this.addedprice = addedprice;
	}
	public Integer getVmid() {
		return vmid;
	}
	public void setVmid(Integer vmid) {
		this.vmid = vmid;
	}
	public Integer getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(Integer goodsid) {
		this.goodsid = goodsid;
	}
	public String getVmname() {
		return vmname;
	}
	public void setVmname(String vmname) {
		this.vmname = vmname;
	}
	
	
	
}
