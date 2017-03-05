package com.wssys.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.wssys.bean.SendMainServer;

public class testdaluanlist {
	public static void main(String[] args) {
		// List<Integer> list = new ArrayList<Integer>();
		// for (int i = 0; i < 10; i++)
		// list.add(new Integer(i));
		// System.out.println("打乱前:");
		// System.out.println(list);

		// List<String> list = new ArrayList<String>();
		// for (int i = 0; i < 10; i++)
		// list.add(new String("a"+i));
		// System.out.println("打乱前:");
		// System.out.println(list);

		List<SendMainServer> list = new ArrayList<SendMainServer>();
		for (int i = 0; i < 10; i++) {
			SendMainServer sm = new SendMainServer();
			sm.setEmailFrom("a" + i);
			list.add(sm);
		}
		System.out.println("打乱前:");
		System.out.println(list.get(0).getEmailFrom());

		for (int i = 0; i < 5; i++) {
			System.out.println("第" + i + "次打乱：");
			Collections.shuffle(list);
			System.out.println(list.get(i).getEmailFrom());
		}
	}
}
