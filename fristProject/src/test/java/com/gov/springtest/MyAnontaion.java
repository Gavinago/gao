package com.gov.springtest;

/**
 * 自定义注解
 * @author Administrator
 *
 */
public @interface MyAnontaion  {
	String value() default "Hello!";
	int test1();
}
