package com.wssys.test;

import java.util.ArrayList;
import java.util.List;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long byteFile=10000000;
		//System.out.println(getFileKB(byteFile));
		
		List<String> mailAddress = new ArrayList<String>(); // 待发送邮件列表
		mailAddress.add("6637152@qq.com");
		mailAddress.add("651947105@qq.com");
		mailAddress.add("2863292280@qq.com");
		mailAddress.add("zhouqb666@sina.com");
		
		String a="qq";
		
		for (int i = 0; i < mailAddress.size(); i++) {
			//System.out.println(mailAddress.get(i).substring(mailAddress.get(i).indexOf("@")+1, mailAddress.get(i).lastIndexOf(".")));
			String yu=mailAddress.get(i).substring(mailAddress.get(i).indexOf("@")+1, mailAddress.get(i).lastIndexOf("."));
			boolean r=a.contains(mailAddress.get(i).substring(mailAddress.get(i).indexOf("@")+1, mailAddress.get(i).lastIndexOf(".")));
			System.out.println(a+":"+yu+"是"+r);
		}
	}

	private static String getFileKB(long byteFile) {
		if (byteFile == 0)
			return "0KB";
		long kb = 1024;
		return "" + byteFile / kb + "KB";
	}
}
