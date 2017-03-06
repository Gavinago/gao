package com.gov.controller;

import java.util.Date;



import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import com.gov.common.auth.ShiroConst;
import com.gov.common.auth.ShiroSessionHelper;
import com.gov.common.service.TUserLogService;
import com.gov.model.TUserLog;
import com.gov.model.User;
import com.gov.service.UserService;
@Controller
public class BaseController {
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private UserService userService;
	/**
	 * 静态注销
	 */
	public static void LogOut(){
		//shiro 注销
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		Session session = subject.getSession();
		session.setAttribute(ShiroConst.SESSION_KEY_USER, null);
	}
	
	/**
	 * 获取当前登录用户
	 * @return
	 */
	public User getCurrentUser(){
		User user = null;		
		try{
			if(SecurityUtils.getSubject().getPrincipal()==null){
				//当前没有登录
			}else{
				Session session = SecurityUtils.getSubject().getSession();
				user = (User) session.getAttribute(ShiroConst.SESSION_KEY_USER);
				if(user==null){
					//没有的话，查数据库
					//获得Shiro保存的用户账号
					String username = SecurityUtils.getSubject().getPrincipal().toString();
					List<User> users = userService.selectUser(username);
					if(users!=null && users.size()>0){
						user = users.get(0);
						//存入session
						session.setAttribute(ShiroConst.SESSION_KEY_USER, user);
					}else{
						//查不到用户账号
						user = new User();
						user.setUserName("error username");
					}
				}
			}
		}catch(Exception ex){
			logger.error("getCurrentUser error:{}",ex);
		}
		return user;
	}

}
