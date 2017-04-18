package com.java.net.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeDemo2 {

	public static void main(String[] args) throws IOException {
		// 服务端接受客户端发送过来的数据，并打印在控制台上。
		
		/*
		 * 建立tcp服务端的思路：
		 * 1.建立服务端socket对象。通过ServerSocket对象。
		 * 2.服务端必须对外提供一个端口，否则客户端无法连接。
		 * 	比如tomcat服务器，提供一个8080端口。
		 * 	包括连接数据库。数据库地址+端口。ip+3306（用户名+密码）
		 * 
		 * 
		 * 【注意】c1和c2都往s发，s回的时候，怎么判断发给c1或者c2.
		 * s 本身不需要流，本身装的全是资源。。
		 * 
		 * s获取到c1的对象。拿c1的socket对象（流）和c1进行通讯。
		 * 或者拿c2的socket对象和c2通讯。
		 * 
		 * 比如你拨打10086，只有一个客服和你通讯。
		 * 
		 * 3.获取连接过来的客户端对象。 
		 * 4.拿到客户端对象获取socket流。读取客户端发来的数据。
		 * 	并打印在控制台上。
		 * 5.关闭资源。 关客户端，关服务端。 
		 * 	服务器同时在线连接数为2的时候，第三个不能连接上来。所以需要断开1个，否则会耗费服务器资源。
		 */
		
		/**
		 * 1.创建服务端对象。
		 */
		ServerSocket ss = new ServerSocket(10002);
		
		/**
		 * 获取连接过来的客户端对象。
		 * accept()  侦听并接受到此套接字的连接。
		 */
		Socket s = ss.accept();//阻塞式。没有socket当然等着。
		/**
		 * 3.通过socket对象获取输入流，要读取客户端发来的数据。
		 */
		InputStream in = s.getInputStream();
		
		/**
		 * 数据多，缓冲
		 */
		byte[] buf = new byte[1024];
		int len = in.read(buf);
		String text = new String(buf,0,len);
		System.out.println("server"+text+s.getInetAddress().getHostAddress());
		
		
		/**
		 * 
		 * 4.往回发。
		 * 
		 * 使用客户端socket对象的输出流给客户端返回数据
		 */
		OutputStream out = s.getOutputStream();
		out.write("收到".getBytes());
		
		s.close();
		ss.close();
		
	}

}
