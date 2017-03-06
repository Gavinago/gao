package com.gov.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gov.model.TRight;

public interface TRightMapper {

	public List<TRight> selectListByUsername(@Param(value="username")String userName);
}
