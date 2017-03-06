package com.gov.common.service;


import java.util.Date;
import java.util.List;

//分页插件
import com.github.pagehelper.*;
import com.gov.model.TUserLog;


/**
 *
 * 用户自定义表 t_user_log 的扩展服务接口
 * 增加功能都添加在该接口中
 *
 * @author
 *
 */
public interface TUserLogService
{
    //selectByPrimaryKey
    TUserLog selectByPrimaryKey(Integer logid);

    //selectAll
    List<TUserLog> selectAll();

    //selectByLogclass
    List<TUserLog> selectByLogclass(String logclass);

    //selectByLogresult
    List<TUserLog> selectByLogresult(String logresult);

    //selectByLogid
    List<TUserLog> selectByLogid(Integer logid);
    
    //countAll
    int countAll();

    //countByLogclass
    int countByLogclass(String logclass);

    //countByLogresult
    int countByLogresult(String logresult);

    //countByLogid
    int countByLogid(Integer logid);

    //deleteByLogclass
    int deleteByLogclass(String logclass);

    //deleteByLogresult
    int deleteByLogresult(String logresult);

    //deleteByLogid
    int deleteByLogid(Integer logid);
    
    //insert
    int insert(TUserLog tuserlog);
    
    //updateLogclass
    int updateLogclass(String logclass, Integer logid);
    
    //updateLogresult
    int updateLogresult(String logresult, Integer logid);
    
    //update
    int update(TUserLog tuserlog);
    
    //分页查询
    PageInfo<TUserLog> selectSearch(int pageNum, int pageSize, String searchText);

    // TODO 添加自定义方法

    // STUB_TAG_MY_CODE 自定义代码必须在该标记之后添加，请不要删除或修改此行

    //分页查询
    PageInfo<TUserLog> selectLogSearch(
    		String logclass,
    		String logresult,
    		Date logtimeBegin,
    		Date logtimeEnd,
			int pageNum, 
			int pageSize, 
    		String searchText);
    
    // STUB_TAG_MY_CODE 自定义代码必须在该标记之前添加，请不要删除或修改此行
}