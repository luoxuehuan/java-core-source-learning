package com.java.net.tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientDemo {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		/*
		 * Tcp传输，客户端建立的过程。
		 * 1.创建tcp客户端socket服务。使用的是Socket对象。
		 * 	建议该对象以创建就明确目的地。要连接的主机。
		 * 2.如果连接建立成，说明数据传输通道已建立。
		 * 	该通道就是socket流。
		 * 
		 * 	Socket流是底层建立好的。说明这里既有输入，又有输出。
		 * 	想要输入输出流对象，可以找Socket来获取。
		 * 	可以通过getInputStream() 返回此套接字的输入流。
		 * 	getOutputStream() 返回此套接字的输出流。
		 * 
		 * 3.使用输出流。将数据写出。	
		 * 4.关闭资源。
		 */
		
		/**
		 * 创建客户端socket服务。
		 */
		Socket socket = new Socket("127.0.0.1",9999);
		
		/**
		 * 获取socket流中的输出流。
		 */
		OutputStream out = socket.getOutputStream();
		
		/**
		 * 使用输出流将指定的数据写出去
		 */
		out.write("hadoop".getBytes());
		
		//关闭资源（其实是将连接断开）
		socket.close();
		
		
		
		
		
		
		
	}

}
