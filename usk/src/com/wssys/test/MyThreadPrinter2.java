package com.wssys.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyThreadPrinter2 implements Runnable {
	static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
	
	private String name;
	private Object prev;
	private Object self;

	private MyThreadPrinter2(String name, Object prev, Object self) {
		this.name = name;
		this.prev = prev;
		this.self = self;
	}

	@Override
	public void run() {
		int count = 10;
		while (count > 0) {
			synchronized (prev) {
				synchronized (self) {
					System.out.println("发邮件系统时间---------------------------------"
							 + df.format(new Date()));
					System.out.println(name);
					count--;

					self.notify();
				}
				try {
					prev.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	}

	public static void main(String[] args) throws Exception {
		Object a = new Object();
		Object b = new Object();
		Object c = new Object();
		Object d = new Object();
		MyThreadPrinter2 pa = new MyThreadPrinter2("A", c, a);
		MyThreadPrinter2 pb = new MyThreadPrinter2("B", a, b);
		MyThreadPrinter2 pc = new MyThreadPrinter2("C", b, c);
		MyThreadPrinter2 pd = new MyThreadPrinter2("D", c, d);

		new Thread(pa).start();
		new Thread(pb).start();
		new Thread(pc).start();
		new Thread(pd).start();
	}

}
