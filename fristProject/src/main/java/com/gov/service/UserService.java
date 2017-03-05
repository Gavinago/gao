package com.gov.service;

import java.util.List;


import com.gov.model.User;

public interface UserService {
	public List<User> selectByPrimaryKey(Integer id);
	public List<User> selectAll();
	@Deprecated
	public boolean selectByUserAndPassword(String userName,String password);
	public List<User>selectUser(String userName);
	public int updatepassword(Integer userid,String password);
}
