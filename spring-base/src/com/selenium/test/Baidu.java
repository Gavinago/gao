package com.selenium.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.omg.Messaging.SyncScopeHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Baidu{
   public static void main(String[]args) throws Exception{
	   System.out.println("selenium");
	   
	   WebDriver driver=new FirefoxDriver();
	   driver.get("http://www.baidu.com");
	   //Thread.sleep("2000");
	   driver.findElement(By.id("kw")).sendKeys("selenium.java");
	   driver.findElement(By.id("su")).click();
	   driver.findElement(By.xpath("id('wrapper')/id('wrapper_wrapper')/id('container')/id('content_left')/id('4')/h3/a")).click();;
	   Thread.sleep(1000);
	   driver.close();
   }
   @After
   public void afterTest(){
	   System.out.println("------------after-----------");
   }
   @Test
   public void seleniumTest(){
	   System.out.println("---------Test------------");
   }
   @Before
   public void beforeTest(){
	   System.out.println("------before---");
   }
}
