package net.guanzhuo.model;

import java.io.Serializable;

public class SynsTables implements Serializable{
	
	private Integer tableid;
	private Integer includeid;
	private String projectid;
	private String remotename;
	private String tablecomment;
	private String remotequery;
	private String localcreate;
	private String localname;
	private String tabletype;
	private String lasttime;
	private String timeformat;
	private String filterformat;
	private String orderformat;
	private String timefield;
	private String filerows;
	
	
	
	public Integer getTableid() {
		return tableid;
	}



	public void setTableid(Integer tableid) {
		this.tableid = tableid;
	}



	public Integer getIncludeid() {
		return includeid;
	}



	public void setIncludeid(Integer includeid) {
		this.includeid = includeid;
	}



	public String getProjectid() {
		return projectid;
	}



	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}



	public String getRemotename() {
		return remotename;
	}



	public void setRemotename(String remotename) {
		this.remotename = remotename;
	}



	public String getTablecomment() {
		return tablecomment;
	}



	public void setTablecomment(String tablecomment) {
		this.tablecomment = tablecomment;
	}



	public String getRemotequery() {
		return remotequery;
	}



	public void setRemotequery(String remotequery) {
		this.remotequery = remotequery;
	}



	public String getLocalcreate() {
		return localcreate;
	}



	public void setLocalcreate(String localcreate) {
		this.localcreate = localcreate;
	}



	public String getLocalname() {
		return localname;
	}



	public void setLocalname(String localname) {
		this.localname = localname;
	}



	public String getTabletype() {
		return tabletype;
	}



	public void setTabletype(String tabletype) {
		this.tabletype = tabletype;
	}



	public String getLasttime() {
		return lasttime;
	}



	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}



	public String getFilterformat() {
		return filterformat;
	}



	public void setFilterformat(String filterformat) {
		this.filterformat = filterformat;
	}



	public String getOrderformat() {
		return orderformat;
	}



	public void setOrderformat(String orderformat) {
		this.orderformat = orderformat;
	}



	public String getTimefield() {
		return timefield;
	}



	public void setTimefield(String timefield) {
		this.timefield = timefield;
	}



	public String getFilerows() {
		return filerows;
	}



	public void setFilerows(String filerows) {
		this.filerows = filerows;
	}



	@Override
	public String toString() {
		return "SynsTables [tableid=" + tableid + ", includeid=" + includeid + ", projectid=" + projectid
				+ ", remotename=" + remotename + ", tablecomment=" + tablecomment + ", remotequery=" + remotequery
				+ ", localcreate=" + localcreate + ", localname=" + localname + ", tabletype=" + tabletype
				+ ", lasttime=" + lasttime + ", timeformat=" + timeformat + ", filterformat=" + filterformat
				+ ", orderformat=" + orderformat + ", timefield=" + timefield + ", filerows=" + filerows + "]";
	}
	
	

}
