package com.gov.common.service;

import java.math.*;
import java.util.*;
import org.apache.ibatis.annotations.Param;

//分页插件
import com.github.pagehelper.*;
import com.gov.model.User;



/**
 *
 * 用户自定义表 t_users 的扩展服务接口
 * 增加功能都添加在该接口中
 *
 * @author jtj
 *
 */
public interface TUsersService
{
    //selectByPrimaryKey
    User selectByPrimaryKey(String userid);

    //selectAll
    List<User> selectAll();

    //selectByOrgid
    List<User> selectByOrgid(String orgid);

    //selectByUserstateid
    List<User> selectByUserstateid(Integer userstateid);

    //selectByUsername
    List<User> selectByUsername(String username);

    //selectByUserid
    List<User> selectByUserid(String userid);
    
    //countAll
    int countAll();

    //countByOrgid
    int countByOrgid(String orgid);

    //countByUserstateid
    int countByUserstateid(Integer userstateid);

    //countByUsername
    int countByUsername(String username);

    //countByUserid
    int countByUserid(String userid);

    //deleteByOrgid
    int deleteByOrgid(String orgid);

    //deleteByUserstateid
    int deleteByUserstateid(Integer userstateid);

    //deleteByUsername
    int deleteByUsername(String username);

    //deleteByUserid
    int deleteByUserid(String userid);
    
    //insert
    int insert(User tusers);
    
    //updateOrgid
    int updateOrgid(String orgid, String userid);
    
    //updateUserstateid
    int updateUserstateid(Integer userstateid, String userid);
    
    //updateUsername
    int updateUsername(String username, String userid);
    
    //update
    int update(User tusers);
    
    //分页查询
    PageInfo<User> selectSearch(int pageNum, int pageSize, String searchText);

    // TODO 添加自定义方法

    // STUB_TAG_MY_CODE 自定义代码必须在该标记之后添加，请不要删除或修改此行

    // STUB_TAG_MY_CODE 自定义代码必须在该标记之前添加，请不要删除或修改此行
}