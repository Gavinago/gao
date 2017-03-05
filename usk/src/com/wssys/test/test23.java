package com.wssys.test;

public class test23 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		
		   new Thread(){

		        public void run() {
		            for (int i = 0; i < 3; i++) {
		                System.out.println("这是一个邮件任务a"+i);
		            }
		        }
		           
		       }.start();
		       
		       new Thread(){
		           public void run() {
		               for (int i = 0; i < 3; i++) {
		                   System.out.println("这是一个邮件任务b"+i);
		               }
		           }
		          }.start();
	}

}
