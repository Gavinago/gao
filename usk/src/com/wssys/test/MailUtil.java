package com.wssys.test;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.wssys.test.Constants;
import com.wssys.test.MyAuthenticator;

/**
 * 
 * @author geloin
 * @date 2012-5-8 上午11:02:41
 */
public class MailUtil {
	/**
	 * 发送html邮件
	 * 
	 * @author 
	 * @date 2012-5-8 上午11:38:44
	 * @param toEmail
	 * @param subject
	 * @param htmlContent
	 */
	public static void sendMail(String [] toEmail, String subject,
			String htmlContent) {

		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();

		// 发送邮箱的邮件服务器
		senderImpl.setHost(Constants.emailHost);

		// 建立邮件消息,发送简单邮件和html邮件的区别
		MimeMessage mailMessage = senderImpl.createMimeMessage();
		// 为防止乱码，添加编码集设置
//		MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,
//				"UTF-8");
		//multipart模式 
		MimeMessageHelper messageHelper = null;
		try {
			messageHelper = new MimeMessageHelper(mailMessage, true, "UTF-8");
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			// 接收方邮箱
			messageHelper.setTo(toEmail);
			
		} catch (MessagingException e) {
			throw new RuntimeException("收件人邮箱地址出错！");
		}
		try {
			// 发送方邮箱
			messageHelper.setFrom(Constants.emailFrom);
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
		
		//发送附件
		String attachmentPath="D:/test/4001230o2.jpg";
		String attachmentName="4001230o2.jpg";
		FileSystemResource attachment=new FileSystemResource(attachmentPath);
		
		String attachmentPath2="e:/soft/4ad79dcd-272a-31f1-b6fe-669d5e178e1c.rar";
		String attachmentName2="4ad79dcd-272a-31f1-b6fe-669d5e178e1c.rar";
		FileSystemResource attachment2=new FileSystemResource(attachmentPath2);
		
		try {
			messageHelper.addAttachment(attachmentName,attachment);
			messageHelper.addAttachment(attachmentName2,attachment2);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		Properties prop = new Properties();
		// 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
		prop.put("mail.smtp.auth", "true");
		// 超时时间
		prop.put("mail.smtp.timeout", "25000");

		// 添加验证
		MyAuthenticator auth = new MyAuthenticator(Constants.emailUsername,
				Constants.emailPassword);

		Session session = Session.getDefaultInstance(prop, auth);
		senderImpl.setSession(session);

		// senderImpl.setJavaMailProperties(prop);
		// 发送邮件
		senderImpl.send(mailMessage);

	}
	
	
	public static void sendMail(String  toEmail, String subject,
			String htmlContent) {

		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();

		// 发送邮箱的邮件服务器
		senderImpl.setHost(Constants.emailHost);

		// 建立邮件消息,发送简单邮件和html邮件的区别
		MimeMessage mailMessage = senderImpl.createMimeMessage();
		// 为防止乱码，添加编码集设置
//		MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,
//				"UTF-8");
		//multipart模式 
		MimeMessageHelper messageHelper = null;
		try {
			messageHelper = new MimeMessageHelper(mailMessage, true, "UTF-8");
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			// 接收方邮箱
			messageHelper.setTo(toEmail);
			
		} catch (MessagingException e) {
			throw new RuntimeException("收件人邮箱地址出错！");
		}
		try {
			// 发送方邮箱
			messageHelper.setFrom(Constants.emailFrom);
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
		
		//发送附件
		String attachmentPath="D:/test/4001230o2.jpg";
		String attachmentName="4001230o2.jpg";
		FileSystemResource attachment=new FileSystemResource(attachmentPath);
		try {
			messageHelper.addAttachment(attachmentName,attachment);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		Properties prop = new Properties();
		// 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
		prop.put("mail.smtp.auth", "true");
		// 超时时间
		prop.put("mail.smtp.timeout", "25000");

		// 添加验证
		MyAuthenticator auth = new MyAuthenticator(Constants.emailUsername,
				Constants.emailPassword);

		Session session = Session.getDefaultInstance(prop, auth);
		senderImpl.setSession(session);

		// senderImpl.setJavaMailProperties(prop);
		// 发送邮件
		senderImpl.send(mailMessage);

	}
}
