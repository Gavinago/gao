package com.gov.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gov.model.Room;

public interface RoomMapper {
	public List<Room> selectByPrimaryKey(@Param(value="roomid")Integer roomid);
	public List<Room> selectAll();
	public List<Room> selectByRoomclass(@Param(value="roomclass")Integer roomclass);
	public List<Room> selectByRoomstate(@Param(value="roomstate")Integer roomstate);
	public List<Room> selectByRoompricesort(@Param(value="sort")String sort);
	public void insertRoom(Room room);
	public void updateRoom(Room room);
	public void updateRoomStateByRoomid(@Param(value="roomstate")Integer roomstate,@Param(value="roomid")Integer roomid);
}
