package com.gao.test;



import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.gao.entity.Car;

public class Test {
	

	
	public static void main(String[] args) {
		
		
	
		
		ApplicationContext cat = new FileSystemXmlApplicationContext("src/applicationContext.xml");
		Car c = (Car) cat.getBean("harv5");
//		Car c2 = (Car) cat.getBean("bmw");
//		Car c3 = (Car) cat.getBean("bmw");
//		Car c4 = (Car) cat.getBean("bmw");
		
//		c.setBrand("宝马x7");
//		c.setPrice(1888.55);
//		Car c1 = (Car) cat.getBean("bmw1");
//		c1.setBrand("奔驰");
//		System.out.println(c.hashCode()+" "+c2.hashCode()+" "+c3.hashCode()+" "+(c==c2));
		System.out.println(c);
	}
}
