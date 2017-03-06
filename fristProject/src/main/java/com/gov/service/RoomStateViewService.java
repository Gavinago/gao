package com.gov.service;

import com.github.pagehelper.PageInfo;
import com.gov.model.RoomStateView;

public interface RoomStateViewService {
	
	public PageInfo<RoomStateView>selectAll(int pageNum, int pageSize);
	public PageInfo<RoomStateView>selectByRoomclass(int pageNum, int pageSize,Integer roomclass,String searchText);
	public PageInfo<RoomStateView>selectByRoomState(int pageNum, int pageSize,Integer roomstate,String searchText);
	public PageInfo<RoomStateView>selectSearch(int pageNum, int pageSize,String searchText);

}
