package com.java.net.udp;

import java.net.DatagramSocket;
import java.net.SocketException;

public class ChatDemo {

	public static void main(String[] args) throws SocketException {
		// TODO Auto-generated method stub
		
		/*
		 * udp的socket既能发送，又能接收！
		 * 
		 */
		DatagramSocket send = new DatagramSocket();
		DatagramSocket rece = new DatagramSocket(10000);
		Send s = new Send(send);
		Rece r = new Rece(rece);
		new Thread(s);
		new Thread(r);
		
	}

}
