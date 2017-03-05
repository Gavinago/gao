package com.wssys.test;

public class MailTest {
	public static void main(String[] args) {
		
	}
	
	public static void MassMailSend(){
		String url = "http://www.google.com.hk/";

		// 发邮件
		// 主题
		String subject = "RUI密码信息";
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

		String[] msgTo = { "6637152@qq.com", "651947105@qq.com" };
		MailUtil.sendMail(msgTo, subject, htmlContent);
	}
}
