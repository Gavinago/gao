package com.gov.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.gov.model.Guest;


public interface GuestService {
	public PageInfo<Guest> selectByguestidcard(Integer pageNum, Integer pageSize,String guestidcard);
	public PageInfo<Guest> selectbySearch(Integer pageNum, Integer pageSize,String searchText);
	public PageInfo<Guest> selectbyTime(Integer pageNum, Integer pageSize,String searchText);
	public PageInfo<Guest> selectbyRelationshipcomeTime(Integer pageNum, Integer pageSize,String guestcometime,String searchText);
	public String[] insert(Guest guest);
	public int updateGuestInfoByGuestid(Guest guest);
	public int updateGuestStateByGuestid(Integer gueststate,Integer guestid);
	public List<Guest> selectByRoomId(Integer guestroomid);
	public PageInfo<Guest> selectByRoomName(String guestroomname,Integer pageNum, Integer pageSize);
	public List<Guest>selectByRoomidAndCometime(Integer guestroomid,String guestcometime);
	public Guest selectHotelGuestByRoomid(Integer guestroomid);
	public PageInfo<Guest> selectGuestBookRoom(Integer pageNum, Integer pageSize,String searchText,String orderBy);
}
