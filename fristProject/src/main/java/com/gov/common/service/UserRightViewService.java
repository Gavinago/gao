package com.gov.common.service;

import java.util.List;
import java.util.Set;

import com.gov.model.UserRightView;


public interface UserRightViewService {
	public Set<String> selectListByUsername(String username);
	public List<UserRightView> selectByUsername(String username);
}
