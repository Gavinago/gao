package com.wssys.test;
import java.net.*;
import java.io.*;
public class test10 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringBuffer document = new StringBuffer();
        try 
        {
            URL url = new URL("http://www.baidu.com");
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null)
            document.append(line + " ");
            reader.close();
        }
        catch (MalformedURLException e) 
        {
            e.printStackTrace(); 
        }
        catch (IOException e)
        {
            e.printStackTrace(); 
        }
        System.out.println(document.toString());

	}

}
