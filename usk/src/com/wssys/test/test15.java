package com.wssys.test;

import java.util.ArrayList;
import java.util.List;

public class test15 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int count = 1000;
        int index = 0;
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < count; i++) {
            list.add(i+1);
        }
        for (int i = 0; i < 10; i++) {
            List<Integer> temp = list.subList(index, index+count/10);
            index += count/10;
            System.out.println("the data of list " + (i + 1) + "===========>");
            for (int j = 0; j < temp.size(); j++) {
                System.out.println(temp.get(j));
            }
        }
	}

}
