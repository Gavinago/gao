package com.gov.common.serviceImpl;


import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gov.common.service.UserRightViewService;
import com.gov.dao.UserRightViewMapper;
import com.gov.model.UserRightView;
@Service
public class UserRightViewServiceImpl implements UserRightViewService {
	@Resource
	private UserRightViewMapper userRightViewMapper;
	@Override
	public Set<String> selectListByUsername(String username) {
		Set<String>  string  = userRightViewMapper.selectListByUsername(username);
		return string;
	}
	@Override
	public List<UserRightView> selectByUsername(String username) {
		List<UserRightView> List = userRightViewMapper.selectByUsername(username);
		return List;
	}

}
