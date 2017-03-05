package com.wssys.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * PusReUserRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="pus_re_user_role")
public class PusReUserRole implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer userid;
	private Integer roleid;
	private Short state;

//	@ManyToOne
//	@Fetch(FetchMode.JOIN)
//	@NotFound(action=NotFoundAction.IGNORE)
//	@JoinColumn(name = "roleid", insertable=false, updatable=false,referencedColumnName = "roleid") 
//	private PusReRoleMenu pusReRoleMenu;
//	
//	
//	
//	// Constructors
//
//	public PusReRoleMenu getPusReRoleMenu() {
//		return pusReRoleMenu;
//	}
//
//	public void setPusReRoleMenu(PusReRoleMenu pusReRoleMenu) {
//		this.pusReRoleMenu = pusReRoleMenu;
//	}

	/** default constructor */
	public PusReUserRole() {
	}

	/** full constructor */
	public PusReUserRole(Integer userid, Integer roleid, Short state) {
		this.userid = userid;
		this.roleid = roleid;
		this.state = state;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getRoleid() {
		return this.roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

}