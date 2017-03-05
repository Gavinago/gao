package com.gov.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gov.dao.StepMapper;
import com.gov.model.Step;
import com.gov.service.StepService;
@Service
public class SteptServiceImpl implements StepService {
	@Resource
	private StepMapper stepMapper;
	@Override
	public List<Step> selectAllStept(Integer stepid) {
		List<Step> step = stepMapper.selectAllStep(stepid);
		return step;
	}
	@Override
	public List<Step> selectAllParent(Integer state) {
		List<Step> step = stepMapper.selectAllParent(state);
		return step;
	}
	@Override
	public List<Step> selectClassByState(Integer state) {
		List<Step> step = stepMapper.selectClassByState(state);
		return step;
	}

}
