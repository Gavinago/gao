package com.selenium.test;

import static org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
public class Testt {
		@Test
		public  void test() throws InterruptedException {
			WebDriver wd ;
			wd = new FirefoxDriver();            //选择浏览器
			wd.get("http://192.168.31.139/fristProject/");   //输入链接
			wd.findElement(By.id("userName")).clear();     //清空用户名文本框
			wd.findElement(By.id("userName")).sendKeys("admin");     //输入登录名
			wd.findElement(By.id("password")).clear();              //清空密码文本框
			wd.findElement(By.id("password")).sendKeys("123456");   //输入密码
			Thread.sleep(10000);
			wd.findElement(By.xpath("id('unlogin')/div[2]/div/div[2]/div[4]/div[1]/input")).click();
			String roomNub = wd.findElement(By.xpath("id('ajaxcontent')/div/table/tbody/tr[2]/td[1]")).getText();
			assertNotNull(roomNub);
			wd.findElement(By.id("searchText")).sendKeys(roomNub);
			wd.findElement(By.xpath("id('ajaxcontent')/div/div[1]/div/div[3]/div/span/a[1]")).click();
			Thread.sleep(2000);
			wd.findElement(By.xpath("id('20')/div/ul/li[2]/a")).click();
			Thread.sleep(2000);
			wd.findElement(By.xpath("id('20')/div/ul/li[3]/a")).click();
			Thread.sleep(2000);
			wd.findElement(By.xpath("id('20')/div/ul/li[4]/a")).click();
			Thread.sleep(2000);
			wd.findElement(By.xpath("id('20')/div/ul/li[5]/a")).click();
			Thread.sleep(2000);
			wd.findElement(By.xpath("id('20')/div/ul/li[6]/a")).click();
			Thread.sleep(2000);
			
			wd.findElement(By.xpath("id('accordion2')/div[2]/div[1]/a")).click();
			Thread.sleep(2000);
			wd.findElement(By.xpath("id('30')/div[1]/ul[1]/li[1]/a")).click();
			Thread.sleep(2000);
			wd.findElement(By.xpath("id('30')/div[1]/ul[1]/li[2]/a")).click();
			Thread.sleep(2000);
			wd.findElement(By.xpath("id('30')/div[1]/ul[1]/li[3]/a")).click();
			Thread.sleep(2000);
			
			wd.findElement(By.xpath("id('accordion2')/div[3]/div[1]/a")).click();
			Thread.sleep(2000);
			wd.findElement(By.xpath("id('accordion2')/div[3]/div[2]/div[1]/ul[1]/li[1]/a")).click();
			Thread.sleep(2000);
			wd.findElement(By.xpath("id('40')/div[1]/ul[1]/li[2]/a")).click();
			Thread.sleep(2000);
			wd.findElement(By.xpath("id('40')/div[1]/ul[1]/li[3]/a")).click();
			Thread.sleep(2000);;
			
			
			wd.findElement(By.xpath("id('navbar-collapse-menu')/ul[2]/li/a")).click();
			Thread.sleep(1000);
			wd.findElement(By.xpath("id('navbar-collapse-menu')/ul[2]/li/ul/li[1]/a")).click();
			Thread.sleep(1000);
			wd.findElement(By.xpath("/html/body/div[3]/div[3]/button[1]")).click();
			Thread.sleep(1000);	
			wd.close(); 
		}
}
