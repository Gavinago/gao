package com.gov.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gov.model.RoomStateView;

public interface RoomStateViewMapper {

	public List<RoomStateView>selectAll();
	public List<RoomStateView>selectByRoomclass(@Param(value="roomclass")Integer roomclass,@Param(value="searchText")String searchText);
	public List<RoomStateView>selectSearch(@Param(value="searchText")String searchText);
	public List<RoomStateView>selectByRoomState(@Param(value="roomstate")Integer roomstate,@Param(value="searchText")String searchText);
}
