package com.gov.common.auth;

import org.apache.shiro.SecurityUtils;

public class ShiroSessionHelper {

	public static Object getAttribute(String key){
		return SecurityUtils.getSubject().getSession().getAttribute(key);
	}
	public static void setAttribute(String key, Object obj){
		SecurityUtils.getSubject().getSession().setAttribute(key,obj);
	}
}
