package com.wssys.controller;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.wssys.bean.Constants;
import com.wssys.dao.PusSysUserDao;
import com.wssys.dao.SysuserLoginlogDao;
import com.wssys.entity.PusSysUser;
import com.wssys.entity.SysuserLoginlog;
import com.wssys.framework.MethodLog;
import com.wssys.shiroController.CaptchaToken;
import com.wssys.utils.LogType;
import com.wssys.utils.StringUtil;
import com.wssys.utils.TCPIPUtil;

@Controller
public class LoginControllers extends FormAuthenticationFilter {
	private static Logger logger = LoggerFactory
			.getLogger(LoginControllers.class);

	// @Resource注解
	// @Resource注解和@Autowired一样，也可以标注在字段或属性的setter方法上
	// @Resource默认按名称装配，名称可以通过name属性指定。当找不到与名称匹配的bean时，才会按类型装配
	// 若注解标注在字段上且未指定name属性，则默认取字段名作为bean名称寻找依赖对象
	// 若注解标注在setter上且未指定name属性，则默认取属性名作为bean名称寻找依赖对象
	// 如果没有指定name属性，并且按照默认的名称仍找不到依赖对象时，它就会按类型匹配
	// 但只要指定了name属性，就只能按名称装配了
	//
	// @Autowired注解
	// @Autowired默认是按类型装配对象的，默认情况下它要求依赖对象必须存在
	// 如果允许null值，可以设置它的required属性为FALSE，如@Autowired(required=false)
	// 若想要按名称装配，可以结合@Qualifier注解一起使用，如@Autowired(required=false)

	@Resource
	private CacheManager shiroCacheManager;

	@Autowired
	private PusSysUserDao pusSysUserDao;

	@Autowired
	private SysuserLoginlogDao sysuserLoginlogDao;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginForm() {
		ModelAndView mav = new ModelAndView("login");
		PusSysUser psu = new PusSysUser();
		mav.addObject("login", psu);
		return mav;
	}
	
	@RequestMapping(value = "/getout", method = RequestMethod.GET)
	public ModelAndView getout(Model model) {
		ModelAndView mav = new ModelAndView("login");
		PusSysUser psu = new PusSysUser();
		mav.addObject("login", psu);
		model.addAttribute("message", "您的帐号在其他地方登录!");
		return mav;
	}

	@RequestMapping(value = "/loginBack", method = RequestMethod.POST)
	public ModelAndView loginBackAction() {
		ModelAndView mav = new ModelAndView("login");
		PusSysUser psu = new PusSysUser();
		mav.addObject("login", psu);
		return mav;
	}

	@RequestMapping(value = "/noAuth", method = RequestMethod.GET)
	public ModelAndView noAuthAction() {
		ModelAndView mav = new ModelAndView("sys/NoCompetence");

		return mav;
	}

//	@RequestMapping(value = "/loginValid", method = RequestMethod.GET)
//	@MethodLog(remark = "用户登录非法get请求", operType = LogType.LOGIN)
//	public String loginValidGet(PusSysUser currUser, Model model,
//			HttpServletRequest request, ServletResponse response,
//			@RequestParam(required = false, defaultValue = "0") boolean remembered,
//			HttpServletResponse httpresponse) {
//		model.addAttribute("message", "请求非法!");
//		logger.info("请求非法!不是用post请求");
//		return "forward:loginBack.do";
//	}
			
	// 已记住（Remembered） vs 已验证（Authenticated）
	// 如上例所示，shiro支持在登录过程中执行"remember me"，在此值得指出，一个已记住的Subject（remembered
	// Subject）和一个正常通过认证的Subject
	// （authenticated Subject）在shiro是完全不同的。
	// 记住的（Remembered）：一个被记住的Subject没有已知身份（也就是说subject.getPrincipals()返回空），但是它的身份被先前的认证过程记住，
	// 并存于先前session中，一个被认为记住的对象在执行subject.isRemembered()返回真。
	// 已验证（Authenticated）：一个被验证的Subject是成功验证后（如登录成功）并存于当前session中，一个被认为验证过的对象调用subject.isAuthenticated()将返回真。
	//
	// 互斥的
	//
	// 已记住（Remembered）和已验证（Authenticated）是互斥的--一个标识值为真另一个就为假，反过来也一样。
	//
	// 为什么区分？
	// 单词验证（authentication）有明显的证明含义，也就是说，需要担保Subject已经被证明他们是谁。
	// 当一个用户仅仅在上一次与程序交互时被记住，证明的状态已经不存在了：被记住的身份只是给系统一个信息这个用户可能是谁，但不确定，没有办法担保这个被记住的Subject是所要求的用户，
	// 一旦这个subject被验证通过，
	// 他们将不再被认为是记住的因为他们的身份已经被验证并存于当前的session中。
	// 所以尽管程序大部分情况下仍可以针对记住的身份执行用户特定的逻辑，比如说自定义的视图，但不要执行敏感的操作直到用户成功执行身份验证使其身份得到确定。
	// 例如，检查一个Subject是否可以访问金融信息应该取决于是否被验证（isAuthenticated()）而不是被记住（isRemembered()），要确保该Subject是所需的和通过身份验证的。
	@RequestMapping(value = "/loginValid", method = { RequestMethod.POST,
			RequestMethod.GET })
	@MethodLog(remark = "用户登录", operType = LogType.LOGIN)
	public String loginValidAction(PusSysUser currUser, Model model,
			HttpServletRequest request, ServletResponse response,
			@RequestParam(required = false, defaultValue = "0") boolean remembered,
			HttpServletResponse httpresponse) {
		if (!request.getMethod().equals("POST")) {
			model.addAttribute("message", "请求非法!");
			logger.info("请求非法!不是用post请求");
			return "forward:loginBack.do";
		}
		Subject user = SecurityUtils.getSubject();

		// PasswordService svc = new DefaultPasswordService();
		// String encrypted = svc.encryptPassword(currUser.getPwd());
		UsernamePasswordToken token = new UsernamePasswordToken(
				currUser.getAccount(), currUser.getPwd());

		CaptchaToken captchaTokentoken = createToken(request, response);
		/* 图形验证码验证 */
		String captcha = doCaptchaValidate(request, captchaTokentoken);

		if (StringUtil.stringIsNull(currUser.getAccount()).equals("")) {
			model.addAttribute("message", "帐号不能为空!");
			return "forward:loginBack.do";
		}

		if (StringUtil.stringIsNull(currUser.getPwd()).equals("")) {
			model.addAttribute("message", "密码不能为空!");
			return "forward:loginBack.do";
		}

		String postCaptcha = captchaTokentoken.getCaptcha();
		if (postCaptcha == null) {
			model.addAttribute("message", "验证码不能为空!");
			return "forward:loginBack.do";
		}

		// 比对
		if (captcha == null) {
			model.addAttribute("message", "验证码过期,请重新登录!");
			return "forward:loginBack.do";
		}
		if (!captcha.equalsIgnoreCase(captchaTokentoken.getCaptcha())) {
			// throw new IncorrectCaptchaException("验证码错误！");
			model.addAttribute("message", "验证码错误!");
			return "forward:loginBack.do";
		}

		token.setRememberMe(remembered);

		ModelAndView mav = new ModelAndView("login");
		PusSysUser psu = new PusSysUser();
		mav.addObject("login", psu);
		try {
			user.login(token);
		} catch (UnknownAccountException uae) {
			model.addAttribute("message", "账号不存在!");
			return "forward:loginBack.do";
		} catch (IncorrectCredentialsException ice) {
			model.addAttribute("message", "密码错误!");
			return "forward:loginBack.do";
		} catch (LockedAccountException lae) {
			model.addAttribute("message", "账号被锁定!");
			return "forward:loginBack.do";
		} catch (AuthenticationException e) {
			model.addAttribute("message", "认证错误!");
			return "forward:loginBack.do";
//		
//		
//		}catch(ExcessiveAttemptsException eae){
//			model.addAttribute("message", "登录失败 次数过多，请稍后再试!");
			//logger.error(eae.getMessage());
//			return "forward:loginBack.do";
		}catch (Exception e) {
			model.addAttribute("message", "未知错误,请联系管理员.");
			// e.printStackTrace();
			// logger.error(e.getMessage());
			logger.error("未知错误,请联系管理员", e);
			return "forward:loginBack.do";
		}
		logger.info("login sucesss!");

		SysuserLoginlog ulog = new SysuserLoginlog();
		ulog.setType(0);
		ulog.setUseraccount(currUser.getAccount());
		ulog.setIp(TCPIPUtil.getIpAddr(request));
		sysuserLoginlogDao.save(ulog);
		// 同一个帐号只能在线一个控制 start-->

		// ----end

		return "redirect:viewAllContacts.do";
	}

	public static final void setLogin(String userId, String password) {

		Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated()) {

			// collect user principals and credentials in a gui specific manner
			// such as username/password html form, X509 certificate, OpenID,
			// etc.
			// We'll use the username/password example here since it is the most
			// common.

			// (do you know what movie this is from? ;)
			UsernamePasswordToken token = new UsernamePasswordToken(userId,
					password);

			// this is all you have to do to support 'remember me' (no config -
			// built in!):
			token.setRememberMe(true);
			currentUser.login(token);

		}

	};

	// @ResponseBody 将内容或对象作为 HTTP
	// 响应正文返回，并调用适合HttpMessageConverter的Adapter转换对象，写入输出流。
	// @ResponseBody可以标注任何对象，由Srping完成对象——协议的转换。
	// @ResponseBody 将内容或对象作为 HTTP
	// 响应正文返回，使用@ResponseBody将会跳过视图处理部分，而是调用适合HttpMessageConverter，将返回值写入输出流。
	// 可以直接返回json字符串。如果不配置@ResponseBody，也可以使用response输出数据然后 return
	// null，达到返回json字符串的效果。
	// 可以直接返回json字符串。如果不配置@ResponseBody，也可以使用response输出数据然后 return
	// null，达到返回json字符串的效果。
	// @ResponseBody之后返回字符串中中文可能会出现乱码，因为sping
	// mvc默认是text/plain;charset=ISO-8859-1，要支持中需做如下配置：
	// <bean
	// class="org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter">
	// <property name="messageConverters">
	// <list>
	// <bean
	// class="org.springframework.http.converter.StringHttpMessageConverter">
	// <property name="supportedMediaTypes">
	// <list>
	// <value>text/plain;charset=UTF-8</value>
	// </list>
	// </property>
	// </bean>
	// </list>
	// </property>
	// </bean>

	@RequestMapping(value = "/userlogout")
	@MethodLog(remark = "用户退出")
	public String userlogout(HttpServletRequest request,
			HttpServletResponse httpresponse) {
		Subject subject = SecurityUtils.getSubject();

		PusSysUser user = (PusSysUser) SecurityUtils.getSubject()
				.getPrincipal();
		if(user!=null){
			Cookie cookie = new Cookie(user.getAccount(), null);
			cookie.setMaxAge(0); // 如果参数是0，就说明立即删除
			httpresponse.addCookie(cookie);
		}
		

		if (subject != null) {
			subject.logout();
		}
		// request.getSession().invalidate();
		// 刷新权限缓存
		shiroCacheManager.getCache(Constants.authorizationCacheName).clear();

		// org.apache.shiro.web.filter.authc.LogoutFilter
		// 记录日志
		SysuserLoginlog ulog = new SysuserLoginlog();
		ulog.setType(1);
		ulog.setUseraccount(user.getAccount());
		ulog.setIp(TCPIPUtil.getIpAddr(request));
		sysuserLoginlogDao.save(ulog);
		return "redirect:login.do";
	};

	public static final void setCurrentUser(PusSysUser user) {

		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			Session session = currentUser.getSession();
			if (null != session) {
				session.setAttribute(Constants.CURRENT_USER, user);
			}

		}

	};

	public static final PusSysUser getCurrentUser() {
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			Session session = currentUser.getSession();
			if (null != session) {
				PusSysUser user = (PusSysUser) session
						.getAttribute(Constants.CURRENT_USER);
				if (null != user) {
					return user;
				}
			}
		}
		return null;
	};

	// 验证码校验
	protected String doCaptchaValidate(HttpServletRequest request,
			CaptchaToken token) {
		// session中的图形码字符串
		String captcha = (String) request.getSession().getAttribute(
				com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		return captcha;
	}

	@Override
	protected CaptchaToken createToken(ServletRequest request,
			ServletResponse response) {
		String captcha = getCaptcha(request);

		return new CaptchaToken(captcha);
	}

	public static final String DEFAULT_CAPTCHA_PARAM = "captcha";

	private String captchaParam = DEFAULT_CAPTCHA_PARAM;

	public String getCaptchaParam() {
		return captchaParam;
	}

	public void setCaptchaParam(String captchaParam) {
		this.captchaParam = captchaParam;
	}

	protected String getCaptcha(ServletRequest request) {
		return WebUtils.getCleanParam(request, getCaptchaParam());
	}

	// 保存异常对象到request
	@Override
	protected void setFailureAttribute(ServletRequest request,
			AuthenticationException ae) {
		request.setAttribute(getFailureKeyAttribute(), ae);
	}
}
