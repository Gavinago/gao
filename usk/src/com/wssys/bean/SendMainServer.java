package com.wssys.bean;

public class SendMainServer {
	public  String emailHost ;
	public  String emailFrom ;
	public  String emailUsername ;
	public  String emailPassword ;
	public  Integer port ;
	
	public int domainid;
	
	public int getDomainid() {
		return domainid;
	}
	public void setDomainid(int domainid) {
		this.domainid = domainid;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public String getEmailHost() {
		return emailHost;
	}
	public void setEmailHost(String emailHost) {
		this.emailHost = emailHost;
	}
	public String getEmailFrom() {
		return emailFrom;
	}
	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}
	public String getEmailUsername() {
		return emailUsername;
	}
	public void setEmailUsername(String emailUsername) {
		this.emailUsername = emailUsername;
	}
	public String getEmailPassword() {
		return emailPassword;
	}
	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}
	
	
}
