package com.wssys.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wssys.bean.Page;
import com.wssys.bean.StudentBean;
import com.wssys.dao.StudentinfosDao;
import com.wssys.entity.Studentinfos;
import com.wssys.framework.MethodLog;
import com.wssys.utils.Commonutil;
import com.wssys.utils.ExcelUtil;
import com.wssys.utils.StringUtil;
import com.wssys.utils.TimestampTypeAdapter;

@Controller
public class StudentinfosController {
	private Logger logger = LoggerFactory
			.getLogger(StudentinfosController.class);

	@Autowired
	private StudentinfosDao studentinfosDao;

	@RequestMapping(value = "/studentinfos", method = RequestMethod.GET)
	public ModelAndView getstudentinfosAction() {
		ModelAndView mav = new ModelAndView("student/importstudent");
		return mav;
	}

	@RequestMapping(value = "/studentinfos", method = RequestMethod.POST)
	@MethodLog(remark = "查看学生信息")
	public ModelAndView studentinfosAction(
			@RequestParam(required = false, defaultValue = "") String name,
			@RequestParam(required = false) Integer age,
			@RequestParam(required = false) Integer sex,
			@RequestParam(required = false, defaultValue = "") String origin,
			@RequestParam(required = false, defaultValue = "") String nation,
			@RequestParam(required = false, defaultValue = "") String syfl,
			@RequestParam(required = false, defaultValue = "") int page,
			@RequestParam(required = false, defaultValue = "") int rows,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		Map<String, Object> mapjson = new HashMap<String, Object>();
		Page pages = new Page();
		pages.setCurpage(page);
		pages.setPagesize(rows);

		Page pagemain = studentinfosDao.queryStudentinfosPagesByname(name, age,
				sex, origin, nation, syfl, pages);
		mapjson.put("total", pagemain.getSumcount());
		mapjson.put("rows", pagemain.getResult());

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

	@RequestMapping(value = "/stuStatistics", method = RequestMethod.GET)
	@MethodLog(remark = "学生信息分类统计页面")
	public ModelAndView stuStatisticsAction() {
		ModelAndView mav = new ModelAndView("student/studentStatistic");
		return mav;
	}

	@RequestMapping(value = "/stuStatistics", method = RequestMethod.POST)
	@MethodLog(remark = "执行学生信息分类统计")
	public ModelAndView stuStatisticsDoAction(HttpServletRequest request,
			@RequestParam(required = false, defaultValue = "") String syfl) {
		ModelAndView mav = new ModelAndView("student/studentStatistic");

		List<StudentBean> listStudentBean = studentinfosDao.findStudentBeanListBean(syfl);
		request.setAttribute("listStudentBean", listStudentBean);
		
		if (!syfl.equals("")) {
			if (syfl.equals("1")) { // 本区学生信息查询(同安区）
				request.setAttribute("title", "本区学生信息查询(同安区）");
			} else if (syfl.equals("2")) { // 本市内非本区学生信息查询
				request.setAttribute("title", "本市内非本区学生信息查询");
			} else if (syfl.equals("3")) { // 省内非本市学生信息查询 福建省除厦门市外其他市区
				request.setAttribute("title", "省内非本市学生信息查询 福建省除厦门市外其他市区");
			} else if (syfl.equals("4")) { // 省外学生信息查询
				request.setAttribute("title", "省外学生信息查询");
			}
		}
		return mav;
	}
	
	@RequestMapping(value = "/studentStaticsAll", method = RequestMethod.GET)
	@MethodLog(remark = "学生信息分类汇总统计页面")
	public ModelAndView studentStaticsAllAction() {
		ModelAndView mav = new ModelAndView("student/studentStatisticAll");
		return mav;
	}
	
	@RequestMapping(value = "/studentStaticsAll", method = RequestMethod.POST)
	@MethodLog(remark = "执行学生信息汇总统计")
	public ModelAndView studentStaticsAllDoAction(HttpServletRequest request,
			@RequestParam(required = false, defaultValue = "") String syfl) {
		ModelAndView mav = new ModelAndView("student/studentStatisticAll");

		List<StudentBean> listStudentBean = studentinfosDao.findStudentBeanListBean("0");
		request.setAttribute("listStudentBeanAll", listStudentBean);	//全年级
		
		List<StudentBean> listStudenttaq = studentinfosDao.findStudentBeanListBean("1");
		request.setAttribute("listStudenttaq", listStudenttaq);	//本区学生信息查询(同安区）
		
		List<StudentBean> listStudentBeanbsfbq = studentinfosDao.findStudentBeanListBean("2");
		request.setAttribute("listStudentBeanbsfbq", listStudentBeanbsfbq);	//本市内非本区学生信息查询
		
		List<StudentBean> listStudentBeansnfbs = studentinfosDao.findStudentBeanListBean("3");
		request.setAttribute("listStudentBeansnfbs", listStudentBeansnfbs);	//省内非本市学生信息查询
		
		List<StudentBean> listStudentBeansw = studentinfosDao.findStudentBeanListBean("4");
		request.setAttribute("listStudentBeansw", listStudentBeansw);	//省外学生信息查询
		
		return mav;
	}

	@RequestMapping(value = "/downloadexceltemplate", method = RequestMethod.POST)
	@MethodLog(remark = "下载学生信息模板")
	public ModelAndView downloadexceltemplateAction(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String storeName = "studentinfos.xls";
		String realName = "学生信息模版.xls";
		String contentType = "application/octet-stream";

		Commonutil
				.download(request, response, storeName, contentType, realName);

		return null;
	}

	@RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	@MethodLog(remark = "导入学生信息")
	public ModelAndView importExcelAction(HttpServletRequest request,
			HttpServletResponse response, @RequestParam MultipartFile[] myfiles)
			throws Exception {

		Map<Integer, JSONObject> content = new LinkedHashMap<Integer, JSONObject>();

		for (MultipartFile myfile : myfiles) {
			if (myfile.isEmpty()) {
				System.out.println("文件未上传");
			} else {
				String filename = myfile.getOriginalFilename();
				System.out.println(filename);
				if (filename.toLowerCase().endsWith("xls")) {
					content = ExcelUtil.readExcelContent(
							myfile.getInputStream(), 2);
				} else if (filename.toLowerCase().endsWith("xlsx")) {
					content = ExcelUtil.read2007Excels(myfile.getInputStream(),
							2);
				}
			}

		}

		for (Map.Entry<Integer, JSONObject> entry : content.entrySet()) {
			Studentinfos s = new Studentinfos();

			if (StringUtil.stringIsNull(entry.getValue().get("name"))
					.equals("")) {
				continue;
			}

			JSONObject obj = entry.getValue();
			s = (Studentinfos) obj.toBean(obj, Studentinfos.class);

			System.out.println(s.getName());
			Integer id = studentinfosDao.save(s);

		}

		PrintWriter out = null;
		out = response.getWriter();
		out.write("导入完成");

		return null;
	}

	@RequestMapping(value = "/deleteStudentsInfos", method = RequestMethod.POST)
	@MethodLog(remark = "删除学生信息")
	public ModelAndView deleteStudentsInfosAction(
			@RequestParam(value = "ids", required = true) String ids,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		Map<String, String> mapjson = new HashMap<String, String>();

		int count = studentinfosDao.batchDelete(ids);
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
