package net.guanzhuo.model;

import java.io.Serializable;

public class TUserTables implements Serializable {

	private Integer userid;
	private Integer tableid;
	private String timeparam;
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getTableid() {
		return tableid;
	}
	public void setTableid(Integer tableid) {
		this.tableid = tableid;
	}
	public String getTimeparam() {
		return timeparam;
	}
	public void setTimeparam(String timepara) {
		this.timeparam = timepara;
	}
	@Override
	public String toString() {
		return "TUserTables [userid=" + userid + ", tableid=" + tableid + ", timeparam=" + timeparam + "]";
	}
	
	
}
