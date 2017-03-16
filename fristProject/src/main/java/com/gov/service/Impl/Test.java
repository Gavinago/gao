package com.gov.service.Impl;

public class Test {
	
	public Test(){
		
	}

	public String reversal(String str){
		StringBuilder sb = new StringBuilder();
		for(int i=str.length()-1;i>=0;i--){
			sb.append(str.charAt(i));
			
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		Test t = new Test();
		String str = t.reversal("123456");
		System.out.println(str);
	}
}
