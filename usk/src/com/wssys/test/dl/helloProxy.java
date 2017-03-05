package com.wssys.test.dl;

public class helloProxy implements myfirstdl {
	private myfirstdlImpl mi;

	/**
	 * 覆盖默认构造器
	 * 
	 * @param myfirstdlImpl
	 */
	public helloProxy(myfirstdlImpl mi) {
		this.mi = mi;
	}

	@Override
	public void hello() {
		// TODO Auto-generated method stub
		// 调用委托类的方法;  
		mi.hello();  
	}

}
