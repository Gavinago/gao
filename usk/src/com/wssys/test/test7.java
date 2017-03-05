package com.wssys.test;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class test7 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double a=33;
		double b=666;
		BigDecimal bd = new BigDecimal(33);  
        BigDecimal bd1 = new BigDecimal(666); 
        DecimalFormat df1 = new DecimalFormat("##.0000000000%"); 
        System.out.println(bd.divide(bd1,10, BigDecimal.ROUND_HALF_DOWN));
        String s=bd.divide(bd1,10, BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(100))+"%";
        System.out.println(s);
		System.out.println(df1.format(bd.divide(bd1,2, BigDecimal.ROUND_HALF_DOWN)));

	}

}
