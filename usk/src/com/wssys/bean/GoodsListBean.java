package com.wssys.bean;

import java.sql.Timestamp;


public class GoodsListBean {
	private Integer id;
	private Integer vmid;
	private String vmname;
	private Double weight;
	private Double addedweight;
	private Integer omid;
	private String omname;
	private Integer companyid;
	private String companyname;
	private Integer contactsid;
	private String contactsname;
	private Integer acceptanceid;
	private String acceptancename;
	private Timestamp createtime;
	private Double addedprice;
	private Integer isadded;
	private String remark;
	private String goodsnum;
	private String account;
	private Integer odstatus;
	private String rkgbf;
	private String xhf;
	private String cdpdfpath;
	private Integer cdid;
	private Integer twoDimensionCodeid;
	private String tdcimagepath;
	
	
	
	public String getTdcimagepath() {
		return tdcimagepath;
	}
	public void setTdcimagepath(String tdcimagepath) {
		this.tdcimagepath = tdcimagepath;
	}
	public Integer getTwoDimensionCodeid() {
		return twoDimensionCodeid;
	}
	public void setTwoDimensionCodeid(Integer twoDimensionCodeid) {
		this.twoDimensionCodeid = twoDimensionCodeid;
	}
	public String getCdpdfpath() {
		return cdpdfpath;
	}
	public void setCdpdfpath(String cdpdfpath) {
		this.cdpdfpath = cdpdfpath;
	}
	public Integer getCdid() {
		return cdid;
	}
	public void setCdid(Integer cdid) {
		this.cdid = cdid;
	}
	public String getRkgbf() {
		return rkgbf;
	}
	public void setRkgbf(String rkgbf) {
		this.rkgbf = rkgbf;
	}
	public String getXhf() {
		return xhf;
	}
	public void setXhf(String xhf) {
		this.xhf = xhf;
	}
	private byte []image1;
	
	
	
	
	public byte[] getImage1() {
		return image1;
	}
	public void setImage1(byte[] image1) {
		this.image1 = image1;
	}
	public Integer getOdstatus() {
		return odstatus;
	}
	public void setOdstatus(Integer odstatus) {
		this.odstatus = odstatus;
	}
	public Double getAddedweight() {
		return addedweight;
	}
	public void setAddedweight(Double addedweight) {
		this.addedweight = addedweight;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getVmid() {
		return vmid;
	}
	public void setVmid(Integer vmid) {
		this.vmid = vmid;
	}
	public String getVmname() {
		return vmname;
	}
	public void setVmname(String vmname) {
		this.vmname = vmname;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Integer getOmid() {
		return omid;
	}
	public void setOmid(Integer omid) {
		this.omid = omid;
	}
	public String getOmname() {
		return omname;
	}
	public void setOmname(String omname) {
		this.omname = omname;
	}
	public Integer getCompanyid() {
		return companyid;
	}
	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public Integer getContactsid() {
		return contactsid;
	}
	public void setContactsid(Integer contactsid) {
		this.contactsid = contactsid;
	}
	public String getContactsname() {
		return contactsname;
	}
	public void setContactsname(String contactsname) {
		this.contactsname = contactsname;
	}
	public Integer getAcceptanceid() {
		return acceptanceid;
	}
	public void setAcceptanceid(Integer acceptanceid) {
		this.acceptanceid = acceptanceid;
	}
	public String getAcceptancename() {
		return acceptancename;
	}
	public void setAcceptancename(String acceptancename) {
		this.acceptancename = acceptancename;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	public Double getAddedprice() {
		return addedprice;
	}
	public void setAddedprice(Double addedprice) {
		this.addedprice = addedprice;
	}
	public Integer getIsadded() {
		return isadded;
	}
	public void setIsadded(Integer isadded) {
		this.isadded = isadded;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getGoodsnum() {
		return goodsnum;
	}
	public void setGoodsnum(String goodsnum) {
		this.goodsnum = goodsnum;
	}
	
	
	
}
