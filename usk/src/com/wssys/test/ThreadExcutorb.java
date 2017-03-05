package com.wssys.test;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadExcutorb {

	/**
	 * 域的日发送上限，这里假设所有域的上限都是一样的
	 */
	public static int REGION_PER_DAY_COUNT = 3000;
	
	/**
	 * 保存每个域对象的信息
	 */
	public static ConcurrentMap<String, MailRegion> mailRegions =
		new ConcurrentHashMap<String, MailRegion>();
	
	/**
	 * 保存未能全部发送成功的邮件对象
	 */
	public static ConcurrentLinkedQueue<Mail> failMailCache = new ConcurrentLinkedQueue<Mail>();
	
	/**
	 * 一次性任务线程池,暂定8个工作线程
	 */
	public static ExecutorService executor = Executors.newFixedThreadPool(8);
	
	/**
	 * 定时任务线程池,暂定1个工作线程
	 */
	public static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
	public static void main(String args[]) {
		//启动定时任务，定时重置今日域发送量，和未发的邮件
		//这里只是测试模拟20秒执行一次，实际是每天凌晨后定时运行
		//3600*1000*24 24小时后 
		scheduler.schedule(new Runnable() {

			@Override
			public void run() {
				
				//重置所有域的今日发送量,全部变为0
				for (MailRegion region : mailRegions.values()) {
					region.todaySendCount.set(0);
				}
				
				//顺便把昨天不能发送的邮件再次丢进去任务队列
				Mail m = null;
				while ((m = failMailCache.poll()) != null) {
					executor.submit(new SendMailTask(m));
				}
			}
			
		}, 3600*1000*24, TimeUnit.SECONDS);
		
		
		//弄2封邮件，分别发给不同的人
		Mail a = Mail.newMail().title("qq邮件1").content("11111111111111")
			//.addRecipient("6637152@qq.com")
			.addRecipient("651947105@qq.com")
			.addRecipient("2863292280@qq.com");
		
		Mail b = Mail.newMail().title("163邮件2").content("22222222222222")
		.addRecipient("zqb8889@163.com");
		
		for(int i=0;i<3500;i++){
			executor.submit(new SendMailTask(a));
			executor.submit(new SendMailTask(b));
		}
		
		
	}
	
	/**
	 * 邮件实体(模拟用)
	 * 
	 * @author Nate
	 * @date 2013-11-7
	 */
	public static class Mail {
		/** 邮件标题 */
		public String title;
		/** 邮件内容 */
		public String content;
		/** 收件人地址集合 */
		public List<String> recipientList = new ArrayList<String>();
		
		private Mail() {}
		
		public static Mail newMail() {
			return new Mail();
		}
		
		public Mail title(String title) {
			this.title = title;
			return this;
		}
		
		public Mail content(String content) {
			this.content = content;
			return this;
		}

		public Mail addRecipient(String region) {
			//空白地址，或者重复的地址，就不要加进去了
			if (region == null || this.recipientList.contains(region))
				return this;
			this.recipientList.add(region);
			return this;
		}
	}
	
	/**
	 * 邮件发送任务
	 * 
	 * @author Nate
	 * @date 2013-11-7
	 */
	public static class SendMailTask implements Runnable {
		/** 一封邮件实例 */
		final Mail mail;
		
		public SendMailTask(Mail mail) {
			this.mail = mail;
		}

		@Override
		public void run() {
			try {
				//循环这封邮件的所有收件人地址,逐个发送
				String[] addressArray = mail.recipientList.toArray(new String[] {});
				for (String address : addressArray) {
					//向该收件人发邮件
					sendMail(address);
					//每发完一封休息2秒
					Thread.sleep(2000L);
				}
				if (!mail.recipientList.isEmpty()) {
					//收件人列表不为空，表示有些人没发送成功
					//先丢去缓存
					failMailCache.add(mail);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		boolean sendMail(String address) {
			try {
				String region = address.substring(address.indexOf("@") + 1);
				MailRegion mailRegion = mailRegions.get(region);
				if (mailRegion == null) {
					//如果还没有记录，就先初始化一个
					mailRegions.putIfAbsent(region, new MailRegion());
					//重新获取一次，这次肯定有记录了
					mailRegion = mailRegions.get(region);
				}
				//检查要发送的这个域今天是否到了发送上限
				if (mailRegion.todaySendCount.incrementAndGet() > REGION_PER_DAY_COUNT) {
					//这个域今天到上限了，不能再发了
					System.out.println("域【" + region + "】到达今天发送上限了，暂时不能发");
					return false;
				}
				
				//尝试去获得这个域对象的信号量权限
				mailRegion.semp.acquire();
				
				//发送邮件
				// xxxx.sendMail(mail);
				MailUtil.sendMail(address, mail.title, mail.content);
				
				mailRegion.semp.release();
				
				System.out.println("收件人【" + address + "】发送成功");
				//从收件人列表移除
				mail.recipientList.remove(address);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
	}
	
	/**
	 * 邮件域
	 * 
	 * @author 
	 * @date 2013-11-7
	 */
	public static class MailRegion {
		/**
		 * 信号量对象，控制这个域能同时被多少个线程访问，即时对这个域，能同时发送多少封邮件
		 * 暂定4个啦
		 */
		private Semaphore semp = new Semaphore(4);
		/**
		 * 今日发送量
		 */
		private AtomicInteger todaySendCount = new AtomicInteger(0);
		
		public Semaphore getSemp() {
			return semp;
		}
		public AtomicInteger getTodaySendCount() {
			return todaySendCount;
		}
	}
}
