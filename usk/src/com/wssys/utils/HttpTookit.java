package com.wssys.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

public class HttpTookit {
	//get请求，url：get地址，cookie 以前cookie
    public static String httpget(String _url,String _cookie){
       String rtnValue="";
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet get=new HttpGet(_url);
            get.setHeader("Content-Type", "application/x-www-form-urlencoded");
            get.setHeader("Connection", " Keep-Alive");
            get.setHeader("Cookie",_cookie);
            HttpResponse response = httpclient.execute(get);
            HttpEntity entity = response.getEntity(); 
             if (entity != null) {
            InputStream instream = entity.getContent();
            rtnValue=InputStreamtoString(instream);
             instream.close();
         }
   } catch (IOException ex) {
        System.out.println("Get数据时出错，ErrorLog： ======================start");
            Logger.getLogger(HttpTookit.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("====================end ");
        }
       return rtnValue;
    }
    //post请求
    public static String httppost(String _url,String _params,String _cookie){
           String rtnValue="";
        try {
             DefaultHttpClient client = new DefaultHttpClient();
             HttpPost post = new HttpPost(_url);
             post.setHeader("Content-Type", "application/x-www-form-urlencoded");
             post.setHeader("Connection", " Keep-Alive");
             post.setHeader("Cookie",_cookie);
            List <NameValuePair> _parameters=StringtoNameValuePair(_params);
             post.setEntity(new UrlEncodedFormEntity(_parameters, HTTP.UTF_8));
             HttpResponse response = client.execute(post);
              HttpEntity entity = response.getEntity(); 
             if (entity != null) {
            InputStream instream = entity.getContent();
            rtnValue=InputStreamtoString(instream);
             instream.close();
         }
            
             
        } catch (IOException ex) {
              System.out.println("Post数据时出错，ErrorLog： ======================start");
            Logger.getLogger(HttpTookit.class.getName()).log(Level.SEVERE, null,"短信发送出错,Post数据时出错"+ ex);
             System.out.println("====================end ");
        } 
        return rtnValue;
    }
    
    //数据流转成字符传
    public static String InputStreamtoString(InputStream _stream){
         String rtnValue="";
        try {
           BufferedReader reader = new BufferedReader(
                             new InputStreamReader(_stream));
                  String readStr=reader.readLine();  
                 while(readStr!=null){
                  rtnValue+=readStr;
                   readStr=reader.readLine();  
                 }
                  
                 reader.close();
        } catch (IOException ex) {
            System.out.println("数据流转换出错，ErrorLog： ======================start");
            Logger.getLogger(HttpTookit.class.getName()).log(Level.SEVERE, null, ex);
           System.out.println("====================end ");
        }
    return rtnValue;
    }
    //post数据转换
    
    public static List <NameValuePair> StringtoNameValuePair(String _strData){
      String[] Strs=_strData.split("&");
      List <NameValuePair> params = new ArrayList <NameValuePair>();
       for(int i=0;i<Strs.length;i++){
           String[] str=Strs[i].split("=");
       params.add(new BasicNameValuePair(str[0], str[1]));
       
       }
    return params;
    
    }
    
    public static void main(String[] args)  {
    	String _params="tel=18668598211,18957866199&ssid=EB102D491A46D4CDC4BA14A59B786A9E&msg=后端http请求测试";
    	HttpTookit.httppost("http://new.worldscrap.com/activity/mobile_cn/res.php", _params, "");
    }
    
    
}
