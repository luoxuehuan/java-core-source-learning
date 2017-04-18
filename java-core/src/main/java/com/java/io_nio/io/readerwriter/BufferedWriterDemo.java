package com.java.io_nio.io.readerwriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedWriterDemo {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileWriter fw = new FileWriter("E:\\io\test.txt");
		
		/*
		 * 为了提高写入效率，使用字符流的缓冲区。
		 * 创建了一个字符写入流的缓冲区对象，并和指定要被缓冲的流对象相关联。
		 * 
		 * 把 商品放在手推车里面。
		 */
		BufferedWriter bufw = new BufferedWriter(fw);
		
		/**
		 * 既然 写入流被封装到缓冲区对象了。那 就用缓冲区的写法写入数据。
		 * 
		 * 以后一律使用缓冲区的方法。
		 */
		bufw.write("abcd\r\nef");
		bufw.newLine();
		bufw.flush();
		
		/*
		 * 其实底层关闭的就是fw
		 * 
		 * 一定要关资源。
		 */
		bufw.close();
	}

}
