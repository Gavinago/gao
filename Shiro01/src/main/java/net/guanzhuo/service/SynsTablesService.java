package net.guanzhuo.service;

import java.util.List;

import net.guanzhuo.model.SynsTables;

public interface SynsTablesService {

	public List<SynsTables>selectByPrimaryKey(Integer tableid);
	public List<SynsTables>selectAll();
}
