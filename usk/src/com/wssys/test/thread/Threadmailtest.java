package com.wssys.test.thread;

import java.util.ArrayList;
import java.util.List;



public class Threadmailtest {
	public static void main(String[] args) {
		TheadPool tp = new TheadPool(5);
		// 任务数
		ThreadMail r[] = new ThreadMail[200];
		List<String> mail=new ArrayList<String>();
		mail.add("6637152@qq.com");
		mail.add("651947105@qq.com");
		
		String url = "http://www.google.com.hk/";
    	// 发邮件
		// 主题
		
		String subject = "群发测试";
		// 正文
		StringBuilder builder = new StringBuilder();
		builder.append("<html><head>");
		builder.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
		builder.append("</head><body>");
		builder.append("您好，zqb：<br />");
		builder.append("\t这是一次多线程的邮件群发测试：<br />");
		builder.append("o(∩_∩)o：");
		builder.append("<a href=\"");
		builder.append(url);
		builder.append("\">");
		builder.append(url);
		builder.append("</a>");
		builder.append("</body></html>");
		String htmlContent = builder.toString();
		
		for (int i = 0;i<mail.size(); i++) {
			r[i]=new ThreadMail();
			r[i].setTo(mail.get(i));
			r[i].setSubject(subject);
			r[i].setHtmlContent(htmlContent);
			tp.execute(r[i]);
		}
	}
}
