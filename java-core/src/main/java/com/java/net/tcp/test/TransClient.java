package com.java.net.tcp.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TransClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		/*
		 * 思路：
		 * 客户端：
		 * 1，需要先有socket端点。
		 * 2，客户端的数据源：键盘————————————————————————改：把键盘录入 改为读入文件。（上传）
		 * 3，客户端的 目的：socket
		 * 4，接受服务端的数据，源：socket
		 * 5，将数据显示在控制台。 目的： 控制台。——————————————改：把文件存到本地。（存档）  网络IO流传输。上传文本文件，用字符流。如果上传图片，用什么流~
		 * 6，在这些流中操作的数据都是文本数据。
		 * 
		 * 转换客户端：
		 * 1.创建socket客户端对象。
		 * 2.获取键盘录入
		 * 3.将录入的信息发送给socket输出流。
		 * 
		 * 
		 * 是思路不会，还是代码不会？
		 */
		Socket s = new Socket("127.0.0.1",10004);
		
		/**
		 * 获取键盘录入
		 */
		BufferedReader bufr = 
				new BufferedReader(new InputStreamReader(System.in));
		
		new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		
		/**
		 * 陷阱，去掉true 自动刷新。
		 */
		PrintWriter out = new PrintWriter(s.getOutputStream(),true);
		
		//4.socket输入流，读取服务端返回的大写数据。
		BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
		
		
		String line = null;
		
		while((line=bufr.readLine())!=null){
			if("over".equals(line)){
				break;
			}
			
			/**
			 * 陷阱： 去掉ln（没有换行标记，如果没有换行标记， 服务端会以为没读完，一直在等待。）
			 * 
			 * line写入到了PrintWriter，没有做刷新动作因此没有进输出流。
			 * 
			 * 可以用out.flush();解决 没刷新问题。
			 */
			out.println(line);
			
			String upperStr = bufIn.readLine();
			System.out.println(upperStr);
			
		}
		
		
		s.close();
		
	}

}
