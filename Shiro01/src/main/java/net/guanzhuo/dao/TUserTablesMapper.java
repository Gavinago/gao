package net.guanzhuo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.guanzhuo.model.TUserTables;

public interface TUserTablesMapper {
	public List<TUserTables>selectByPrimaryKey(@Param(value="userid")Integer userid);
	public List<TUserTables>selectByUserTable(@Param(value="userid")Integer userid);
	public void deleteUser(@Param(value="userid")Integer userid);
	public void insertUser(TUserTables tusertables);
	public void updataUserTime(@Param(value="userid")Integer userid,@Param(value="timeparam")String timeparam);
}