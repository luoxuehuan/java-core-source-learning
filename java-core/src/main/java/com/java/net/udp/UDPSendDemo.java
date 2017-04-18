package com.java.net.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSendDemo {

	public static void main(String[] args) throws IOException {
		
		System.out.println("发送端启动");//没有启动顺序。面向服务连接，不可靠，速度快。（无连接，你爱在不在，你爱收不收。如果是演示的话，先启动接收端）
		
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		//1.udpsocket服务。使用DatagramSocket对象。
		DatagramSocket ds = new DatagramSocket();
		
		//String str = "udp传输演示：哥们来了！";
		BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
		
		String line = null;
		while((line=bufr.readLine())!=null){
			
			
			byte[] buf = line.getBytes();
			
			DatagramPacket dp = 	
					new DatagramPacket(buf, buf.length,InetAddress.getByName("127.0.0.1"),9999);
			ds.send(dp);
			
			if("886".equals(line)){
				break;
			}
			
		}
		//使用DatagramPacket将数据封装到该对象中。
		//byte[] buf = str.getBytes();
		
		
		//通过udp的socket服务奖数据包发送出去。使用send方法。
		
		//关闭资源
		ds.close();
		
	}

}
