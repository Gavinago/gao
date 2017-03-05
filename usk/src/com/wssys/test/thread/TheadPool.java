package com.wssys.test.thread;

import java.util.LinkedList;



public class TheadPool {
	private final int nThreads;// 线程池的大小
	private final PoolWorker[] threads;// 用数组实现线程池
	private final LinkedList queue;// 任务队列

	/**
	 * 设置线程池中线程数
	 * 
	 * @param nThreads
	 */
	public TheadPool(int nThreads) {
		this.nThreads = nThreads;
		queue = new LinkedList();
		threads = new PoolWorker[nThreads];

		for (int i = 0; i < nThreads; i++) {
			threads[i] = new PoolWorker();
			threads[i].start();// 启动所有工作线程
		}
	}

	/**
	 * 执行任务队列
	 * 
	 * @param r
	 */
	public void execute(Runnable r) {// 执行任务
		synchronized (queue) {
			queue.addLast(r);
			queue.notify();
		}
	}

	/**
	 * 检查任务队列是否有任务，有就执行
	 */
	private class PoolWorker extends Thread {// 工作线程类
		public void run() {
			Runnable r;
			while (true) {
				synchronized (queue) {
					while (queue.isEmpty()) {// 如果任务队列中没有任务，等待
						try {
							queue.wait();
						} catch (InterruptedException ignored) {
						}
					}
					r = (Runnable) queue.removeFirst();// 有任务时，取出任务
				}
				try {
					r.run();// 执行任务
				} catch (RuntimeException e) {

					System.out.println("TheadPool.PoolWorker:: error......");
					e.printStackTrace();
				}
			}
		}
	}
}
