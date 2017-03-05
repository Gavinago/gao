package com.gov.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gov.model.Step;

public interface StepMapper {
	
	public List<Step> selectAllStep(@Param(value="stepparent")Integer stepparent);
	
	public List<Step> selectAllParent(@Param(value="state")Integer state);
	public List<Step> selectClassByState(@Param(value="state")Integer state);

}
