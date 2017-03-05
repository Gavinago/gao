package com.gov.model;

import java.io.Serializable;
import java.util.Date;

public class TUserLog implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer logId;
	private Integer userId;
	private String ip;
	private String name;
	private String name1;
	private String operation;
	private Date operationTime;
	private Date loginTime;
	private Date logoutTime;
	
	public TUserLog() {
		super();
	}
	public TUserLog getClone(TUserLog tUserLog){
		TUserLog userLog = new TUserLog();
		userLog.setUserId(tUserLog.getUserId());
		userLog.setIp(tUserLog.getIp());
		userLog.setName(tUserLog.getName());
		userLog.setName1(tUserLog.getName1());
		userLog.setOperation(tUserLog.getOperation());
		userLog.setOperationTime(tUserLog.getOperationTime());
		userLog.setLoginTime(tUserLog.getLoginTime());
		userLog.setLogoutTime(tUserLog.getLogoutTime());
		return userLog;
	}
	public Integer getLogId() {
		return logId;
	}
	public void setLogId(Integer logId) {
		this.logId = logId;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public Date getOperationTime() {
		return operationTime;
	}
	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public Date getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Log [logId=" + logId + ", userId=" + userId + ", ip=" + ip + ", name=" + name + ", name1=" + name1
				+ ", operation=" + operation + ", operationTime=" + operationTime + ", loginTime=" + loginTime
				+ ", logoutTime=" + logoutTime + "]";
	}
	
	
}
