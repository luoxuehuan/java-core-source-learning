package com.java.net.tcp.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TransServer {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		/*
		 * 转化服务端。
		 * 分析：
		 * 1，serversocket服务
		 * 2，获取socket对象。
		 * 3，源：socket。获取输入流，读取客户端发过来的需要转化的数据。
		 * 4，目的： 显示在 控制台上。
		 * 5，将数据转成大写  发给客户端。
		 * 6，。
		 */
		ServerSocket ss = new ServerSocket(10004);
		
		Socket s = ss.accept();
		
		String ip = s.getInetAddress().getHostAddress();
		System.out.println(ip+" ....connected");
		//3.获取socket 读取流，并装饰
		BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
		
		/**
		 * 陷阱去掉true
		 */
		//4.获取socket的输出流,并装饰。
		PrintWriter out = new PrintWriter(s.getOutputStream(),true);
		
		String line  = null;
		/**
		 * 若socket关闭。该方法返回-1？
		 * line=bufIn.readLine()
		 */
		while((line=bufIn.readLine())!=null){
			System.out.println(line);
			
			/**
			 * 陷阱去掉ln
			 */
			out.println(line.toUpperCase());
			
			/**
			 * 1.缺少结束标记。没做标记我就一直等着读。 所以要用println 和自动刷新。 
			 * 2.因阻塞式方法造成。
			 */
			/*out.print(line.toUpperCase()+"\r\n");
			out.flush();*/
		}
		s.close();
	}

}
