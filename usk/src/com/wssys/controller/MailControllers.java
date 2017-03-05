package com.wssys.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wssys.bean.MaildomainsBean;
import com.wssys.bean.Page;
import com.wssys.dao.DoMainDAO;
import com.wssys.dao.HtmltempDao;
import com.wssys.dao.IndividuationconfigDao;
import com.wssys.dao.MaillimitsDao;
import com.wssys.dao.MailreceiptDao;
import com.wssys.dao.SMailDAO;
import com.wssys.entity.Htmltemp;
import com.wssys.entity.Individuationconfig;
import com.wssys.entity.Mailclient;
import com.wssys.entity.Maildomains;
import com.wssys.entity.Maillimits;
import com.wssys.entity.Mailreceipt;
import com.wssys.entity.Receivingmail;
import com.wssys.entity.Sendmail;
import com.wssys.utils.MailUtil;
import com.wssys.utils.StringUtil;
import com.wssys.utils.TimestampTypeAdapter;

@Controller
public class MailControllers {
	private Logger logger = LoggerFactory.getLogger(ContactsControllers.class);

	@Autowired
	private MailreceiptDao mailreceiptDao;

	@Autowired
	private MaillimitsDao maillimitsDao;

	@Autowired
	private HtmltempDao htmltempDao;

	@Autowired
	private SMailDAO sMailDAO;

	@Autowired
	private DoMainDAO doMainDAO;

	@Autowired
	private IndividuationconfigDao individuationconfigDao;

	@RequestMapping("/maillog")
	public ModelAndView viewMailLog() {
		ModelAndView mav = new ModelAndView("mailsys/listmaillog");
		return mav;
	}

	@RequestMapping("/viewAllMailLogJson")
	public ModelAndView getAllMailLogJsonAction(
			@RequestParam(required = false, defaultValue = "") String rmailaddress,
			@RequestParam(required = false, defaultValue = "") String uniquemailid,
			@RequestParam(required = false, defaultValue = "") String batchnumber,
			@RequestParam(required = false, defaultValue = "") Integer status,
			@RequestParam(required = false, defaultValue = "") int page,
			@RequestParam(required = false, defaultValue = "") int rows,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		Map<String, Object> mapjson = new HashMap<String, Object>();
		Page pages = new Page();
		pages.setCurpage(page);
		pages.setPagesize(rows);

		mapjson.put(
				"total",
				mailreceiptDao.queryUserPagesByrmail(rmailaddress,
						uniquemailid, batchnumber, status, pages).getSumcount());
		mapjson.put(
				"rows",
				mailreceiptDao.queryUserPagesByrmail(rmailaddress,
						uniquemailid, batchnumber, status, pages).getResult());
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

	@RequestMapping("/viewStatistics")
	public ModelAndView viewStatisticsAction() {
		ModelAndView mav = new ModelAndView("mailsys/listmailStatistics");
		return mav;
	}

	@RequestMapping("/viewStatisticsJson")
	public ModelAndView getViewStatisticsAction(
			@RequestParam(required = false, defaultValue = "") String batchnumber,
			@RequestParam(required = false, defaultValue = "") int page,
			@RequestParam(required = false, defaultValue = "") int rows,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		Map<String, Object> mapjson = new HashMap<String, Object>();
		Page pages = new Page();
		pages.setCurpage(page);
		pages.setPagesize(rows);

		mapjson.put("total",
				mailreceiptDao.queryUserPagesBybatchnumber(batchnumber, pages)
						.getSumcount());

		List<Mailreceipt> listeasyuiformate = new ArrayList<Mailreceipt>();
		List list = mailreceiptDao.queryUserPagesBybatchnumber(batchnumber,
				pages).getResult();

		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Mailreceipt mc = new Mailreceipt();
				Object[] o = (Object[]) list.get(i);
				mc.setBatchnumber(StringUtil.stringIsNull(o[0]));
				listeasyuiformate.add(mc);

			}
		}

		mapjson.put("rows", listeasyuiformate);
		// mapjson.put("rows",
		// mailreceiptDao.queryUserPagesBybatchnumber(batchnumber, pages)
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

	@RequestMapping("/mailcallbacklist")
	public ModelAndView mailcallbacklistAction() {
		ModelAndView mav = new ModelAndView("mailsys/listmailcallback");
		return mav;
	}

	@RequestMapping("/viewAllMailcallbackJson")
	public ModelAndView getMailcallbackJsonAction(
			@RequestParam(required = false, defaultValue = "") String batchnumber,
			@RequestParam(required = false, defaultValue = "") String smailId,
			@RequestParam(required = false, defaultValue = "") int page,
			@RequestParam(required = false, defaultValue = "") int rows,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		Map<String, Object> mapjson = new HashMap<String, Object>();
		Page pages = new Page();
		pages.setCurpage(page);
		pages.setPagesize(rows);

		mapjson.put(
				"total",
				maillimitsDao.queryUserPagesBybatchnumber(batchnumber, smailId,
						pages).getSumcount());

		mapjson.put(
				"rows",
				maillimitsDao.queryUserPagesBybatchnumber(batchnumber, smailId,
						pages).getResult());
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

	@RequestMapping("/tj")
	public ModelAndView tjAction(
			HttpServletRequest request,
			@RequestParam(required = false, defaultValue = "") String batchnumber) {
		ModelAndView mav = new ModelAndView("mailsys/staData");
		request.setAttribute("batchnumber", batchnumber);

		// 首次打开数
		int fc = maillimitsDao.firsopencount(batchnumber);
		request.setAttribute("fc", fc);
		// 该批次一共发了几封
		int sumsend = mailreceiptDao.sumsendcount(batchnumber);

		// 首次打开率
		// DecimalFormat df1 = new DecimalFormat("##.00%");
		// BigDecimal bd = new BigDecimal(fc);
		// BigDecimal bd1 = new BigDecimal(sumsend);

		// System.out.println(bd.divide(bd1,10, BigDecimal.ROUND_HALF_DOWN));
		// ROUND_HALF_UP 四舍五入
		// String firstRate = bd.divide(bd1, 3, BigDecimal.ROUND_HALF_UP)
		// .multiply(new BigDecimal(100)) + "%";

		// String firstRate=df1.format(a/b);
		request.setAttribute("firstRate", MailUtil.percent(fc, sumsend));

		// 总打开数
		int sumopen = maillimitsDao.sumopencount(batchnumber);
		request.setAttribute("sumopen", sumopen);
		// 总打开率
		// BigDecimal sumopenb = new BigDecimal(sumopen);
		// BigDecimal sumsendb = new BigDecimal(sumsend);
		// String sumRate = sumopenb.divide(sumsendb, 3,
		// BigDecimal.ROUND_HALF_UP)
		// .multiply(new BigDecimal(100)) + "%";
		request.setAttribute("sumRate", MailUtil.percent(sumopen, sumsend));

		return mav;
	}

	@RequestMapping("/viewtjJson")
	public ModelAndView getMailviewtjJsonAction(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		Map<String, Object> mapjson = new HashMap<String, Object>();

		String bn = StringUtil
				.stringIsNull(request.getParameter("batchnumber"));

		int total = maillimitsDao.queryListCountbybatchnumber(bn);
		mapjson.put("total", total);
		List<Maillimits> lm = maillimitsDao.queryList(bn);
		mapjson.put("rows", lm);

		// List<StaticBean> listsbs=new ArrayList<StaticBean>();
		// StaticBean sbs=new StaticBean();
		// sbs.setId(2);
		// sbs.setRmailId("2%");
		// sbs.setOpentime(5);
		// sbs.setIp("8%");
		//
		// listsbs.add(sbs);
		//
		// mapjson.put("footer",listsbs);

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

	// 模版导入
	@RequestMapping(value = "/importHtmlStencil", method = RequestMethod.POST)
	public String importHtmlStencilAction(
			@ModelAttribute("newHtmltemp") Htmltemp ht,
			HttpServletResponse response,
			@RequestParam(value = "mbname", required = false) MultipartFile file,
			HttpServletRequest request) throws Exception {

		response.setContentType("text/html;charset=utf-8");
		if (!StringUtil.stringIsNull(ht.getTempname()).equals("")) {
			if (htmltempDao.checktempname(ht.getTempname()) > 0) {
				PrintWriter out = null;
				try {
					out = response.getWriter();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				out.write("模版名称已经存在请重命名");

				out.flush();
				return null;
			}
		}

		String savefilepath = "";
		if (null != file && !file.isEmpty()) {
			String path = request.getRealPath("/upload/stencil");

			try {
				FileCopyUtils.copy(file.getBytes(),
						new File(path + "/" + file.getOriginalFilename()));

				savefilepath = path + "/" + file.getOriginalFilename();

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				// e1.printStackTrace();
				PrintWriter out = null;

				out = response.getWriter();

				out.write("文件上传失败");
				out.flush();
			}
		} else {
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.write("文件读取异常");
			out.flush();
			return null;
		}

		String html = readFileByLines(savefilepath);

		// 保存模版
		if (ht.getStatus() == 1) {
			if (StringUtil.stringIsNull(ht.getTempname()).equals("")) {
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd-HH-mm-ss");// 设置日期格式
				ht.setTempname("未命名" + df.format(new Date()));
			}
			ht.setContent(html);
			htmltempDao.save(ht);
		}

		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.write(html);
		// out.write("<h1>你好<h1>");
		out.flush();
		return null;

	}

	@RequestMapping(value = "/seeTemp", method = RequestMethod.GET)
	public ModelAndView seeTempAction(@RequestParam("id") Integer id) {
		ModelAndView mav = new ModelAndView("mailsys/viewTemp");
		Htmltemp ht = htmltempDao.getById(id);
		mav.addObject("ht", ht);
		return mav;
	}

	@RequestMapping("/viewTemp")
	public ModelAndView getviewTempAction() {
		logger.info("excute search...");
		ModelAndView mav = new ModelAndView("mailsys/listTemp"); //

		return mav;
	}

	@RequestMapping("/viewAllTempJson")
	public ModelAndView getAllTempJsonAction(
			@RequestParam(required = false, defaultValue = "") String tempname,
			@RequestParam(required = false, defaultValue = "") int page,
			@RequestParam(required = false, defaultValue = "") int rows,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		Map<String, Object> mapjson = new HashMap<String, Object>();
		Page pages = new Page();
		pages.setCurpage(page);
		pages.setPagesize(rows);

		mapjson.put("total",
				htmltempDao.queryHtmltempPagesBytempname(tempname, pages)
						.getSumcount());
		mapjson.put("rows",
				htmltempDao.queryHtmltempPagesBytempname(tempname, pages)
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

	@RequestMapping(value = "/selectTemp", method = RequestMethod.POST)
	public ModelAndView selectTempAction(@RequestParam("id") Integer id,
			HttpServletResponse response) {
		// ModelAndView mav = new ModelAndView("mailsys/viewTemp");
		response.setContentType("text/html;charset=utf-8");
		Htmltemp ht = htmltempDao.getById(id);
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd hh:mm:ss");
		gsonBuilder.registerTypeAdapter(Timestamp.class,
				new TimestampTypeAdapter());
		Gson gson = gsonBuilder.create();

		// String json = gson.toJson(mapjson);
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.write(ht.getContent());
		out.flush();
		return null;
	}

	@RequestMapping(value = "/deleteTemp", method = RequestMethod.POST)
	public ModelAndView deleteTempAction(
			@RequestParam(value = "ids", required = true) String ids,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		Map<String, String> mapjson = new HashMap<String, String>();

		int count = htmltempDao.batchDelete(ids);
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

	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 * 
	 * @throws IOException
	 */
	public static String readFileByLines(String fileName) throws IOException {
		StringBuffer shtml = new StringBuffer(); // 多线程操作字符串缓冲区 下操作大量数据
		List<String> lines = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(fileName), "UTF-8"));
		String line = null;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
			shtml.append(line);
			lines.add(line);
		}
		br.close();
		// File file = new File(fileName);
		// BufferedReader reader = null;
		// StringBuffer shtml=new StringBuffer(); //多线程操作字符串缓冲区 下操作大量数据
		//
		// try {
		// // System.out.println("以行为单位读取文件内容，一次读一整行：");
		// reader = new BufferedReader(new FileReader(file));
		// String tempString = null;
		// int line = 1;
		// // 一次读入一行，直到读入null为文件结束
		// while ((tempString = reader.readLine()) != null) {
		// // 显示行号
		// // System.out.println("line " + line + ": " + tempString);
		// System.out.println( tempString);
		// shtml.append(tempString);
		// line++;
		// }
		// reader.close();
		// } catch (IOException e) {
		// e.printStackTrace();
		// } finally {
		// if (reader != null) {
		// try {
		// reader.close();
		// } catch (IOException e1) {
		// }
		// }
		// }
		return shtml.toString();
	}

	@RequestMapping(value = "/txtSendInputSave", method = RequestMethod.POST)
	public String txtSendInputSaveAction(
			@ModelAttribute("newSendmailInput") Sendmail formrm,
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

						List<Sendmail> listrmail = new ArrayList<Sendmail>();

						while ((lineTxt = bufferedReader.readLine()) != null) {
							Sendmail sm = new Sendmail();
							sm.setMailAddress(lineTxt);
							Timestamp now = new Timestamp(
									System.currentTimeMillis());// 获取系统当前时间
							sm.setCreatetime(now);
							sm.setMailRemark("txt导入");
							sm.setMaiSmtpAddress(formrm.getMaiSmtpAddress());
							sm.setMailPwd(formrm.getMailPwd());
							sm.setMailPort(formrm.getMailPort());
							listrmail.add(sm);
							// System.out.println(lineTxt);
						}

						for (int i = 0; i < listrmail.size(); i++) {
							if (sMailDAO.checkMailAddress(StringUtil
									.stringIsNull(listrmail.get(i)
											.getMailAddress())) > 0) { // 有重复就跳到下一条
								continue;
							} else {
								int sid = sMailDAO.save(listrmail.get(i));

								// 自动导入邮件域
								Maildomains mdqq = new Maildomains();
								mdqq.setDomainDaylimit(20);
								mdqq.setDomainDayuse(0);
								mdqq.setDomainMinutelimit(200);
								mdqq.setDomainName("qq");
								mdqq.setRemark("txt导入");
								mdqq.setSid(sid);
								doMainDAO.save(mdqq);

								Maildomains md163 = new Maildomains();
								md163.setDomainDaylimit(20);
								md163.setDomainDayuse(0);
								md163.setDomainMinutelimit(200);
								md163.setDomainName("163");
								md163.setRemark("txt导入");
								md163.setSid(sid);
								doMainDAO.save(md163);

								Maildomains md126 = new Maildomains();
								md126.setDomainDaylimit(20);
								md126.setDomainDayuse(0);
								md126.setDomainMinutelimit(200);
								md126.setDomainName("126");
								md126.setRemark("txt导入");
								md126.setSid(sid);
								doMainDAO.save(md126);

								Maildomains mdyahoo = new Maildomains();
								mdyahoo.setDomainDaylimit(20);
								mdyahoo.setDomainDayuse(0);
								mdyahoo.setDomainMinutelimit(200);
								mdyahoo.setDomainName("yahoo");
								mdyahoo.setRemark("txt导入");
								mdyahoo.setSid(sid);
								doMainDAO.save(mdyahoo);

								Maildomains mdhotmail = new Maildomains();
								mdhotmail.setDomainDaylimit(20);
								mdhotmail.setDomainDayuse(0);
								mdhotmail.setDomainMinutelimit(200);
								mdhotmail.setDomainName("hotmail");
								mdhotmail.setRemark("txt导入");
								mdhotmail.setSid(sid);
								doMainDAO.save(mdhotmail);

								Maildomains mdgmail = new Maildomains();
								mdgmail.setDomainDaylimit(20);
								mdgmail.setDomainDayuse(0);
								mdgmail.setDomainMinutelimit(200);
								mdgmail.setDomainName("gmail");
								mdgmail.setRemark("txt导入");
								mdgmail.setSid(sid);
								doMainDAO.save(mdgmail);

								Maildomains mdsohu = new Maildomains();
								mdsohu.setDomainDaylimit(20);
								mdsohu.setDomainDayuse(0);
								mdsohu.setDomainMinutelimit(200);
								mdsohu.setDomainName("sohu");
								mdsohu.setRemark("txt导入");
								mdsohu.setSid(sid);
								doMainDAO.save(mdsohu);

								Maildomains mdsina = new Maildomains();
								mdsina.setDomainDaylimit(20);
								mdsina.setDomainDayuse(0);
								mdsina.setDomainMinutelimit(200);
								mdsina.setDomainName("sina");
								mdsina.setRemark("txt导入");
								mdsina.setSid(sid);
								doMainDAO.save(mdsina);
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

	@RequestMapping(value = "/updateDoMainDayUseAllReset", method = RequestMethod.POST)
	public String updateDoMainDayUseAllResetAction(

	HttpServletResponse response) {

		// status.setComplete();
		PrintWriter out = null;
		try {
			doMainDAO.updateDomain_dayuseAll();
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block

			//out.write("重置失败");
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		out.write("重置成功");

		out.flush();
		return null;
	}

	@RequestMapping("/maillogview")
	public ModelAndView maillogviewAction(HttpServletRequest request,
			@RequestParam(required = false, defaultValue = "") Integer id) {
		ModelAndView mav = new ModelAndView("mailsys/mailsysView");
		Mailreceipt msyslog = mailreceiptDao.getById(id);
		request.setAttribute("mslog", msyslog);
		return mav;
	}

	@RequestMapping(value = "/saveIndividuationconfig", method = RequestMethod.POST)
	public String createIndividuationconfig(
			@ModelAttribute("newIndividuationconfig") Individuationconfig ic,
			BindingResult result, SessionStatus status,
			HttpServletResponse response) {
		if (individuationconfigDao.checkIcName(ic.getConfigkey()) > 0) {

			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			out.write("保存失败,该服务已经有 该配置属性已存在");

			out.flush();
			return null;
		}

		int saverec = 0;
		saverec = individuationconfigDao.save(ic);

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

	@RequestMapping("/addIc")
	public ModelAndView addIcAction() {
		ModelAndView mav = new ModelAndView("mailconfig/addconfig");
		Individuationconfig ic = new Individuationconfig();
		mav.getModelMap().put("newIndividuationconfig", ic);
		return mav;
	}

	@RequestMapping("/viewAllIc")
	public ModelAndView getAllIcAction() {
		logger.info("excute viewAllIc...");
		ModelAndView mav = new ModelAndView("mailconfig/listconfig");

		return mav;
	}

	@RequestMapping("/viewAllIndividuationconfigJson")
	public ModelAndView getAllIndividuationconfigJson(
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

		mapjson.put("total", individuationconfigDao.queryUserPages(pages)
				.getSumcount());

		mapjson.put("rows", individuationconfigDao.queryUserPages(pages)
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
	
	@RequestMapping(value = "/updateIc", method = RequestMethod.POST)
	public String updateIcAction(
			@ModelAttribute("editIc") Individuationconfig ic,
			BindingResult result, SessionStatus status,
			HttpServletResponse response) {

		// status.setComplete();
		PrintWriter out = null;
		try {
			individuationconfigDao.update(ic);
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
	
	@RequestMapping(value = "/eidtIc", method = RequestMethod.GET)
	public ModelAndView eidtIcAction(@RequestParam("id") Integer id) {
		ModelAndView mav = new ModelAndView("mailconfig/editconfig");
		Individuationconfig ic = individuationconfigDao.getById(id);
		mav.addObject("editIc", ic);
		return mav;
	}
	
	@RequestMapping(value = "/deleteIc", method = RequestMethod.POST)
	public ModelAndView deleteIcAction(
			@RequestParam(value = "ids", required = true) String ids,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		Map<String, String> mapjson = new HashMap<String, String>();

		int count = individuationconfigDao.batchDelete(ids);
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

}
