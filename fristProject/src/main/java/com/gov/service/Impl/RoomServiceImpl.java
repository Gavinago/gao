package com.gov.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gov.dao.RoomMapper;
import com.gov.model.Room;
import com.gov.service.RoomService;
@Service
public class RoomServiceImpl implements RoomService {
	@Resource
	RoomMapper roomMapper;

	@Override
	public List<Room> selectByPrimaryKey(Integer roomid) {
		List<Room> list = roomMapper.selectByPrimaryKey(roomid);
		return list;
	}

	@Override
	public PageInfo<Room> selectAll(int pageNum, int pageSize) {
		//之前加入分页器
		PageHelper.startPage(pageNum, pageSize);
		List<Room> list =roomMapper.selectAll();
		//将结果用分页结果包装
		PageInfo<Room> pagelist = new PageInfo<Room>(list);
		return pagelist;
	}

	@Override
	public PageInfo<Room> selectByRoomclass(Integer roomclass, int pageNum, int pageSize) {
		//之前加入分页器
		PageHelper.startPage(pageNum, pageSize);
		List<Room> list =roomMapper.selectByRoomclass(roomclass);
		//将结果用分页结果包装
		PageInfo<Room> pagelist = new PageInfo<Room>(list);
		return pagelist;
	}

	@Override
	public PageInfo<Room> selectByRoomstate(Integer roomstate, int pageNum, int pageSize) {
		//之前加入分页器
		PageHelper.startPage(pageNum, pageSize);
		List<Room> list =roomMapper.selectByRoomstate(roomstate);
		//将结果用分页结果包装
		PageInfo<Room> pagelist = new PageInfo<Room>(list);
		return pagelist;
	}

	@Override
	public PageInfo<Room> selectByRoompricesort(String sort, int pageNum, int pageSize) {
		//之前加入分页器
		PageHelper.startPage(pageNum, pageSize);
		List<Room> list =roomMapper.selectByRoompricesort(sort);
		//将结果用分页结果包装
		PageInfo<Room> pagelist = new PageInfo<Room>(list);
		return pagelist;
	}

	@Override
	public void insertRoom(Room room) {
		roomMapper.insertRoom(room);
	}

	@Override
	public void updateRoom(Room room) {
		roomMapper.updateRoom(room);
	}

	@Override
	public void updateRoomStateByRoomid(Integer roomstate, Integer roomid) {
		roomMapper.updateRoomStateByRoomid(roomstate, roomid);
	}

	
}
