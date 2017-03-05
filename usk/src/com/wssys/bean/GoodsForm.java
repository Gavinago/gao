package com.wssys.bean;

import java.sql.Timestamp;


import org.springframework.web.multipart.MultipartFile;

public class GoodsForm {
	private Integer id;
	private MultipartFile image1;
	private MultipartFile image2;
	private MultipartFile image3;
	private MultipartFile image4;
	private MultipartFile image5;
	private Integer vmid;
	private Double weight;
	private Integer omid;
	private Integer thckid;
	private Integer companyid;
	private Integer contactsid;
	private Integer acceptanceid;
	private Timestamp createtime;
	private Double addedprice;
	private Integer isadded;
	private String remark;
	private String goodsnum;
	private Integer state;
	private Integer gyslxrid;	//供应商联系人id
	private String qq;
	private String imagefileid;
	private String rkgbf;
	private String xhf;
	
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

	public String getImagefileid() {
		return imagefileid;
	}

	public void setImagefileid(String imagefileid) {
		this.imagefileid = imagefileid;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public Integer getGyslxrid() {
		return gyslxrid;
	}

	public void setGyslxrid(Integer gyslxrid) {
		this.gyslxrid = gyslxrid;
	}

	public Integer getThckid() {
		return thckid;
	}

	public void setThckid(Integer thckid) {
		this.thckid = thckid;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MultipartFile getImage1() {
		return image1;
	}

	public void setImage1(MultipartFile image1) {
		this.image1 = image1;
	}

	public MultipartFile getImage2() {
		return image2;
	}

	public void setImage2(MultipartFile image2) {
		this.image2 = image2;
	}

	public MultipartFile getImage3() {
		return image3;
	}

	public void setImage3(MultipartFile image3) {
		this.image3 = image3;
	}

	public MultipartFile getImage4() {
		return image4;
	}

	public void setImage4(MultipartFile image4) {
		this.image4 = image4;
	}

	public MultipartFile getImage5() {
		return image5;
	}

	public void setImage5(MultipartFile image5) {
		this.image5 = image5;
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

}
