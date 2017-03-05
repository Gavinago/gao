package com.wssys.test;

public class testfor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		label: for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (i == 1) {
					System.out.println(i);
					System.out.println(j);
					break label;
				}
			}
		}
		System.out.println("ok");
	}

}
