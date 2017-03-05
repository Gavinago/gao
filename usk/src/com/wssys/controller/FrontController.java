package com.wssys.controller;

import java.io.BufferedWriter;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.wssys.bean.OrdersBean;
import com.wssys.bean.Page;
import com.wssys.dao.ExpenseManagementDao;
import com.wssys.dao.PusSysUserDao;
import com.wssys.dao.SeqsDao;
import com.wssys.entity.PusFrontUser;
import com.wssys.entity.PusSysUser;
import com.wssys.framework.MethodLog;
import com.wssys.utils.TimestampTypeAdapter;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 会员管理
 * 
 * @author q
 * 
 */
@Controller
public class FrontController {
	private static String separator = File.separator;	//灵活显示 linux和windows下的分隔符
	private Logger logger = LoggerFactory.getLogger(FrontController.class);



	@Autowired
	private ExpenseManagementDao expenseManagementDao;


	@Autowired
	private PusSysUserDao pusSysUserDao;

	@Autowired
	private SeqsDao seqsDao;

	@RequestMapping("/frontuser")
	public ModelAndView usersysAction() {
		ModelAndView mav = new ModelAndView("sys/front/listFrontUser");
		return mav;
	}


	@RequestMapping("/addFrontUser")
	public ModelAndView addUserAction() {
		ModelAndView mav = new ModelAndView("sys/front/addFrontUser");
		PusFrontUser u = new PusFrontUser();
		mav.getModelMap().put("pfu", u);
		return mav;
	}

	

	

	

	@RequestMapping(value = "/orderedInfos", method = RequestMethod.GET)
	public ModelAndView viewOrderedInfosAction() {
		ModelAndView mav = new ModelAndView("sys/goodsSys/listOrders");
		return mav;
	}


	@RequestMapping(value = "/purchasingm", method = RequestMethod.GET)
	public ModelAndView viewPurchasingmAction() {
		ModelAndView mav = new ModelAndView("sys/goodsSys/listPurchasing");
		return mav;
	}

	

	

	@RequestMapping(value = "/lxrsysmsg", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public String lxrsysmsgAction() {

		return "供应商联系人为空，信息不完整该订单非法";
	}

	


	// HttpServletRequest request,
	public String CreatePDFReturnPmPdfpath(String basePathpdf, Map root) {

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
			temp = cfg.getTemplate("pm.ftl");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String file1 = basePathpdf + "sys/html/pm" + root.get("htbh")
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
		String outputFile = basePathpdf + "sys/pdf/pm" + root.get("htbh")
				+ ".pdf";
		String pdfPath = "sys/pdf/pm" + root.get("htbh") + ".pdf";
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
				// // //微软雅黑字体文件
				// fontResolver.addFont(basePathpdf + "ui\\fonts\\msyh.ttf",
				// BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
				// // 微软雅黑加粗后额字体文件
				// fontResolver.addFont(basePathpdf + "ui\\fonts\\msyhbd.ttf",
				// BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
				// java.awt.Font strfont=new java.awt.Font("宋体",
				// com.lowagie.text.Font.NORMAL,14);

				fontResolver.addFont(basePathpdf + "ui/fonts/simsun.ttc",
						BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

				// fontResolver.addFont("C:/Windows/Fonts/ahronbd.ttf",
				// BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
				// fontResolver.addFont("C:/Windows/Fonts/arialuni.ttf",
				// BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
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
		try {
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pdfPath;
	}

	


	


	

	

	

	@RequestMapping(value = "/bolsucaction", method = RequestMethod.GET)
	public ModelAndView bolsucactionAction() {
		ModelAndView mav = new ModelAndView("sys/goodsSys/bolsuc");

		return mav;
	}

	@RequestMapping(value = "/deliversuc", method = RequestMethod.GET)
	public ModelAndView deliversucAction() {
		ModelAndView mav = new ModelAndView("sys/goodsSys/deliversuc");

		return mav;
	}

	public String CreateBolPDF(String basePathpdf, Map root) {

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
			temp = cfg.getTemplate("bol.ftl");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String file1 = basePathpdf + "sys\\html\\bol" + root.get("bolnum")
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
		String outputFile = basePathpdf + "sys\\pdf\\bol" + root.get("bolnum")
				+ ".pdf";
		String pdfPath = "sys/pdf/bol" + root.get("bolnum") + ".pdf";
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
				fontResolver.addFont(basePathpdf + "ui\\fonts\\simsun.ttc",
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
		try {
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pdfPath;
	}

	/**
	 * 创建提单 pdf
	 * @param basePathpdf
	 * @param root
	 * @return
	 */
	public String CreateTdPDF(String basePathpdf, Map root) {

		/* 将生成的内容写入hello .html中 */
		/* 创建配置 */
		Configuration cfg = new Configuration();
		/* 指定模板存放的路径 */
		try {
			cfg.setDirectoryForTemplateLoading(new File(basePathpdf
					+ separator+"sys"+separator+"ftl"));
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
			temp = cfg.getTemplate("td.ftl");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String file1 = basePathpdf + "sys"+separator+"html"+separator+"td" + root.get("ownum")
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
		String outputFile = basePathpdf + "sys"+separator+"pdf"+separator+"td" + root.get("ownum")
				+ ".pdf";
		String pdfPath = "sys/pdf/td" + root.get("ownum") + ".pdf";	//由于mysql不支持 "\"这种字符 所以 只能这里写死   用于页面上的跳转路径是不需要区分操作系统的
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
				fontResolver.addFont(basePathpdf + "ui"+separator+"fonts"+separator+"simsun.ttc",
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
		logger.info("转换出库单pdf成功！");
		try {
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pdfPath;
	}

	public String CreateDeliverPDF(String basePathpdf, Map root) {

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
			temp = cfg.getTemplate("deliver.ftl");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String file1 = basePathpdf + "sys/html/deliver" + root.get("cknum")
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
		String outputFile = basePathpdf + "sys/pdf/deliver"
				+ root.get("cknum") + ".pdf";
		String pdfPath = "sys/pdf/deliver" + root.get("cknum") + ".pdf";
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
		logger.info("转换出库单pdf成功！");
		try {
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pdfPath;
	}

	

	

	// 打印出库单列表
	@RequestMapping(value = "/printDelivery", method = RequestMethod.GET)
	public ModelAndView printDeliveryAction() {
		ModelAndView mav = new ModelAndView("sys/goodsSys/listDelivery");

		return mav;
	}

	
	// 已经出库单列表
	@RequestMapping(value = "/hascklist", method = RequestMethod.GET)
	public ModelAndView hascklistAction() {
		ModelAndView mav = new ModelAndView("sys/goodsSys/listHasck");
		return mav;
	}



	@RequestMapping(value = "/tdlist", method = RequestMethod.GET)
	public ModelAndView tdlistAction() {
		ModelAndView mav = new ModelAndView("sys/goodsSys/listTd");
		return mav;
	}



	@RequestMapping(value = "/financeManager", method = RequestMethod.GET)
	public ModelAndView viewfinanceManagerAction() {
		ModelAndView mav = new ModelAndView("sys/goodsSys/listFinance");
		return mav;
	}

	
}
