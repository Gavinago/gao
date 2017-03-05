package com.gov.service.Impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.gov.dao.TUserLogMapper;
import com.gov.dao.UserMapper;
import com.gov.model.User;
import com.gov.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Resource
	private UserMapper userMapper;
	public List<User> selectByPrimaryKey(Integer id) {
		List<User> list = userMapper.selectByPrimaryKey(id);
		return list;
	}

	public List<User> selectAll() {
		List<User> list = userMapper.selectAll();
		return list;
	}

	public boolean selectByUserAndPassword(String userName, String password) {
		User user = userMapper.selectByUserAndPassword(userName, password);
		return null!=user?true:false;
	}

	@Override
	public List<User> selectUser(String userName) {
		List<User> list = userMapper.selectUser(userName);
		return list;
	}

	@Override
	public int updatepassword(Integer userid, String password) {
		int i = userMapper.updatepassword(userid, password);
		return i;
	}





}
