package com.gov.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gov.dao.RoomStateViewMapper;
import com.gov.model.RoomStateView;
import com.gov.service.RoomStateViewService;

@Service
public class RoomStateViewServiceImpl implements RoomStateViewService {
	@Resource
	RoomStateViewMapper roomStateViewMapper;

	@Override
	public PageInfo<RoomStateView> selectAll(int pageNum, int pageSize) {
		//之前加入分页器
		PageHelper.startPage(pageNum, pageSize);
		List<RoomStateView> list = roomStateViewMapper.selectAll();
		//将结果用分页结果包装
		PageInfo<RoomStateView> pagelist = new PageInfo<RoomStateView>(list);
		return pagelist;
	}

	@Override
	public PageInfo<RoomStateView> selectByRoomclass(int pageNum, int pageSize,Integer roomclass,String searchText) {
		//之前加入分页器
		PageHelper.startPage(pageNum, pageSize);
		List<RoomStateView> list = roomStateViewMapper.selectByRoomclass(roomclass,searchText);
		//将结果用分页结果包装
		PageInfo<RoomStateView> pagelist = new PageInfo<RoomStateView>(list);
		return pagelist;
	}

	@Override
	public PageInfo<RoomStateView> selectSearch(int pageNum, int pageSize,String searchText) {
		//之前加入分页器
		PageHelper.startPage(pageNum, pageSize);
		List<RoomStateView> list = roomStateViewMapper.selectSearch(searchText);
		//将结果用分页结果包装
		PageInfo<RoomStateView> pagelist = new PageInfo<RoomStateView>(list);
		return pagelist;
	}

	@Override
	public PageInfo<RoomStateView> selectByRoomState(int pageNum, int pageSize, Integer roomstate, String searchText) {
		PageHelper.startPage(pageNum, pageSize);
		List<RoomStateView> list = roomStateViewMapper.selectByRoomState(roomstate,searchText);
		//将结果用分页结果包装
		PageInfo<RoomStateView> pagelist = new PageInfo<RoomStateView>(list);
		return pagelist;
	}

}
