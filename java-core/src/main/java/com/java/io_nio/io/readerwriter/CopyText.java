package com.java.io_nio.io.readerwriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopyText {

	private static final int BUFFER_SIZE = 1024;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileReader fr = new FileReader("E:\\io\\test.txt");
		FileWriter fw = new FileWriter("E:\\io2\\test.txt");
		
		int len = 0;
		char[] ch = new char[BUFFER_SIZE];
		
		/**
		 * 复制的原理：连读带写！
		 * 
		 * 循环次数少。
		 * 读了一大片，才写。
		 */
		while((len = fr.read(ch))!=-1){
			fw.write(new String(ch,0,len));
		}
		
		fw.flush();
		fr.close();
		fw.close();
		
		
		/**
		 * 改成处理exception形式
		 * 1.申明对象
		 * 2.初始化对象
		 * 3.finally里面关闭。关闭之前判断是否为空
		 */
	}

}
