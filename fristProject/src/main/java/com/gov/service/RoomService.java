package com.gov.service;

import java.util.List;


import com.github.pagehelper.PageInfo;
import com.gov.model.Room;

public interface RoomService {
	
		public List<Room> selectByPrimaryKey(Integer roomid);
		public PageInfo<Room> selectAll(int pageNum, int pageSize);
		public PageInfo<Room> selectByRoomclass(Integer roomclass,int pageNum, int pageSize);
		public PageInfo<Room> selectByRoomstate(Integer roomstate,int pageNum, int pageSize);
		public PageInfo<Room> selectByRoompricesort(String sort,int pageNum, int pageSize);
		public void insertRoom(Room room);
		public void updateRoom(Room room);
		public void updateRoomStateByRoomid(Integer roomstate,Integer roomid);

}
