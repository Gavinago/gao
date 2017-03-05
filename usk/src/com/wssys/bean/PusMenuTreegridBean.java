package com.wssys.bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PusMenuTreegridBean {
	private Integer id;
	private String name;
	private Integer menupid;
	private String description;
	private String pageurl;
	private Short type;
	private Short state;
	private Timestamp createtime;
	private List<PusMenuTreegridBean>  children;   //孩子节点的List
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getMenupid() {
		return menupid;
	}
	public void setMenupid(Integer menupid) {
		this.menupid = menupid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPageurl() {
		return pageurl;
	}
	public void setPageurl(String pageurl) {
		this.pageurl = pageurl;
	}
	public Short getType() {
		return type;
	}
	public void setType(Short type) {
		this.type = type;
	}
	public Short getState() {
		return state;
	}
	public void setState(Short state) {
		this.state = state;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	public List<PusMenuTreegridBean> getChildren() {
		return children;
	}
	public void setChildren(List<PusMenuTreegridBean> children) {
		this.children = children;
	}
	
	 //添加孩子的方法

    public void addChild(PusMenuTreegridBean node){
       if(this.children == null){
           children= new ArrayList<PusMenuTreegridBean>();
           children.add(node);
       }else{
           children.add(node);
       }
          
    }
	
}
