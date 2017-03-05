package com.wssys.test;

import java.util.ArrayList;
import java.util.List;

public class test18 {

	/**
	 * @author nate
	 * @date 2013-11-22
	 */
	public static void main(String[] args) {
		
		int listsize=55;
		int t = listsize/10;	//每份多少
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i <= listsize; i++) {
			list.add(i);
		}
		
		int max = list.size();
		for (int i = 0; i < max; i++) {
			if (i + t < max) {
				System.out.println(list.subList(i, i + t));
				i += t - 1;
			} else {
				System.out.println(list.subList(i, max));
				break;
			} 
		}
	}

}
