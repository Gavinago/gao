package com.gov.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

/**
 * Servlet implementation class ZxingServlet
 */
//@WebServlet("/assets/img/zxing.png")
public class ZxingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ZxingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");  
        //设置返回的文件编码  
        response.setContentType("image/jpeg"); 
		
		//获得当前 app root 完整 url
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ path + "/";
		Map hints = new HashMap();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		
		MultiFormatWriter formatWriter = new MultiFormatWriter();
		// 按照指定的宽度，高度和附加参数对字符串进行编码
		BitMatrix matrix;
		try {
			matrix = formatWriter.encode(basePath,
					BarcodeFormat.QR_CODE, 120, 120, hints);
			//File imageFile = new File(path);
			// 直接返回字节流
			//MatrixToImageWriter.writeToFile(bitMatrix, "png", imageFile);
			ServletOutputStream out=response.getOutputStream();  
			MatrixToImageWriter.writeToStream(matrix, "jpeg", out);
			out.flush();
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
