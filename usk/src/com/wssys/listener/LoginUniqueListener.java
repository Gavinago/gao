package com.wssys.listener;

import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.wssys.bean.UserSessionAdd;
import com.wssys.utils.Commonutil;

public class LoginUniqueListener implements HttpSessionAttributeListener {

	Map<String, HttpSession> map = new HashMap<String, HttpSession>();

	public void attributeAdded(HttpSessionBindingEvent event) {

		String name = event.getName();

		if (name.equals("usa")) {
			UserSessionAdd usa = (UserSessionAdd) event.getValue();
			if (map.get(usa.getMac()) != null) {
				HttpSession sess = map.get(usa.getMac());
				String host = null;
				try {
					host = Commonutil.getLocalMac();
				} catch (SocketException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(usa.getMac().equals(host)){
					sess.removeAttribute("usa");
					sess.invalidate();
				}
				//UserSessionAdd usa1 = (UserSessionAdd) sess.getAttribute("usa");
				
			}
			map.put(usa.getMac(), event.getSession());

		}

	}

	public void attributeRemoved(HttpSessionBindingEvent event) {
		String name = event.getName();
		if (name.equals("usa")) {
			UserSessionAdd usa = (UserSessionAdd) event.getValue();
			map.remove(usa.getMac());
		}
	}

	public void attributeReplaced(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		// ````

	}

}
