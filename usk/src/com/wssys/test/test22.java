package com.wssys.test;

import java.math.BigDecimal;

public class test22 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 double First=599;
		    double Second=10;
		    int scale=0;
		    BigDecimal b1 = new BigDecimal(Double.toString(First));
		    BigDecimal b2 = new BigDecimal(Double.toString(Second));
		        int one=(int) b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue(); 
		        System.out.println(one);
		        int lost=(int) (First-one*(Second-1));
		        System.out.println(lost);
	}

}
