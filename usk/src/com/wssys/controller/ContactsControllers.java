package com.wssys.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wssys.bean.AttechMent;
import com.wssys.bean.MailSend;
import com.wssys.bean.MaildomainsBean;
import com.wssys.bean.Page;
import com.wssys.bean.ReceivingmailNew;
import com.wssys.bean.Rmails;
import com.wssys.bean.SendMainServer;
import com.wssys.dao.DoMainDAO;
import com.wssys.dao.IndividuationconfigDao;
import com.wssys.dao.MailclientDao;
import com.wssys.dao.MaillimitsDao;
import com.wssys.dao.MailreceiptDao;
import com.wssys.dao.MailtaskDao;
import com.wssys.dao.RMailDAO;
import com.wssys.dao.SMailDAO;
import com.wssys.entity.Individuationconfig;
import com.wssys.entity.Mailclient;
import com.wssys.entity.Maildomains;
import com.wssys.entity.Maillimits;
import com.wssys.entity.Mailreceipt;
import com.wssys.entity.Mailtask;
import com.wssys.entity.Receivingmail;
import com.wssys.entity.Sendmail;
import com.wssys.test.MyAuthenticator;
import com.wssys.utils.ContactFormValidator;
import com.wssys.utils.StringUtil;
import com.wssys.utils.TCPIPUtil;
import com.wssys.utils.TimestampTypeAdapter;

@Controller
//@SessionAttributes("login")
public class ContactsControllers {
	private static Logger logger = LoggerFactory
			.getLogger(ContactsControllers.class);
	// 创建固定数目线程的线程池。
	private static int cpuCoreNumber = Runtime.getRuntime()
			.availableProcessors(); // cpu 核心数 返回到Java虚拟机的可用的处理器数量 决不会小于一个;

	private static long millimsforThreademail = 3000l; // 并发 每封邮件任务间隔多少时间  默认值
	//private static long millims = 10000l; // 并发 每个任务间隔多少时间
	private static long millims_less = 3000l; // 单例情况下 每个人每发送一封邮件休息的时间
	private final static int mailsize = 20; // 超过多少封使用并发 默认值
	//static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
	

	@Autowired
	private SMailDAO sMailDAO;

	@Autowired
	private RMailDAO rMailDAO;

	@Autowired
	private DoMainDAO doMainDAO;

	@Autowired
	private MailreceiptDao mailreceiptDao;

	@Autowired
	private MailclientDao mailclientDao;

	@Autowired
	private MaillimitsDao maillimitsDao;

	@Autowired
	private MailtaskDao mailtaskDao;

	@Autowired
	private ContactFormValidator validator;
	
	@Autowired
	private IndividuationconfigDao individuationconfigDao;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	// @ModelAttribute("logUser")
	// public Contact getContact(){
	// Contact logUser = new Contact();
	// return logUser;
	// }

//	@RequestMapping(value = "/login", method = RequestMethod.GET)
//	public ModelAndView loginForm() {
//		ModelAndView mav = new ModelAndView("login");
//		Contact contact = new Contact();
//		mav.addObject("login", contact);
//		return mav;
//	}

//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public String login(
//			@RequestParam(required = true, defaultValue = "") String name,
//			@RequestParam(required = true, defaultValue = "") String password,
//			@ModelAttribute("login") Contact contact, BindingResult result,
//			SessionStatus status) {
//		validator.loginValidate(contact, result);
//		if (result.hasErrors()) {
//			return "login";
//		} else {
//			if (contactsDAO.checkName(name) == 0) {
//				result.rejectValue("name", "filed.name", "Name Does not exist.");
//				return "login";
//			} else {
//				boolean flag = contactsDAO.loginContacts(name, password);
//				if (flag) {
//					logger.info("login sucesss!");
//					return "redirect:viewAllContacts.do";
//				} else {
//					result.rejectValue("password", "filed.password",
//							"Password is Error!");
//					return "login";
//				}
//			}
//		}
//	}
	
	


	

	@RequestMapping("/viewAllContacts")
	public ModelAndView getAllContacts() {
		logger.info("excute search...");
		ModelAndView mav = new ModelAndView("myIndex"); // showContacts
//		List<Contact> contacts = contactsDAO.getAllContacts();
//		mav.addObject("SEARCH_CONTACTS_RESULTS_KEY", contacts);
		return mav;
	}



	









//	@RequestMapping("exit")
//	public ModelAndView exit(SessionStatus status) {
//		ModelAndView mav = new ModelAndView("login");
//		// status.setComplete();
//		logger.info("exit success!");
//		return mav;
//	}

	// -----------------------------------------------------------------
	@RequestMapping("/main")
	public ModelAndView getMain() {
		logger.info("主页...");
		ModelAndView mav = new ModelAndView("main");

		return mav;
	}

	@RequestMapping("/addS")
	public ModelAndView addSmail() {
		ModelAndView mav = new ModelAndView("addSendMail");
		Sendmail sm = new Sendmail();
		mav.getModelMap().put("newSendmail", sm);
		return mav;
	}

	@RequestMapping(value = "/saveSMail", method = RequestMethod.POST)
	public String createSMail(@ModelAttribute("newSendmail") Sendmail sendmail,
			BindingResult result, SessionStatus status,
			HttpServletResponse response) {
		if (sMailDAO.checkMailAddress(sendmail.getMailAddress()) > 0) {
			result.rejectValue("mailAddress", "filed.mailAddress", "邮件地址已经存在.");

			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			out.write("保存失败,邮件地址已存在");

			out.flush();
			return null;
		}
		Timestamp now = new Timestamp(System.currentTimeMillis());// 获取系统当前时间
		sendmail.setCreatetime(now);
		int saverec = 0;
		saverec = sMailDAO.save(sendmail);

		// status.setComplete();
		// return "redirect:/viewAllSendMail.do";

		// return "redirect:viewAllSendMail.do";

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

	@RequestMapping("/viewAllSendMail")
	public ModelAndView getAllSendMail() {
		logger.info("excute search...");
		ModelAndView mav = new ModelAndView("listSendMail"); //

		return mav;
	}

	@RequestMapping("/viewAllSendMailJson")
	public ModelAndView getAllSendMailJson(
			@RequestParam(required = false, defaultValue = "") String mailAddress,
			@RequestParam(required = false, defaultValue = "") int page,
			@RequestParam(required = false, defaultValue = "") int rows,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		Map<String, Object> mapjson = new HashMap<String, Object>();
		Page pages = new Page();
		pages.setCurpage(page);
		pages.setPagesize(rows);

		mapjson.put("total",
				sMailDAO.queryUserPagesBymailAddress(mailAddress, pages)
						.getSumcount());
		mapjson.put("rows",
				sMailDAO.queryUserPagesBymailAddress(mailAddress, pages)
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

	@RequestMapping(value = "/eidtS", method = RequestMethod.GET)
	public ModelAndView editSmail(@RequestParam("id") Integer id) {
		ModelAndView mav = new ModelAndView("editSendMail");
		Sendmail sm = sMailDAO.getById(id);
		mav.addObject("seditmail", sm);
		return mav;
	}

	@RequestMapping(value = "/updateSMail", method = RequestMethod.POST)
	public String updateSMail(@ModelAttribute("seditmail") Sendmail smail,
			BindingResult result, SessionStatus status,
			HttpServletResponse response) {

		// status.setComplete();
		PrintWriter out = null;
		try {
			sMailDAO.update(smail);
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//out.write("保存失败");
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		out.write("保存成功,您可以继续更改或者关闭当前页面");

		out.flush();
		return null;
	}

	@RequestMapping(value = "/deleteBSMail", method = RequestMethod.POST)
	public ModelAndView deleteBatchSMail(
			@RequestParam(value = "ids", required = true) String ids,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		Map<String, String> mapjson = new HashMap<String, String>();

		int count = sMailDAO.batchDelete(ids);
		int dcount = doMainDAO.batchDeletebysid(ids); // 关联删除
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

	// -----------------------------------------------------------------接受邮件--start
	@RequestMapping("/viewAllRMail")
	public ModelAndView getAllRMail() {
		logger.info("excute viewAllRMail...");
		ModelAndView mav = new ModelAndView("listReceiveMail"); //

		return mav;
	}

	@RequestMapping("/viewAllRMailJson")
	public ModelAndView getAllRMailJson(
			@RequestParam(required = false, defaultValue = "") String mailAddress,
			@RequestParam(required = false, defaultValue = "") Integer cid,
			@RequestParam(required = false, defaultValue = "") int page,
			@RequestParam(required = false, defaultValue = "") int rows,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		Map<String, Object> mapjson = new HashMap<String, Object>();
		Page pages = new Page();
		pages.setCurpage(page);
		pages.setPagesize(rows);

		mapjson.put("total",
				rMailDAO.queryUserPagesBymailAddress(mailAddress, cid, pages)
						.getSumcount());
		List<Rmails> rm = new ArrayList<Rmails>();
		List result = rMailDAO.queryUserPagesBymailAddress(mailAddress, cid,
				pages).getResult();
		List<ReceivingmailNew> lr = new ArrayList<ReceivingmailNew>();
		for (int i = 0; i < result.size(); i++) {
			ReceivingmailNew rn = new ReceivingmailNew();
			Object[] obj = (Object[]) result.get(i);
			Receivingmail rmb = new Receivingmail();
			Mailclient mc = new Mailclient();
			mc = (Mailclient) obj[0];
			rmb = (Receivingmail) obj[1];
			rn.setCid(rmb.getCid());
			rn.setClientname(mc.getClintname());
			rn.setCreatetime(rmb.getCreatetime());
			rn.setId(rmb.getId());
			rn.setMailAddress(rmb.getMailAddress());
			rn.setMailRemark(rmb.getMailRemark());
			// rn.setCid(rm.get(i).getRm().getCid());
			// rn.setClientname(rm.get(i).getMc().getClintname());
			// rn.setCreatetime(rm.get(i).getRm().getCreatetime());
			// rn.setId(rm.get(i).getRm().getId());
			// rn.setMailAddress(rm.get(i).getRm().getMailAddress());
			// rn.setMailRemark(rm.get(i).getRm().getMailRemark());
			lr.add(rn);
		}

		// mapjson.put("rows",
		// rMailDAO.queryUserPagesBymailAddress(mailAddress, pages)
		// .getResult());
		mapjson.put("rows", lr);
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

	@RequestMapping("/addR")
	public ModelAndView addRmail() {
		ModelAndView mav = new ModelAndView("addRMail");
		Receivingmail rm = new Receivingmail();
		mav.getModelMap().put("newRmail", rm);
		return mav;
	}

	@RequestMapping(value = "/saveRMail", method = RequestMethod.POST)
	public String createRMail(@ModelAttribute("newRmail") Receivingmail rmail,
			BindingResult result, SessionStatus status,
			HttpServletResponse response) {
		if (rMailDAO.checkMailAddress(rmail.getMailAddress(), rmail.getCid()) > 0) {
			result.rejectValue("mailAddress", "filed.mailAddress", "邮件地址已经存在.");
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			out.write("保存失败,邮件地址已存在");

			out.flush();
			return null;
		}
		Timestamp now = new Timestamp(System.currentTimeMillis());// 获取系统当前时间
		rmail.setCreatetime(now);
		int saverec = 0;
		saverec = rMailDAO.save(rmail);

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

	@RequestMapping(value = "/eidtR", method = RequestMethod.GET)
	public ModelAndView editRmail(@RequestParam("id") Integer id) {
		ModelAndView mav = new ModelAndView("editRMail");
		Receivingmail rm = rMailDAO.getById(id);
		mav.addObject("reditmail", rm);
		return mav;
	}

	@RequestMapping(value = "/updateRMail", method = RequestMethod.POST)
	public String updateRMail(@ModelAttribute("reditmail") Receivingmail rmail,
			BindingResult result, SessionStatus status,
			HttpServletResponse response) {

		// status.setComplete();
		PrintWriter out = null;
		try {
			rMailDAO.update(rmail);
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//out.write("保存失败");
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		out.write("保存成功,您可以继续更改或者关闭当前页面");

		out.flush();
		return null;
	}

	@RequestMapping(value = "/deleteBRMail", method = RequestMethod.POST)
	public ModelAndView deleteBatchRMail(
			@RequestParam(value = "ids", required = true) String ids,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		Map<String, String> mapjson = new HashMap<String, String>();

		int count = rMailDAO.batchDelete(ids);
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

	// --------------------------------------------------------->域管理
	@RequestMapping("/viewAllDoMain")
	public ModelAndView getAllDoMain() {
		logger.info("excute viewAllDoMain...");
		ModelAndView mav = new ModelAndView("listDoMain"); //

		return mav;
	}

	@RequestMapping("/viewAllDoMainJson")
	public ModelAndView getAllDoMainJson(
			@RequestParam(required = false, defaultValue = "") String domainname,
			@RequestParam(required = false, defaultValue = "") String sort,
			@RequestParam(required = false, defaultValue = "") String order,
			@RequestParam(required = false, defaultValue = "") Integer sid,
			@RequestParam(required = false, defaultValue = "") int page,
			@RequestParam(required = false, defaultValue = "") int rows,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");

		// System.out.println("------------------------------------"+sort);
		// System.out.println("------------------------------------"+order);

		Map<String, Object> mapjson = new HashMap<String, Object>();
		Page pages = new Page();
		pages.setCurpage(page);
		pages.setPagesize(rows);

		mapjson.put(
				"total",
				doMainDAO.queryUserPagesByDomainName(domainname, sid, pages,
						sort, order).getSumcount());

		List result = doMainDAO.queryUserPagesByDomainName(domainname, sid,
				pages, sort, order).getResult();
		List<MaildomainsBean> lr = new ArrayList<MaildomainsBean>();
		for (int i = 0; i < result.size(); i++) {
			MaildomainsBean md = new MaildomainsBean();
			Object[] obj = (Object[]) result.get(i);
			Maildomains mdb = new Maildomains();
			Sendmail sm = new Sendmail();
			sm = (Sendmail) obj[0];
			mdb = (Maildomains) obj[1];
			md.setDomainDaylimit(mdb.getDomainDaylimit());
			md.setDomainDayuse(mdb.getDomainDayuse());
			md.setDomainMinutelimit(mdb.getDomainMinutelimit());
			md.setDomainName(mdb.getDomainName());
			md.setId(mdb.getId());
			md.setRemark(mdb.getRemark());
			md.setSendmailaddress(sm.getMailAddress());
			md.setSid(mdb.getSid());

			lr.add(md);
		}

		mapjson.put("rows", lr);
		// mapjson.put("rows",
		// doMainDAO.queryUserPagesByDomainName(domainname, sid, pages)
		// .getResult());
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

	@RequestMapping("/addD")
	public ModelAndView addDoM() {
		ModelAndView mav = new ModelAndView("addDoMain");
		Maildomains dm = new Maildomains();
		mav.getModelMap().put("newDoMain", dm);
		return mav;
	}

	@RequestMapping(value = "/saveDoM", method = RequestMethod.POST)
	public String createDoM(@ModelAttribute("newDoMain") Maildomains dm,
			BindingResult result, SessionStatus status,
			HttpServletResponse response) {
		if (doMainDAO.checkDomainName(dm.getDomainName(), dm.getSid()) > 0) {

			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			out.write("保存失败,该服务已经有 该域已存在");

			out.flush();
			return null;
		}

		int saverec = 0;
		saverec = doMainDAO.save(dm);

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

	@RequestMapping(value = "/eidtD", method = RequestMethod.GET)
	public ModelAndView editDoMain(@RequestParam("id") Integer id) {
		ModelAndView mav = new ModelAndView("editDoMain");
		Maildomains dm = doMainDAO.getById(id);
		mav.addObject("editDoMainAttribute", dm);
		List<Sendmail> list = sMailDAO.getSendmailList();
		mav.addObject("selectSendMail", list);
		return mav;
	}

	@RequestMapping(value = "/updateDoMain", method = RequestMethod.POST)
	public String updateDoMain(
			@ModelAttribute("editDoMainAttribute") Maildomains dm,
			BindingResult result, SessionStatus status,
			HttpServletResponse response) {

		// status.setComplete();
		PrintWriter out = null;
		try {
			doMainDAO.update(dm);
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//out.write("保存失败");
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		out.write("保存成功,您可以继续更改或者关闭当前页面");

		out.flush();
		return null;
	}

	@RequestMapping("/SelectSendMailJson")
	public ModelAndView getSelectSendMailJson(HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		Map<String, Object> mapjson = new HashMap<String, Object>();

		List<Sendmail> list = sMailDAO.getSendmailList();

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd hh:mm:ss");
		gsonBuilder.registerTypeAdapter(Timestamp.class,
				new TimestampTypeAdapter());
		Gson gson = gsonBuilder.create();

		String json = gson.toJson(list);
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

	@RequestMapping(value = "/deleteDoMain", method = RequestMethod.POST)
	public ModelAndView deleteDoMainAction(
			@RequestParam(value = "ids", required = true) String ids,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		Map<String, String> mapjson = new HashMap<String, String>();

		int count = doMainDAO.batchDelete(ids);
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

	// -------------------------------------------------------------------------------邮件发送

	@RequestMapping("/sendMail")
	public ModelAndView sendMailAction() {
		ModelAndView mav = new ModelAndView("sendOperate/SendMail");
		MailSend ms = new MailSend();
		mav.getModelMap().put("newMailSend", ms);
		return mav;
	}

	@ExceptionHandler(RuntimeException.class)
	public ModelAndView handleException(Exception ex, HttpServletRequest request) {
		Map<Object, Object> model = new HashMap<Object, Object>();
		if (ex instanceof MaxUploadSizeExceededException) {
			model.put(
					"errors",
					"文件应不大于 "
							+ getFileKB(((MaxUploadSizeExceededException) ex)
									.getMaxUploadSize()));
		} else {
			model.put("errors", "不知错误: " + ex.getMessage());
		}
		return new ModelAndView("exception", (Map) model);

	}

	public static ExecutorService executor = Executors
			.newFixedThreadPool(cpuCoreNumber);

	@RequestMapping(value = "/sendMailSave", method = RequestMethod.POST)
	public String sendMailSaveAction(
			@ModelAttribute("newMailSend") MailSend ms, BindingResult result,
			SessionStatus status, HttpServletResponse response,
			@RequestParam(value = "fj", required = false) MultipartFile file,
			HttpServletRequest request) throws Exception {
		//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式

		List<Sendmail> smlistAll = sMailDAO.getSendmailList(); // 得到所有发送邮件服务列表
		if (smlistAll == null || smlistAll.size() <= 0) {
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("IOException",e);
				//e.printStackTrace();
			}
			out.write("无发送服务，请配置后再做操作");
			out.flush();

			logger.info("无发送服务，请配置后再做操作...");
			System.out.println("无发送服务，请配置后再做操作");
			return null;
		}

		List<Object> smlisthavedomaina = sMailDAO.queryListHaveDomain(); // 优先用有配置域的发送服务
		List<Sendmail> smlisthavedomain =new ArrayList<Sendmail>();
		for(int i=0;i<smlisthavedomaina.size();i++){
			Object[] o = (Object[]) smlisthavedomaina.get(i);
		
			Sendmail sm=new Sendmail();
			sm.setMailAddress(StringUtil.stringIsNull(o[0]));
			sm.setMailRemark(StringUtil.stringIsNull(o[1]));
			//sm.setCreatetime(createtime)
			sm.setId(StringUtil.IntegerIsNull(o[3]));
			sm.setMailPwd(StringUtil.stringIsNull(o[4]));
			sm.setMailPort(StringUtil.IntegerIsNull(o[5]));
			sm.setMaiSmtpAddress(StringUtil.stringIsNull(o[6]));
			sm.setDayuseLimit(StringUtil.IntegerIsNull(o[7]));
			sm.setDomainids(StringUtil.stringIsNull(o[8]));
			sm.setSendcount(StringUtil.IntegerIsNull(o[9]));
			sm.setTaskid(StringUtil.IntegerIsNull(o[10]));
			smlisthavedomain.add(sm);
		}
		// System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		// ms.setContent(ms.getContent() + df.format(new Date()));
		// ms.setSubject(ms.getSubject() + "_" + df.format(new Date()));

		// List<Sendmail> listdmtest=sMailDAO.queryListNoDomain();
		// final MailSend msa = ms;
		mailtaskDao.deleteAll(); // 发送之前清空以前的临时记录

		String paths = request.getContextPath();
		final String basePath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ paths + "/";

		logger.info("开始发送邮件...");
		UUID uuid = UUID.randomUUID();
		String batchnumber = uuid + "-" + System.currentTimeMillis();

		Long t_start = System.currentTimeMillis(); // 当前系统时间 毫秒

		final List<AttechMent> lam = new ArrayList<AttechMent>();
		if (null != file && !file.isEmpty()) {
			String path = request.getRealPath("/upload");

			try {
				FileCopyUtils.copy(file.getBytes(),
						new File(path + "/" + file.getOriginalFilename()));

				AttechMent am = new AttechMent();
				am.setAttachmentPath(path + "/" + file.getOriginalFilename());
				am.setAttachmentName(file.getOriginalFilename());
				lam.add(am);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				// e1.printStackTrace();
				logger.error("IOException",e1);
				PrintWriter out = null;

				out = response.getWriter();

				out.write("文件上传失败");
				out.flush();
			}
		}

		List<String> mailAddress = new ArrayList<String>(); // 待发送邮件列表

		List<Receivingmail> rmaillist = rMailDAO.getReceivingmailList(ms
				.getCid());
		if (rmaillist != null) {
			for (int i = 0; i < rmaillist.size(); i++) {
				mailAddress.add(rmaillist.get(i).getMailAddress());
			}
		}

		int sendCount = 0; // 即时发送量 临时变量 初始化0

		// 发送服务器
		SendMainServer sms = new SendMainServer();

		// 初始化发送状态信息
		Mailtask mt = new Mailtask();
		mt.setMailsum(mailAddress.size());
		mt.setSented(0);
		mt.setPercentage("");
		mailtaskDao.save(mt);

		if (mailAddress.size() > mailsize) { // 多于十封邮件 走并发发送
			int count = mailAddress.size() / 10;
			int yu = mailAddress.size() % 10;
			String resultthread = "";
			ExecutorService threadPool = Executors
					.newFixedThreadPool(cpuCoreNumber);
			CompletionService<String> pool = new ExecutorCompletionService<String>(
					threadPool);
			for (int i = 0; i < 10; i++) {
				List<String> subList = new ArrayList<String>();
				if (i == 9) {
					subList = mailAddress.subList(i * count, count * (i + 1)
							+ yu);
				} else {
					subList = mailAddress.subList(i * count, count * (i + 1));
				}

				// List<String> subListend = subList;

				// submit 提交要执行的值返回任务，并返回表示挂起的任务结果的 Future。在完成时，可能会提取或轮询此任务。
				// 参数：
				// task - 要提交的任务
				// 返回：
				// 一个表示挂起的任务完成的 Future
				// 抛出：
				// RejectedExecutionException - 如果不能安排要执行的任务
				// NullPointerException - 如果任务为 null

				// for (int ie = 0; ie< 10; ie++) {
				// Future<String> future = (Future<String>) executor
				// .submit(new MassMailSendTask(StringUtil
				// .stringIsNull(mailAddress.get(i)), ms
				// .getSubject(), htmlContent, lam, sms));

				// System.out.println("发件服务器" + i + ":" + smlist);
				// sendmailFor(subList, sms,
				// sendCount, basePath, batchnumber, ms,
				// lam, t_start, smlist,
				// millims);

				pool.submit(new MailTaskThreads(subList, sms, sendCount,
						basePath, batchnumber, ms, lam, t_start,
						smlisthavedomain, millimsforThreademail));
				//System.out.println("提交了任务:" + i);
				// }

				// for (int iek = 0; iek < 10; i++) {

				// Compute the result
				// }

				// new Thread() {
				//
				// public void run() {
				// try {
				// sendmailFor(subListend, sms,
				// sendCount, basePath, batchnumber, msa,
				// lam, t_start);
				// } catch (InterruptedException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// } catch (TimeoutException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }
				// // for (int i = 0; i < 3; i++) {
				// // System.out.println("这是一个邮件任务a" + i);
				// // }
				// }
				//
				// }.start();

				// System.out.println(subList);
			}
			for (int i = 0; i < 10; i++) {

				resultthread += pool.take().get();
			}

			

			//System.out.println("邮件任务发送结束");

			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				logger.error("IOException",e);
			}
			out.write("一共发送" + mailAddress.size() + "封邮件,发送成功"
					+ mailtaskDao.getMailtask().getSented() + "封邮件!");
			out.flush();

			logger.info("批量邮件发送任务完成...");
			threadPool.shutdown(); // 关闭线程池
			// 发送完成 清空发送任务进度临时表
			// mailtaskDao.deleteAll();
			return null;
		} else { // 小于10封邮件走单例发送
			// List<Sendmail> smlist = sMailDAO.getSendmailList(); //
			// 得到所有发送邮件服务列表
			sendmailFor(mailAddress, sms, sendCount, basePath, batchnumber, ms,
					lam, t_start, smlisthavedomain, millims_less);
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				logger.error("IOException",e);
			}
			out.write("一共发送" + mailAddress.size() + "封邮件,发送成功"
					+ mailtaskDao.getMailtask().getSented() + "封邮件!");
			out.flush();

			logger.info("小于10封邮件发送任务完成...");
			// 发送完成 清空发送任务进度临时表
			// mailtaskDao.deleteAll();
			//System.out.println("邮件任务发送结束");
			return null;
		}

		// List<Integer> list = new ArrayList<Integer>();
		// for (int i = 1; i <= mailAddress.size(); i++)
		// // 任务总数
		// list.add(i);

		// 下面是多线程并发任务分发 但是数据大的情况下 邮件容易被封 而且数据库会连接异常

		// System.out.println("content:" + ms.getContent());
		// // System.out.println("附件名称:"+ms.getFj());
		// PrintWriter out = null;
		// try {
		// out = response.getWriter();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// Mailtask mend = mailtaskDao.getMailtask();
		// if (mend.getSented() == mend.getMailsum()) {
		// // out.write("一共发送" + mailAddress.size() + "封邮件,发送成功!");
		// out.write("一共发送" + mailAddress.size() + "封邮件,发送成功!");
		// } else {
		// out.write("");
		// }
		//
		// out.flush();
		//
		// logger.info("邮件发送任务完成...");
		// // 发送完成 清空发送任务进度临时表
		// // mailtaskDao.deleteAll();
		// return null;

	}

	/**
	 * 
	 * @param mailAddress
	 * @param sms
	 * @param sendCount
	 * @param basePath
	 * @param batchnumber
	 * @param ms
	 * @param lam
	 * @param t_start
	 * @throws InterruptedException
	 * @throws TimeoutException
	 * @throws UnsupportedEncodingException
	 *             synchronized
	 */
	synchronized public void sendmailFor(List<String> mailAddress,
			SendMainServer sms, int sendCount, String basePath,
			String batchnumber, MailSend ms, List<AttechMent> lam,
			Long t_start, List<Sendmail> smlist, long millimsforThreademail)
			{
		
		for (int i = 0; i < mailAddress.size(); i++) {
			//boolean havesms=false;	//是否找到发送服务
			int inticount = i;
			String uniquenum = "";
			// 某域下每发每分钟邮件发送量-1封邮件的时间
			long summinTime = 0l;

			// 计算 用哪个邮件发送服务地址---->
			//
			// List<Sendmail> smlist = sMailDAO.getSendmailList(); //
			// 得到所有发送邮件服务列表
			// List<Sendmail> oks=new ArrayList<Sendmail>(); //可发送邮件列表
			Collections.shuffle(smlist); // 打乱发送邮件列表里元素的顺序
			
			havesms:for (int ia = 0; ia < smlist.size(); ia++) {
//				if(!StringUtil.stringIsNull(sms.getEmailUsername()).equals("")){	//内层循环已经找到合适的发送服务
//					break;
//				}
//				if(havesms){
//					break;
//				}
				List<Maildomains> listdomain = doMainDAO
						.getMaildomainsList(smlist.get(ia).getId()); // //发送邮件地址下的邮件域列表

				if (listdomain.size() <= 0) {
					sms.setEmailFrom("");
					sms.setEmailUsername("");
					sms.setDomainid(0);
					sms.setEmailHost("");
					sms.setEmailPassword("");
					sms.setPort(null);
				}
				for (int im = 0; im < listdomain.size(); im++) {
					String yuname = mailAddress.get(i).substring(
							mailAddress.get(i).indexOf("@") + 1,
							mailAddress.get(i).lastIndexOf("."));
					// 如果域包括了该邮件地址 并且限量没有满
					if (listdomain.get(im).getDomainName().contains(yuname)) {
						if ((StringUtil.IntegerIsNull(
								listdomain.get(im).getDomainDaylimit())
								.intValue() > StringUtil.IntegerIsNull(
								listdomain.get(im).getDomainDayuse())
								.intValue())) {
							// int sendtmapcounts=0;
							// Map<String,Integer> m=new
							// HashMap<String,Integer>();
							// m.put(listdomain.get(im).getDomainName(),
							// sendtmapcounts++);
							// sendCounts.add(m);
							// 每分钟发送量
							// 得到该域下的每分钟发送量
							int minutesCount = listdomain.get(im).getSid();
							if (sendCount == (minutesCount - 1)) {
								if (summinTime >= 1000 * 60 * 60) { // 如果超过了一分钟
									try {
										Thread.sleep(1000 * 60 * 60l);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										//e.printStackTrace();
										logger.error("InterruptedException",e);
									} // 休息一分钟再发
								}
							}

							sms.setEmailFrom(smlist.get(ia).getMailAddress());
							sms.setEmailHost(smlist.get(ia).getMaiSmtpAddress());
							sms.setEmailPassword(smlist.get(ia).getMailPwd());
							sms.setEmailUsername(StringUtil.stringIsNull(
									smlist.get(ia).getMailAddress()).substring(
									0,
									StringUtil.stringIsNull(
											smlist.get(ia).getMailAddress())
											.indexOf("@")));
							sms.setPort(smlist.get(ia).getMailPort());

							// sms.setEmailFrom("zqb666@163.com");
							// sms.setEmailHost("smtp.163.com");
							// sms.setEmailPassword("88314477");
							// sms.setEmailUsername("zqb666");
							// sms.setPort(25);

							sms.setDomainid(listdomain.get(im).getId());

							UUID uuid2 = UUID.randomUUID();
							uniquenum = "unique" + System.currentTimeMillis()
									+ uuid2;
							//havesms=true;
							break havesms; // 如果找到满足条件的发送邮箱地址就不再查找
						} else {
							sms.setEmailFrom("");
							sms.setEmailUsername("");
							sms.setDomainid(0);
							sms.setEmailHost("");
							sms.setEmailPassword("");
							sms.setPort(null);
							continue; // 没有找到继续找
						}
						// } else { // 把不能发送完的邮件丢尽临时列表第二天继续发
						// List<String> mailAddressFail = new
						// ArrayList<String>();
						// mailAddressFail.add(mailAddress.get(i));
						//
						// // 明天0点时间 毫秒
						// Calendar cal24 = Calendar.getInstance();
						// cal24.set(Calendar.HOUR_OF_DAY, 24);
						// cal24.set(Calendar.SECOND, 0);
						// cal24.set(Calendar.MINUTE, 0);
						// cal24.set(Calendar.MILLISECOND, 0);
						// long tommorow = cal24.getTimeInMillis() + 1000 * 60 *
						// 60; // 鉴于

						// // //
						// // 休眠到明天
						//

						//
						// // 重置邮件日已使用量
						// doMainDAO.resetDomain_dayuse();
						// continue;
						// }
					} else {
						sms.setEmailFrom("");
						sms.setEmailUsername("");
						sms.setDomainid(0);
						sms.setEmailHost("");
						sms.setEmailPassword("");
						sms.setPort(null);
					}
					// }else{ //当发送服务器没有设置 邮件域的时候
					// sms.setEmailFrom(smlist.get(ia).getMailAddress());
					// sms.setEmailHost(smlist.get(ia).getMaiSmtpAddress());
					// sms.setEmailPassword(smlist.get(ia).getMailPwd());
					// sms.setEmailUsername(StringUtil.stringIsNull(
					// smlist.get(ia).getMailAddress()).substring(
					// 0,
					// StringUtil.stringIsNull(
					// smlist.get(ia).getMailAddress())
					// .indexOf("@")));
					// sms.setPort(smlist.get(ia).getMailPort());
					// sms.setDomainid(listdomain.get(im).getId());
					//
					// UUID uuid2 = UUID.randomUUID();
					// uniquenum ="unique"+System.currentTimeMillis()+ uuid2 ;
					// break; // 如果找到满足条件的发送邮箱地址就不再查找
					// }
				}

			}// 循环发送服务结束
			
			
			//------------------------------------------------------------------------

			if (StringUtil.stringIsNull(sms.getEmailUsername()).equals("")) { // 如果所有发件服务器下的所有域找遍后
				// 还是没有找到合适的发送服务器 那么就找 没有配置发送域的服务

				// 找没有域的发送服务
				List listdm = sMailDAO.queryListNoDomain();

				if (listdm != null) {
					if (listdm.size() <= 0) { // 没有无配置域的服务 就等到第二天继续发
						// List<String> mailAddressFail = new
						// ArrayList<String>();
						// mailAddressFail.add(mailAddress.get(i));

						// 明天0点时间 毫秒
						Calendar cal24 = Calendar.getInstance();
						cal24.set(Calendar.HOUR_OF_DAY, 24);
						cal24.set(Calendar.SECOND, 0);
						cal24.set(Calendar.MINUTE, 0);
						cal24.set(Calendar.MILLISECOND, 0);
						long tommorow = cal24.getTimeInMillis() + 1000 * 60 * 60; // 鉴于
						System.out.print("有任务队列没有找到合适的发送服务，休眠到第二天再继续");
						// 休眠到明天

						try {
							Thread.sleep(tommorow);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							//e.printStackTrace();
							logger.error("InterruptedException",e);
						} //

						// 重置邮件日已使用量
						doMainDAO.resetDomain_dayuse();
						i=i-1;
						continue;
					} 
					
						for (int k = 0; k < listdm.size(); k++) { // 选择 没有配置域的服务
							// 可以无限发送
							Object[] o = (Object[]) listdm.get(k);
							// 计算发送的邮件 是否超过了该服务的日最大发送量
							// inticount=i;
							// int sumcount = i - inticount;
							// if (sumcount > StringUtil.IntegerIsNull(o[9])) {
							// //inticount=0;
							// continue;
							// }

							sms.setEmailFrom(StringUtil.stringIsNull(o[0]));
							sms.setEmailHost(StringUtil.stringIsNull(o[6]));
							sms.setEmailPassword(StringUtil.stringIsNull(o[4]));
							sms.setEmailUsername(StringUtil.stringIsNull(o[0])
									.substring(
											0,
											StringUtil.stringIsNull(o[0])
													.indexOf("@")));
							sms.setPort((Integer) o[5]);
							// sms.setDomainid(StringUtil.stringIsNull(o[8]));

							UUID uuid2 = UUID.randomUUID();
							uniquenum = "unique" + System.currentTimeMillis()
									+ uuid2;
							break; // 如果找到满足条件的发送邮箱地址就不再查找
						}
					

				}

			}
			// 2邮件域是否包含

			// 3如果所有发送邮件服务都满了 那么放入缓存列表第二天丢尽 线程任务继续执行任务

			// <--------------------------------
			// 发邮件
			if (!StringUtil.stringIsNull(sms.getEmailUsername()).equals("")) {
				Timestamp d = new Timestamp(System.currentTimeMillis());
				// 唯一号

				// 正文
				StringBuilder builder = new StringBuilder();
				builder.append("<html><head>");
				builder.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
				builder.append("</head><body>");
				builder.append(ms.getContent());
				String paramdata = "?pch=" + batchnumber + "&sendtime=" + d
						+ "&subject=" + ms.getSubject() + "&smail="
						+ sms.getEmailFrom() + "&rmail=" + mailAddress.get(i)
						+ "&uniquenum=" + uniquenum;
				builder.append("<img src=\"" + basePath + "backMail.do"
						+ paramdata + "\"  style=\"display:none;\" />");
				// style=\"display:none;\"
				// <iframe src="/i/eg_landscape.jpg"></iframe>
				// builder.append("<script type=\"text/javascript\" src=\""+basePath+"/jq/jq2.0.3.js\"></script>");
				// builder.append("<script type=\"text/javascript\" src=\""+basePath+"/jq/kyfw.js\"></script>");
				builder.append("<span style=\"display:none;\">"
						+ System.currentTimeMillis() + "</span>"); // 内容相同的邮件会被当成垃圾邮件
																	// 每封邮件添加不同的时间戳
				builder.append("</body></html>");
				String htmlContent = builder.toString();

				// long begin = System.currentTimeMillis();//取开始时间 单位是毫秒
				// Future<String> future = (Future<String>) executor
				// .submit(new MassMailSendTask(StringUtil
				// .stringIsNull(mailAddress.get(i)), ms
				// .getSubject(), htmlContent, lam, sms));
				Mailreceipt mr = new Mailreceipt();
				mr.setBatchnumber(batchnumber);
				mr.setSendtime(d);
				mr.setRmailaddress(mailAddress.get(i));
				mr.setSmailaddress(sms.getEmailFrom());
				mr.setSubject(ms.getSubject());
				mr.setUniquemailid(uniquenum);

				try {

					sendMailMain(StringUtil.stringIsNull(mailAddress.get(i)),
							ms.getSubject(), htmlContent, lam, sms);
					// Maildomains md = doMainDAO.getById(sms.getDomainid());
					//
					// if (md.getDomainDaylimit().intValue() >
					// md.getDomainDayuse()
					// .intValue()) {
					//
					// sendMailMain(StringUtil.stringIsNull(mailAddress.get(i)),
					// ms.getSubject(), htmlContent, lam, sms);
					//
					//
					// }
					mr.setRemark("ok");
					mr.setStatus(1);
				} catch (Exception e) {
					mr.setRemark(e.getMessage());
					mr.setStatus(0);
				}
				mailreceiptDao.save(mr);

				

				sendCount++; // 每发送一封 该变量加1 aa

				// 每发送一封邮件该邮件的日限量+1

				// sendMail(StringUtil
				// .stringIsNull(mailAddress.get(i)), ms.getSubject(), ms
				// .getContent(), lam, sms);
				// MailUtil.sendMail(StringUtil.stringIsNull(mailAddress.get(i)),
				// ms.getSubject(), ms.getContent());
				// executor.submit(new MassMailSendTask(StringUtil
				// .stringIsNull(mailAddress.get(i)), ms.getSubject(), ms
				// .getContent(), lam, sms));

				// Mailreceipt mr = new Mailreceipt();
				// mr.setRmailaddress(mailAddress.get(i));
				// mr.setSmailaddress(sms.getEmailFrom());
				//
				// mr.setSendtime(d);

				// try {
				// if (future.get(1500000l, TimeUnit.MILLISECONDS) == null) {//
				// 如果Future's
				// // get返回null，任务完成
				// // 超时15秒
				// // System.out.println("任务完成");
				// logger.info(sms.getEmailFrom() + " to"
				// + mailAddress.get(i) + "任务完成!");
				// mr.setStatus(1);
				// mr.setRemark("ok");
				// }
				// } catch (InterruptedException e) {
				// mr.setStatus(0);
				// mr.setRemark("发送失败："+e.getCause().getMessage());
				// } catch (ExecutionException e) {
				// // 否则我们可以看看任务失败的原因是什么
				// System.out.println(e.getCause().getMessage());
				// mr.setStatus(0);
				// mr.setRemark("发送失败："+e.getCause().getMessage());
				// }
				// mr.setStatus(0);
				// mr.setRemark("ok");
				// mr.setBatchnumber(batchnumber);
				// mr.setSubject(ms.getSubject());
				//
				// mr.setUniquemailid(uniquenum);
			

			}

			Long t_end = System.currentTimeMillis(); // 当前系统时间 毫秒

			// 每发送一封邮件所花时间
			long oneMailTime = t_end - t_start;

			summinTime += oneMailTime;
			
			Individuationconfig ic=individuationconfigDao.findByconfigkey("mailsendSecond");
			if(ic==null||StringUtil.stringIsNull(ic.getConfigvalue()).equals("")){
				try {
					Thread.sleep(millimsforThreademail);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					logger.error("InterruptedException",e);
				}
			}else{
				long sleeptime=Integer.valueOf(ic.getConfigvalue())*1000l;
				try {
					Thread.sleep(sleeptime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					logger.error("InterruptedException",e);
				}
			}
			

		} // 收件邮箱循环 <-------------------------------------------------------

	}

	public class MailTaskThreads implements Callable<String> {
		final List<String> mailAddress;
		final SendMainServer sms;
		final int sendCount;
		final String basePath;
		final String batchnumber;
		final MailSend ms;
		final List<AttechMent> lam;
		final Long t_start;
		final List<Sendmail> smlist;
		final long millimsforThreademail;

		public MailTaskThreads(List<String> mailAddress, SendMainServer sms,
				int sendCount, String basePath, String batchnumber,
				MailSend ms, List<AttechMent> lam, Long t_start,
				List<Sendmail> smlist, long millimsforThreademail) {
			this.mailAddress = mailAddress;
			this.sms = sms;
			this.sendCount = sendCount;
			this.basePath = basePath;
			this.batchnumber = batchnumber;
			this.ms = ms;
			this.lam = lam;
			this.t_start = t_start;
			this.smlist = smlist;
			this.millimsforThreademail = millimsforThreademail;
		}

		void sendmailfors(List<String> mailAddress, SendMainServer sms,
				int sendCount, String basePath, String batchnumber,
				MailSend ms, List<AttechMent> lam, Long t_start,
				List<Sendmail> smlist, long millimsforThreademail)
				throws InterruptedException, TimeoutException,
				UnsupportedEncodingException {
			sendmailFor(mailAddress, sms, sendCount, basePath, batchnumber, ms,
					lam, t_start, smlist, millimsforThreademail);
		}

		// @Override
		// public void run() {
		// // TODO Auto-generated method stub
		// try {
		// sendmailfors(mailAddress, sms, sendCount, basePath,
		// batchnumber, ms, lam, t_start);
		// System.out.println("发送完毕");
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }

		@Override
		public String call() throws Exception {
			// TODO Auto-generated method stub
			try {

				sendmailfors(mailAddress, sms, sendCount, basePath,
						batchnumber, ms, lam, t_start, smlist, millimsforThreademail);
				// System.out.println("发送完毕");
			} catch (Exception e) {
				//e.printStackTrace();
				logger.error("Exception",e);
				return e.getMessage();
			}
			return "发送完毕";
		}

	}

	// public class MassMailSendTask implements Runnable {
	// final String mailAddress;
	// final String subject;
	// final String content;
	// final List<AttechMent> listattachement;
	// final SendMainServer sms;
	//
	// public MassMailSendTask(String mailAddress, String subject,
	// String content, List<AttechMent> listattachement,
	// SendMainServer sms) {
	// this.mailAddress = mailAddress;
	// this.subject = subject;
	// this.content = content;
	// this.listattachement = listattachement;
	// this.sms = sms;
	// }
	//
	// void MassMailSend(String mailAddress)
	// throws UnsupportedEncodingException {
	//
	// String htmlContent = content;
	//
	// // String[] msgTo = { "6637152@qq.com", "651947105@qq.com" };
	// // String[] msgTo = new String[mailAddress.size()];
	// // for (int i = 0; i < mailAddress.size(); i++) {
	// // msgTo[i] = mailAddress.get(i);
	// // }
	//
	// // List<AttechMent> listattachement = new ArrayList<AttechMent>();
	// Maildomains md = doMainDAO.getById(sms.getDomainid());
	// if(null!=md){
	// if (md.getDomainDaylimit().intValue() > md.getDomainDayuse()
	// .intValue()) {
	// sendMail(mailAddress, subject, htmlContent, listattachement,
	// sms);

	// } else {
	// System.out.println("限量已到");
	// if(null!=sms){
	// if(md.getId()==null){ //没有配置域的服务
	// sendMail(mailAddress, subject, htmlContent, listattachement,
	// sms);

	// }
	//
	// }
	// }
	// }else{ //无限量发送
	// sendMail(mailAddress, subject, htmlContent, listattachement,
	// sms);

	// }
	//
	// }
	//
	// public void run() {
	//
	// try {
	// MassMailSend(mailAddress);
	// System.out.println("发送完毕");
	// } catch (Exception e) {
	// Timestamp now = new Timestamp(System.currentTimeMillis());// 获取系统当前时间
	// Mailreceipt mlog=new Mailreceipt();
	// mlog.setRmailaddress(mailAddress);
	// mlog.setRemark(e.getMessage());
	// mlog.setSendtime(now);
	// mlog.setStatus(0);
	// mlog.setSmailaddress(sms.getEmailFrom());
	//
	// mailreceiptDao.save(mlog); // 记录邮件发送信息日志表
	// e.printStackTrace();
	// System.out.println("发送失败");
	// }
	// }
	//
	// }

	/**
	 * 发送html邮件
	 * 
	 * @author
	 * @date
	 * @param toEmail
	 * @param subject
	 * @param htmlContent
	 * @throws UnsupportedEncodingException
	 * @throws InterruptedException
	 */
	public void sendMailMain(String toEmail, String subject,
			String htmlContent, List<AttechMent> listattachement,
			SendMainServer sms) throws UnsupportedEncodingException,
			InterruptedException {
		Maildomains md = doMainDAO.getById(sms.getDomainid());
		if (null != md) {
			if (md.getDomainDaylimit().intValue() > md.getDomainDayuse()
					.intValue()) {
				sendmailmethod(toEmail, subject, htmlContent, listattachement,
						sms);
				doMainDAO.updateDomain_dayuse(sms.getDomainid()); // 每发送一封邮件该邮件的日限量+1
				mailtaskDao.updateMailtaskSented();
			} else {
				System.out.println("限量已到");

			}
		} else {
			sendmailmethod(toEmail, subject, htmlContent, listattachement, sms);

			// doMainDAO.updateDomain_dayuse(sms.getDomainid()); //
			// 每发送一封邮件该邮件的日限量+1
			mailtaskDao.updateMailtaskSented();
			mailtaskDao.updateMailtaskSented();
		}
		// public static final String emailHost = "smtp.163.com";
		// public static final String emailFrom = "zqb666@163.com";
		// public static final String emailUsername = "zqb666";
		// public static final String emailPassword = "88314477";
		// sms.setEmailFrom("651947105@qq.com");
		// sms.setEmailHost("smtp.qq.com");
		// sms.setEmailPassword("zqb88314477");
		// sms.setEmailUsername("651947105");
		// sms.setPort(25);

		// sms.setEmailFrom("651947105@qq.com");
		// sms.setEmailHost("smtp.qq.com");
		// sms.setEmailPassword("zqb88314477");
		// sms.setEmailUsername("651947105");
		// sms.setPort(25);

	}

	public void sendmailmethod(String toEmail, String subject,
			String htmlContent, List<AttechMent> listattachement,
			SendMainServer sms) throws UnsupportedEncodingException {
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		// 发送邮箱的邮件服务器
		senderImpl.setHost(sms.getEmailHost());
		senderImpl.setPort(sms.getPort());

		// 建立邮件消息,发送简单邮件和html邮件的区别
		MimeMessage mailMessage = senderImpl.createMimeMessage();
		// 为防止乱码，添加编码集设置
		// MimeMessageHelper messageHelper = new
		// MimeMessageHelper(mailMessage,
		// "UTF-8");
		// multipart模式
		MimeMessageHelper messageHelper = null;
		try {
			messageHelper = new MimeMessageHelper(mailMessage, true, "UTF-8");
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			logger.error("javamailMessagingException",e1);
		}

		try {
			// 接收方邮箱
			messageHelper.setTo(toEmail);

		} catch (MessagingException e) {
			throw new RuntimeException("收件人邮箱地址出错！");
		}
		try {
			// 发送方邮箱
			messageHelper.setFrom(sms.getEmailFrom());
		} catch (MessagingException e) {
			throw new RuntimeException("发件人邮箱地址出错！");
		}
		try {
			messageHelper.setSubject(subject);
		} catch (MessagingException e) {
			throw new RuntimeException("邮件主题出错！");
		}
		try {
			// true 表示启动HTML格式的邮件
			messageHelper.setText(htmlContent, true);
		} catch (MessagingException e) {
			throw new RuntimeException("邮件内容出错！");
		}

		// 发送附件
		// String attachmentPath="D:/test/4001230o2.jpg";
		// String attachmentName="4001230o2.jpg";
		// FileSystemResource attachment=new
		// FileSystemResource(attachmentPath);
		//
		// String
		// attachmentPath2="e:/soft/4ad79dcd-272a-31f1-b6fe-669d5e178e1c.rar";
		// String
		// attachmentName2="4ad79dcd-272a-31f1-b6fe-669d5e178e1c.rar";
		// FileSystemResource attachment2=new
		// FileSystemResource(attachmentPath2);

		try {
			// listattachement
			// messageHelper.addAttachment(attachmentName,attachment);
			// messageHelper.addAttachment(attachmentName2,attachment2);
			if (null != listattachement) {
				for (int i = 0; i < listattachement.size(); i++) {
					FileSystemResource attachment = new FileSystemResource(
							listattachement.get(i).getAttachmentPath());
					// 附件中文乱码解决方案 MimeUtility.encodeWord
					messageHelper.addAttachment(MimeUtility
							.encodeWord(listattachement.get(i)
									.getAttachmentName()), attachment);

				}
			}

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			throw new RuntimeException("邮件内容编码出错！");
		}
		Properties prop = new Properties();
		// 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
		prop.put("mail.smtp.auth", "true");
		// 超时时间
		prop.put("mail.smtp.timeout", "30000");

		logger.info("系统时间---------------------------------"
				+ df.format(new Date()));
		logger.info("发给谁---------------------------------" + toEmail);
		logger.info("---------------------------------" + sms.getEmailHost()
				+ "-" + sms.getEmailFrom() + ":" + sms.getEmailPassword());

		System.out.println("系统时间---------------------------------"
				+ df.format(new Date()));
		System.out.println("发给谁---------------------------------" + toEmail);
		System.out.println("---------------------------------"
				+ sms.getEmailHost() + "-" + sms.getEmailFrom() + ":"
				+ sms.getEmailPassword());

		// 添加验证
//		MyAuthenticator auth = new MyAuthenticator(sms.getEmailUsername(),
//				sms.getEmailPassword());

		// MyAuthenticator auth = new MyAuthenticator();
		// PasswordAuthentication my =
		// auth.performCheck(sms.getEmailUsername(),
		// sms.getEmailPassword());

		// getDefaultInstance 如果用这个不适合多邮件给多邮件发 因为是单实例 第一次记录后 后面都用第一次的配置
		// 会造成验证失败的异常
		// Session session = Session.getDefaultInstance(prop, auth);
		Session session;
		if (sms.getEmailUsername() != null
				&& sms.getEmailUsername().length() > 0) {
			MyAuthenticator authentic = new MyAuthenticator(
					sms.getEmailUsername(), sms.getEmailPassword());
			session = Session.getInstance(prop, authentic);
		} else {
			session = Session.getInstance(prop, null);
		}

		// session.
		senderImpl.setSession(session);

		// senderImpl.setJavaMailProperties(prop);
		// 发送邮件
		senderImpl.send(mailMessage);
	}

	@RequestMapping(value = "/txtInputSave", method = RequestMethod.POST)
	public String txtInputSaveAction(
			@ModelAttribute("newReceivingmailInput") Receivingmail formrm,
			HttpServletResponse response,
			@RequestParam(value = "txtpath", required = false) MultipartFile file,
			HttpServletRequest request) throws IOException {
		if (null != file && !file.isEmpty()) {
			String path = request.getRealPath("/upload");
			try {
				FileCopyUtils.copy(file.getBytes(),
						new File(path + "/" + file.getOriginalFilename()));

				try {
					String encoding = "UTF-8";
					File txtfile = new File(path + "/"
							+ file.getOriginalFilename());
					if (txtfile.isFile() && txtfile.exists()) { // 判断文件是否存在
						InputStreamReader read = new InputStreamReader(
								new FileInputStream(txtfile), encoding);// 考虑到编码格式
						BufferedReader bufferedReader = new BufferedReader(read);
						String lineTxt = null;

						List<Receivingmail> listrmail = new ArrayList<Receivingmail>();

						while ((lineTxt = bufferedReader.readLine()) != null) {
							Receivingmail rm = new Receivingmail();
							rm.setMailAddress(lineTxt);
							Timestamp now = new Timestamp(
									System.currentTimeMillis());// 获取系统当前时间
							rm.setCreatetime(now);
							rm.setMailRemark("txt导入");
							rm.setCid(formrm.getCid());
							listrmail.add(rm);
							// System.out.println(lineTxt);
						}

						for (int i = 0; i < listrmail.size(); i++) {
							if (rMailDAO.checkMailAddress(StringUtil
									.stringIsNull(listrmail.get(i)
											.getMailAddress()), StringUtil
									.IntegerIsNull(listrmail.get(i).getCid())) > 0) { // 有重复就跳到下一条
								continue;
							} else {
								rMailDAO.save(listrmail.get(i));
							}

						}

						read.close();

						PrintWriter out = null;

						out = response.getWriter();

						out.write("ok"); // 读取成功
						out.flush();
					} else {
						PrintWriter out = null;

						out = response.getWriter();

						out.write("找不到指定的文件"); // 读取成功
						out.flush();
						// System.out.println("找不到指定的文件");
					}
				} catch (Exception e) {
					PrintWriter out = null;

					out = response.getWriter();

					out.write("读取文件内容出错"); // 读取成功
					out.flush();
					// System.out.println("读取文件内容出错");
					logger.error(e.getMessage());
					e.printStackTrace();

				}
				// AttechMent am = new AttechMent();
				// am.setAttachmentPath(path + "/" +
				// file.getOriginalFilename());
				// am.setAttachmentName(file.getOriginalFilename());
				// lam.add(am);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				// e1.printStackTrace();
				PrintWriter out = null;

				out = response.getWriter();

				out.write("文件上传失败");
				out.flush();
			}

			return null;
		} else {
			PrintWriter out = null;

			out = response.getWriter();

			out.write("文件不存在");
			out.flush();
			return null;
		}
	}

	@RequestMapping("/viewAllMailClient")
	public ModelAndView getAllMailClient() {
		logger.info("excute viewAllMailClient...");
		ModelAndView mav = new ModelAndView("listMailClient"); //

		return mav;
	}

	@RequestMapping("/viewAllMailClientJson")
	public ModelAndView getAllMailClientJson(
			@RequestParam(required = false, defaultValue = "") String clintname,
			@RequestParam(required = false, defaultValue = "") int page,
			@RequestParam(required = false, defaultValue = "") int rows,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		Map<String, Object> mapjson = new HashMap<String, Object>();
		Page pages = new Page();
		pages.setCurpage(page);
		pages.setPagesize(rows);

		mapjson.put("total",
				mailclientDao.queryUserPagesByclintname(clintname, pages)
						.getSumcount());
		mapjson.put("rows",
				mailclientDao.queryUserPagesByclintname(clintname, pages)
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

	@RequestMapping(value = "/saveMailClient", method = RequestMethod.POST)
	public String createMailClient(
			@ModelAttribute("newMailClient") Mailclient mc,
			BindingResult result, SessionStatus status,
			HttpServletResponse response) {
		if (mailclientDao.checkclintname(mc.getClintname()) > 0) {
			// result.rejectValue("mailAddress", "filed.mailAddress",
			// "客户名称已经存在.");
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			out.write("保存失败,客户名称已经存在");

			out.flush();
			return null;
		}
		// Timestamp now = new Timestamp(System.currentTimeMillis());// 获取系统当前时间
		// mc.setCreatetime(now);
		int saverec = 0;
		saverec = mailclientDao.save(mc);

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

	@RequestMapping("/addMC")
	public ModelAndView addMC() {
		ModelAndView mav = new ModelAndView("addMailClient");
		Mailclient mc = new Mailclient();
		mav.getModelMap().put("newMailClient", mc);
		return mav;
	}

	@RequestMapping(value = "/eidtMC", method = RequestMethod.GET)
	public ModelAndView editMCAction(@RequestParam("id") Integer id) {
		ModelAndView mav = new ModelAndView("editMC");
		Mailclient mc = mailclientDao.getById(id);
		mav.addObject("editMCForm", mc);
		return mav;
	}

	@RequestMapping(value = "/updateMC", method = RequestMethod.POST)
	public String updateMCAction(@ModelAttribute("editMCForm") Mailclient mc,
			BindingResult result, SessionStatus status,
			HttpServletResponse response) {

		PrintWriter out = null;
		try {
			mailclientDao.update(mc);
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//out.write("保存失败");
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		out.write("保存成功,您可以继续更改或者关闭当前页面");

		out.flush();
		return null;
	}

	@RequestMapping(value = "/deleteMC", method = RequestMethod.POST)
	public ModelAndView deleteMCAction(
			@RequestParam(value = "ids", required = true) String ids,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		Map<String, String> mapjson = new HashMap<String, String>();

		int count = mailclientDao.batchDelete(ids);
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

	@RequestMapping("/SelectMCJson")
	public ModelAndView getSelectMCJsonAction(HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		Map<String, Object> mapjson = new HashMap<String, Object>();

		List<Mailclient> list = mailclientDao.getMailclientList();

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd hh:mm:ss");
		gsonBuilder.registerTypeAdapter(Timestamp.class,
				new TimestampTypeAdapter());
		Gson gson = gsonBuilder.create();

		String json = gson.toJson(list);
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

	@RequestMapping("/backMail")
	public ModelAndView getBackMailAction(
			HttpServletResponse response,
			@RequestParam(required = false, defaultValue = "") String pch,
			@RequestParam(required = false, defaultValue = "") String subject,
			@RequestParam(required = false, defaultValue = "") String smail,
			@RequestParam(required = false, defaultValue = "") String rmail,
			@RequestParam(required = false, defaultValue = "") String sendtime,
			@RequestParam(required = false, defaultValue = "") String uniquenum,
			HttpServletRequest request) throws UnsupportedEncodingException {
		response.setContentType("text/html;charset=utf-8");
		Map<String, String> mapjson = new HashMap<String, String>();

		mapjson.put("resultMsg", "ok");

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd hh:mm:ss");
		gsonBuilder.registerTypeAdapter(Timestamp.class,
				new TimestampTypeAdapter());
		Gson gson = gsonBuilder.create();

		String json = gson.toJson(mapjson);

		System.out.println("反馈-------------------------------" + pch);
		Timestamp now = new Timestamp(System.currentTimeMillis());// 获取系统当前时间
		Maillimits ml = new Maillimits();
		ml.setBatchnumber(pch);
		ml.setOpentime(now);
		ml.setIp(TCPIPUtil.getIpAddr(request));
		ml.setSubject(new String(subject.getBytes("ISO-8859-1"), "UTF-8"));
		ml.setRmailId(rmail);
		ml.setSmailId(uniquenum);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		format.setLenient(false);
		// 要转换字符串 str_test
		Timestamp ts = null;
		try {
			ts = new Timestamp(format.parse(sendtime).getTime());
			System.out.println(ts.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ml.setSendtime(ts);
		// ml.setIsFirstOpen(0);

		int result = maillimitsDao.save(ml);

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

	@RequestMapping("/callback")
	public ModelAndView callbackAction() {
		ModelAndView mav = new ModelAndView("common/callback");
		return mav;
	}

	private String getFileKB(long byteFile) {
		if (byteFile == 0)
			return "0KB";
		long kb = 1024;
		return "" + byteFile / kb + "KB";
	}

	

}