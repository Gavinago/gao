package com.gov.service.Impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gov.dao.PathMapper;
import com.gov.model.Path;
import com.gov.service.PathService;
@Service
public class PathServiceImpl implements PathService {
	@Resource
	private PathMapper pathMapper;

	@Override
	public Path loginUrl(String path) {
		Path path1 = pathMapper.loginUrl(path);
		return path1;
	}

}
