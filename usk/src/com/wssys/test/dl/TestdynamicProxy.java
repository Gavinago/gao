package com.wssys.test.dl;

public class TestdynamicProxy {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mydenamieProxy proxy = new mydenamieProxy();
		mydynamic mkProxy = (mydynamic) proxy.bind(new mydynamicImpl());
		mkProxy.hello();
	}

}
