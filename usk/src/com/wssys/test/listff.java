package com.wssys.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class listff {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] i = new Integer[]{1,2,3};
		List s=new ArrayList();
		for(int ii=0;ii<i.length;ii++){
			s.add(i[ii]);
		}
//		List s=Arrays.asList(i);
		s.remove(2);
		for(int ii=0;ii<s.size();ii++){
			System.out.println(s.get(ii));
		}
	}

}
