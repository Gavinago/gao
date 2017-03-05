package net.guanzhuo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.guanzhuo.model.TUser;

public interface TUserService {

	public List<TUser>selectByPrimaryKey(Integer Userid);
	public List<TUser>selectAllUser();
	public void updateUser(TUser tUser);
	public void addUser(TUser tUser);
	public void deleteUser(Integer userid);
}
