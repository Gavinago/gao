package com.wssys.test;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 
 * @author
 * @date
 */
public class MyAuthenticator extends Authenticator {
	 private String username;
	 private String password;
	
	
	
	 /**
	 *
	 * @author
	 * @date
	 * @param username
	 * @param password
	 */
	 public MyAuthenticator(String username, String password) {
	 super();
	 this.username = username;
	 this.password = password;
	 }
	
	 protected PasswordAuthentication getPasswordAuthentication() {
	 return new PasswordAuthentication(username, password);
	 }

//	String userName = null;
//	String password = null;
//
//	public MyAuthenticator() {
//	}
//
//	public PasswordAuthentication performCheck(String userName, String password) {
//		this.userName = userName;
//		this.password = password;
//		return getPasswordAuthentication();
//	}
//
//	protected PasswordAuthentication getPasswordAuthentication() {
//		return new PasswordAuthentication(userName, password);
//	}

}
