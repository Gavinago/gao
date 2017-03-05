package com.gov.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gov.model.User;

public interface UserMapper {
	public List<User> selectByPrimaryKey(@Param(value="id")Integer id);
	public List<User> selectAll();
	public User selectByUserAndPassword(@Param(value="username")String userName,@Param(value="password")String password);
	public List<User>selectUser(@Param(value="username")String username);
	public int updatepassword(@Param(value="userid")Integer userid,@Param(value="password")String password);
}
