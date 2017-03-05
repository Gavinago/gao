package com.gov.springtest;

public class TestAnnotation {
	
	
public static void main(String[] args) {
	new TestAnnotation().Test();
}
	@MyAnontaion(value="你好！", test1 = 2)
	public void Test(){
		System.out.println("niiiiii!");
	}
}
