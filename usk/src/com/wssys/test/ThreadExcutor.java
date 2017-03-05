package com.wssys.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadExcutor {

	// 拥有固定线程数的线程池，如果没有任务执行，那么线程会一直等待，代码： Executors.newFixedThreadPool()
	public static ExecutorService executor = Executors.newFixedThreadPool(4);

	public static void main(String args[]) throws InterruptedException {
		// //模拟发送1000封邮件
		// int mailCount = 2;
		// for (int i = 0; i < mailCount; i++) {
		//
		// }
		// executor.submit(new SendMailTask("标题" + i, "内容" + i));
		List<String> mailAddress = new ArrayList<String>();
		
		mailAddress.add("6637152@qq.com");
		mailAddress.add("zqb8889@163.com");
		mailAddress.add("2863292280@qq.com");
		mailAddress.add("zhouqb666@sina.com");
		// mailAddress.add("6637152@qq.com");
		//mailAddress.add("zqb8889@163.com");
		// mailAddress.add("493026752@qq.com");
		// mailAddress.add("236302298@qq.com");
		// mailAddress.add("274975340@qq.com");
		// mailAddress.add("123922233@qq.com");
		// mailAddress.add("403226530@qq.com");
		// mailAddress.add("545161463@qq.com");
		// mailAddress.add("1015113139@qq.com");
		// mailAddress.add("970616873@qq.com");
		// mailAddress.add("520000chinawei@gmail.com");
		// mailAddress.add("421818869@qq.com");

		int mailCount = 2000;
		//for (int i = 0; i < mailCount; i++) {
			executor.submit(new MassMailSendTask(mailAddress));
			Thread.sleep(2000L);
		//}

		// 主线程继续往下面执行逻辑
		System.out.println("等待邮件执行完毕吧");
	}

	// demo
	// public static class SendMailTask implements Runnable {
	// final String tille;
	// final String content;
	//
	// public SendMailTask(String title, String content) {
	// this.tille = title;
	// this.content = content;
	// }
	//
	// public void run() {
	// try {
	// System.out.println(tille + "发送完毕");
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	//
	// }

	public static class MassMailSendTask implements Runnable {
		final List<String> mailAddress;

		public MassMailSendTask(List<String> mailAddress) {
			this.mailAddress = mailAddress;
		}

		void MassMailSend(List<String> mailAddress) {
			int j = 0;
			System.out.println("我来了" + j++);
			String url = "http://www.google.com.hk/";

			// 发邮件
			// 主题
			String subject = "邮件群发测试1";
			// 正文
			StringBuilder builder = new StringBuilder();
			builder.append("<html><head>");
			builder.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
			builder.append("</head><body>");
			builder.append("您好，祝您身体健康，工作愉快：<br />");
			builder.append("\t这是一次多线程的邮件群发测试：<br />");
			builder.append("o(∩_∩)o：888");
			builder.append("<a href=\"");
			builder.append(url);
			builder.append("\">");
			builder.append(url);
			builder.append("</a>");
			builder.append("</body></html>");
			String htmlContent = builder.toString();

			// String[] msgTo = { "6637152@qq.com", "651947105@qq.com" };
			String[] msgTo = new String[mailAddress.size()];
			for (int i = 0; i < mailAddress.size(); i++) {
				msgTo[i] = mailAddress.get(i);
			}
			MailUtil.sendMail(msgTo, subject, htmlContent);
		}

		public void run() {
			try {
				MassMailSend(mailAddress);
				System.out.println("发送完毕");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
