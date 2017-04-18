package com.java.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPReceDemo {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("接收端启动");
		/*
		 * 建立UDP接受端的思路
		 * 1,建立udp socket服务
		 * 2,创建数据包，用于存储接收到的数据。方便用数据包对象的方法解析这些数据。
		 * 3,用socket服务的receive方法将接受的数据存储到数据包中。
		 * 4,通过数据包的方法解析数据包中的类。
		 */
		
		
		//1,建立udp socket服务
		DatagramSocket ds = new DatagramSocket(9999);
		
		while(true){
			//2,创建数据包，用于存储接收到的数据。方便用数据包对象的方法解析这些数据。                    
			byte[] buf = new byte[1024];
			DatagramPacket dp = new DatagramPacket(buf,buf.length);
			
			//3.使用接收方法将数据存储到数据包中。
			ds.receive(dp);//阻塞式的。
			
			//4.通过数据包对象的方法，解析其中的数据，比如，地址，端口数据内容。
			String ip = dp.getAddress().getHostAddress();
			int port = dp.getPort();
			String text = new String(dp.getData(),0,dp.getLength());
			
			System.out.println(ip+":"+port+":"+text);
		}
		//ds.close();
	}

}
