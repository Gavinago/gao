package com.wssys.test;

import java.math.BigDecimal;
import java.math.MathContext;

public class fffff {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//BigDecimal htzl=new BigDecimal("55.601222");
		BigDecimal htzl=new BigDecimal("55.601222");
		BigDecimal realzl=new BigDecimal("75.601522");
		//,new MathContext(4)
		System.out.println(realzl.subtract(htzl));
		
		  BigDecimal a = new BigDecimal("-12");
		  
		  BigDecimal b = new BigDecimal("56");
		  
		  System.out.println(a.subtract(b));
		  System.out.println(a.divide(b,new MathContext(4)));
	}

}
