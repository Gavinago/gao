package net.guanzhuo.model;

import java.io.Serializable;

public class TUser implements Serializable {

	private Integer userid;
	private Integer usertypeid;
	private String username;
	private String userenable;
	private String userfull;
	private String password;
	private String usermobile;
	private String userphone;
	private String useremail;
	private String useraddress;
	private String userparam;
	private String userip;
	private String usersession;
	private String lasttime;
	private String timestamp;
	
	
	
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getUsertypeid() {
		return usertypeid;
	}
	public void setUsertypeid(Integer usertypeid) {
		this.usertypeid = usertypeid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}



	public String getUserenable() {
		return userenable;
	}



	public void setUserenable(String userenable) {
		this.userenable = userenable;
	}



	public String getUserfull() {
		return userfull;
	}



	public void setUserfull(String userfull) {
		this.userfull = userfull;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getUsermobile() {
		return usermobile;
	}



	public void setUsermobile(String usermobile) {
		this.usermobile = usermobile;
	}



	public String getUserphone() {
		return userphone;
	}



	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}



	public String getUseremail() {
		return useremail;
	}



	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}



	public String getUseraddress() {
		return useraddress;
	}



	public void setUseraddress(String useraddress) {
		this.useraddress = useraddress;
	}



	public String getUserparam() {
		return userparam;
	}



	public void setUserparam(String userparam) {
		this.userparam = userparam;
	}



	public String getUserip() {
		return userip;
	}



	public void setUserip(String userip) {
		this.userip = userip;
	}



	public String getUsersession() {
		return usersession;
	}



	public void setUsersession(String usersession) {
		this.usersession = usersession;
	}



	public String getLasttime() {
		return lasttime;
	}



	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}



	public String getTimestamp() {
		return timestamp;
	}



	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}



	@Override
	public String toString() {
		return "TUser [userid=" + userid + ", usertypeid=" + usertypeid + ", username=" + username + ", userenable="
				+ userenable + ", userfull=" + userfull + ", password=" + password + ", usermobile=" + usermobile
				+ ", userphone=" + userphone + ", useremail=" + useremail + ", useraddress=" + useraddress
				+ ", userparam=" + userparam + ", userip=" + userip + ", usersession=" + usersession + ", lasttime="
				+ lasttime + ", timestamp=" + timestamp + "]";
	}
	
	
	
	


}
