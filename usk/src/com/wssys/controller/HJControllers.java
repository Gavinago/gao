package com.wssys.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.MalformedURLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.hash.Sha384Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.wssys.bean.ComPanyForm;
import com.wssys.bean.Constants;
import com.wssys.bean.DwhBean;
import com.wssys.bean.EditPurviewBean;
import com.wssys.bean.GoodsForm;
import com.wssys.bean.Page;
import com.wssys.bean.PusDeptTreeBean;
import com.wssys.bean.PusMenuTreegridBean;
import com.wssys.bean.TreeNode;
import com.wssys.dao.DeptReDwhDao;
import com.wssys.dao.ExpenseManagementDao;
import com.wssys.dao.PusDeptDao;
import com.wssys.dao.PusMenuDao;
import com.wssys.dao.PusReRoleMenuDao;
import com.wssys.dao.PusReUserRoleDao;
import com.wssys.dao.PusRoleDao;
import com.wssys.dao.PusSysUserDao;
import com.wssys.dao.SeqsDao;
import com.wssys.dao.SysuserLoginlogDao;
import com.wssys.dao.TwodimensioncodeDao;
import com.wssys.dao.UrdDao;
import com.wssys.entity.Dwh;
import com.wssys.entity.ExpenseManagement;
import com.wssys.entity.PusDept;
import com.wssys.entity.PusMenu;
import com.wssys.entity.PusReRoleMenu;
import com.wssys.entity.PusReUserRole;
import com.wssys.entity.PusRole;
import com.wssys.entity.PusSysUser;
import com.wssys.entity.Urd;
import com.wssys.framework.MethodLog;
import com.wssys.utils.StringUtil;
import com.wssys.utils.TimestampTypeAdapter;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 汇金交易平台控制层
 * 
 * @author q
 * 
 */
@Controller
public class HJControllers {
	private Logger logger = LoggerFactory.getLogger(HJControllers.class);
	@Autowired
	private PusSysUserDao pusSysUserDao;

	@Autowired
	private PusRoleDao pusRoleDao;

	@Autowired
	private PusMenuDao pusMenuDao;

	@Autowired
	private PusReRoleMenuDao pusReRoleMenuDao;

	@Autowired
	private PusReUserRoleDao pusReUserRoleDao;


	@Autowired
	private UrdDao urdDao;
	
	@Autowired
	private PusDeptDao pusDeptDao;



	@Autowired
	private ExpenseManagementDao expenseManagementDao;

	@Autowired
	private TwodimensioncodeDao twodimensioncodeDao;


	
	@Autowired
	private SysuserLoginlogDao sysuserLoginlogDao;

	
	@Autowired
	private DeptReDwhDao deptReDwhDao;

	@Resource
	private CacheManager shiroCacheManager;

	@Resource
	private SeqsDao seqsDao;

	// public boolean checkResource(Locale locale) throws Exception {
	// File file = new File(this.getServletContext().getRealPath("/")
	// + getUrl());
	// return file.exists();// 判断该jsp页面是否存在
	// }

	@RequestMapping("/usersys")
	public ModelAndView usersysAction() {
		ModelAndView mav = new ModelAndView("sys/listUser");
		return mav;
	}

	@RequestMapping("/viewAllUserJson")
	@MethodLog(remark = "用户数据查看")
	public ModelAndView getViewAllUserJson(
			@RequestParam(required = false, defaultValue = "") String account,
			@RequestParam(required = false, defaultValue = "") int page,
			@RequestParam(required = false, defaultValue = "") int rows,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		Map<String, Object> mapjson = new HashMap<String, Object>();
		Page pages = new Page();
		pages.setCurpage(page);
		pages.setPagesize(rows);

		// 安全过滤
		if (account.equalsIgnoreCase("' or 1 like '%1%")) {
			account = "";
		}
		account = account.replace(" ", "");
		account = account.trim();

		mapjson.put("total",
				pusSysUserDao.queryUserPagesByAccount(account, pages)
						.getSumcount());
		// mapjson.put("rows",
		// pusSysUserDao.queryUserPagesByAccount(account, pages)
		// .getResult());
		mapjson.put("rows",
				pusSysUserDao.findPusSysUserBeanListBean(account, pages));

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd hh:mm:ss");
		gsonBuilder.registerTypeAdapter(Timestamp.class,
				new TimestampTypeAdapter());
		Gson gson = gsonBuilder.create();

		String json = gson.toJson(mapjson);
		// String json = gsonBuilder.(mapjson);
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

	@RequestMapping("/addUser")
	public ModelAndView addUserAction() {
		ModelAndView mav = new ModelAndView("sys/addUser");
		PusSysUser u = new PusSysUser();
		mav.getModelMap().put("psu", u);
		return mav;
	}

	@RequestMapping(value = "/eidtUser", method = RequestMethod.GET)
	public ModelAndView editMCAction(@RequestParam("id") Integer id) {
		ModelAndView mav = new ModelAndView("sys/editUser");
		PusSysUser mc = pusSysUserDao.getById(id);
		mav.addObject("editUserForm", mc);
		return mav;
	}

	@RequestMapping(value = "/eidtUserPwd", method = RequestMethod.GET)
	@MethodLog(remark = "更改后台用户密码跳转")
	public ModelAndView eidtUserPwdAction(@RequestParam("id") Integer id) {
		ModelAndView mav = new ModelAndView("sys/editUser");
		PusSysUser mc = pusSysUserDao.getById(id);
		mav.addObject("editUserForm", mc);
		return mav;
	}

	@RequestMapping(value = "/eidtUserPwd", method = RequestMethod.POST)
	@MethodLog(remark = "更改后台用户密码操作")
	public ModelAndView eidtUserPwdAction(
			@ModelAttribute("editUserForm") PusSysUser psu,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		Map<String, String> mapjson = new HashMap<String, String>();
		String newpwd = new Sha384Hash(psu.getPwd()).toBase64();
		int count = pusSysUserDao.batchresetPwd(psu.getId(), newpwd);
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (count > 0) {
			out.write("修改成功");
		} else {
			out.write("修改失败");
		}

		out.flush();
		return null;
	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	@MethodLog(remark = "更改后台用户")
	public String updateUserAction(
			@ModelAttribute("editUserForm") PusSysUser psu,
			BindingResult result, SessionStatus status,
			HttpServletResponse response) {

		PrintWriter out = null;
		try {
			pusSysUserDao.update(psu);
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// out.write("保存失败");
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		out.write("保存成功,您可以继续更改或者关闭当前页面");

		out.flush();
		return null;
	}

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	@MethodLog(remark = "新增后台用户")
	public String saveUserAction(@ModelAttribute("psu") PusSysUser psu,
			BindingResult result, SessionStatus status,
			HttpServletResponse response) {
		if (pusSysUserDao.checkAccount(psu.getAccount()) > 0) {
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			out.write("保存失败,帐号已经存在");

			out.flush();
			return null;
		}
		// Timestamp now = new Timestamp(System.currentTimeMillis());// 获取系统当前时间
		// mc.setCreatetime(now);
		int saverec = 0;

		psu.setPwd(new Sha384Hash(psu.getPwd()).toBase64());
		saverec = pusSysUserDao.save(psu);

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

		out.flush();
		return null;
	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	@MethodLog(remark = "删除后台用户")
	public ModelAndView deleteUserAction(
			@RequestParam(value = "ids", required = true) String ids,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		Map<String, String> mapjson = new HashMap<String, String>();

		int count = pusSysUserDao.batchDelete(ids);
		if (count > 0) {
			mapjson.put("resultMsg", "删除成功");
		} else {
			mapjson.put("resultMsg", "删除失败");
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

	@RequestMapping(value = "/saveRole", method = RequestMethod.POST)
	@MethodLog(remark = "新增角色")
	public String saveRoleAction(@ModelAttribute("pr") PusRole pr,
			BindingResult result, SessionStatus status,
			HttpServletResponse response) {
		if (pusRoleDao.checkRoleName(pr.getName()) > 0) {
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			out.write("保存失败,角色名称已经存在");

			out.flush();
			return null;
		}
		// Timestamp now = new Timestamp(System.currentTimeMillis());// 获取系统当前时间
		// mc.setCreatetime(now);
		int saverec = 0;

		saverec = pusRoleDao.save(pr);

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

		out.flush();
		return null;
	}

	@RequestMapping("/viewAllRoleJson")
	@MethodLog(remark = "角色列表查看")
	public ModelAndView getViewAllRoleJson(
			@RequestParam(required = false, defaultValue = "") String name,
			@RequestParam(required = false, defaultValue = "") int page,
			@RequestParam(required = false, defaultValue = "") int rows,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		Map<String, Object> mapjson = new HashMap<String, Object>();
		Page pages = new Page();
		pages.setCurpage(page);
		pages.setPagesize(rows);

		mapjson.put("total", pusRoleDao.queryUserPagesByname(name, pages)
				.getSumcount());
		mapjson.put("rows", pusRoleDao.queryUserPagesByname(name, pages)
				.getResult());
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd hh:mm:ss");
		gsonBuilder.registerTypeAdapter(Timestamp.class,
				new TimestampTypeAdapter());
		Gson gson = gsonBuilder.create();

		String json = gson.toJson(mapjson);
		// String json = gsonBuilder.(mapjson);
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

	@RequestMapping("/rolesys")
	public ModelAndView rolesysAction() {
		ModelAndView mav = new ModelAndView("sys/listRole");
		return mav;
	}

	@RequestMapping("/addRole")
	public ModelAndView addRoleAction() {
		ModelAndView mav = new ModelAndView("sys/addRole");
		PusRole pr = new PusRole();
		mav.getModelMap().put("pr", pr);
		return mav;
	}

	@RequestMapping(value = "/eidtRole", method = RequestMethod.GET)
	public ModelAndView eidtRoleAction(@RequestParam("id") Integer id) {
		ModelAndView mav = new ModelAndView("sys/editRole");
		PusRole pr = pusRoleDao.getById(id);
		mav.addObject("editPusRoleForm", pr);
		return mav;
	}

	@RequestMapping(value = "/updateRole", method = RequestMethod.POST)
	@MethodLog(remark = "角色名称修改")
	public String updateRoleAction(
			@ModelAttribute("editPusRoleForm") PusRole pr,
			BindingResult result, SessionStatus status,
			HttpServletResponse response) {

		PrintWriter out = null;
		try {
			pusRoleDao.update(pr);
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// out.write("保存失败");
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		out.write("保存成功,您可以继续更改或者关闭当前页面");

		out.flush();
		return null;
	}

	@RequestMapping(value = "/deleteRole", method = RequestMethod.POST)
	@MethodLog(remark = "删除角色")
	public ModelAndView deleteRoleAction(
			@RequestParam(value = "ids", required = true) String ids,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		Map<String, String> mapjson = new HashMap<String, String>();

		int count = pusRoleDao.batchDelete(ids);
		if (count > 0) {
			mapjson.put("resultMsg", "删除成功");
		} else {
			mapjson.put("resultMsg", "删除失败");
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

	@RequestMapping("/addDept")
	public ModelAndView addDeptAction() {
		ModelAndView mav = new ModelAndView("sys/addDept");
		PusDept pd = new PusDept();
		mav.getModelMap().put("pd", pd);
		return mav;
	}

	@RequestMapping("/deptsys")
	public ModelAndView deptsysAction() {
		ModelAndView mav = new ModelAndView("sys/listDept");
		return mav;
	}

	@RequestMapping("/viewDeptJson")
	@MethodLog(remark = "部门数据查看")
	public ModelAndView getViewDeptJson(
			@RequestParam(required = false, defaultValue = "") String name,
			HttpServletResponse response) {

		response.setContentType("text/html;charset=utf-8");

		// shiro几乎所有的环境下，都可以通过这种方式获取当前用户
		// PusSysUser user = (PusSysUser)
		// SecurityUtils.getSubject().getPrincipal();

		// int userid=user.getId();

		List<PusDept> listpd = pusDeptDao.getPusDeptList(name);
		// 拉出数据库的数据，放入list2中

		List<PusDeptTreeBean> list = new ArrayList<PusDeptTreeBean>();
		Map map = new HashMap<String, PusMenuTreegridBean>();
		// 拉出数据库的数据，放入list2中
		Map pmap = new HashMap<String, PusMenuTreegridBean>();
		// ArrayList<PusMenu> list2 = (ArrayList<TDict>)this.find(hql);

		// 将listpm中的数据，转换成tree/treegrid/contextmenu接收的数据格式
		for (PusDept pd : listpd) {
			PusDeptTreeBean node = new PusDeptTreeBean();
			node.setId(pd.getId());
			node.setName(pd.getName());
			node.setCreatetime(pd.getCreatetime());
			node.setDescription(pd.getDescription());
			node.setPid(pd.getPid());
			node.setDeptgrade(pd.getDeptgrade());
			if (listpd.size() == 1) {
				node.set_parentId(0); // easyui的一个bug 当_parentId不等于0的时候
										// 但是只有一条数据的时候不显示 所以当只有一条数据的时候认为设置该值为0
			} else {
				node.set_parentId(pd.getPid());
			}

			if (pd.getPd() != null) {
				node.setPidname(pd.getPd().getName());
			} else {
				node.setPidname("");
			}
			//List<DwhBean> ldw = deptReDwhDao.findDwhNameBean(pd.getId());
//			String dwname = "";
//			if (ldw.size() > 0) {
//				dwname = ldw.get(0).getName();
//			}
			//node.setDwname(dwname);
			list.add(node);
		}

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd hh:mm:ss");
		gsonBuilder.registerTypeAdapter(Timestamp.class,
				new TimestampTypeAdapter());
		Gson gson = gsonBuilder.create();

		map.put("rows", list);
		map.put("total", list.size());
		String json = gson.toJson(map);

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

	@RequestMapping("/menusys")
	public ModelAndView menusysAction() {
		ModelAndView mav = new ModelAndView("sys/listMenu");
		return mav;
	}

	@RequestMapping("/viewAllMenuJson")
	@MethodLog(remark = "菜单列表数据查看")
	public ModelAndView getViewAllMenuJson(
			@RequestParam(required = false, defaultValue = "") String name,
			HttpServletResponse response) {

		response.setContentType("text/html;charset=utf-8");

		// shiro几乎所有的环境下，都可以通过这种方式获取当前用户
		PusSysUser user = (PusSysUser) SecurityUtils.getSubject()
				.getPrincipal();

		int userid = user.getId();

		List<PusMenu> listpm = pusMenuDao.getPusMenuList(name);
		// 拉出数据库的数据，放入list2中

		List<PusMenuTreegridBean> list = new ArrayList<PusMenuTreegridBean>();
		Map map = new HashMap<String, PusMenuTreegridBean>();
		// 拉出数据库的数据，放入list2中
		Map pmap = new HashMap<String, PusMenuTreegridBean>();
		// ArrayList<PusMenu> list2 = (ArrayList<TDict>)this.find(hql);

		// 将listpm中的数据，转换成TreeNode类型，放入Map中备用
		for (PusMenu pm : listpm) {
			PusMenuTreegridBean node = new PusMenuTreegridBean();
			node.setId(pm.getId());
			node.setName(pm.getName());
			node.setCreatetime(pm.getCreatetime());
			node.setDescription(pm.getDescription());
			node.setMenupid(pm.getMenupid());
			node.setPageurl(pm.getPageurl());
			node.setState(pm.getState());
			node.setType(pm.getType());

			// Map<String, String> attributes = new HashMap<String, String>();
			// attributes.put("url", pm.getPageurl());

			// node.setAttribute(attributes);
			// if (pm.getPm() != null) {
			// node.setParentId(pm.getPm().getId());
			// }

			map.put(pm.getId(), node);

		}

		// 遍历listpm的数据，把每个节点加入他的父节点的孩子List

		for (PusMenu pm : listpm) {

			if (pm.getPm() != null) {

				if (pm.getPm().getId() == null) {
					list.add((PusMenuTreegridBean) map.get(pm.getId()));
				} else {

					Integer pidInteger = pm.getPm().getId();
					PusMenuTreegridBean pnode = (PusMenuTreegridBean) map
							.get(pidInteger);
					PusMenuTreegridBean cnode = (PusMenuTreegridBean) map
							.get(pm.getId());

					if (pnode == null) { // 当分配用户子菜单的时候没有分配 父菜单的情况 如果 不做这个判断
											// 将会无法显示菜单
						pnode = new PusMenuTreegridBean();
						pnode.setId(0);
						pnode.addChild(cnode);
						list.add((PusMenuTreegridBean) map.get(pm.getId()));
					} else {
						pnode.addChild(cnode);
					}
					// if (cnode != null) {
					//
					// list.add((PusMenuTreegridBean) map.get(pm.getId()));
					// }
					// if(pnode==null){
					// pnode=new PusMenuTreegridBean();
					// pnode.setId(0);
					// }
					// pnode.addChild(cnode);
					// if (pnode != null) {
					// pnode.addChild(cnode);
					// }
					// if (!StringUtil.stringIsNull(name).equals("")) {
					// list.add((PusMenuTreegridBean) map.get(pm.getId()));
					// }
				}

			} else {
				list.add((PusMenuTreegridBean) map.get(pm.getId()));

			}

		}

		// Map mapend = new HashMap<Integer, PusMenuTreegridBean>();
		//
		// for (int i = 0; i < list.size(); i++) {
		// int id = list.get(i).getId();
		// // PusMenuTreegridBean pb=
		// mapend.put(id, list.get(i));
		// }
		//
		// List<PusMenuTreegridBean> listend = new
		// ArrayList<PusMenuTreegridBean>();
		// Set set = mapend.keySet();
		//
		// for (Iterator iter = set.iterator(); iter.hasNext();) {
		// Integer key = (Integer) iter.next();
		// PusMenuTreegridBean value = (PusMenuTreegridBean) map.get(key);
		// listend.add(value);
		// // System.out.println(key+"===="+value);
		// }

		// List<PusMenuTreegridBean> listend=new
		// ArrayList<PusMenuTreegridBean>();
		// for (Object value : mapend.values()) {
		// listend.add(value.)
		// }

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd hh:mm:ss");
		gsonBuilder.registerTypeAdapter(Timestamp.class,
				new TimestampTypeAdapter());
		Gson gson = gsonBuilder.create();

		String json = gson.toJson(list);

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

	@RequestMapping("/addMenu")
	public ModelAndView addMenuAction() {
		ModelAndView mav = new ModelAndView("sys/addMenu");
		PusMenu pm = new PusMenu();
		mav.getModelMap().put("menu", pm);
		return mav;
	}

	@RequestMapping(value = "/saveMenu", method = RequestMethod.POST)
	@MethodLog(remark = "保存菜单")
	public String saveMenuAction(@ModelAttribute("menu") PusMenu pm,
			BindingResult result, SessionStatus status,
			HttpServletResponse response) {
		if (pusMenuDao.checkMenuName(pm.getName()) > 0) {
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			out.write("保存失败,名已经存在");

			out.flush();
			return null;
		}
		// Timestamp now = new Timestamp(System.currentTimeMillis());// 获取系统当前时间
		// mc.setCreatetime(now);
		int saverec = 0;
		if (pm.getMenupid() == null) {
			pm.setMenupid(0);
		}
		saverec = pusMenuDao.save(pm);

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

		out.flush();
		return null;
	}

	@RequestMapping(value = "/eidtMenu", method = RequestMethod.GET)
	public ModelAndView editMenuAction(@RequestParam("id") Integer id) {
		ModelAndView mav = new ModelAndView("sys/editMenu");
		PusMenu mc = pusMenuDao.getById(id);
		mav.addObject("editMenuForm", mc);
		return mav;
	}

	@RequestMapping(value = "/updateMenu", method = RequestMethod.POST)
	@MethodLog(remark = "更新菜单")
	public String updateMenuAction(@ModelAttribute("editMenuForm") PusMenu pm,
			BindingResult result, SessionStatus status,
			HttpServletResponse response) {

		PrintWriter out = null;
		try {
			pusMenuDao.update(pm);
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// out.write("保存失败");
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		out.write("保存成功,您可以继续更改或者关闭当前页面");

		out.flush();
		return null;
	}

	@RequestMapping("/viewAddMenuJson")
	public ModelAndView getAddMenuJson(HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");

		List<PusMenu> listpm = pusMenuDao.getPusMenuList();
		// 拉出数据库的数据，放入list2中

		List<TreeNode> list = new ArrayList<TreeNode>();
		Map map = new HashMap<String, TreeNode>();
		// 拉出数据库的数据，放入list2中
		Map pmap = new HashMap<String, TreeNode>();
		// ArrayList<PusMenu> list2 = (ArrayList<TDict>)this.find(hql);

		// 将listpm中的数据，转换成TreeNode类型，放入Map中备用
		for (PusMenu pm : listpm) {
			TreeNode node = new TreeNode();
			node.setId(pm.getId());
			node.setText(pm.getName());
			if (pm.getPm() != null) {
				node.setParentId(pm.getPm().getId());
			}

			map.put(pm.getId(), node);

		}

		// 遍历listpm的数据，把每个节点加入他的父节点的孩子List

		for (PusMenu pm : listpm) {

			if (pm.getPm() != null) {

				if (pm.getPm().getId() == null) {
					list.add((TreeNode) map.get(pm.getId()));
				} else {

					Integer pidInteger = pm.getPm().getId();
					TreeNode pnode = (TreeNode) map.get(pidInteger);
					TreeNode cnode = (TreeNode) map.get(pm.getId());
					pnode.addChild(cnode);
				}
			} else {
				list.add((TreeNode) map.get(pm.getId()));
			}
		}

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd hh:mm:ss");
		gsonBuilder.registerTypeAdapter(Timestamp.class,
				new TimestampTypeAdapter());
		Gson gson = gsonBuilder.create();

		String json = gson.toJson(list);

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

	@RequestMapping("/viewLeftMenuJson")
	@MethodLog(remark = "系统左侧菜单查询")
	public ModelAndView getLeftMenuJson(
			HttpServletResponse response,
			@RequestParam(value = "index", required = false, defaultValue = "") String index) {
		response.setContentType("text/html;charset=utf-8");

		// shiro几乎所有的环境下，都可以通过这种方式获取当前用户
		PusSysUser user = (PusSysUser) SecurityUtils.getSubject()
				.getPrincipal();

		Integer userid = user.getId();

		List<PusMenu> listpm = new ArrayList<PusMenu>();
		try {
			listpm = pusMenuDao.findMenuByUserId(userid);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 拉出数据库的数据，放入list2中

		List<TreeNode> list = new ArrayList<TreeNode>();
		Map map = new HashMap<String, TreeNode>();
		// 拉出数据库的数据，放入list2中
		// Map pmap = new HashMap<String, TreeNode>();
		// ArrayList<PusMenu> list2 = (ArrayList<TDict>)this.find(hql);

		// 将listpm中的数据，转换成TreeNode类型，放入Map中备用
		for (PusMenu pm : listpm) {
			TreeNode node = new TreeNode();
			node.setId(pm.getId());
			node.setText(pm.getName());

			Map<String, String> attributes = new HashMap<String, String>();
			attributes.put("url", pm.getPageurl());

			node.setAttribute(attributes);
			// jdbc方式数据集的特殊处理
			if (pm.getPm() != null) {
				node.setParentId(pm.getPm().getId());
			} else {
				pm.setPm(pusMenuDao.getById(pm.getMenupid()));
				if (pm.getPm() != null) {
					pm.setPm(pusMenuDao.getById(pm.getMenupid()));
				}
			}
			// node.setParentId(pm.getId());
			map.put(pm.getId(), node);

		}

		// 遍历listpm的数据，把每个节点加入他的父节点的孩子List

		for (PusMenu pm : listpm) {

			if (pm.getPm() != null) {

				if (pm.getPm().getId() == null) {
					list.add((TreeNode) map.get(pm.getId()));
				} else {

					Integer pidInteger = pm.getPm().getId();
					TreeNode pnode = (TreeNode) map.get(pidInteger);
					TreeNode cnode = (TreeNode) map.get(pm.getId());
					// if (pnode != null) {
					// pnode.addChild(cnode);
					// }
					if (pnode == null) { // 当分配用户子菜单的时候没有分配 父菜单的情况 如果 不做这个判断
											// 将会无法显示菜单
						pnode = new TreeNode();
						pnode.setId(0);
						pnode.addChild(cnode);
						list.add((TreeNode) map.get(pm.getId()));
					} else {
						pnode.addChild(cnode);
					}

				}
			} else {
				list.add((TreeNode) map.get(pm.getId()));
			}
		}

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd hh:mm:ss");
		gsonBuilder.registerTypeAdapter(Timestamp.class,
				new TimestampTypeAdapter());
		Gson gson = gsonBuilder.create();

		String indexis = StringUtil.stringIsNull(index);

		// 选择该菜单后子菜单置为顶级菜单
		TreeNode dj = new TreeNode();
		dj.setId(0);
		dj.setText("顶级菜单");
		if (!indexis.equals("true")) {
			list.add(dj);
		}

		String json = gson.toJson(list);

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

	@RequestMapping(value = "/deleteMenu", method = RequestMethod.POST)
	@MethodLog(remark = "删除菜单")
	public ModelAndView deleteMenuAction(
			@RequestParam(value = "ids", required = true) String ids,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		Map<String, String> mapjson = new HashMap<String, String>();
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd hh:mm:ss");
		gsonBuilder.registerTypeAdapter(Timestamp.class,
				new TimestampTypeAdapter());
		Gson gson = gsonBuilder.create();

		int pcount = pusMenuDao.pidcount(StringUtil.IntegerIsNull(ids));
		if (pcount > 0) {
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mapjson.put("resultMsg", "请先删除子菜单");
			String json = gson.toJson(mapjson);
			out.write(json);
			out.flush();
			return null;
		}

		int count = pusMenuDao.batchDelete(ids);
		if (count > 0) {
			mapjson.put("resultMsg", "删除成功");
		} else {
			mapjson.put("resultMsg", "删除失败");
		}

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

	// @RequestMapping("/listPurview")
	// public ModelAndView listPurviewAction() {
	// ModelAndView mav = new ModelAndView("sys/listPurview");
	// return mav;
	// }

	@RequestMapping(value = "/assignPermissions", method = RequestMethod.GET)
	@MethodLog(remark = "查看权限分配情况")
	public ModelAndView assignPermissionsAction(@RequestParam("id") Integer id)
			throws Exception {
		ModelAndView mav = new ModelAndView("sys/editPurview");
		PusRole pr = pusRoleDao.getById(id);
		// List<PusMenu> listpm = pusMenuDao.findMenuByRoleId(id);

		EditPurviewBean ep = new EditPurviewBean();
		ep.setRoleid(id);
		ep.setRolename(pr.getName());

		mav.addObject("editPermissionsForm", ep);
		return mav;
	}

	@RequestMapping("/viewPermissionsMenuJson")
	@MethodLog(remark = "权限菜单显示")
	public ModelAndView getPermissionsMenuJson(@RequestParam("id") Integer id,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");

		List<PusMenu> listpm = pusMenuDao.findMenuByRoleId(id); // 被选过的

		List<PusMenu> listrelationpm = pusMenuDao.getPusMenuList();
		// 拉出数据库的数据，放入list2中

		List<TreeNode> list = new ArrayList<TreeNode>();
		Map map = new HashMap<String, TreeNode>();
		// 拉出数据库的数据，放入list2中
		Map pmap = new HashMap<String, TreeNode>();
		// ArrayList<PusMenu> list2 = (ArrayList<TDict>)this.find(hql);
		// if (listpm.size() <= 0) {
		// listpm = listrelationpm;
		// } else {
		//
		// }
		// 将listpm中的数据，转换成TreeNode类型，放入Map中备用
		for (PusMenu pm : listrelationpm) {
			TreeNode node = new TreeNode();
			for (PusMenu pmchecked : listpm) {
				if (pm.getId().equals(pmchecked.getId())) {
					node.setChecked(true);
				}
			}

			node.setId(pm.getId());
			node.setText(pm.getName());

			Map<String, String> attributes = new HashMap<String, String>();
			attributes.put("url", pm.getPageurl());

			node.setAttribute(attributes);
			if (pm.getPm() != null) {
				node.setParentId(pm.getPm().getId());
			}

			map.put(pm.getId(), node);

		}

		// 遍历listpm的数据，把每个节点加入他的父节点的孩子List

		for (PusMenu pm : listrelationpm) {

			if (pm.getPm() != null) {

				if (pm.getPm().getId() == null) {
					list.add((TreeNode) map.get(pm.getId()));
				} else {

					Integer pidInteger = pm.getPm().getId();
					TreeNode pnode = (TreeNode) map.get(pidInteger);
					TreeNode cnode = (TreeNode) map.get(pm.getId());
					pnode.addChild(cnode);
				}
			} else {
				list.add((TreeNode) map.get(pm.getId()));
			}
		}

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd hh:mm:ss");
		gsonBuilder.registerTypeAdapter(Timestamp.class,
				new TimestampTypeAdapter());
		Gson gson = gsonBuilder.create();

		String json = gson.toJson(list);

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

	@RequestMapping(value = "/savePermission", method = RequestMethod.POST)
	@MethodLog(remark = "角色菜单权限分配保存")
	public String savePermissionAction(@RequestParam("roleid") Integer roleid,
			@RequestParam("state") Integer state,
			@RequestParam("menuid") String menuid, SessionStatus status,
			HttpServletResponse response) {
		String menuidarry[] = menuid.split(",");
		// Timestamp now = new Timestamp(System.currentTimeMillis());// 获取系统当前时间
		// mc.setCreatetime(now);
		// 保存前先删除以前该角色的所有角色菜单权限关系
		// for (int i = 0; i < menuidarry.length; i++) {
		// pusReRoleMenuDao.batchDelete(roleid,StringUtil.IntegerIsNull(menuidarry[i]));
		// }
		pusReRoleMenuDao.batchDelete(roleid);

		PusReRoleMenu prrm = new PusReRoleMenu();

		int saverec = 0;
		for (int i = 0; i < menuidarry.length; i++) {
			prrm.setMenuid(StringUtil.IntegerIsNull(menuidarry[i]));
			prrm.setRoleid(roleid);
			prrm.setState(state);

			// int
			// count=pusReRoleMenuDao.haveprrmcount(StringUtil.IntegerIsNull(menuidarry[i]),roleid);
			// if(count>0){
			// pusReRoleMenuDao.batchDelete(StringUtil.IntegerIsNull(menuidarry[i]),roleid);
			// }
			saverec = pusReRoleMenuDao.save(prrm);
		}

		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		out.write("保存成功,您可以继续保存或者关闭当前页面");
		// 刷新权限缓存
		shiroCacheManager.getCache(Constants.authorizationCacheName).clear();
		// if (saverec > 0) {
		// out.write("保存成功,您可以继续保存或者关闭当前页面");
		// } else {
		// out.write("保存失败");
		// }

		out.flush();
		return null;
	}

	@RequestMapping(value = "/aps", method = RequestMethod.GET)
	@MethodLog(remark = "分配用户角色")
	public ModelAndView apAction(@RequestParam("id") Integer id,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("sys/AssignPermissions");
		PusSysUser mc = pusSysUserDao.getById(id);
		PusReUserRole pru = new PusReUserRole();
		pru.setUserid(mc.getId());
		// Map<Object, Object> model = new HashMap<Object, Object>();
		// model.put("account", mc.getAccount());
		mav.addObject("editPusReUserRoleForm", pru);
		mav.addObject("account", mc.getAccount());
		try {
			List<PusRole> listpm = pusRoleDao.findRoleByUserId(id);
			if (listpm.size() > 0) {
				PusRole pr = listpm.get(0);
				mav.addObject("roleid", pr.getId());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// request.setAttribute("account", mc.getAccount());
		// mav.addObject(model);
		return mav;
	}

	@RequestMapping("/viewRolesJson")
	public ModelAndView getRolesJson(@RequestParam("id") Integer id,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");

		List<PusRole> listpm = pusRoleDao.findRoleByUserId(id); // 被选过的

		List<PusRole> listrelationpm = pusRoleDao.getPusRoleList();
		// 拉出数据库的数据，放入list2中

		List<TreeNode> list = new ArrayList<TreeNode>();
		Map map = new HashMap<String, TreeNode>();
		// 拉出数据库的数据，放入list2中
		// Map pmap = new HashMap<String, TreeNode>();
		// ArrayList<PusMenu> list2 = (ArrayList<TDict>)this.find(hql);
		// if (listpm.size() <= 0) {
		// listpm = listrelationpm;
		// } else {
		//
		// }
		// 将listpm中的数据，转换成TreeNode类型，放入Map中备用
		for (PusRole pm : listrelationpm) {
			TreeNode node = new TreeNode();
			for (PusRole pmchecked : listpm) {
				if (pm.getId().equals(pmchecked.getId())) {
					node.setChecked(true);
				}
			}

			node.setId(pm.getId());
			node.setText(pm.getName());

			Map<String, String> attributes = new HashMap<String, String>();

			node.setAttribute(attributes);
			if (pm.getPr() != null) {
				node.setParentId(pm.getPr().getId());
			}

			map.put(pm.getId(), node);

		}

		// 遍历listpm的数据，把每个节点加入他的父节点的孩子List

		for (PusRole pm : listrelationpm) {

			if (pm.getPr() != null) {

				if (pm.getPr().getId() == null) {
					list.add((TreeNode) map.get(pm.getId()));
				} else {

					Integer pidInteger = pm.getPr().getId();
					TreeNode pnode = (TreeNode) map.get(pidInteger);
					TreeNode cnode = (TreeNode) map.get(pm.getId());
					pnode.addChild(cnode);
				}
			} else {
				list.add((TreeNode) map.get(pm.getId()));
			}
		}

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd hh:mm:ss");
		gsonBuilder.registerTypeAdapter(Timestamp.class,
				new TimestampTypeAdapter());
		Gson gson = gsonBuilder.create();

		String json = gson.toJson(list);

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

	@RequestMapping(value = "/updateUserRole", method = RequestMethod.POST)
	@MethodLog(remark = "用户角色关系保存")
	public String updateUserRoleAction(
			@RequestParam(required = false, defaultValue = "") String roleids,
			@RequestParam("userid") Integer userid, SessionStatus status,
			HttpServletResponse response) {
		String roleidarry[] = roleids.split(",");
		// 保存前先删除以前的关系
		for (int i = 0; i < roleidarry.length; i++) {
			pusReUserRoleDao.batchDelete(userid);
		}

		PusReUserRole prur = new PusReUserRole();
		for (int i = 0; i < roleidarry.length; i++) {

			prur.setRoleid(StringUtil.IntegerIsNull(roleidarry[i]));
			prur.setUserid(userid);
			Short s = 1;
			prur.setState(s);

			int saverec = 0;
			// int
			// count=pusReRoleMenuDao.haveprrmcount(StringUtil.IntegerIsNull(menuidarry[i]),roleid);
			// if(count>0){
			// pusReRoleMenuDao.batchDelete(StringUtil.IntegerIsNull(menuidarry[i]),roleid);
			// }
			saverec = pusReUserRoleDao.save(prur);
		}

		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		out.write("保存成功,您可以继续保存或者关闭当前页面");
		// 刷新权限缓存
		shiroCacheManager.getCache(Constants.authorizationCacheName).clear();
		// if (saverec > 0) {
		// out.write("保存成功,您可以继续保存或者关闭当前页面");
		// } else {
		// out.write("保存失败");
		// }

		out.flush();
		return null;
	}

	@RequestMapping(value = "/resetUserPwd", method = RequestMethod.POST)
	@MethodLog(remark = "重置密码")
	public ModelAndView resetUserPwdAction(
			@RequestParam(value = "ids", required = true) String ids,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		Map<String, String> mapjson = new HashMap<String, String>();

		String newpwd = new Sha384Hash("123456").toBase64();
		int count = pusSysUserDao.batchresetPwd(ids, newpwd);
		if (count > 0) {
			mapjson.put("resultMsg", "重置成功");
		} else {
			mapjson.put("resultMsg", "重置失败");
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

	@RequestMapping(value = "/saveDept", method = RequestMethod.POST)
	@MethodLog(remark = "部门新增")
	public String saveDeptAction(@ModelAttribute("pd") PusDept pd,
			BindingResult result, SessionStatus status,
			HttpServletResponse response) {
		if (pusDeptDao.checkPusDeptName(pd.getName()) > 0) {
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			out.write("保存失败,名已经存在");

			out.flush();
			return null;
		}
		// Timestamp now = new Timestamp(System.currentTimeMillis());// 获取系统当前时间
		// mc.setCreatetime(now);
		int saverec = 0;

		saverec = pusDeptDao.save(pd);

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

		out.flush();
		return null;
	}

	@RequestMapping(value = "/saveContextDept", method = RequestMethod.POST)
	@MethodLog(remark = "右键菜单保存")
	public String saveContextDeptAction(@RequestParam() String name,
			@RequestParam() String description, @RequestParam() Integer pid,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");

		if (pusDeptDao.checkPusDeptName(name) > 0) {
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			out.write("保存失败,名已经存在");

			out.flush();
			return null;
		}

		PusDept pd = new PusDept();
		pd.setPid(pid);
		pd.setDescription(description);
		pd.setName(name);

		int s = pusDeptDao.save(pd);
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (s > 0) {
			out.write("保存成功,您可以继续保存或者关闭当前页面");
		} else {
			out.write("保存失败");
		}

		// if (saverec > 0) {
		// out.write("保存成功,您可以继续保存或者关闭当前页面");
		// } else {
		// out.write("保存失败");
		// }

		out.flush();
		return null;
	}

	@RequestMapping(value = "/deleteDept", method = RequestMethod.POST)
	@MethodLog(remark = "删除部门")
	public ModelAndView deleteDeptAction(
			@RequestParam(value = "ids", required = true) String ids,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		Map<String, String> mapjson = new HashMap<String, String>();
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd hh:mm:ss");
		gsonBuilder.registerTypeAdapter(Timestamp.class,
				new TimestampTypeAdapter());
		Gson gson = gsonBuilder.create();

		if (StringUtil.IntegerIsNull(ids) == null) {
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mapjson.put("resultMsg", "所选元素不能为空");
			String json = gson.toJson(mapjson);
			out.write(json);
			out.flush();
			return null;
		}
		int pcount = pusDeptDao.pidcount(StringUtil.IntegerIsNull(ids));
		if (pcount > 0) {
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mapjson.put("resultMsg", "请先删除子部门");
			String json = gson.toJson(mapjson);
			out.write(json);
			out.flush();
			return null;
		}

		int count = pusDeptDao.batchDelete(ids);
		if (count > 0) {
			mapjson.put("resultMsg", "删除成功");
		} else {
			mapjson.put("resultMsg", "删除失败");
		}

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

	@RequestMapping(value = "/updateDept", method = RequestMethod.POST)
	@MethodLog(remark = "更改部门")
	public String updateDeptAction(@RequestParam() String name,
			@RequestParam() String description, @RequestParam() Integer id,
			@RequestParam() Integer pid, SessionStatus status,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		PusDept pd = new PusDept();
		pd.setName(name);
		pd.setDescription(description);
		pd.setId(id);
		pd.setPid(pid);
		pusDeptDao.update(pd);

		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		out.write("保存成功");

		// if (saverec > 0) {
		// out.write("保存成功,您可以继续保存或者关闭当前页面");
		// } else {
		// out.write("保存失败");
		// }

		out.flush();
		return null;
	}

	@RequestMapping("/viewUserDeptTreeJson")
	@MethodLog(remark = "部门分配树显示")
	public ModelAndView getDeptJson(@RequestParam("id") Integer id,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");

		List<PusDept> listpd = pusDeptDao.findPusDeptByUserid(id); // 被选过的

		List<PusDept> listrelationpd = pusDeptDao.getPusDeptList();
		// 拉出数据库的数据，放入list2中

		List<TreeNode> list = new ArrayList<TreeNode>();
		Map map = new HashMap<String, TreeNode>();
		// 拉出数据库的数据，放入list2中
		Map pmap = new HashMap<String, TreeNode>();
		// ArrayList<PusMenu> list2 = (ArrayList<TDict>)this.find(hql);
		// if (listpm.size() <= 0) {
		// listpm = listrelationpm;
		// } else {
		//
		// }
		// 将listpm中的数据，转换成TreeNode类型，放入Map中备用
		for (PusDept pd : listrelationpd) {
			TreeNode node = new TreeNode();
			for (PusDept pdchecked : listpd) {
				if (pd.getId().equals(pdchecked.getId())) {
					node.setChecked(true);
				}
			}

			node.setId(pd.getId());
			node.setText(pd.getName());

			if (pd.getPd() != null) {
				node.setParentId(pd.getPd().getId());
			}

			map.put(pd.getId(), node);

		}

		// 遍历listpm的数据，把每个节点加入他的父节点的孩子List

		for (PusDept pd : listrelationpd) {

			if (pd.getPd() != null) {

				if (pd.getPd().getId() == null) {
					list.add((TreeNode) map.get(pd.getId()));
				} else {

					Integer pidInteger = pd.getPd().getId();
					TreeNode pnode = (TreeNode) map.get(pidInteger);
					TreeNode cnode = (TreeNode) map.get(pd.getId());
					pnode.addChild(cnode);
				}
			} else {
				list.add((TreeNode) map.get(pd.getId()));
			}
		}

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd hh:mm:ss");
		gsonBuilder.registerTypeAdapter(Timestamp.class,
				new TimestampTypeAdapter());
		Gson gson = gsonBuilder.create();

		String json = gson.toJson(list);

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

	@RequestMapping(value = "/udAllocation", method = RequestMethod.GET)
	public ModelAndView udAllocationAction(@RequestParam("id") Integer id)
			throws Exception {
		ModelAndView mav = new ModelAndView("sys/editUserDept");
		PusSysUser pu = pusSysUserDao.getById(id);

		mav.addObject("pu", pu);
		return mav;
	}

	@RequestMapping(value = "/saveUserDeptRelation", method = RequestMethod.POST)
	@MethodLog(remark = "用户部门分配保存")
	public String saveUserDeptRelationAction(
			@RequestParam("userid") Integer userid,
			@RequestParam("deptid") String deptid, SessionStatus status,
			HttpServletResponse response) {
		String deptidarry[] = deptid.split(",");

		// 保存前先删除之前的关系
		urdDao.batchDelete(userid);

		Urd urd = new Urd();

		int saverec = 0;
		for (int i = 0; i < deptidarry.length; i++) {
			urd.setDeptid(StringUtil.IntegerIsNull(deptidarry[i]));
			urd.setUserid(userid);

			saverec = urdDao.save(urd);
		}

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

	@RequestMapping("/companyinforssys")
	public ModelAndView companyinforssysAction() {
		ModelAndView mav = new ModelAndView("sys/listCompany");
		return mav;
	}



	@RequestMapping("/frontcompanyinforssys")
	public ModelAndView frontcompanyinforssysAction() {
		ModelAndView mav = new ModelAndView("sys/listFrontCompany");
		return mav;
	}

	

	@RequestMapping(value = "/addCompany", method = RequestMethod.GET)
	public ModelAndView addCompanyAction() {
		ModelAndView mav = new ModelAndView("sys/addCompany");
		ComPanyForm ciForm = new ComPanyForm();
		mav.getModelMap().put("ciForm", ciForm);
		return mav;
	}

	

	@RequestMapping(value = "/addFrontCompany", method = RequestMethod.GET)
	@MethodLog(remark = "查看新增会员公司页面")
	public ModelAndView addFrontCompanyAction() {
		ModelAndView mav = new ModelAndView("sys/addFrontCompany");
		ComPanyForm ciForm = new ComPanyForm();
		mav.getModelMap().put("ciForm", ciForm);
		return mav;
	}

	

	/**
	 * 等比例缩放图片
	 * 
	 * @param data
	 * @param width
	 * @param height
	 * @return
	 * @throws IOException
	 */
	public static byte[] scaleImage(byte[] data, int width, int height)
			throws IOException {
		BufferedImage buffered_oldImage = ImageIO
				.read(new ByteArrayInputStream(data));
		int imageOldWidth = buffered_oldImage.getWidth();
		int imageOldHeight = buffered_oldImage.getHeight();
		double scale_x = (double) width / imageOldWidth;
		double scale_y = (double) height / imageOldHeight;
		double scale_xy = Math.min(scale_x, scale_y);
		int imageNewWidth = (int) (imageOldWidth * scale_xy);
		int imageNewHeight = (int) (imageOldHeight * scale_xy);
		BufferedImage buffered_newImage = new BufferedImage(imageNewWidth,
				imageNewHeight, BufferedImage.TYPE_INT_RGB);
		buffered_newImage.getGraphics()
				.drawImage(
						buffered_oldImage.getScaledInstance(imageNewWidth,
								imageNewHeight, BufferedImage.SCALE_SMOOTH), 0,
						0, null);
		buffered_newImage.getGraphics().dispose();
		ByteArrayOutputStream outPutStream = new ByteArrayOutputStream();
		ImageIO.write(buffered_newImage, "jpeg", outPutStream);
		return outPutStream.toByteArray();
	}

	

	

	@RequestMapping(value = "/saveUserContactsRelation", method = RequestMethod.GET)
	public ModelAndView saveUserContactsRelationAction(
			@RequestParam("id") Integer id) throws Exception {
		ModelAndView mav = new ModelAndView("sys/user_contacts");
		PusSysUser pu = pusSysUserDao.getById(id);

		mav.addObject("pu", pu);
		return mav;
	}

	

	@RequestMapping("/orgcontacts")
	public ModelAndView orgcontactsAction() {
		ModelAndView mav = new ModelAndView("sys/listOrgContacts");
		return mav;
	}



	@RequestMapping(value = "/frontorgcontacts", method = RequestMethod.GET)
	public ModelAndView frontorgcontactsAction() {
		ModelAndView mav = new ModelAndView("sys/listFrontOrgContacts");
		return mav;
	}

	
	

	@RequestMapping("/vmsys")
	public ModelAndView vmsysAction() {
		ModelAndView mav = new ModelAndView("sys/listVm");
		return mav;
	}



	@RequestMapping(value = "/omsys", method = RequestMethod.GET)
	public ModelAndView omsysAction() {
		ModelAndView mav = new ModelAndView("sys/listOm");
		return mav;
	}


	@RequestMapping(value = "/emsys", method = RequestMethod.GET)
	public ModelAndView emsysAction() {
		ModelAndView mav = new ModelAndView("sys/listEm");
		return mav;
	}

	@RequestMapping(value = "/emsys", method = RequestMethod.POST)
	@MethodLog(remark = "费用管理")
	public ModelAndView getemsysJson(
			@RequestParam(required = false, defaultValue = "") String expensename,
			@RequestParam(required = false, defaultValue = "") int page,
			@RequestParam(required = false, defaultValue = "") int rows,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		Map<String, Object> mapjson = new HashMap<String, Object>();
		Page pages = new Page();
		pages.setCurpage(page);
		pages.setPagesize(rows);

		mapjson.put("total",
				expenseManagementDao.queryEMPagesByname(expensename, pages)
						.getSumcount());
		mapjson.put("rows",
				expenseManagementDao.queryEMPagesByname(expensename, pages)
						.getResult());
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

	@RequestMapping(value = "/addEm", method = RequestMethod.GET)
	public ModelAndView addEMAction() {
		ModelAndView mav = new ModelAndView("sys/addEm");
		ExpenseManagement em = new ExpenseManagement();
		mav.getModelMap().put("em", em);
		return mav;
	}

	@RequestMapping(value = "/addEm", method = RequestMethod.POST)
	@MethodLog(remark = "新增费用")
	public String saveEmAction(@ModelAttribute("em") ExpenseManagement em,
			BindingResult result, SessionStatus status,
			HttpServletResponse response) {
		if (expenseManagementDao.checkEmName(em.getExpensename()) > 0) {
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			out.write("保存失败,费用类别已经存在");

			out.flush();
			return null;
		}
		int saverec = 0;
		saverec = expenseManagementDao.save(em);

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

		out.flush();
		return null;
	}

	@RequestMapping(value = "/eidtEm", method = RequestMethod.GET)
	public ModelAndView eidtEmAction(@RequestParam("id") Integer id) {
		ModelAndView mav = new ModelAndView("sys/editEm");
		ExpenseManagement em = expenseManagementDao.getById(id);
		mav.addObject("editEmForm", em);
		return mav;
	}

	@RequestMapping(value = "/eidtEm", method = RequestMethod.POST)
	@MethodLog(remark = "修改费用")
	public String updateEmAction(
			@ModelAttribute("editEm") ExpenseManagement em,
			BindingResult result, SessionStatus status,
			HttpServletResponse response) {

		PrintWriter out = null;
		try {
			expenseManagementDao.update(em);
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// out.write("保存失败");
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		out.write("保存成功,您可以继续更改或者关闭当前页面");

		out.flush();
		return null;
	}

	@RequestMapping(value = "/deleteEm", method = RequestMethod.POST)
	@MethodLog(remark = "删除费用")
	public ModelAndView deleteEmAction(
			@RequestParam(value = "ids", required = true) String ids,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		Map<String, String> mapjson = new HashMap<String, String>();

		int count = expenseManagementDao.batchDelete(ids);
		if (count > 0) {
			mapjson.put("resultMsg", "删除成功");
		} else {
			mapjson.put("resultMsg", "删除失败");
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

	@RequestMapping(value = "/goodsSys", method = RequestMethod.GET)
	public ModelAndView goodsSysAction() {
		ModelAndView mav = new ModelAndView("sys/goodsSys/listGoods");
		return mav;
	}


	@RequestMapping(value = "/addGoods", method = RequestMethod.GET)
	public ModelAndView addGoodsAction() {
		ModelAndView mav = new ModelAndView("sys/goodsSys/addGoods");
		GoodsForm goodsForm = new GoodsForm();
		mav.getModelMap().put("goodsForm", goodsForm);
		mav.getModelMap().put("goodsnum", System.currentTimeMillis());
		return mav;
	}

	

	@RequestMapping(value = "/addGoodsByImageId", method = RequestMethod.GET)
	public ModelAndView addGoodsByImageIdAction() {
		ModelAndView mav = new ModelAndView("sys/goodsSys/addGoodsMultipleFile");
		GoodsForm goodsForm = new GoodsForm();
		mav.getModelMap().put("goodsForm", goodsForm);
		mav.getModelMap().put("goodsnum", System.currentTimeMillis());

		ExpenseManagement twentyem = expenseManagementDao
				.getFyByExpensename("卸货费:20尺柜");
		mav.addObject("twentyem", twentyem);

		ExpenseManagement fortyem = expenseManagementDao
				.getFyByExpensename("卸货费:40尺柜");
		mav.addObject("fortyem", fortyem);

		ExpenseManagement hcem = expenseManagementDao
				.getFyByExpensename("卸货费:货车");
		mav.addObject("hcem", hcem);

		ExpenseManagement rkgbfem = expenseManagementDao
				.getFyByExpensename("入库过磅费");
		mav.addObject("rkgbfem", rkgbfem);
		return mav;
	}

	

	

	



	@RequestMapping(value = "/dwhlist", method = RequestMethod.GET)
	public ModelAndView dwhlistAction() {
		ModelAndView mav = new ModelAndView("sys/listDwh");
		return mav;
	}

	@RequestMapping(value = "/dwhrelist", method = RequestMethod.GET)
	public ModelAndView dwhrelistAction(
			@RequestParam(value = "deptid", required = false, defaultValue = "") Integer deptid) {
		ModelAndView mav = new ModelAndView("sys/listDwhreDept");
		mav.addObject("deptid", deptid);
		List<DwhBean> d = deptReDwhDao.findDwhNameBean(deptid);

		if (d.size() > 0) {
			mav.addObject("dwhname", d.get(0).getName());
		}

		return mav;
	}

	

	@RequestMapping(value = "/addDwh", method = RequestMethod.GET)
	public ModelAndView addDwhAction() {
		ModelAndView mav = new ModelAndView("sys/addDwh");
		Dwh dwh = new Dwh();
		mav.getModelMap().put("dwh", dwh);
		return mav;
	}




	@RequestMapping(value = "/userloginlog", method = RequestMethod.GET)
	public ModelAndView userloginlogAction() {
		ModelAndView mav = new ModelAndView("sys/listUserLoginLog");
		return mav;
	}

	@RequestMapping(value = "/userloginlog", method = RequestMethod.POST)
	@MethodLog(remark = "用户登录日志查询")
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

		mapjson.put("total", sysuserLoginlogDao
				.querySysuserLoginlogPagesByAccount(account, pages)
				.getSumcount());
		mapjson.put("rows", sysuserLoginlogDao
				.querySysuserLoginlogPagesByAccount(account, pages).getResult());
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd hh:mm:ss");
		gsonBuilder.registerTypeAdapter(Timestamp.class,
				new TimestampTypeAdapter());
		Gson gson = gsonBuilder.create();

		String json = gson.toJson(mapjson);
		// String json = gsonBuilder.(mapjson);
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

	

	public String CreateWarehousePDF(String basePathpdf, Map root) {

		// 站点根目录的绝对路径
		// ModelAndView modelAndView = new ModelAndView("contractTemplate");
		// modelAndView.addObject("STATIC_HTML", false); // 当设置false时不生成静态页面
		// String basePath = request.getSession().getServletContext()
		// .getRealPath("/");

		/* 将生成的内容写入hello .html中 */
		/* 创建配置 */
		Configuration cfg = new Configuration();
		/* 指定模板存放的路径 */
		try {
			cfg.setDirectoryForTemplateLoading(new File(basePathpdf
					+ "/sys/ftl"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cfg.setDefaultEncoding("UTF-8");
		// cfg.setObjectWrapper(new DefaultObjectWrapper());

		/* 从上面指定的模板目录中加载对应的模板文件 */
		// contractTemplate
		Template temp = null;
		try {
			temp = cfg.getTemplate("cd.ftl");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String file1 = basePathpdf + "sys/html/cd" + root.get("goodsnum")
				+ ".html";
		File file = new File(file1);
		if (!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		// Writer out = new FileWriter(file);
		Writer out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file), "utf-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Writer out = new OutputStreamWriter(System.out);
		try {
			temp.process(root, out);
		} catch (TemplateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			out.flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String url = null;
		try {
			url = new File(file1).toURI().toURL().toString();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String outputFile = basePathpdf + "sys/pdf/cd" + root.get("goodsnum")
				+ ".pdf";
		String pdfPath = "sys/pdf/cd" + root.get("goodsnum") + ".pdf";
		OutputStream os = null;
		try {
			os = new FileOutputStream(outputFile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		ITextRenderer renderer = new ITextRenderer();
		// PDFEncryption pdfEncryption = new
		// PDFEncryption(null,null,PdfWriter.ALLOW_PRINTING);
		// renderer.setPDFEncryption(pdfEncryption); //只有打印权限的
		renderer.setDocument(url);

		// 解决中文问题
		ITextFontResolver fontResolver = renderer.getFontResolver();
		try {
			try {
				fontResolver.addFont(basePathpdf + "ui/fonts/simsun.ttc",
						BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		renderer.layout();
		try {
			renderer.createPDF(os);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("转换成功！");
		logger.info("转换仓单pdf成功！");
		try {
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pdfPath;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String basePath = HJControllers.class.getResource("/").getPath();
		// String websiteURL = (basePath.replace("/build/classes",
		// "").replace("%20"," ").replace("classes/", "") +
		// "parameter.properties").replaceFirst("/", "");
		String websiteURL = (basePath.replace("/build/classes", "").replace(
				"%20", " ").replace("classes/", "")).replaceFirst("/", "");
		System.out.println(websiteURL);
	}
}
