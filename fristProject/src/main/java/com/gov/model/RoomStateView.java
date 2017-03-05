package com.gov.model;

import java.io.Serializable;

public class RoomStateView implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer roomid;
	private String roomsnum;
	private Integer roomstate;
	private Double roomprice;
	private Double roomvip;
	private Integer roomclass;
	private String roomname;
	private Double roomfirmprice;
	private Double roomcash;
	private String statename;
	
	public RoomStateView() {
		super();
	}

	public Integer getRoomid() {
		return roomid;
	}

	public void setRoomid(Integer roomid) {
		this.roomid = roomid;
	}

	public String getRoomsnum() {
		return roomsnum;
	}

	public void setRoomsnum(String roomsnum) {
		this.roomsnum = roomsnum;
	}

	public Integer getRoomstate() {
		return roomstate;
	}

	public void setRoomstate(Integer roomstate) {
		this.roomstate = roomstate;
	}

	public Double getRoomprice() {
		return roomprice;
	}

	public void setRoomprice(Double roomprice) {
		this.roomprice = roomprice;
	}

	public Double getRoomvip() {
		return roomvip;
	}

	public void setRoomvip(Double roomvip) {
		this.roomvip = roomvip;
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

	public Double getRoomfirmprice() {
		return roomfirmprice;
	}

	public void setRoomfirmprice(Double roomfirmprice) {
		this.roomfirmprice = roomfirmprice;
	}

	public String getStatename() {
		return statename;
	}

	public void setStatename(String statename) {
		this.statename = statename;
	}
	public Double getRoomcash() {
		return roomcash;
	}

	public void setRoomcash(Double roomcash) {
		this.roomcash = roomcash;
	}

	@Override
	public String toString() {
		return "RoomStateView [roomid=" + roomid + ", roomsnum=" + roomsnum + ", roomstate=" + roomstate
				+ ", roomprice=" + roomprice + ", roomvip=" + roomvip + ", roomclass=" + roomclass + ", roomname="
				+ roomname + ", roomfirmprice=" + roomfirmprice + ", roomcash=" + roomcash + ", statename=" + statename
				+ "]";
	}
	
	
}
