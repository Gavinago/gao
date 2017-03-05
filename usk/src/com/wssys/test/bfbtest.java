package com.wssys.test;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class bfbtest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BigDecimal hasbeennum = new BigDecimal(12232);
		BigDecimal sumnum = new BigDecimal(12327);

		// ROUND_HALF_UP 四舍五入
		String persents = hasbeennum.divide(sumnum, 2, BigDecimal.ROUND_UP)
				.multiply(new BigDecimal(100)) + "";
		System.out.println(persents);
		Integer sum = 36;
		Integer hassented =36;
		System.out.println(percent(hassented,sum).replace("%", ""));
		// engine.sendToAll(CHANNEL, persents);
	}

	/**
	 * 获取百分比
	 * 
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static String percent(double p1, double p2) {
		String str;
		double p3 = p1 / p2;
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits(2);
		str = nf.format(p3);
		return str;
	}

}
