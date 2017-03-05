package com.wssys.bean;

import java.sql.Timestamp;

public class OrdersBean {
	/**
	 * 货物信息
	 */
	private Integer id;
	private Integer vmid;
	private Integer scrid;
	private Integer owstatus;
	private Double weight;
	private Double realweight;
	private Double addedweight;
	private Double zj;	//总价
	private Double xhf;
	private Double rkgbf;

	private String ownum;
	private Integer omid;
	private Integer companyid;
	private Integer contactsid;
	private Integer piskfp;
	private Integer acceptanceid;
	private Integer owuserid;
	private Timestamp createtime;
	private Double addedprice;
	private Integer isadded;
	private String remark;
	private String goodsnum;
	private String blpdfpath;
	private String vmname;
	private String omname;
	private Integer blstatus;
	private Integer scstatus;
	private Integer isExceedWeight;
	private Integer owid;
	/**
	 * 订单信息
	 */
	private Integer goodsid;
	private Integer status;
	private String ordernum;
	private Integer buyersid;
	private String salescontractnum;
	private String procurementnum;
	private String billoflading;
	private String deliverynum;
	private Integer state;
	private Timestamp ordertime;
	private String buyaccout;
	private String name;
	private Integer pstatus;
	private Integer purid;

	private Integer fkstatus;
	private Double fkje;
	private Integer jsstatus;
	private Double jsje;
	private Double dfk;	//代付款
	private Integer sfkjfp;
	private Double fpje;
	private Integer isjyjs;
	private Integer iskfp;
	private Integer psfkjfp;
	private Double hk;	//货款
	private Double jyf; //交易费
	private Double ccf;	//仓储费
	private String buyerCompanyName;
	private String salesnum;	//销售合同编号 

	
	private String dhrq;
	private String	jlfs;
	private String bzfs;
	private String ch;
	private String xh;
	private String cz;
	private Integer jianshu;
	private Integer hwzl;
	private String hw;
	private String hwbz;
	private String zlcbpd;
	private String bhgcpclfs;
	private Integer cdid;
	private Integer twoDimensionCodeid;
	private String owclUserName;
	private String sellerCompanyName;
	private String cksj;
	private String ckxh;
	private String trucknum;
	
	private String tdpdf;
	
	
	
	
	
	public Integer getPsfkjfp() {
		return psfkjfp;
	}

	public void setPsfkjfp(Integer psfkjfp) {
		this.psfkjfp = psfkjfp;
	}

	public Integer getPiskfp() {
		return piskfp;
	}

	public void setPiskfp(Integer piskfp) {
		this.piskfp = piskfp;
	}

	public String getOwnum() {
		return ownum;
	}

	public void setOwnum(String ownum) {
		this.ownum = ownum;
	}

	public Integer getOwid() {
		return owid;
	}

	public void setOwid(Integer owid) {
		this.owid = owid;
	}

	public String getTdpdf() {
		return tdpdf;
	}

	public void setTdpdf(String tdpdf) {
		this.tdpdf = tdpdf;
	}

	public Double getDfk() {
		return dfk;
	}

	public void setDfk(Double dfk) {
		this.dfk = dfk;
	}

	public String getCkxh() {
		return ckxh;
	}

	public void setCkxh(String ckxh) {
		this.ckxh = ckxh;
	}

	public String getTrucknum() {
		return trucknum;
	}

	public void setTrucknum(String trucknum) {
		this.trucknum = trucknum;
	}

	public String getCksj() {
		return cksj;
	}

	public void setCksj(String cksj) {
		this.cksj = cksj;
	}

	public String getSellerCompanyName() {
		return sellerCompanyName;
	}

	public void setSellerCompanyName(String sellerCompanyName) {
		this.sellerCompanyName = sellerCompanyName;
	}

	public String getOwclUserName() {
		return owclUserName;
	}

	public void setOwclUserName(String owclUserName) {
		this.owclUserName = owclUserName;
	}

	public Integer getOwuserid() {
		return owuserid;
	}

	public void setOwuserid(Integer owuserid) {
		this.owuserid = owuserid;
	}

	public String getSalesnum() {
		return salesnum;
	}

	public void setSalesnum(String salesnum) {
		this.salesnum = salesnum;
	}

	public String getDhrq() {
		return dhrq;
	}

	public void setDhrq(String dhrq) {
		this.dhrq = dhrq;
	}

	public String getJlfs() {
		return jlfs;
	}

	public void setJlfs(String jlfs) {
		this.jlfs = jlfs;
	}

	public String getBzfs() {
		return bzfs;
	}

	public void setBzfs(String bzfs) {
		this.bzfs = bzfs;
	}

	public String getCh() {
		return ch;
	}

	public void setCh(String ch) {
		this.ch = ch;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getCz() {
		return cz;
	}

	public void setCz(String cz) {
		this.cz = cz;
	}

	public Integer getJianshu() {
		return jianshu;
	}

	public void setJianshu(Integer jianshu) {
		this.jianshu = jianshu;
	}

	public Integer getHwzl() {
		return hwzl;
	}

	public void setHwzl(Integer hwzl) {
		this.hwzl = hwzl;
	}

	public String getHw() {
		return hw;
	}

	public void setHw(String hw) {
		this.hw = hw;
	}

	public String getHwbz() {
		return hwbz;
	}

	public void setHwbz(String hwbz) {
		this.hwbz = hwbz;
	}

	public String getZlcbpd() {
		return zlcbpd;
	}

	public void setZlcbpd(String zlcbpd) {
		this.zlcbpd = zlcbpd;
	}

	public String getBhgcpclfs() {
		return bhgcpclfs;
	}

	public void setBhgcpclfs(String bhgcpclfs) {
		this.bhgcpclfs = bhgcpclfs;
	}

	public Integer getCdid() {
		return cdid;
	}

	public void setCdid(Integer cdid) {
		this.cdid = cdid;
	}

	public Integer getTwoDimensionCodeid() {
		return twoDimensionCodeid;
	}

	public void setTwoDimensionCodeid(Integer twoDimensionCodeid) {
		this.twoDimensionCodeid = twoDimensionCodeid;
	}

	public String getBuyerCompanyName() {
		return buyerCompanyName;
	}

	public void setBuyerCompanyName(String buyerCompanyName) {
		this.buyerCompanyName = buyerCompanyName;
	}

	public Double getCcf() {
		return ccf;
	}

	public void setCcf(Double ccf) {
		this.ccf = ccf;
	}

	public Double getRkgbf() {
		return rkgbf;
	}

	public void setRkgbf(Double rkgbf) {
		this.rkgbf = rkgbf;
	}

	public Double getXhf() {
		return xhf;
	}

	public void setXhf(Double xhf) {
		this.xhf = xhf;
	}

	public Double getZj() {
		return zj;
	}

	public void setZj(Double zj) {
		this.zj = zj;
	}
	public Double getJyf() {
		return jyf;
	}

	public void setJyf(Double jyf) {
		this.jyf = jyf;
	}

	public Double getHk() {
		return hk;
	}

	public void setHk(Double hk) {
		this.hk = hk;
	}

	public Double getRealweight() {
		return realweight;
	}

	public void setRealweight(Double realweight) {
		this.realweight = realweight;
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

	public Integer getOwstatus() {
		return owstatus;
	}

	public void setOwstatus(Integer owstatus) {
		this.owstatus = owstatus;
	}

	public Integer getScrid() {
		return scrid;
	}

	public void setScrid(Integer scrid) {
		this.scrid = scrid;
	}

	public Integer getScstatus() {
		return scstatus;
	}

	public void setScstatus(Integer scstatus) {
		this.scstatus = scstatus;
	}

	public Double getAddedweight() {
		return addedweight;
	}

	public void setAddedweight(Double addedweight) {
		this.addedweight = addedweight;
	}

	public Integer getSfkjfp() {
		return sfkjfp;
	}

	public void setSfkjfp(Integer sfkjfp) {
		this.sfkjfp = sfkjfp;
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

	public Double getJsje() {
		return jsje;
	}

	public void setJsje(Double jsje) {
		this.jsje = jsje;
	}

	public Integer getFkstatus() {
		return fkstatus;
	}

	public void setFkstatus(Integer fkstatus) {
		this.fkstatus = fkstatus;
	}

	public Double getFkje() {
		return fkje;
	}

	public void setFkje(Double fkje) {
		this.fkje = fkje;
	}

	public Integer getIsExceedWeight() {
		return isExceedWeight;
	}

	public void setIsExceedWeight(Integer isExceedWeight) {
		this.isExceedWeight = isExceedWeight;
	}

	private String owpdfpath;

	public String getOwpdfpath() {
		return owpdfpath;
	}

	public void setOwpdfpath(String owpdfpath) {
		this.owpdfpath = owpdfpath;
	}

	public String getBlpdfpath() {
		return blpdfpath;
	}

	public void setBlpdfpath(String blpdfpath) {
		this.blpdfpath = blpdfpath;
	}

	public Integer getBlstatus() {
		return blstatus;
	}

	public void setBlstatus(Integer blstatus) {
		this.blstatus = blstatus;
	}

	public Integer getPurid() {
		return purid;
	}

	public void setPurid(Integer purid) {
		this.purid = purid;
	}

	public Integer getPstatus() {
		return pstatus;
	}

	public void setPstatus(Integer pstatus) {
		this.pstatus = pstatus;
	}

	// public Integer getIspayment() {
	// return ispayment;
	// }
	// public void setIspayment(Integer ispayment) {
	// this.ispayment = ispayment;
	// }
	public String getBuyaccout() {
		return buyaccout;
	}

	public void setBuyaccout(String buyaccout) {
		this.buyaccout = buyaccout;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String pdfpath;

	public String getPdfpath() {
		return pdfpath;
	}

	public void setPdfpath(String pdfpath) {
		this.pdfpath = pdfpath;
	}

	public Timestamp getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Timestamp ordertime) {
		this.ordertime = ordertime;
	}

	public Integer getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(Integer goodsid) {
		this.goodsid = goodsid;
	}

	public String getOmname() {
		return omname;
	}

	public void setOmname(String omname) {
		this.omname = omname;
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

	public Integer getVmid() {
		return vmid;
	}

	public void setVmid(Integer vmid) {
		this.vmid = vmid;
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

	public Integer getCompanyid() {
		return companyid;
	}

	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}

	public Integer getContactsid() {
		return contactsid;
	}

	public void setContactsid(Integer contactsid) {
		this.contactsid = contactsid;
	}

	public Integer getAcceptanceid() {
		return acceptanceid;
	}

	public void setAcceptanceid(Integer acceptanceid) {
		this.acceptanceid = acceptanceid;
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

	public String getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}

	public Integer getBuyersid() {
		return buyersid;
	}

	public void setBuyersid(Integer buyersid) {
		this.buyersid = buyersid;
	}

	public String getSalescontractnum() {
		return salescontractnum;
	}

	public void setSalescontractnum(String salescontractnum) {
		this.salescontractnum = salescontractnum;
	}

	public String getProcurementnum() {
		return procurementnum;
	}

	public void setProcurementnum(String procurementnum) {
		this.procurementnum = procurementnum;
	}

	public String getBilloflading() {
		return billoflading;
	}

	public void setBilloflading(String billoflading) {
		this.billoflading = billoflading;
	}

	public String getDeliverynum() {
		return deliverynum;
	}

	public void setDeliverynum(String deliverynum) {
		this.deliverynum = deliverynum;
	}

}
