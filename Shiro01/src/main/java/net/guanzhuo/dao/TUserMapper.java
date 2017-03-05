package net.guanzhuo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.guanzhuo.model.TUser;
import net.guanzhuo.service.TUserService;

public interface TUserMapper {
	public List<TUser>selectByPrimaryKey(@Param(value="userid")Integer Userid);
	public List<TUser>selectAllUser();
	public void updateUser(TUser tUser);
	public void addUser(TUser tUser);
	public void deleteUser(@Param(value="userid")Integer userid);
}
