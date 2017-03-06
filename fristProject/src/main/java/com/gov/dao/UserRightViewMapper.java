package com.gov.dao;


import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.gov.model.UserRightView;


public interface UserRightViewMapper {
  public Set<String> selectListByUsername(@Param(value="username")String username);
  public List<UserRightView> selectByUsername(@Param(value="username")String username);
}
