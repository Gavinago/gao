package com.wssys.test;

import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.Sha384Hash;

public class pwdtest {
	public static void main(String[] args) {
		PasswordService svc = new DefaultPasswordService();  
	    String encrypted = svc.encryptPassword("123");
	    String pwd = new Sha256Hash("123", "zqb", 1024).toBase64(); // 将用户输入密码与用户名salt加密
	    String pwdb = new Sha256Hash("12345", "zqb", 1024).toBase64();
	    String sha384Hex = new Sha384Hash("123").toBase64();
	    String sha384Hexb = new Sha384Hash("88314477").toBase64();
	    System.out.println(sha384Hex);
	    System.out.println(sha384Hexb);
//	    System.out.println(pwd);
//	    System.out.println(pwdb);
//	    boolean a=svc.passwordsMatch("123", encrypted);
//	    System.out.println(encrypted);
//	    System.out.println(a);
	}
}
