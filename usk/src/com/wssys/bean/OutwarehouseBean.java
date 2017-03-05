package com.wssys.bean;

import java.sql.Timestamp;


public class OutwarehouseBean {
	private Integer id;
	private String ordernum;
	private String num;
	private String billnum;
	private String salesnum;
	private Integer goodsid;
	private Integer blstatus;
	private String realweight;
	private String weightdiff;
	private String amountdiff;
	private String gbf;
	private String onfares;
	private String pdfpath;
	private String trucknum;
	private String remark;
	private Integer state;
	private Integer userid;
	private Integer status;
	private String cksj;
	private Integer isjyjs;	//是否交易结算
	private Integer iskfp;
	private Integer jsstatus;
	private Double fpmoney;
	private Integer jyjsuserid;	//交易结算操作人员
	private Integer sfkjfp;
	private Timestamp createtime;

	private Double fpje;
	private Double weight;
	private Double addedweight;
	private Double addedprice;
	private Double htje;
	private Double jsje;
	private String contacttsname;
	private String scpdfpath;
	private String blpdfpath;
	private String account;
	private String vmname;
	private String goodsnum;
	private String companyname;
	
	private String addeduseraccount;
	
	private Double cjje;
	
	
	public Double getCjje() {
		return cjje;
	}
	public void setCjje(Double cjje) {
		this.cjje = cjje;
	}
	public Double getFpje() {
		return fpje;
	}
	public void setFpje(Double fpje) {
		this.fpje = fpje;
	}
	public Integer getJsstatus() {
		return jsstatus;
	}
	public void setJsstatus(Integer jsstatus) {
		this.jsstatus = jsstatus;
	}
	public Integer getSfkjfp() {
		return sfkjfp;
	}
	public void setSfkjfp(Integer sfkjfp) {
		this.sfkjfp = sfkjfp;
	}
	public Integer getBlstatus() {
		return blstatus;
	}
	public void setBlstatus(Integer blstatus) {
		this.blstatus = blstatus;
	}
	public Double getAddedweight() {
		return addedweight;
	}
	public void setAddedweight(Double addedweight) {
		this.addedweight = addedweight;
	}
	public String getAddeduseraccount() {
		return addeduseraccount;
	}
	public void setAddeduseraccount(String addeduseraccount) {
		this.addeduseraccount = addeduseraccount;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getGoodsnum() {
		return goodsnum;
	}
	public void setGoodsnum(String goodsnum) {
		this.goodsnum = goodsnum;
	}
	public String getVmname() {
		return vmname;
	}
	public void setVmname(String vmname) {
		this.vmname = vmname;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getBillnum() {
		return billnum;
	}
	public void setBillnum(String billnum) {
		this.billnum = billnum;
	}
	public String getSalesnum() {
		return salesnum;
	}
	public void setSalesnum(String salesnum) {
		this.salesnum = salesnum;
	}
	public Integer getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(Integer goodsid) {
		this.goodsid = goodsid;
	}
	public String getRealweight() {
		return realweight;
	}
	public void setRealweight(String realweight) {
		this.realweight = realweight;
	}
	public String getWeightdiff() {
		return weightdiff;
	}
	public void setWeightdiff(String weightdiff) {
		this.weightdiff = weightdiff;
	}
	public String getAmountdiff() {
		return amountdiff;
	}
	public void setAmountdiff(String amountdiff) {
		this.amountdiff = amountdiff;
	}
	public String getGbf() {
		return gbf;
	}
	public void setGbf(String gbf) {
		this.gbf = gbf;
	}
	public String getOnfares() {
		return onfares;
	}
	public void setOnfares(String onfares) {
		this.onfares = onfares;
	}
	public String getPdfpath() {
		return pdfpath;
	}
	public void setPdfpath(String pdfpath) {
		this.pdfpath = pdfpath;
	}
	public String getTrucknum() {
		return trucknum;
	}
	public void setTrucknum(String trucknum) {
		this.trucknum = trucknum;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCksj() {
		return cksj;
	}
	public void setCksj(String cksj) {
		this.cksj = cksj;
	}
	public Integer getIsjyjs() {
		return isjyjs;
	}
	public void setIsjyjs(Integer isjyjs) {
		this.isjyjs = isjyjs;
	}
	public Integer getIskfp() {
		return iskfp;
	}
	public void setIskfp(Integer iskfp) {
		this.iskfp = iskfp;
	}
	public Double getFpmoney() {
		return fpmoney;
	}
	public void setFpmoney(Double fpmoney) {
		this.fpmoney = fpmoney;
	}
	public Integer getJyjsuserid() {
		return jyjsuserid;
	}
	public void setJyjsuserid(Integer jyjsuserid) {
		this.jyjsuserid = jyjsuserid;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
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
	public Double getHtje() {
		return htje;
	}
	public void setHtje(Double htje) {
		this.htje = htje;
	}
	public Double getJsje() {
		return jsje;
	}
	public void setJsje(Double jsje) {
		this.jsje = jsje;
	}
	public String getContacttsname() {
		return contacttsname;
	}
	public void setContacttsname(String contacttsname) {
		this.contacttsname = contacttsname;
	}
	public String getScpdfpath() {
		return scpdfpath;
	}
	public void setScpdfpath(String scpdfpath) {
		this.scpdfpath = scpdfpath;
	}
	public String getBlpdfpath() {
		return blpdfpath;
	}
	public void setBlpdfpath(String blpdfpath) {
		this.blpdfpath = blpdfpath;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	
}
