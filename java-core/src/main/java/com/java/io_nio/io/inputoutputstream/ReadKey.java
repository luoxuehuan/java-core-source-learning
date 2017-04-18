package com.java.io_nio.io.inputoutputstream;

import java.io.IOException;
import java.io.InputStream;

/**
 * 键盘本身就是一个标准的 输入设备。
 * 对于java而言，对于这种输入设备都有对应的对象。
 * 描述系统类。
 * @author lxh
 *
 */
public class ReadKey {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		readKey2();
	}

	private static void readKey() throws IOException {
		// TODO Auto-generated method stub
		InputStream in = System.in;
		
		int ch = 0;//in.read()是堵塞式方法
		while((ch = in.read())!=-1){
			System.out.print((char)ch);
		}
	}
	
	private static void readOne() throws IOException {
		// TODO Auto-generated method stub
		InputStream in = System.in;
		
		int ch = in.read();//  windows里面换行。。。。\r \n 。。。。输入一个a 然后回车，是 3个字节。
		
		System.out.print(ch);
		
		/*
		 * 为什么不关流。in  in2  其实in       只有一个System.in。
		 * 随着系统消失而消失。
		 */
		
	}

	/*
	 * 一个中文2个字节。
	 * 
	 * 1.因为键盘录入只读取一个字节。
	 */
	private static void readKey2() throws IOException {
		// TODO Auto-generated method stub
		InputStream in = System.in;
		Byte[] buf = new Byte[1024];
		
		int ch = 0;//in.read()是堵塞式方法
		while((ch = in.read())!=-1){
			if(true)
			System.out.print((char)ch);
		}
	}
}
