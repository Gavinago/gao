package com.gov.common.service;

import java.util.*;


//分页插件
import com.github.pagehelper.*;
import com.gov.model.TRight;

/**
 *
 * 用户自定义表 t_right 的扩展服务接口
 * 增加功能都添加在该接口中
 *
 * @author jtj
 *
 */
public interface TRightService
{
    //selectByPrimaryKey
    TRight selectByPrimaryKey(Integer rightid);

    //selectAll
    List<TRight> selectAll();

    //selectByFolderid
    List<TRight> selectByFolderid(Integer folderid);

    //selectByRightid
    List<TRight> selectByRightid(Integer rightid);
    
    //countAll
    int countAll();

    //countByFolderid
    int countByFolderid(Integer folderid);

    //countByRightid
    int countByRightid(Integer rightid);

    //deleteByFolderid
    int deleteByFolderid(Integer folderid);

    //deleteByRightid
    int deleteByRightid(Integer rightid);
    
    //insert
    int insert(TRight tright);
    
    //updateFolderid
    int updateFolderid(Integer folderid, Integer rightid);
    
    //update
    int update(TRight tright);
    
    //分页查询
    PageInfo<TRight> selectSearch(int pageNum, int pageSize, String searchText);

    // TODO 添加自定义方法

    // STUB_TAG_MY_CODE 自定义代码必须在该标记之后添加，请不要删除或修改此行

    List<String> selectListByUsername(String username);
    PageInfo<TRight> selectByRoleIdSearch(String roleId, Integer pageNum,
			Integer pageSize, String searchText);
    int insertNext(TRight tright);

    // STUB_TAG_MY_CODE 自定义代码必须在该标记之前添加，请不要删除或修改此行
}