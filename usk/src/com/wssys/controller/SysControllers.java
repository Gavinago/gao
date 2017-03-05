package com.wssys.controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Sha384Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wssys.bean.DwhBean;
import com.wssys.bean.Page;
import com.wssys.bean.PusDeptBean;
import com.wssys.bean.TreeNode;
import com.wssys.dao.DeptReDwhDao;
import com.wssys.dao.PusDeptDao;
import com.wssys.dao.PusSysUserDao;
import com.wssys.dao.SyslogDao;
import com.wssys.entity.DeptReDwh;
import com.wssys.entity.PusSysUser;
import com.wssys.entity.Syslog;
import com.wssys.framework.MethodLog;
import com.wssys.utils.Commonutil;
import com.wssys.utils.TimestampTypeAdapter;

@Controller
public class SysControllers {
	private Logger logger = LoggerFactory.getLogger(SysControllers.class);
	@Autowired
	private SyslogDao syslogDao;



	@Autowired
	private DeptReDwhDao deptReDwhDao;

	@Autowired
	private PusSysUserDao pusSysUserDao;
	
	

	@Autowired
	private PusDeptDao pusDeptDao;

	@RequestMapping(value = "/sjzljcbfb", method = RequestMethod.GET)
	@MethodLog(remark = "上架重量加成百分比查询")
	public ModelAndView sjzljcbfbAction(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("sys/jczl");
//		String basePath = request.getSession().getServletContext()
//				.getRealPath("/WEB-INF/classes");
		String basePath =SysControllers.class.getResource("/").getPath();
//		String websiteURL = (basePath.replace("/build/classes", "").replace("%20"," ").replace("classes/", "") ).replaceFirst("/", "");
		String websiteURL = (basePath.replace("/build/classes", "").replace("%20"," ").replace("classes/", "") );
		InputStream in = null;
		logger.info("properties路径是："+websiteURL
				+ "classes/config/sysconfig.properties");
		try {
			in = new BufferedInputStream(new FileInputStream(websiteURL
					+ "classes/config/sysconfig.properties"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			 e1.printStackTrace();
			// mav.addObject("weightadd","5");
		}
		Properties p = new Properties();
		try {
			p.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// mav.addObject("weightadd","5");
		}
		// String decStr = Commonutil.getPropertyFromFile("config/sysconfig",
		// "weightadd");
		mav.addObject("weightadd", p.get("weightadd"));
		return mav;
	}

	@RequestMapping(value = "/updateSjzljcbfb", method = { RequestMethod.POST,
			RequestMethod.GET })
	@MethodLog(remark = "上架重量百分比修改")
	public ModelAndView updateSjzljcbfbAction(
			@RequestParam("weightadd") String weightadd,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("sys/jczl");
//		String basePath = request.getSession().getServletContext()
//				.getRealPath("/WEB-INF/classes");
		String basePath =HJControllers.class.getResource("/").getPath();
		String websiteURL = (basePath.replace("/build/classes", "").replace("%20"," ").replace("classes/", "") );

		Commonutil.writeProperties(websiteURL
				+ "classes/config/sysconfig.properties",
				"weightadd", weightadd); // 修改

		// String decStr = Commonutil.getPropertyFromFile("config/sysconfig",
		// "weightadd");
		// mav.addObject("weightadd", decStr);
		InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(websiteURL
					+ "classes/config/sysconfig.properties"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
			// mav.addObject("weightadd","5");
		}
		Properties p = new Properties();
		try {
			p.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			// mav.addObject("weightadd","5");
		}
		mav.addObject("weightadd", p.get("weightadd"));
		mav.addObject("message", "修改成功");
		return mav;
	}

	@RequestMapping(value = "/sysloglist", method = RequestMethod.GET)
	public ModelAndView userloginlogAction() {
		ModelAndView mav = new ModelAndView("sys/log/listSysLog");
		return mav;
	}

	@RequestMapping(value = "/sysloglist", method = RequestMethod.POST)
	@MethodLog(remark = "系统日志查询")
	public ModelAndView getUserloginlogJson(
			@RequestParam(required = false, defaultValue = "") String account,
			@RequestParam(required = false, defaultValue = "") int page,
			@RequestParam(required = false, defaultValue = "") int rows,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		Map<String, Object> mapjson = new HashMap<String, Object>();
		Page pages = new Page();
		pages.setCurpage(page);
		pages.setPagesize(rows);

		mapjson.put("total", syslogDao
				.querySysLogPagesByAccount(account, pages).getSumcount());

		List<Syslog> list = syslogDao.querySysLogPagesByAccount(account, pages)
				.getResult();
		List<Syslog> endlist = new ArrayList<Syslog>();

		for (int i = 0; i < list.size(); i++) {
			Syslog endsyslog = list.get(i);
			endsyslog.setMethodName(endsyslog.getMethodName().substring(
					endsyslog.getMethodName().lastIndexOf("."),
					endsyslog.getMethodName().length()));
			endlist.add(endsyslog);
		}

		mapjson.put("rows", endlist);
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd hh:mm:ss");
		gsonBuilder.registerTypeAdapter(Timestamp.class,
				new TimestampTypeAdapter());
		Gson gson = gsonBuilder.create();

		String json = gson.toJson(mapjson);
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.write(json);
		out.flush();
		return null;
	}

	@RequestMapping(value = "/lockgoodslist", method = RequestMethod.GET)
	public ModelAndView lockgoodslistAction() {
		ModelAndView mav = new ModelAndView("sys/listLockOrders");
		return mav;
	}

	
	//
	@RequestMapping(value = "/jyjs", method = RequestMethod.GET)
	public ModelAndView hasjyjsAction() {
		ModelAndView mav = new ModelAndView("sys/goodsSys/listHasJyJs");
		return mav;
	}

	

	// 已经出库单列表
	@RequestMapping(value = "/cwxsfp", method = RequestMethod.GET)
	public ModelAndView cwxsfpAction() {
		ModelAndView mav = new ModelAndView("sys/goodsSys/listXsFp");
		return mav;
	}

	

	// 交易结束信息列表
	@RequestMapping(value = "/jyjsxx", method = RequestMethod.GET)
	public ModelAndView jyjsinfosAction() {
		ModelAndView mav = new ModelAndView("sys/goodsSys/listJyjsInfos");
		return mav;
	}



	// 采购付款确认
	@RequestMapping(value = "/cgglfk", method = RequestMethod.GET)
	public ModelAndView cgglfkAction() {
		ModelAndView mav = new ModelAndView("sys/goodsSys/listCgQr");
		return mav;
	}

	

	

	@RequestMapping(value = "/cggljyjs", method = RequestMethod.GET)
	public ModelAndView cggljyjsAction() {
		ModelAndView mav = new ModelAndView("sys/goodsSys/listCgJs");
		return mav;
	}

	


	@RequestMapping(value = "/yjsj", method = RequestMethod.GET)
	public ModelAndView yjsjAction() {
		ModelAndView mav = new ModelAndView("sys/goodsSys/listYjSjGoods");
		return mav;
	}

	

	@RequestMapping(value = "/saveDeptDwhRelation", method = RequestMethod.POST)
	@MethodLog(remark = "部门提货仓库关系分配保存")
	public String saveDeptDwhRelationAction(
			@RequestParam("dwhid") Integer dwhid,
			@RequestParam("deptid") Integer deptid, SessionStatus status,
			HttpServletResponse response) {
		// String deptidarry[] = deptid.split(",");

		// 保存前先删除之前的关系
		deptReDwhDao.batchDelete(deptid);

		DeptReDwh drd = new DeptReDwh();

		int saverec = 0;
		// for (int i = 0; i < deptidarry.length; i++) {
		drd.setDeptid(deptid);
		drd.setDwhid(dwhid);

		saverec = deptReDwhDao.save(drd);
		// }

		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (saverec > 0) {
			out.write("保存成功,您可以继续保存或者关闭当前页面");
		} else {
			out.write("保存失败");
		}
		// out.write("保存成功,您可以继续保存或者关闭当前页面");

		out.flush();
		return null;
	}

	/**
	 * 入库 用户所属部门数据
	 * 
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/viewSelectDeptByUserid")
	@MethodLog(remark = "入库 用户所属部门下拉数据查看")
	public ModelAndView getviewSelectDeptByUseridJson(
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");

		response.setContentType("text/html;charset=utf-8");
		PusSysUser user = (PusSysUser) SecurityUtils.getSubject()
				.getPrincipal();
		List<PusDeptBean> list = pusDeptDao.findPusDeptBeanByUserid(user
				.getId());

		List<TreeNode> listcombox = new ArrayList<TreeNode>();
		for (PusDeptBean ci : list) {
			TreeNode node = new TreeNode();
			node.setId(ci.getId());
			node.setText(ci.getName());
			listcombox.add(node);
		}

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd hh:mm:ss");
		gsonBuilder.registerTypeAdapter(Timestamp.class,
				new TimestampTypeAdapter());
		Gson gson = gsonBuilder.create();

		String json = gson.toJson(listcombox);

		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.write(json);
		out.flush();
		return null;
	}

	/**
	 * 根据部门id得到提货仓库地址
	 * 
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/viewDwhByDeptIdJson")
	@MethodLog(remark = "涉及查询根据部门id得到提货仓库地址的下拉框数据")
	public ModelAndView getviewDwhByDeptIdJsonJsonJson(
			@RequestParam("deptid") Integer deptid, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html;charset=utf-8");

		response.setContentType("text/html;charset=utf-8");
		List<DwhBean> listDwhBean = new ArrayList<DwhBean>();

		listDwhBean = deptReDwhDao.findDwhNameBean(deptid);

		List<TreeNode> listcombox = new ArrayList<TreeNode>();
		for (DwhBean ci : listDwhBean) {
			TreeNode node = new TreeNode();

			node.setId(ci.getId());
			node.setText(ci.getName());
			listcombox.add(node);

		}

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd hh:mm:ss");
		gsonBuilder.registerTypeAdapter(Timestamp.class,
				new TimestampTypeAdapter());
		Gson gson = gsonBuilder.create();

		String json = gson.toJson(listcombox);

		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.write(json);
		out.flush();
		return null;
	}

	// 采购交易结束信息列表
	@RequestMapping(value = "/cgjyjsxx", method = RequestMethod.GET)
	public ModelAndView cgjyjsxxAction() {
		ModelAndView mav = new ModelAndView("sys/goodsSys/listCgJyjsInfos");
		return mav;
	}


	@RequestMapping(value = "/setAcceptUser", method = RequestMethod.POST)
	@MethodLog(remark = "设置短信接收人")
	public ModelAndView setAcceptUserAction(
			@RequestParam(value = "ids", required = true) String ids,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		Map<String, String> mapjson = new HashMap<String, String>();

		int count = pusSysUserDao.batchSetAcceptUser(ids, 1);
		if (count > 0) {
			mapjson.put("resultMsg", "设置成功");
		} else {
			mapjson.put("resultMsg", "设置失败");
		}

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd hh:mm:ss");
		gsonBuilder.registerTypeAdapter(Timestamp.class,
				new TimestampTypeAdapter());
		Gson gson = gsonBuilder.create();

		String json = gson.toJson(mapjson);
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.write(json);
		out.flush();
		return null;
	}

	@RequestMapping(value = "/CancelAcceptUser", method = RequestMethod.POST)
	@MethodLog(remark = "取消短信接收人")
	public ModelAndView CancelAcceptUserAction(
			@RequestParam(value = "ids", required = true) String ids,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		Map<String, String> mapjson = new HashMap<String, String>();

		int count = pusSysUserDao.batchSetAcceptUser(ids, 0);
		if (count > 0) {
			mapjson.put("resultMsg", "取消成功");
		} else {
			mapjson.put("resultMsg", "取消失败");
		}

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd hh:mm:ss");
		gsonBuilder.registerTypeAdapter(Timestamp.class,
				new TimestampTypeAdapter());
		Gson gson = gsonBuilder.create();

		String json = gson.toJson(mapjson);
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.write(json);
		out.flush();
		return null;
	}

	@RequestMapping(value = "/updateMySysPwd", method = RequestMethod.GET)
	public ModelAndView updateMyPwdviewAction( HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("sys/editMypwd");

		return mav;
	}

	@RequestMapping(value = "/updateMySysPwd", method = RequestMethod.POST)
	@MethodLog(remark = "修改我的密码")
	public ModelAndView updateMySysPwdAction(
			@RequestParam("pwd") String pwd, HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");

		PusSysUser user = (PusSysUser) SecurityUtils.getSubject()
				.getPrincipal();
		ModelAndView mav = new ModelAndView("sys/editMypwd");

		String newpwd = new Sha384Hash(pwd).toBase64();
		int count = pusSysUserDao.batchresetPwd(user.getId(), newpwd);

		if (count > 0) {
			mav.addObject("message", "修改成功,下次登录将会用新密码验证登录");
			
		} else {
			mav.addObject("message", "修改失败");
			
		}
		return mav;
	}
	
	
	
	
	@RequestMapping(value = "/setAcceptDkUser", method = RequestMethod.POST)
	@MethodLog(remark = "设置到款短信接收人")
	public ModelAndView setAcceptDkUserAction(
			@RequestParam(value = "ids", required = true) String ids,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		Map<String, String> mapjson = new HashMap<String, String>();

		int count = pusSysUserDao.batchSetAcceptDkUser(ids, 1);
		if (count > 0) {
			mapjson.put("resultMsg", "设置成功");
		} else {
			mapjson.put("resultMsg", "设置失败");
		}

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd hh:mm:ss");
		gsonBuilder.registerTypeAdapter(Timestamp.class,
				new TimestampTypeAdapter());
		Gson gson = gsonBuilder.create();

		String json = gson.toJson(mapjson);
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.write(json);
		out.flush();
		return null;
	}

	@RequestMapping(value = "/CancelAcceptUserDk", method = RequestMethod.POST)
	@MethodLog(remark = "取消到款短信接收人")
	public ModelAndView CancelAcceptDkUserAction(
			@RequestParam(value = "ids", required = true) String ids,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		Map<String, String> mapjson = new HashMap<String, String>();

		int count = pusSysUserDao.batchSetAcceptDkUser(ids, 0);
		if (count > 0) {
			mapjson.put("resultMsg", "取消成功");
		} else {
			mapjson.put("resultMsg", "取消失败");
		}

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd hh:mm:ss");
		gsonBuilder.registerTypeAdapter(Timestamp.class,
				new TimestampTypeAdapter());
		Gson gson = gsonBuilder.create();

		String json = gson.toJson(mapjson);
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.write(json);
		out.flush();
		return null;
	}
	
	
	@RequestMapping(value = "/setAcceptCkUser", method = RequestMethod.POST)
	@MethodLog(remark = "设置出库短信接收人")
	public ModelAndView setAcceptCkUserAction(
			@RequestParam(value = "ids", required = true) String ids,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		Map<String, String> mapjson = new HashMap<String, String>();

		int count = pusSysUserDao.batchSetAcceptCkUser(ids, 1);
		if (count > 0) {
			mapjson.put("resultMsg", "设置成功");
		} else {
			mapjson.put("resultMsg", "设置失败");
		}

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd hh:mm:ss");
		gsonBuilder.registerTypeAdapter(Timestamp.class,
				new TimestampTypeAdapter());
		Gson gson = gsonBuilder.create();

		String json = gson.toJson(mapjson);
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.write(json);
		out.flush();
		return null;
	}

	@RequestMapping(value = "/CancelAcceptUserCk", method = RequestMethod.POST)
	@MethodLog(remark = "取消出库短信接收人")
	public ModelAndView CancelAcceptCkUserAction(
			@RequestParam(value = "ids", required = true) String ids,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		Map<String, String> mapjson = new HashMap<String, String>();

		int count = pusSysUserDao.batchSetAcceptCkUser(ids, 0);
		if (count > 0) {
			mapjson.put("resultMsg", "取消成功");
		} else {
			mapjson.put("resultMsg", "取消失败");
		}

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd hh:mm:ss");
		gsonBuilder.registerTypeAdapter(Timestamp.class,
				new TimestampTypeAdapter());
		Gson gson = gsonBuilder.create();

		String json = gson.toJson(mapjson);
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.write(json);
		out.flush();
		return null;
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String basePath =HJControllers.class.getResource("/").getPath();
		String websiteURL = (basePath.replace("/build/classes", "").replace("%20"," ").replace("WebRoot/WEB-INF/classes/", "") );
		System.out.println( websiteURL);
	}
}
