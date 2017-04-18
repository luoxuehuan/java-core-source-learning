package com.java.io_nio.io.readerwriter;

import java.io.FileReader;
import java.io.IOException;

/**
 * 自定义的读取缓冲区。其实就是模拟一个 字符缓冲区。
 * 
 * @author lxh
 *
 * 分析：
 * 缓冲区无非就是封装了一个数组。
 * 并对外提供了更多的方法对数组进行访问。
 * 其实这些方法最终操作的都是数组的角标。
 * 
 * 缓冲的原理：
 *  其实就是从源中获取一批数据装进缓冲区中。
 *  在从缓存区中不断取出一个一个数据。
 *  
 *  在此次 取完后，再从源中继续取一批数据进缓冲区。
 *  当源中数据取光 时，用-1作为结束标记。
 *  
 *  但此次取完后。
 * 
 * 
 * 原缓冲区里面有啥：
 * 
 * 构造方法：
 * 
 * 
 * read()
 * 
 * readLine()
 */
public class MyBufferedReader {
	
	//定义一个数组作为缓冲区
	private char[] buf = new char[1024];
	
	//定义一个指针用于操作这个数组中的元素。当操作到最后一个元素后，指针应该归零。
	private int pos = 0;
	
	//定义一个计数器用于记录缓冲区中的数据个数。当该数据减到0，就从源中继续获取数据到缓冲区中。
	private int count = 0;
	
	private FileReader r;
	public MyBufferedReader(FileReader r){
		this.r = r	;
	}
	
	public int myRead() throws IOException{
		
		//1.从源中获取一批数据到缓冲区中。
		if(count == 0){
			count = r.read(buf);
			
			
			if(count<0){
				return -1;
			}
			//每次获取数据到缓冲区后，角标归零。
			pos = 0;
			char ch =buf[pos];
			pos++;
			count--;
			return ch;
		}else if(count>0){
			char ch =buf[pos];
			pos++;
			count--;
			return ch;
			
		}
		
		return 0;
		
		
		
	}
	
	public String myReadLine(){
		return "";
	}

}
