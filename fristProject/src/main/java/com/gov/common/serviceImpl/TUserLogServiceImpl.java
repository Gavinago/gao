package com.gov.common.serviceImpl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.gov.common.service.TUserLogService;
import com.gov.dao.TUserLogMapper;
import com.gov.model.TUserLog;
@Service
public class TUserLogServiceImpl implements TUserLogService {
	@Resource
	private TUserLogMapper tUserLogMapper;
	@Override
	public TUserLog selectByPrimaryKey(Integer logid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TUserLog> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TUserLog> selectByLogclass(String logclass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TUserLog> selectByLogresult(String logresult) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TUserLog> selectByLogid(Integer logid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countByLogclass(String logclass) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countByLogresult(String logresult) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countByLogid(Integer logid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByLogclass(String logclass) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByLogresult(String logresult) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByLogid(Integer logid) {
		
		return 0;
	}

	@Override
	public int insert(TUserLog tuserlog) {
		int i = tUserLogMapper.insertLog(tuserlog);
		return i;
	}

	@Override
	public int updateLogclass(String logclass, Integer logid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateLogresult(String logresult, Integer logid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(TUserLog tuserlog) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PageInfo<TUserLog> selectSearch(int pageNum, int pageSize, String searchText) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<TUserLog> selectLogSearch(String logclass, String logresult, Date logtimeBegin, Date logtimeEnd,
			int pageNum, int pageSize, String searchText) {
		// TODO Auto-generated method stub
		return null;
	}

}
