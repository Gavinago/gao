package com.wssys.shiroController;

import java.util.Set;
import javax.annotation.PostConstruct;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.wssys.bean.Constants;
import com.wssys.controller.ContactsControllers;
import com.wssys.dao.PusSysUserDao;
import com.wssys.entity.PusSysUser;

/**
 * Subject验证的过程可以有效地划分分以下三个步骤： 1.收集Subject提交的身份和证明； 2.向Authenticating提交身份和证明；
 * 3.如果提交的内容正确，允许访问，否则重新尝试验证或阻止访问
 * Realm：可以有1个或多个Realm，可以认为是安全实体数据源，即用于获取安全实体的；
 * 可以是JDBC实现，也可以是LDAP实现，或者内存实现等等；由用户提供；
 * 注意：Shiro不知道你的用户/权限存储在哪及以何种格式存储；所以我们一般在应用中都需要实现自己的Realm
 * @author q
 * 
 */
@Component("shiroDbRealm")
public class ShiroDbRealm extends AuthorizingRealm {
	private static Logger logger = LoggerFactory.getLogger(ShiroDbRealm.class);

	@Autowired
	private PusSysUserDao pusSysUserDao;

	// public static final String HASH_ALGORITHM = "MD5";
	// public static final String HASH_ALGORITHMB = "Sha384";
	// public static final int HASH_INTERATIONS = 1;

	// private static final int SALT_SIZE = 8;

	public ShiroDbRealm() {
		//访问量小的情况下  
		// 认证
		super.setAuthenticationCacheName(Constants.authenticationCacheName);
		super.setAuthenticationCachingEnabled(false);	//如果为true  密码会被缓存
		// 授权
		super.setAuthorizationCacheName(Constants.authorizationCacheName);
		super.setAuthorizationCachingEnabled(false);
		// super.setAuthorizationCachingEnabled(false); //测试的时候先关闭缓存

		// -----------------

		// //认证
		// super.setAuthenticationCacheName(GlobalStatic.authenticationCacheName);
		// //
		// super.setAuthenticationCacheName(GlobalStatic.authenticationCacheName);
		// super.setAuthenticationCachingEnabled(false);
		// //授权
		// super.setAuthorizationCacheName(GlobalStatic.authorizationCacheName);
	}

	// 授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principalCollection) {

		// 因为非正常退出，即没有显式调用 SecurityUtils.getSubject().logout()
		// (可能是关闭浏览器，或超时)，但此时缓存依旧存在(principals)，所以会自己跑到授权方法里。
		if (!SecurityUtils.getSubject().isAuthenticated()) {
			doClearCache(principalCollection);
			SecurityUtils.getSubject().logout();
			return null;
		}

		PusSysUser psu = (PusSysUser) principalCollection.getPrimaryPrincipal();
		// String userId = (String)
		// principalCollection.fromRealm(getName()).iterator().next();
		Integer userId = psu.getId();
		// if (StringUtils.isBlank(userId)) {
		// return null;
		// }
		if (userId == null) {
			return null;
		}
		// 添加角色及权限信息
		SimpleAuthorizationInfo sazi = new SimpleAuthorizationInfo();
		try {
			sazi.addRoles(pusSysUserDao.getRolesAsString(userId)); // 获取当前用户所有的角色,
			// //用于依据角色判断权限的shiro过滤器
			Set<String> sp = pusSysUserDao.getPermissionsAsString(userId);
			sazi.addStringPermissions(sp); // 获取当前用户的所有权限,
			// System.out.println("我的权限:" + sp);
			// 权限就是url,一个url的集合
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return sazi;
	}

	// 认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		/*
		 * String pwd = new String(upToken.getPassword()); if
		 * (StringUtils.isNotBlank(pwd)) { pwd = DigestUtils.md5Hex(pwd); }
		 */
		// 调用业务方法
		PusSysUser user = null;
		String userName = upToken.getUsername();
		try {
			user = pusSysUserDao.findByaccount(userName);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		if (user != null) {
			// 要放在作用域中的东西，请在这里进行操作
			// SecurityUtils.getSubject().getSession().setAttribute("c_user",
			// user);
			// byte[] salt = EncodeUtils.decodeHex(user.getSalt());

			// Session session = SecurityUtils.getSubject().getSession(false);
			AuthenticationInfo authinfo = new SimpleAuthenticationInfo(
					new PusSysUser(user), user.getPwd(), getName());
			// Cache<Object, Object> cache =
			// shiroCacheManager.getCache(GlobalStatic.authenticationCacheName);
			// cache.put(GlobalStatic.authenticationCacheName+"-"+userName,
			// session.getId());
			return authinfo;
		}
		// 认证没有通过
		return null;
	}

	/**
	 * 设定Password校验.
	 */
	@PostConstruct
	public void initCredentialsMatcher() {
		// HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(
		// "Sha384");
		// matcher.setHashIterations(HASH_INTERATIONS);

		setCredentialsMatcher(new CustomCredentialsMatcher());

		// setCredentialsMatcher(matcher);
		// setCredentialsMatcher(new Sha256CredentialsMatcher());

		// setCacheManager(new MemoryConstrainedCacheManager());

		// HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(
		// HASH_ALGORITHMB);
		// matcher.setHashIterations(HASH_INTERATIONS);
		// setCredentialsMatcher(matcher);
	}

}
