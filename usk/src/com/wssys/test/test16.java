package com.wssys.test;

import java.util.ArrayList;
import java.util.List;

public class test16 {

	/**
	 * java list拆分最终确定版
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> list = new ArrayList<Integer>();
		int sizes=122;	//sizes是一个动态变量 测试的时候先写死
		for (int i = 1; i <= sizes; i++)	
			list.add(i);
		
		int count = list.size()/10;
		int yu = list.size() % 10;

		for (int i = 0; i < 10; i++) {
			List<Integer> subList = new ArrayList<Integer>();
			if (i == 9) {
				subList = list.subList(i * count, count * (i + 1) + yu);
			} else {
				subList = list.subList(i * count, count * (i + 1));
			}

			System.out.println(subList);
		}
	}

}
