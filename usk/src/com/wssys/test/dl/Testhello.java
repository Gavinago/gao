package com.wssys.test.dl;

public class Testhello {

	/**
	 * 静态代理
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		myfirstdlImpl mi=new myfirstdlImpl();
		
		helloProxy hp=new helloProxy(mi);
		
		hp.hello();
	}

}
