package com.wssys.comet4j;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.comet4j.core.CometContext;
import org.comet4j.core.CometEngine;




public class MailtaskProgressb implements ServletContextListener{
	private static final String CHANNEL = "hello";
	
	//@Autowired
	//private MailtaskDao   mailtaskDao;
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		CometContext cc = CometContext.getInstance();
		 cc.registChannel(CHANNEL);//注册应用的channel
//		 Mailtask mt=new Mailtask();
//         mt=mailtaskDao.getMailtask();
		 
		 Thread mailAppModule = new Thread(new MailAppModule(), "Sender App Module");
		 mailAppModule.setDaemon(true);	//守护线程
		 mailAppModule.start();
	}

	class MailAppModule implements Runnable {
		public void run() {
            while (true) {
                    try {
                            Thread.sleep(1000);
                    } catch (Exception ex) {
                            ex.printStackTrace();
                    }
                    CometEngine engine = CometContext.getInstance().getEngine();
                    
//                    Mailtask mt=new Mailtask();
//                    mt=mailtaskDao.getMailtask();
//                    if(null!=mt){
//                    	 int sum=mt.getMailsum();
//                         int hassented=mt.getSented();
//                         BigDecimal hasbeennum = new BigDecimal(hassented);
//                 		BigDecimal sumnum= new BigDecimal(sum);
//
//                 		
//                 		// ROUND_HALF_UP 四舍五入
//                 		String persents = hasbeennum.divide(sumnum, 2, BigDecimal.ROUND_UP)
//                 				.multiply(new BigDecimal(100)) + "";
//
//                         engine.sendToAll(CHANNEL, persents);
//                    }else{
//                    	engine.sendToAll(CHANNEL, "0");
//                    }
                    engine.sendToAll(CHANNEL, Runtime.getRuntime().freeMemory()/1024);
                   
            }
    }
	}
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
