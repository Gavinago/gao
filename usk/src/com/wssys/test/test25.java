package com.wssys.test;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class test25 {

	/**
	 * @param args
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		ExecutorService pool2 = Executors.newFixedThreadPool(10);
		CompletionService<String> completionService = new ExecutorCompletionService<String>(
				pool2);
		for (int i = 0; i < 10; i++) {
			final int index = i;
			completionService.submit(new Callable<String>() {

				public String call() throws Exception {
					Thread.sleep(2000l);
					String strparam="任务名称:"+index+"_"+System.currentTimeMillis();
					return strparam;
				}
			});
		}
		System.out.println(completionService.take().get());
//		for (int i = 0; i < 10; i++) {
//			try {
//				System.out.println(completionService.take().get());
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (ExecutionException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}

}
