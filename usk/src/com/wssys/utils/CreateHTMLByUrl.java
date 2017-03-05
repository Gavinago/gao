package com.wssys.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;

public class CreateHTMLByUrl {

	private static long star = 0;
	private static long end = 0;
	private static long ttime = 0;

	/**
	 * 根据url 生成对应的 html代码
	 * 
	 * @param httpUrl
	 * @return
	 */
	public static String getHtmlCode(String httpUrl) {
		httpUrl = StringUtil.stringIsNull(httpUrl);
		if (httpUrl.equals("")) {
			return "";
		}
		Date before = new Date();
		star = before.getTime();
		String htmlCode = "";
		try {
			InputStream in;
			URL url = new java.net.URL(httpUrl);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("User-Agent", "Mozilla/4.0");
			connection.connect();
			in = connection.getInputStream();
			java.io.BufferedReader breader = new BufferedReader(
					new InputStreamReader(in, "utf-8"));
			String currentLine;
			while ((currentLine = breader.readLine()) != null) {
				htmlCode += currentLine + "\n";
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			Date after = new Date();
			end = after.getTime();
			ttime = end - star;

		}
		return htmlCode;
	}

	/**
	 * 根据给的的url List 生成给定名称路径的html文件列表
	 * 
	 * @param urlLst
	 *            url列表
	 * @param fileLst
	 *            要生成的文件的全路径列表
	 * @param isDel
	 *            true 表示 要删除存在的文件
	 */
	public static synchronized void writeHtml(List<String> urlLst,
			List<String> fileLst, boolean isDel) {
		if (urlLst == null || fileLst == null
				|| (urlLst.size() != fileLst.size())) {
			return;
		}
		for (int i = 0, count = urlLst.size(); i < count; i++) {
			writeHtml(urlLst.get(i), fileLst.get(i), isDel);
		}

	}

	/**
	 * 根据给的的url 生成给定名称路径的html文件
	 * 
	 * @param url
	 * 
	 * @param filePath
	 * @param isDel
	 *            true 表示 要删除存在的文件
	 */
	public static synchronized void writeHtml(String url, String filePath,
			boolean isDel) {
		Writer ow = null;
		try {
			File writeFile = new File(filePath);
			boolean isExit = writeFile.exists();
			File folder = writeFile.getParentFile();
			if (folder.exists() == false) {
				folder.mkdirs();
			}
			if (isExit != true) {
				writeFile.createNewFile();
			} else {
				if (isDel) {
					writeFile.delete();
					writeFile.createNewFile();
				}
			}
			ow = new OutputStreamWriter(new FileOutputStream(filePath), "utf-8");
			ow.write(getHtmlCode(url));
		} catch (Exception ex) {

		} finally {
			try {
				ow.close();
			} catch (Exception ex) {

			}

		}
	}
}
