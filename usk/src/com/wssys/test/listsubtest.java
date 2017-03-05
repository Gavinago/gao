package com.wssys.test;

import java.util.ArrayList;
import java.util.List;

public class listsubtest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> tests = new ArrayList<Integer>();
		// init list
		for (int i = 0; i < 11; i++) {
			tests.add(i); // auto boxing
		}
		System.out.println(tests.size());
		List<Integer> subs = tests.subList(0, 10);
		for (int i = 0; i < subs.size(); i++) {
			//System.out.println(subs.get(i) + " ");
		}
		System.out.println(subs.size());

		// List<Integer> test = new ArrayList<Integer>();
		// //init list
		// for (int i = 0; i < 5; i++) {
		// test.add(i); //auto boxing
		// }
		// //display the list
		// System.out.print("the orginal list: ");
		// for (int i = 0; i < test.size(); i++) {
		// System.out.print(test.get(i) + " ");
		// }
		// System.out.println();
		//
		// //sub list
		// List<Integer> sub = test.subList(1, 3); //sub list contains elements:
		// 1, 2
		// sub.remove(1); //remove element “2” from sub list
		//
		// //display the list again
		// System.out.print("the orginal list after sublist modified: ");
		// for (int i = 0; i < test.size(); i++) {
		// System.out.print(test.get(i) + " ");
		// }
		// System.out.println();
	}

}
