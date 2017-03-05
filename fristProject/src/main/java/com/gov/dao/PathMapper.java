package com.gov.dao;

import org.apache.ibatis.annotations.Param;

import com.gov.model.Path;

public interface PathMapper {
	public Path loginUrl(@Param(value="path")String path);
}
