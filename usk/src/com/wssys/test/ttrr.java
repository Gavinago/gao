package com.wssys.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.wssys.bean.ComPanyForm;

public class ttrr {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ComPanyForm n=new ComPanyForm();
		if(n.getAddress()!=null){
			System.out.print("true");
		}else{
			System.out.print("false");
		}
	}
	
	public void check() {  
        Method[] methods = this.getClass().getMethods();  
        for (int i = 0; i < methods.length; i++) {  
            Method method = methods[i];  
            if (method.getName().startsWith("set")  
                    && (method.getParameterTypes())[0].getName().equals(String.class.getName())) {  
                try {  
                    method.invoke(this);  
                } catch (IllegalArgumentException e) {  
                    e.printStackTrace();  
                } catch (IllegalAccessException e) {  
                    e.printStackTrace();  
                } catch (InvocationTargetException e) {  
                    e.printStackTrace();  
                }  
            }  
        } 
	}

}
