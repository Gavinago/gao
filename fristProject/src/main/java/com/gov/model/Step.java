package com.gov.model;

import java.io.Serializable;

public class Step implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer stepid;
	private Integer stepparent;
	private String stepname;
	private String stepurl;
	private Integer clazz;
	private Integer state;
	private String addition;
	private String addition2;
	public Step() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getStepid() {
		return stepid;
	}
	public void setStepid(Integer stepid) {
		this.stepid = stepid;
	}
	public Integer getStepparent() {
		return stepparent;
	}
	public void setStepparent(Integer stepparent) {
		this.stepparent = stepparent;
	}
	public String getStepname() {
		return stepname;
	}
	public void setStepname(String stepname) {
		this.stepname = stepname;
	}
	public String getStepurl() {
		return stepurl;
	}
	public void setStepurl(String stepurl) {
		this.stepurl = stepurl;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getAddition() {
		return addition;
	}
	public void setAddition(String addition) {
		this.addition = addition;
	}
	public String getAddition2() {
		return addition2;
	}
	public void setAddition2(String addition2) {
		this.addition2 = addition2;
	}
	public Integer getClazz() {
		return clazz;
	}
	public void setClazz(Integer clazz) {
		this.clazz = clazz;
	}
	@Override
	public String toString() {
		return "Step [stepid=" + stepid + ", stepparent=" + stepparent + ", stepname=" + stepname + ", stepurl="
				+ stepurl + ", clazz=" + clazz + ", state=" + state + ", addition=" + addition + ", addition2="
				+ addition2 + "]";
	}
	

}
