package com.wssys.test;

import java.util.ArrayList;
import java.util.List;

public class test14 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> listmail = new ArrayList<String>();
		listmail.add("1");
		listmail.add("2");
		listmail.add("3");
		listmail.add("4");
		listmail.add("5");
		listmail.add("6");
		listmail.add("7");
		listmail.add("8");
		listmail.add("9");
		listmail.add("10");

		List<String> listsub = listmail.subList(listmail.size() / 2,
				listmail.size());

		for (int i = 0; i < listmail.size(); i++) {
			System.out.println(listmail.get(i));
		}
		System.out.println("---------------------------------------");
		for (int i = 0; i < listsub.size(); i++) {
			System.out.println(listsub.get(i));
		}
	}

}
