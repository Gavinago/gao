package com.wssys.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Urd entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="urd")
public class Urd  implements java.io.Serializable {


    // Fields    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id;
     private Integer deptid;
     private Integer userid;
     private Timestamp createtime;


    // Constructors

    /** default constructor */
    public Urd() {
    }

	/** minimal constructor */
    public Urd(Timestamp createtime) {
        this.createtime = createtime;
    }
    
    /** full constructor */
    public Urd(Integer deptid, Integer userid, Timestamp createtime) {
        this.deptid = deptid;
        this.userid = userid;
        this.createtime = createtime;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeptid() {
        return this.deptid;
    }
    
    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }

    public Integer getUserid() {
        return this.userid;
    }
    
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Timestamp getCreatetime() {
        return this.createtime;
    }
    
    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }
   








}