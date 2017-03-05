package com.wssys.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component("contactFormValidator")
public class ContactFormValidator implements Validator {

//	J2SE 提供的最后一个批注是 @SuppressWarnings。该批注的作用是给编译器一条指令，
//	告诉它对被批注的代码元素内部的某些警告保持静默。
//	unchecked 执行了未检查的转换时的警告，例如当使用集合时没有用泛型 (Generics) 来指定集合保存的类型。 
//	@SuppressWarnings("unchecked")
//	public boolean supports(Class clazz)// 该校验支持的目标类
//	{
//		return Contact.class.isAssignableFrom(clazz);
//	}
//
//	public void validate(Object target, Errors errors) {// 对目标类对象进行校验，错误记录在errors中
//		Contact contact = (Contact) target;
//		// Name
//		userCheck(contact.getName(), errors);
//		// Password
//		passwordCheck(contact.getPassword(), errors);
//		// Email
//		emailCheck(contact.getEmail(), errors);
//		// mobile
//		moblieCheck(contact.getMobile(), errors);
//
//	}

	public void loginValidate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name","required.name", "Name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password","required.password", "Password is required.");
	}

	private void userCheck(String name, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name","required.name", "Name is required.");
		Pattern pattern = Pattern.compile("^[\u4E00-\u9FA5a-zA-Z0-9_]{1,14}$");
		Matcher matcher = pattern.matcher(name);
		if (name.length() < 1) {
			errors.rejectValue("name", "filed.name","Name's length must be more than 1 character.");
		}else if(!matcher.matches()){
			errors.rejectValue("name", "filed.name","User name is allowed only for English, underline, figures and Chinese characters.");
		}
	}

	private void passwordCheck(String password, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password","required.password", "Password is required.");
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9_]{1,}$");
		Matcher matcher = pattern.matcher(password);
		if (password.length() < 8 || password.length() > 30) {
			errors.rejectValue("password", "filed.password","Password's length must be between 8 and 30.");
		}else if(!matcher.matches()){
			errors.rejectValue("password", "filed.password","Password is allowed only by English, underline and digital components.");
		}
	}

	private void moblieCheck(String moblie, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobile","required.mobile", "Mobile is required.");
		Pattern pattern = Pattern.compile("^1[3|5|8][\\d]{9}$");
		Matcher matcher = pattern.matcher(moblie);
		if (!matcher.matches()) {
			errors.rejectValue("mobile", "filed.mobile","Mobile is invalidate");
		}
	}

	private void emailCheck(String email, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email","required.email", "Email is required.");
		Pattern pattern = Pattern.compile("[\\w-]{1,}@[\\w-]{1,}.[\\w-]{1,}");
		Matcher matcher = pattern.matcher(email);
		if (!matcher.matches()) {
			errors.rejectValue("email", "filed.email", "Email is invalidate");
		}
	}

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub
		
	}

	// public static void main(String[] args) {
	// String s="13345678912";
	// Pattern pattern =Pattern.compile("^1[3|5|8][\\d]{9}$");
	// Matcher matcher= pattern.matcher(s);
	// System.out.println(matcher.matches());
	// }
}