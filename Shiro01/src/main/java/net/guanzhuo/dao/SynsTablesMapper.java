package net.guanzhuo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.guanzhuo.model.SynsTables;

public interface SynsTablesMapper {
	
	public List<SynsTables>selectByPrimaryKey(@Param(value="tableid")Integer tableid);
	public List<SynsTables>selectAll();
}
