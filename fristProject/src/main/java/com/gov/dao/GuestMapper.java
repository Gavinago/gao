package com.gov.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gov.model.Guest;

public interface GuestMapper {
	public List<Guest> selectByguestidcard(@Param(value="guestidcard")String guestidcard);
	public List<Guest> selectbySearch(@Param(value="searchText")String searchText);
	public List<Guest> selectbyTime(@Param(value="searchText")String searchText);
	public List<Guest> selectbyRelationshipcomeTime(@Param(value="guestcometime")String guestcometime,@Param(value="searchText")String searchText);
	public int insert(Guest guest);
	public int updateGuestInfoByGuestid(Guest guest);
	public int updateGuestStateByGuestid(@Param(value="gueststate")Integer gueststate,@Param(value="guestid")Integer guestid);
	public List<Guest> selectByRoomId(@Param(value="guestroomid")Integer guestroomid);
	public List<Guest> selectGuestByGuestid(@Param(value="guestid")Integer guestid);
	public List<Guest> selectByRoomName(@Param(value="guestroomname")String guestroomname);
	public List<Guest>selectByRoomidAndCometime(@Param(value="guestroomid")Integer guestroomid,@Param(value="guestcometime")String guestcometime);
	public Guest selectHotelGuestByRoomid(@Param(value="guestroomid")Integer guestroomid);
	public int deleteGuestByGuestid(@Param(value="guestid")Integer guestid);
	List<Guest>selectGuestBookRoom(@Param(value="searchText")String searchText,@Param(value="orderBy")String orderBy);
}
