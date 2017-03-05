package com.wssys.shiroController;

import org.apache.shiro.authc.UsernamePasswordToken;

public class CaptchaToken extends UsernamePasswordToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//验证码字符串  
    private String captcha;  
  
    public CaptchaToken(String captcha) {   
        this.captcha = captcha;  
    }  
  
    public String getCaptcha() {  
        return captcha;  
    }  
  
    public void setCaptcha(String captcha) {  
        this.captcha = captcha;  
    }  
}
