package com.gov.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gov.common.auth.ShiroConst;
import com.gov.common.auth.ShiroSessionHelper;
import com.gov.common.service.TUserLogService;
import com.gov.model.Path;
import com.gov.model.Step;
import com.gov.model.TUserLog;
import com.gov.model.User;
import com.gov.service.PathService;
import com.gov.service.StepService;
import com.gov.service.UserService;
import com.gov.util.CacheUtils;
import com.gov.util.NetworkUtil;
import com.gov.util.SyncWeather;
import com.gov.util.exception.AccountFreezeException;
import com.gov.util.exception.PasswordException;
import com.gov.util.exception.UserEmptyException;
import com.gov.util.exception.UserNullException;


@Controller
public class LoginController extends BaseController{
	@Resource
	private UserService userservice;
	@Resource
	private PathService pathService;
	@Resource
	private UserService userService;
	@Resource
	private StepService stepService;
	@Resource
	private TUserLogService tUserLogService;
	String ip;
//	TimerTaskTest ttk = new TimerTaskTest();
	protected Logger logger = LoggerFactory.getLogger(getClass());
//	public String cityname;
	@RequestMapping(value="{param}/login.do",method= RequestMethod.GET)
	public ModelAndView login(@PathVariable String param,HttpServletRequest req,String userName,String password){
		boolean falg ;
		Path path = pathService.loginUrl(param);
		String url = path.getUrl();
		//获取当前客户端的ip地址
		ip = NetworkUtil.getClientIp(req);
		//获取ip对应的地理位置的json字符串
//		String ipAdderss = NetworkUtil.getAddress(ip);
		//解析json字符串获取城市名
//		String cityName = NetworkUtil.getCity(ipAdderss);
		//String str = cityName.substring(cityName.indexOf(','),cityName.lastIndexOf(',') );
//		String[] str = cityName.split("[,]");
		//str[1]国家 2 城市 3 区县
//		cityname = str[1];
//		List<String> list = getWeather(str[1]);
		ModelAndView mav = new ModelAndView();
//		mav.addObject("weather", list);
		System.out.println(url);
		mav.setViewName(url);
		this.logoutlog();
		BaseController.LogOut();
		if(url.equals("/hotel/login")){
			String footer = (String) CacheUtils.get("footer");
			String title = (String) CacheUtils.get("title");
			String site =(String) CacheUtils.get("site");
			String tips = (String) CacheUtils.get("tips");
			if(null==footer){
				List<Step> footers = stepService.selectAllParent(10);
				if(null!=footers&&footers.size()>0){
					footer = footers.get(0).getStepurl();
					title = footers.get(0).getStepname();
					site = footers.get(0).getAddition();
					tips = footers.get(0).getAddition2();
					CacheUtils.put("site",site);
					CacheUtils.put("tips",tips);
					CacheUtils.put("footer",footer);
					CacheUtils.put("title",title);
				}
			}
			mav.addObject("site", site);
			mav.addObject("tips", tips);
			mav.addObject("footer", footer);
			mav.addObject("title", title);
			mav.addObject("color", "");
		}
		
		return mav;
	}
/**
 * 获取天气
 * @return
 */
public List<String> getWeather(String str){
	List<String>list =new ArrayList<String>();
	if(str.trim()!=""&&null!=str&&str!="未分配或者内网IP"){
		list = SyncWeather.weather().getWeather(str);
	}else{
		list = SyncWeather.weather().getWeather("");
	}
	return list;
}
@RequestMapping(value="page/user/login.do",method=RequestMethod.POST)
public ModelAndView login(
		String userName,
		String password,
		String verification,
		HttpServletRequest request,
		HttpServletResponse response){
	ModelAndView mav = new ModelAndView("/hotel/login");
	String tips="";
	String refurl="";
	//保存之前的路径
	SavedRequest savesRequest = WebUtils.getSavedRequest(request);
	if(null==savesRequest){
	}else{
		refurl = savesRequest.getRequestUrl();
	}
	String captchaInServer = (String)request.getSession().getAttribute( 
	         com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY); 
	if(null!=captchaInServer&&captchaInServer.equalsIgnoreCase(verification)){
		if(null==userName||userName.length()<1){
			tips = "用户名不能为空！";
		}else if(null==password){
			tips = "密码不能为空！";
		}
		if(tips.isEmpty()){
		try {
			this.loginValidation(userName,password);
		}catch(UserEmptyException e){
			tips = "用户名不存在！";
			logger.debug("{}登陆失败!UserEmptyException{}",userName,tips);
		}catch(UserNullException e){
			tips = "用户实体不存在！";
			logger.debug("{}登陆失败!UserNullException{}",userName,tips);
		}catch(AccountFreezeException e){
			tips = "用户账户冻结！";
			logger.debug("{}登陆失败!AccountFreezeException{}",userName,tips);
		}catch (AuthenticationException e){
			tips = "密码错误！";
			logger.debug("{}登陆失败! AuthenticationException{}",userName,tips);
		}
		}
		String url =null;
		if(tips.isEmpty()){
			if(refurl.isEmpty()){
				User user = getCurrentUser();
				boolean falg = false;
				if(user.getState()==0){
					falg = true;
					logger.debug("{} 登陆成功！ 检测为初始密码强行修改",userName);
				}
					url = "/back/home.do?change="+falg;
					logger.debug("{} 登陆成功 {}！",userName,url);
			}else{
				//否则跳转到之前请求的页面
        		url = request.getScheme() + "://" + 
    					request.getServerName() + ":" + 
    					request.getServerPort() + refurl;
			}
			mav.setViewName("redirect:"+url); 
		}
	}else{
		tips = "验证码错误 ,请重新输入！";
		logger.debug("{} 验证码错误 ,请重新输入！",userName);
	}
	if(!tips.isEmpty()){
		mav.addObject("site", tips);
		mav.addObject("color", "red");
	}
	return mav;
}
@RequestMapping(value="back/home.do")
public ModelAndView backlogin(boolean change){
	
	ModelAndView mav = new ModelAndView();
	mav.setViewName("back/home");
//	List<String> list = getWeather(cityname);
//	mav.addObject("weather", list);
	String footer = (String) CacheUtils.get("footer");
	String title = (String) CacheUtils.get("title");
	String site =(String) CacheUtils.get("site");
	if(null==footer){
		List<Step> footers = stepService.selectAllParent(10);
		if(null!=footers&&footers.size()>0){
			footer = footers.get(0).getStepurl();
			title = footers.get(0).getStepname();
			CacheUtils.put("footer",footer);
			CacheUtils.put("title",title);
		}
	}
	mav.addObject("footer", footer);
	mav.addObject("title", title);
	TUserLog tuserlog = (TUserLog) ShiroSessionHelper.getAttribute("tuserlog");
	if(null==tuserlog){
		tuserlog = new TUserLog();
		tuserlog.setIp(ip);
		tuserlog.setUserId(getCurrentUser().getUserId());
		tuserlog.setName(getCurrentUser().getUserName());
		tuserlog.setLoginTime(new Date());
		tuserlog.setOperation("登录成功");
		tUserLogService.insert(tuserlog);
		ShiroSessionHelper.setAttribute("tuserlog", tuserlog);
		mav.addObject("change", change);
	}
	return mav;
} 
/**
 * 登录验证
 * @param userName
 * @param password
 * @throws UserEmptyException
 * @throws UserNullException
 * @throws AccountFreezeException
 * @throws PasswordException
 * @throws AuthenticationException
 */
public void loginValidation(String userName,String password)throws
		UserEmptyException,
		UserNullException,
		AccountFreezeException,
		PasswordException,
		AuthenticationException
		{
		//如果调用登录功能，缺省认为先处理注销
		BaseController.LogOut();
		if(null==userName){
			logger.debug("user not found:{} 登录失败！", userName);
			throw new UserEmptyException();//失败
		}
		List<User> list = userService.selectUser(userName);
			if(null==list||list.size()<1){
			logger.debug("user not found:{} 登录失败！", userName);
			throw new UserEmptyException();//失败
		}
		User user = list.get(0);
			if(null==user){
			logger.debug("user entity is null:{} 登录失败！", userName);
			throw new UserNullException();//失败
		}
		if(user.getState()==2){
			logger.debug("user account freeze :{} 登录失败！", userName);
			throw new AccountFreezeException();
		}
		//将当前登录用户实体保存到session中
		SecurityUtils.getSubject().getSession().setAttribute(ShiroConst.SESSION_KEY_USER, user);
		UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
		SecurityUtils.getSubject().login(token);
		}

	@RequestMapping(value="logout.do")
	public String logout(){
		BaseController.LogOut();
		return"redirect:/index/login.do";
	}
	@RequestMapping(value="changepassword.do",method=RequestMethod.POST)
	@ResponseBody
	public int changepassword(String oldpassword,String password){
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute(ShiroConst.SESSION_KEY_USER);
		int i =0;
		oldpassword = new Md5Hash(oldpassword).toString();
		if(user.getPassword().equalsIgnoreCase(oldpassword)){
			if(password.trim()!=""){
				String md5db = new Md5Hash(password).toString();
				 i = userService.updatepassword(user.getUserId(), md5db);
				 logger.debug(":{} 修改密码成功！", user.getUserName());
				 try{
				 TUserLog tuserlog = (TUserLog) ShiroSessionHelper.getAttribute("tuserlog");
				 tuserlog.setOperation("修改密码");
				 tuserlog.setOperationTime(new Date());
				 tUserLogService.insert(tuserlog);
				 }catch(Exception e){
					 logger.debug(":{} 修改密码成功插入日志错误！", user.getUserName());
				 }
			}
		}
		return i;
	}
	/**
	 * 退出登录时插入日志
	 */
	public void logoutlog(){
		TUserLog tuserlog = (TUserLog) ShiroSessionHelper.getAttribute("tuserlog");
		try{
		if(null!=tuserlog){
		tuserlog.setOperation("注销登录");
		tuserlog.setLogoutTime(new Date());
		tUserLogService.insert(tuserlog);
		ShiroSessionHelper.setAttribute("tuserlog",null);
		logger.debug("注销日志插入成功！");
		}
		}catch(Exception e){
			logger.debug("注销日志插入时失败！");
		}
	}
}
