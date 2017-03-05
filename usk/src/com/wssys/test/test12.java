package com.wssys.test;

import java.math.BigDecimal;

public class test12 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BigDecimal bd = new BigDecimal(15);
		BigDecimal bd1 = new BigDecimal(37);

		
		// ROUND_HALF_UP 四舍五入
		String firstRate = bd.divide(bd1, 2, BigDecimal.ROUND_UP)
				.multiply(new BigDecimal(100)) + "";
		
		 System.out.println(bd.divide(bd1,2, BigDecimal.ROUND_UP));
		System.out.println(firstRate);
	}

}
