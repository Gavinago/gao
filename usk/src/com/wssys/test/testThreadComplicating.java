package com.wssys.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

public class testThreadComplicating {
	private static int cpuCoreNumber = Runtime.getRuntime()
			.availableProcessors() * 2; // cpu 核心数 返回到Java虚拟机的可用的处理器数量 决不会小于一个;
	//static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式

	/**
	 * @param args
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException,
			ExecutionException {
		// TODO Auto-generated method stub
		// System.out.println(cpuCoreNumber);
		String resultthread = "";
		ExecutorService threadPool = Executors
				.newFixedThreadPool(cpuCoreNumber);
		CompletionService<String> pool = new ExecutorCompletionService<String>(
				threadPool);

		List<String> mailAddress = new ArrayList<String>();
		for (int i = 0; i < 66; i++) {
			mailAddress.add(i + "@qq.com");
		}
		
		List<String> sendmailAddress = new ArrayList<String>();
		for (int i = 0; i < 31; i++) {
			sendmailAddress.add(i + "@163.com");
		}
		
		int count = mailAddress.size() / 10;
		int yu = mailAddress.size() % 10;
		
		int sendcount = sendmailAddress.size() / 10;
		int sendyu = sendmailAddress.size() % 10;

		for (int i = 0; i < 10; i++) {
			// System.out.println("go系统时间---------------------------------"
			// + df.format(new Date()));
			List<String> subList = new ArrayList<String>();
			if (i == 9) {
				subList = mailAddress.subList(i * count, count * (i + 1) + yu);
			} else {
				subList = mailAddress.subList(i * count, count * (i + 1));
			}
			
			List<String> subsendList = new ArrayList<String>();
			if (i == 9) {
				subsendList = sendmailAddress.subList(i * sendcount, sendcount * (i + 1) + sendyu);
			} else {
				subsendList = sendmailAddress.subList(i * sendcount, sendcount * (i + 1));
			}
			System.out.println(subList);
			String strparam = "任务名称:" + i + "_" + System.currentTimeMillis();
			pool.submit(new MailTaskThreads(strparam, subList,subsendList));

			// System.out.println(resultthread);
		}
		for (int i = 0; i < 10; i++) {
			resultthread += pool.take().get();
		}
		threadPool.shutdown();
	}

	public static class MailTaskThreads implements Callable<String> {
		final String istr;
		final List<String> subList;
		final List<String> sendmailAddress;

		public MailTaskThreads(String istr, List<String> subList,List<String> sendmailAddress) {
			this.istr = istr;
			this.subList = subList;
			this.sendmailAddress=sendmailAddress;
		}

		synchronized void sendmailfors(String istr,List<String> subList,List<String> sendmailAddress)
				throws InterruptedException, TimeoutException {
			sendmailFor(istr, subList,sendmailAddress);
		}

		@Override
		public String call() throws Exception {
			// TODO Auto-generated method stub
			try {

				sendmailfors(istr, subList,sendmailAddress);
				// System.out.println("发送完毕");
			} catch (Exception e) {
				e.printStackTrace();
				return e.getMessage();
			}
			return "发送完毕";
		}

	}

	  public static String sendmailFor(String istr, List<String> subList,List<String> sendmailAddress)
			throws InterruptedException {
		// System.out.println("发邮件系统时间---------------------------------"
		// + df.format(new Date()));
		// System.out.println(istr);
		System.out.println(istr);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		for (int i = 0; i < subList.size(); i++) {
			
			System.out.println("发邮件系统时间---------------------------------"
					 + df.format(new Date()));
			System.out.println("发送服务器:"+sendmailAddress.get(0));
			System.out.println("发邮件" + subList.get(i));
			System.out.println("");
			Thread.sleep(5000l);
		}

		// wait(3000l);
		// Thread.sleep(2000l);
		return istr;
	}
}
