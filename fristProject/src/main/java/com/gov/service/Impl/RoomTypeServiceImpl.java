package com.gov.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gov.dao.RoomTypeMapper;
import com.gov.model.RoomType;
import com.gov.service.RoomTypeService;
@Service
public class RoomTypeServiceImpl implements RoomTypeService {
	@Resource
	private RoomTypeMapper roomTypeMapper;
	@Override
	public List<RoomType> selectAll() {
		List<RoomType> list = roomTypeMapper.selectAll();
		return list;
	}

}
