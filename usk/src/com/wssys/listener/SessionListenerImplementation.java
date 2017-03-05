package com.wssys.listener;

import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

import com.wssys.bean.UserSessionAdd;
import com.wssys.utils.Commonutil;

public class SessionListenerImplementation implements SessionListener {
	Map<String, HttpSession> map = new HashMap<String, HttpSession>();
	@Override
	public void onStart(Session session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStop(Session session) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onExpiration(Session session) {
		// TODO Auto-generated method stub

	}

}
