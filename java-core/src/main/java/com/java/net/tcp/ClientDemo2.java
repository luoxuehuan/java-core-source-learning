package com.java.net.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientDemo2 {

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
		
	
		//Socket socket = new Socket("127.0.0.1",10002);
		Socket socket = new Socket("ds101.navroom.com",88);

		OutputStream out = socket.getOutputStream();

		String msg = "NDTP/1.0 HELLO\n" +
				"Accept-Encoding: gzip, deflate\n" +
				"Content-Length: 7\n" +
				"pid=123";
	
		out.write(msg.getBytes());
		
		
		//获取服务端返回的数据，使用socket读取流。
		InputStream in = socket.getInputStream();
		
		//缓冲区数组 buf
		byte[] buf = new byte[1024];
		 
		//从输入流中读取一定数量的字节，并将其存储在缓冲区数组 buf 中。
		//buf长度为0 返回0;	If the length of b is zero, then no bytes are read and 0 is returned;
		//读到末尾返回-1;	If no byte is available because the stream is at the end of the file, the value -1 is returned;
		int len = in.read(buf);
		
		String text = new String(buf,0,len);
		
		System.out.println(":"+text);
		
		
		//关闭资源（其实是将连接断开）
		socket.close();
		
		
		
		
		
		
		
	}

}
