package net.guanzhuo.service;

import java.util.List;

import net.guanzhuo.model.TUserTables;


public interface TUserTablesService {

	public List<TUserTables>selectByPrimaryKey(Integer userid);
	public List<TUserTables>selectByUserTable(Integer userid);
	public void deleteUser(Integer userid);
	public void insertUser(TUserTables tusertables);
	public void updataUserTime(Integer userid,String timeparam);
}
