package com.wssys.test;

import java.util.ArrayList;
import java.util.List;

public class test21 {

	/**
	 * 小俊
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  final List<Integer> list = new ArrayList<Integer>();
	        for (int i = 1; i <= 8194; i++) {
	            list.add(i);
	        }
	        if (list.size() % 10 == 0) {
	            // 能整除
	    		int count = list.size()/10;
	    		for (int i = 0; i < 10; i++) {
	    			List<Integer> subList = new ArrayList<Integer>();
	    			subList = list.subList(i * count, count * (i + 1));

	    			System.out.println(subList);
	    		}
	        } else {
	            final int count = (int) Math.ceil(list.size() / 10.0);
	            for (int i = 0; i < 10; i++) {
	                List<Integer> subList = new ArrayList<Integer>();
	                if (i == 9) {
	                    subList = list.subList(i * count,
	                            (i * count) + count - (count * 10 - list.size()));
	                } else { 
	                    subList = list.subList(i * count, (i + 1) * count);
	                }
	                System.out.println(subList);
	            }
	        }

	}

}
