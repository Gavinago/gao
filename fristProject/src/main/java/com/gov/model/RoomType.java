package com.gov.model;

import java.io.Serializable;

public class RoomType implements Serializable{
	private static final long serialVersionUID = 2597195611840544608L;
	
	private Integer roomclass;
	private String roomname;
	
	public RoomType() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getRoomclass() {
		return roomclass;
	}
	public void setRoomclass(Integer roomclass) {
		this.roomclass = roomclass;
	}
	public String getRoomname() {
		return roomname;
	}
	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}
	@Override
	public String toString() {
		return "RoomType [roomclass=" + roomclass + ", roomname=" + roomname + "]";
	}
	
	

}
