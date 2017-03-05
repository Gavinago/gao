package com.gov.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Guest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6896906531737083346L;
	private Integer guestid;
	private String guestname;
	private String guestidcard;
	private String guestphone;
	private String guestaddress;
	private String guestsex;
	private String guestimg;
	private Integer guestroomid;
	private String guestroomname;
	private Integer gueststate;
	private Double guestprice;
	private Double guestroomprice;
	private Double guestroomvip;
	private Double guestroomtotleprice;
	private String guestremark;
	private String guestbooktime;
	private String guestcometime;
	private String guestleavetime;
	private String guestchickouttime;
	private Double timeoutprice;
	private Integer timeouttime;
	private Double guestcash;
	private Integer guestcashreturn;
	private String guestcomepayee;
	private String guestleavepayee;
	private String guestcashremark;
	private String guestcashremarkimg1;
	private String guestcashremarkimg2;
	private String guestcashremarkimg3;
	private String guestcashremarkimg4;
	private Integer guestreviewusing;
	private String guestreview;
	private String guestreviewimg1;
	private String guestreviewimg2;
	private String guestreviewimg3;
	public Guest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getGuestid() {
		return guestid;
	}
	public void setGuestid(Integer guestid) {
		this.guestid = guestid;
	}
	public String getGuestname() {
		return guestname;
	}
	public void setGuestname(String guestname) {
		this.guestname = guestname;
	}
	public String getGuestidcard() {
		return guestidcard;
	}
	public void setGuestidcard(String guestidcard) {
		this.guestidcard = guestidcard;
	}
	public String getGuestphone() {
		return guestphone;
	}
	public void setGuestphone(String guestphone) {
		this.guestphone = guestphone;
	}
	public String getGuestaddress() {
		return guestaddress;
	}
	public void setGuestaddress(String guestaddress) {
		this.guestaddress = guestaddress;
	}
	public String getGuestsex() {
		return guestsex;
	}
	public void setGuestsex(String guestsex) {
		this.guestsex = guestsex;
	}
	public String getGuestimg() {
		return guestimg;
	}
	public void setGuestimg(String guestimg) {
		this.guestimg = guestimg;
	}
	public Integer getGuestroomid() {
		return guestroomid;
	}
	public void setGuestroomid(Integer guestroomid) {
		this.guestroomid = guestroomid;
	}
	public Integer getGueststate() {
		return gueststate;
	}
	public void setGueststate(Integer gueststate) {
		this.gueststate = gueststate;
	}
	public Double getGuestprice() {
		return guestprice;
	}
	public void setGuestprice(Double guestprice) {
		this.guestprice = guestprice;
	}
	public String getGuestremark() {
		return guestremark;
	}
	public void setGuestremark(String guestremark) {
		this.guestremark = guestremark;
	}
	public String getGuestbooktime() {
		return guestbooktime;
	}
	public void setGuestbooktime(String guestbooktime) {
		this.guestbooktime = guestbooktime;
	}
	public String getGuestcometime() {
		return guestcometime;
	}
	public void setGuestcometime(String guestcometime) {
		this.guestcometime = guestcometime;
	}
	public String getGuestleavetime() {
		return guestleavetime;
	}
	public void setGuestleavetime(String guestleavetime) {
		this.guestleavetime = guestleavetime;
	}
	public String getGuestchickouttime() {
		return guestchickouttime;
	}
	public void setGuestchickouttime(String guestchickouttime) {
		this.guestchickouttime = guestchickouttime;
	}
	public Double getTimeoutprice() {
		return timeoutprice;
	}
	public void setTimeoutprice(Double timeoutprice) {
		this.timeoutprice = timeoutprice;
	}
	public Integer getTimeouttime() {
		return timeouttime;
	}
	public void setTimeouttime(Integer timeouttime) {
		this.timeouttime = timeouttime;
	}
	public Double getGuestcash() {
		return guestcash;
	}
	public void setGuestcash(Double guestcash) {
		this.guestcash = guestcash;
	}
	public Integer getGuestcashreturn() {
		return guestcashreturn;
	}
	public void setGuestcashreturn(Integer guestcashreturn) {
		this.guestcashreturn = guestcashreturn;
	}
	public String getGuestcashremark() {
		return guestcashremark;
	}
	public void setGuestcashremark(String guestcashremark) {
		this.guestcashremark = guestcashremark;
	}
	public String getGuestcashremarkimg1() {
		return guestcashremarkimg1;
	}
	public void setGuestcashremarkimg1(String guestcashremarkimg1) {
		this.guestcashremarkimg1 = guestcashremarkimg1;
	}
	public String getGuestcashremarkimg2() {
		return guestcashremarkimg2;
	}
	public void setGuestcashremarkimg2(String guestcashremarkimg2) {
		this.guestcashremarkimg2 = guestcashremarkimg2;
	}
	public String getGuestcashremarkimg3() {
		return guestcashremarkimg3;
	}
	public void setGuestcashremarkimg3(String guestcashremarkimg3) {
		this.guestcashremarkimg3 = guestcashremarkimg3;
	}
	public String getGuestcashremarkimg4() {
		return guestcashremarkimg4;
	}
	public void setGuestcashremarkimg4(String guestcashremarkimg4) {
		this.guestcashremarkimg4 = guestcashremarkimg4;
	}
	public Integer getGuestreviewusing() {
		return guestreviewusing;
	}
	public void setGuestreviewusing(Integer guestreviewusing) {
		this.guestreviewusing = guestreviewusing;
	}
	public String getGuestreview() {
		return guestreview;
	}
	public void setGuestreview(String guestreview) {
		this.guestreview = guestreview;
	}
	public String getGuestreviewimg1() {
		return guestreviewimg1;
	}
	public void setGuestreviewimg1(String guestreviewimg1) {
		this.guestreviewimg1 = guestreviewimg1;
	}
	public String getGuestreviewimg2() {
		return guestreviewimg2;
	}
	public void setGuestreviewimg2(String guestreviewimg2) {
		this.guestreviewimg2 = guestreviewimg2;
	}
	public String getGuestreviewimg3() {
		return guestreviewimg3;
	}
	public void setGuestreviewimg3(String guestreviewimg3) {
		this.guestreviewimg3 = guestreviewimg3;
	}
	public String getGuestroomname() {
		return guestroomname;
	}
	public void setGuestroomname(String guestroomname) {
		this.guestroomname = guestroomname;
	}
	@Override
	public String toString() {
		return "Guest [guestid=" + guestid + ", guestname=" + guestname + ", guestidcard=" + guestidcard
				+ ", guestphone=" + guestphone + ", guestaddress=" + guestaddress + ", guestsex=" + guestsex
				+ ", guestimg=" + guestimg + ", guestroomid=" + guestroomid + ", gueststate=" + gueststate
				+ ", guestprice=" + guestprice + ", guestremark=" + guestremark + ", guestbooktime=" + guestbooktime
				+ ", guestcometime=" + guestcometime + ", guestleavetime=" + guestleavetime
				+ ", guestchickouttime=" + guestchickouttime + ", timeoutprice=" + timeoutprice
				+ ", timeouttime=" + timeouttime + ", guestcash=" + guestcash + ", guestcashreturn=" + guestcashreturn
				+ ", guestcashremark=" + guestcashremark + ", guestcashremarkimg1=" + guestcashremarkimg1
				+ ", guestcashremarkimg2=" + guestcashremarkimg2 + ", guestcashremarkimg3=" + guestcashremarkimg3
				+ ", guestcashremarkimg4=" + guestcashremarkimg4 + ", guestreviewusing=" + guestreviewusing
				+ ", guestreview=" + guestreview + ", guestreviewimg1=" + guestreviewimg1 + ", guestreviewimg2="
				+ guestreviewimg2 + ", guestreviewimg3=" + guestreviewimg3 + "]";
	}
	public Double getGuestroomprice() {
		return guestroomprice;
	}
	public void setGuestroomprice(Double guestroomprice) {
		this.guestroomprice = guestroomprice;
	}
	public Double getGuestroomvip() {
		return guestroomvip;
	}
	public void setGuestroomvip(Double guestroomvip) {
		this.guestroomvip = guestroomvip;
	}
	public Double getGuestroomtotleprice() {
		return guestroomtotleprice;
	}
	public void setGuestroomtotleprice(Double guestroomtotleprice) {
		this.guestroomtotleprice = guestroomtotleprice;
	}
	public String getGuestcomepayee() {
		return guestcomepayee;
	}
	public void setGuestcomepayee(String guestcomepayee) {
		this.guestcomepayee = guestcomepayee;
	}
	public String getGuestleavepayee() {
		return guestleavepayee;
	}
	public void setGuestleavepayee(String guestleavepayee) {
		this.guestleavepayee = guestleavepayee;
	}
	


}
