package com.wssys.test.dl;

import java.util.WeakHashMap;

public class TestWeakHashMap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WeakHashMap whm = new WeakHashMap();  
        //添加三个键值对  
        //三个键都是匿名字符串对象(没有其它引用)  
        whm.put(new String("语文"),new String("良好"));  
        whm.put(new String("数学"),new String("及格"));  
        whm.put(new String("英文"),new String("中等"));  
        //添加一个键值对  
        //该Key是一个系统缓存的字符串对象  
        whm.put("java",new String("中等"));  
        whm.put("javaa",new String("nice"));  
        //输出whm,将看到四个键值对  
        System.out.println(whm);  
        //通知系统进行垃圾回收  
        System.gc();  
        System.runFinalization();  
        //通常情况下将只看到一个键值对  
        System.out.println(whm);  
	}

}
