package com.wssys.test;

public class test30 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String m="sdfd@qq.163.com";
		System.out.println(m.substring(
				m.indexOf("@") + 1,
				m.lastIndexOf(".")));
		
	}

}
