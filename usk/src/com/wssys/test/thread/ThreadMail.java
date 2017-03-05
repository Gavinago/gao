package com.wssys.test.thread;

import com.wssys.test.MailUtil;

public class ThreadMail implements Runnable{
	 String  to;
	 String  subject;
	 String  htmlContent;
	    
	 

		public void run(){
	    	

			//String[] msgTo = { "6637152@qq.com", "651947105@qq.com" };
			MailUtil.sendMail(to, subject, htmlContent);
	    }
	    
	    
		   public void setSubject(String subject) {
				this.subject = subject;
			}

			public void setHtmlContent(String htmlContent) {
				this.htmlContent = htmlContent;
			}

				public void setTo(String  to) {
				this.to = to;
			}
	   
}
