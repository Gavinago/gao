package com.gov.common.auth;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.gov.common.service.UserRightViewService;
import com.gov.controller.BaseController;
import com.gov.model.User;
import com.gov.service.UserService;
import com.gov.util.CacheUtils;
import com.gov.util.exception.AccountFreezeException;
import com.gov.util.exception.PasswordException;
import com.gov.util.exception.UserEmptyException;
import com.gov.util.exception.UserNullException;
@Component(value="securityRealm")//spring中在持久层、业务层和控制层分别采用 @Repository、@Service 和 @Controller 对分层中的类进行注释，而用 @Component 对那些比较中立的类进行注释
public class ShiroRealmSimple extends AuthorizingRealm{
	@Resource
	private UserService userService;
	@Resource
	private UserRightViewService userRightViewService;
	private static Logger logger = LoggerFactory.getLogger(ShiroRealmSimple.class);  
	/** 
     * 为当前登录的Subject授予角色和权限 
     * @see 该方法的调用时机为需授权资源被访问时 
     */  
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		//简单实现，查询当前登录的权限代码串清单，表示该用户拥有的全部权限
		//获得要检查权限表的用户名
		String userName = (String) principals.getPrimaryPrincipal();
		Set<String> rights  =  (Set<String>) CacheUtils.get(userName);
		if(null==rights){
			rights = this.userRightViewService.selectListByUsername(userName);
			CacheUtils.put(userName,rights);
		try {
			StringBuilder sb = new StringBuilder();
			for (String string : rights) {
				sb.append(string+" , "); 
			}
			logger.debug("{} 权限表 :{}",userName,sb.substring(0,sb.lastIndexOf(",")).toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();  
//		simpleAuthorInfo.addStringPermissions(rights);
		simpleAuthorInfo.setStringPermissions(rights);
		return simpleAuthorInfo;
	}
	/** 
     * 验证当前登录的Subject 
     * @see  该方法的调用时机为login()方法中执行Subject.login()时 
     */  
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws 
									AuthenticationException
									{
		this.showDebug();
		UsernamePasswordToken upt = (UsernamePasswordToken) token;
		if(upt==null)return null;
		String userName = (String) upt.getPrincipal();
		if(null==userName||null==upt.getPassword()){
			return null;
		}
		String password = new String (upt.getPassword());
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute(ShiroConst.SESSION_KEY_USER);;
		if(user==null)return null;
		String md5db = new Md5Hash(userName+user.getPassword()).toString();
		logger.debug("用户：{} 密码：{} 正确密码：{}",userName,password,md5db);
		if(password.equalsIgnoreCase(md5db)){
			//密码验证通过
        	//Shiro 的SimpleAuthenticationInfo扩展后不知道保存在什么地方
        	//只好使用 SimpleAuthenticationInfo，然后利用 Session 保存当前用户信息
        	//之后返回一个SimpleAuthenticationInfo表示成功
        	SimpleAuthenticationInfo info =
        			new SimpleAuthenticationInfo(
        					userName,
        					upt.getPassword(),
        					getName());
        	//将当前登录用户实体保存到session中
        	SecurityUtils.getSubject().getSession().setAttribute(ShiroConst.SESSION_KEY_USER, user);
        	return info;//成功，返回一个自定义的用户信息对象
		}else{
			logger.debug("{} 用户密码错误",userName);
			SecurityUtils.getSubject().getSession().setAttribute(ShiroConst.SESSION_KEY_USER, null);
			return null;
		}
		
	}
	private void showDebug(){
		/*
		<property name="cacheManager" ref="shiroCacheManager" />		
		<property name="cachingEnabled" value="true"/>
	    <property name="authenticationCachingEnabled" value="true"/>
	    <property name="authenticationCacheName" value="authenticationCache"/>
	    <property name="authorizationCachingEnabled" value="true"/>
	    <property name="authorizationCacheName" value="authorizationCache"/>		
	    */
		//CacheManager cm = this.getCacheManager();
		//cm.getCache("av").
		//logger.debug("cacheManager = {}",cm);
	}

}
