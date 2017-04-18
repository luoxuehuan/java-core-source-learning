package com.java.io_nio.io.readerwriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedReaderDemo {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileReader fr = new FileReader("E:\\io\\test.txt");
		
		/**
		 * 缓冲区里一定要有缓存的对象。
		 * 
		 * 从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组
		 * 
		 * 和【行】的高效读取。
		 * 
		 * 可见：是 文本特有的！！！
		 */
		BufferedReader bufr = new BufferedReader(fr);
		
		FileWriter fw = new FileWriter("E:\\io2\\test.txt");
		BufferedWriter bufw  = new BufferedWriter(fw);
		
		int len;
		
		/*
		 * 可以一行一行得读
		 * 
		 * (读一行写一行)
		 * 
		 * 读到结尾处 判断是否为 -1
		 */
		String line = null;
		while((line = bufr.readLine())!=null){
			System.out.println(line);
			bufw.write(line);
			/**
			 * 注意！！！要自己换行！
			 */
			bufw.newLine();
			bufw.flush();
		}
		
	/*	纯复制：
	 * 
	 * 如果要判断里面有没有 java字符串呢？
	 * 
	 * int ch =0;
		while((ch=bufr.read())!=-1){
			bufw.write(ch);
		}*/
		
	}
/*
 * 原理：
 * 
 */
}



