package com.wssys.utils;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

public class Commonutil {
	/**
	 * 根据名字获取cookie
	 * 
	 * @param request
	 * @param name
	 *            cookie名字
	 * @return
	 */
	public static Cookie getCookieByName(HttpServletRequest request, String name) {
		Map<String, Cookie> cookieMap = ReadCookieMap(request);
		if (cookieMap.containsKey(name)) {
			Cookie cookie = (Cookie) cookieMap.get(name);
			return cookie;
		} else {
			return null;
		}
	}

	/**
	 * 将cookie封装到Map里面
	 * 
	 * @param request
	 * @return
	 */
	private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}

	/**
	 * 设置cookie
	 * 
	 * @param response
	 * @param name
	 *            cookie名字
	 * @param value
	 *            cookie值
	 * @param maxAge
	 *            cookie生命周期 以秒为单位
	 */
	public static void addCookie(HttpServletResponse response, String name,
			String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		if (maxAge > 0)
			cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

	// /**
	// * 获取本机所有物理地址
	// *
	// * @return
	// */
	// private static String[] getAllLocalMac() {
	// List<String> res = new ArrayList<String>();
	// Enumeration<NetworkInterface> netInterfaces;
	// try {
	// netInterfaces = NetworkInterface.getNetworkInterfaces();
	// while (netInterfaces.hasMoreElements()) {
	// NetworkInterface ni = (NetworkInterface) netInterfaces
	// .nextElement();
	// byte[] mac = ni.getHardwareAddress();
	// StringBuilder sb = new StringBuilder();
	// if (mac != null) {
	// for (byte b : mac) {
	// sb.append(toHex(b));
	// sb.append("-");
	// }
	// if (sb.length() > 1)
	// sb.deleteCharAt(sb.length() - 1);
	// res.add(sb.toString());
	//
	// }
	// }
	// } catch (SocketException e) {
	// e.printStackTrace();
	// }
	// return (String[]) res.toArray(new String[0]);
	// }
	//
	// public static StringBuffer toHex(byte b) {
	// byte factor = 16;
	// int v = b & 0xff;// 去掉byte转换之后的负数部分。
	// byte high = (byte) (v / factor);
	// byte low = (byte) (v % factor);
	// StringBuffer buf = new StringBuffer();
	// buf.append(toHexLow(high)).append(toHexLow(low));
	// return buf;
	// }
	//
	// private static char toHexLow(byte b) {
	// if (b > 16 || b < 0) {
	// throw new IllegalArgumentException(
	// "inpt parameter should less than 16 and greater than 0");
	// }
	// if (b < 10) {
	// return (char) ('0' + (char) b);
	// } else {
	// return (char) ('A' + (b - 10));
	// }
	// }

	public static String getLocalMac() throws SocketException {

		// TODO Auto-generated method stub
		InetAddress ia = null;
		try {
			ia = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 获取网卡，获取地址

		byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();

		// System.out.println("mac数组长度："+mac.length);
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < mac.length; i++) {
			if (i != 0) {
				sb.append("-");
			}
			// 字节转换为整数
			int temp = mac[i] & 0xff;
			String str = Integer.toHexString(temp);
			// System.out.println("每8位:"+str);
			if (str.length() == 1) {
				sb.append("0" + str);
			} else {
				sb.append(str);
			}
		}
		return sb.toString().toUpperCase();
		// System.out.println("本机MAC地址:"+sb.toString().toUpperCase());
	}

	/**
	 * 写入properties信息
	 * 
	 * @param filePath
	 *            绝对路径（包括文件名和后缀名）
	 * @param parameterName
	 *            名称
	 * @param parameterValue
	 *            值
	 */
	public static void writeProperties(String filePath, String parameterName,
			String parameterValue) {
		Properties props = new Properties();
		try {

			// 如果文件不存在，创建一个新的
			File file = new File(filePath);
			if (!file.exists()) {

				file.createNewFile();
			}

			InputStream fis = new FileInputStream(filePath);
			// 从输入流中读取属性列表（键和元素对）
			props.load(fis);
			fis.close();
			OutputStream fos = new FileOutputStream(filePath);
			props.setProperty(parameterName, parameterValue);
			// 以适合使用 load 方法加载到 Properties 表中的格式，
			// 将此 Properties 表中的属性列表（键和元素对）写入输出流
			props.store(fos, parameterName);
			fos.close(); // 关闭流
		} catch (IOException e) {
			System.err.println("Visit " + filePath + " for updating "
					+ parameterName + " value error");

		}
	}

	/**
	 * filename: 相对路径+文件名（不要后缀）
	 */
	public synchronized static String getPropertyFromFile(String filename,
			String key) {
		ResourceBundle rb = ResourceBundle.getBundle(filename);
		return rb.getString(key).trim();
	}

	/**
	 * 
	 * @Title: readValue
	 * @Description: TODO 通过绝对路径获取properties文件属性， 根据key读取value
	 * @param filePath
	 *            properties文件绝对路径（包括文件名和后缀）
	 * @param key
	 *            属性key
	 * @return String 返回value
	 */
	public static String readValue(String filePath, String key) {
		Properties props = new Properties();
		InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(filePath));
			props.load(in);
			String value = props.getProperty(key);
			return value;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				in.close();// -----------------------------------important
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 本类主要是对config。properties的属性进行修改
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 写文件
		String weightadd = "12.2";
		// 更改src的config包下的config.properties文件中的“weightadd”属性的值
		writeProperties("config/sysconfig.properties", "weightadd", weightadd); // config.properties一定要写完整

		// 从文件中取出userPassword，
		String decStr = getPropertyFromFile("config/config", "weightadd");
		System.out.println("============" + decStr);
	}

	/**
	 * 由于Java的简单类型不能够精确的对浮点数进行运算，这个工具类提供精 确的浮点数运算，包括加减乘除和四舍五入。
	 */

	private static final int DEF_DIV_SCALE = 10; // 这个类不能实例化

	/**
	 * 提供精确的加法运算。
	 * 
	 * @param v1
	 *            被加数
	 * @param v2
	 *            加数
	 * @return 两个参数的和
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	/**
	 * 提供精确的减法运算。
	 * 
	 * @param v1
	 *            被减数
	 * @param v2
	 *            减数
	 * @return 两个参数的差
	 */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 提供精确的乘法运算。
	 * 
	 * @param v1
	 *            被乘数
	 * @param v2
	 *            乘数
	 * @return 两个参数的积
	 */
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	// public static void main(String[] args) {
	//
	// try {
	// System.out.println(getLocalMac());
	// } catch (SocketException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }
	
	/**
	 * 图片流压缩
	 * @param data
	 * @return
	 */
	public static byte[] CompressionImage(byte[] data) {
		ByteArrayInputStream is = new ByteArrayInputStream(data);

		BufferedImage src = null;
		ByteArrayOutputStream out = null;
		ImageWriter imgWrier;
		ImageWriteParam imgWriteParams;

		// 指定写图片的方式为 jpg
		imgWrier = ImageIO.getImageWritersByFormatName("jpg").next();
		imgWriteParams = new javax.imageio.plugins.jpeg.JPEGImageWriteParam(
				null);
		// 要使用压缩，必须指定压缩方式为MODE_EXPLICIT
		imgWriteParams.setCompressionMode(imgWriteParams.MODE_EXPLICIT);
		// 这里指定压缩的程度，参数qality是取值0~1范围内，
		imgWriteParams.setCompressionQuality((float) 0.1);	// data.length

		imgWriteParams.setProgressiveMode(imgWriteParams.MODE_DISABLED);
		ColorModel colorModel = ColorModel.getRGBdefault();
		// 指定压缩时使用的色彩模式
		imgWriteParams.setDestinationType(new javax.imageio.ImageTypeSpecifier(
				colorModel, colorModel.createCompatibleSampleModel(16, 16)));
		//colorModel.createCompatibleSampleModel(16, 16)

		try {
			src = ImageIO.read(is);
			out = new ByteArrayOutputStream(data.length);

			imgWrier.reset();
			// 必须先指定 out值，才能调用write方法, ImageOutputStream可以通过任何 OutputStream构造
			imgWrier.setOutput(ImageIO.createImageOutputStream(out));
			// 调用write方法，就可以向输入流写图片
			imgWrier.write(null, new IIOImage(src, null, null), imgWriteParams);

			out.flush();
			out.close();
			is.close();
			data = out.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	/**
	 * 图片压缩
	 * @param data
	 * @return
	 */
	public static byte[] compressPic(byte[] data) {
		ByteArrayInputStream is = new ByteArrayInputStream(data);
		//File file = null;
		BufferedImage src = null;
		//FileOutputStream out = null;
		ByteArrayOutputStream out = null;
		ImageWriter imgWrier;
		ImageWriteParam imgWriteParams;

		// 指定写图片的方式为 jpg
		imgWrier = ImageIO.getImageWritersByFormatName("jpg").next();
		imgWriteParams = new javax.imageio.plugins.jpeg.JPEGImageWriteParam(
				null);
		// 要使用压缩，必须指定压缩方式为MODE_EXPLICIT
		imgWriteParams.setCompressionMode(imgWriteParams.MODE_EXPLICIT);
		// 这里指定压缩的程度，参数qality是取值0~1范围内，
		imgWriteParams.setCompressionQuality((float) 0.1);
		imgWriteParams.setProgressiveMode(imgWriteParams.MODE_DISABLED);
		ColorModel colorModel = ColorModel.getRGBdefault();
		// 指定压缩时使用的色彩模式
		imgWriteParams.setDestinationType(new javax.imageio.ImageTypeSpecifier(
				colorModel, colorModel.createCompatibleSampleModel(16, 16)));

		try {
			src = ImageIO.read(is);
				//file = new File(srcFilePath);
				//src = ImageIO.read(file);
				//out = new FileOutputStream(descFilePath);
			out = new ByteArrayOutputStream(data.length);
				imgWrier.reset();
				// 必须先指定 out值，才能调用write方法, ImageOutputStream可以通过任何
				// OutputStream构造
				imgWrier.setOutput(ImageIO.createImageOutputStream(out));
				// 调用write方法，就可以向输入流写图片
				imgWrier.write(null, new IIOImage(src, null, null),
						imgWriteParams);
				out.flush();
				out.close();
				data = out.toByteArray();
		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
		}
		return data;
	}
	
	/**
	 * 压缩物理路径图片
	 * @param srcFilePath
	 * @param descFilePath
	 * @return
	 */
	public static boolean compressPic(String srcFilePath, String descFilePath) {
		File file = null;
		BufferedImage src = null;
		FileOutputStream out = null;
		ImageWriter imgWrier;
		ImageWriteParam imgWriteParams;

		// 指定写图片的方式为 jpg
		imgWrier = ImageIO.getImageWritersByFormatName("jpg").next();
		imgWriteParams = new javax.imageio.plugins.jpeg.JPEGImageWriteParam(
				null);
		// 要使用压缩，必须指定压缩方式为MODE_EXPLICIT
		imgWriteParams.setCompressionMode(imgWriteParams.MODE_EXPLICIT);
		// 这里指定压缩的程度，参数qality是取值0~1范围内，
		imgWriteParams.setCompressionQuality((float) 0.1);
		imgWriteParams.setProgressiveMode(imgWriteParams.MODE_DISABLED);
		ColorModel colorModel = ColorModel.getRGBdefault();
		// 指定压缩时使用的色彩模式
		imgWriteParams.setDestinationType(new javax.imageio.ImageTypeSpecifier(
				colorModel, colorModel.createCompatibleSampleModel(16, 16)));

		try {
			if (StringUtils.isBlank(srcFilePath)) {
				return false;
			} else {
				file = new File(srcFilePath);
				src = ImageIO.read(file);
				out = new FileOutputStream(descFilePath);

				imgWrier.reset();
				// 必须先指定 out值，才能调用write方法, ImageOutputStream可以通过任何
				// OutputStream构造
				imgWrier.setOutput(ImageIO.createImageOutputStream(out));
				// 调用write方法，就可以向输入流写图片
				imgWrier.write(null, new IIOImage(src, null, null),
						imgWriteParams);
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/** 
     * 下载 
     *  
     * @author  
     * @date 
     * @param request 
     * @param response 
     * @param storeName 
     * @param contentType 
     * @param realName 
     * @throws Exception 
     */  
    public static void download(HttpServletRequest request,  
            HttpServletResponse response, String storeName, String contentType,  
            String realName) throws Exception {  
        response.setContentType("text/html;charset=UTF-8");  
        request.setCharacterEncoding("UTF-8");  
        BufferedInputStream bis = null;  
        BufferedOutputStream bos = null;  
  
        String ctxPath = request.getSession().getServletContext()  
                .getRealPath("/")+"student/file/"  
                ;  
        String downLoadPath = ctxPath + storeName;  
  
        long fileLength = new File(downLoadPath).length();  
  
        response.setContentType(contentType);  
        response.setHeader("Content-disposition", "attachment; filename="  
                + new String(realName.getBytes("utf-8"), "ISO8859-1"));  
        response.setHeader("Content-Length", String.valueOf(fileLength));  
  
        bis = new BufferedInputStream(new FileInputStream(downLoadPath));  
        bos = new BufferedOutputStream(response.getOutputStream());  
        byte[] buff = new byte[2048];  
        int bytesRead;  
        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
            bos.write(buff, 0, bytesRead);  
        }  
        bis.close();  
        bos.close();  
    }  
}
