package com.wssys.test;

public class containstest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a = "org.apache.shiro.web.serv";
		if (a.contains("shiro")) {
			System.out.println("有这个字符");
		}
		if (a.indexOf("shiro")>-1) {
			System.out.println("有这个字符");
		}
	}

}
