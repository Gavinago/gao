package com.wssys.comet4j;

import java.text.NumberFormat;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.comet4j.core.CometContext;
import org.comet4j.core.CometEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.wssys.dao.MailtaskDao;
import com.wssys.entity.Mailtask;

public class MailtaskProgress implements ServletContextListener {
	// 手动注入spring 容器
	private static MailtaskProgress instance;
	private String[] PATH = { "classpath:applicationContext.xml" };

	public MailtaskProgress() {
		this.applicationContext = new ClassPathXmlApplicationContext(PATH);
	}

	public synchronized static MailtaskProgress getInstance() {
		if (instance == null)
			instance = new MailtaskProgress();
		return instance;
	}

	@Autowired
	private MailtaskDao mailtaskDao;

	private static final String CHANNEL = "hello";

	private AbstractApplicationContext applicationContext;

	public AbstractApplicationContext getAbstractApplicationContext() {
		return this.applicationContext;
	}

	public void contextInitialized(ServletContextEvent arg0) {
		CometContext cc = CometContext.getInstance();
		cc.registChannel(CHANNEL);// 注册应用的channel
		Thread helloAppModule = new Thread(new HelloAppModule(),
				"Sender App Module");
		helloAppModule.setDaemon(true);
		helloAppModule.start();

	}

	class HelloAppModule implements Runnable {
		public void run() {
			while (true) {
				try {
					Thread.sleep(1000);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				CometEngine engine = CometContext.getInstance().getEngine();
				// engine.sendToAll(CHANNEL,
				// Runtime.getRuntime().freeMemory()/1024);

				Mailtask mt = new Mailtask();
				if (mailtaskDao == null)
					mailtaskDao = (MailtaskDao) MailtaskProgress.getInstance()
							.getAbstractApplicationContext()
							.getBean("mailtaskDao");

				mt = mailtaskDao.getMailtask();

				if (null != mt) {
					Integer sum = mt.getMailsum();
					Integer hassented = mt.getSented();

					if (null == sum || sum == 0) {
						engine.sendToAll(CHANNEL, "0");
						// break;
					} else {
						// BigDecimal hasbeennum = new BigDecimal(hassented);
						// BigDecimal sumnum = new BigDecimal(sum);
						//
						// // ROUND_HALF_UP 四舍五入
						// String persents = hasbeennum.divide(sumnum, 2,
						// BigDecimal.ROUND_UP).multiply(new BigDecimal(100))
						// + "";
						String persents = percent(hassented, sum).replace("%",
								"");
						engine.sendToAll(CHANNEL, persents);
					}
					
				} else {
					engine.sendToAll(CHANNEL, "100");
					 break;
				}
			}
		}
	}

	public void contextDestroyed(ServletContextEvent arg0) {

	}

	/**
	 * 获取百分比
	 * 
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static String percent(double p1, double p2) {
		String str;
		double p3 = p1 / p2;
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits(2);
		str = nf.format(p3);
		return str;
	}
}
