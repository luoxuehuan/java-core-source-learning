package com.java.net.ie_server;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class URLDemo {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String str_url = "http://www.luoxuehuan.com";//:8080/aboutme?name=list
		URL url = new URL(str_url);
		System.out.println("getProtocol:"+url.getProtocol());
		System.out.println("getHost:"+url.getHost());
		System.out.println("getPort:"+url.getPort());
		System.out.println("getFile:"+url.getFile());
		System.out.println("getPath:"+url.getPath());
		System.out.println("getQuery:"+url.getQuery());
		/*getProtocol:http
		getHost:www.luoxuehuan.com
		getPort:8080
		getFile:/aboutme?name=list
		getPath:/aboutme
		getQuery:name=list*/
		
		
		
		//InputStream in = url.openStream();
		
		//open就是openConnection+getInputStream
		
		/**
		 * 获取url对象的url连接器对象。
		 * 将连接封装成了对象：
		 * java中内置的可以解析的具体协议的对象+socket。
		 * 
		 * 把http的应答头，解除掉了。
		 * 
		 * 以后就全用这个。
		 */
		URLConnection conn = url.openConnection();
		
		String value = conn.getHeaderField("Content-Type");
		System.out.println(value);
		System.out.println(conn);
		//sun.net.www.protocol.http.HttpURLConnection:http://www.luoxuehuan.com
		InputStream in = conn.getInputStream();
		
		byte[] buf = new byte[1024];
		int len = in.read(buf);
		
		String text =  new String(buf,0,len);
		System.out.println(text);
		in.close();
	}

}
