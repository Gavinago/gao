package com.wssys.test;

import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * 本类测试简单邮件
 * 
 * @author sunny
 * 
 */
public class SingleMailSend {
	public static void main(String args[]) {
		try {
			SimpleMailMessage msg = new SimpleMailMessage();
			// msg.setTo("bingliang85@sina.com");
			// //msg.setTo("zhangfl85@163.com");
			/*
			 * //可以实现邮件群发 String[] msgTo =
			 * {"zhangfl85@126.com","zhangfl85@163.com"}; msg.setTo(msgTo);
			 */
			//"6637152@qq.com", "421818869@qq.com", "651947105@qq.com"
			String[] msgTo = {  "187328407@qq.com","6637152@qq.com" };
			msg.setTo(msgTo);

			msg.setFrom("zhouqb666@sina.com");
			msg.setSubject("群发在测试2");
			msg.setText("确认下问题原因2");
			JavaMailSenderImpl sender = new JavaMailSenderImpl();
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true"); // 实现服务器的验证
			sender.setHost("smtp.sina.com");
			sender.setUsername("zhouqb666");
			sender.setPassword("88314477");
			sender.setJavaMailProperties(props);
			sender.setPort(25);
			sender.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("邮件发送成功.....");
	}
}
