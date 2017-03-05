package com.wssys.test;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class sockettest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String socketIp ="192.168.1.1";
		String socketPort = "8080";
		try {
			Socket s=new Socket(socketIp, Integer.valueOf(socketPort));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
