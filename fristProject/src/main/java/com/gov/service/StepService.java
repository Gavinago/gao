package com.gov.service;

import java.util.List;


import com.gov.model.Step;

public interface StepService {
	public List<Step> selectAllStept(Integer stepid);
	public List<Step> selectAllParent(Integer state);
	public List<Step> selectClassByState(Integer state);
}
