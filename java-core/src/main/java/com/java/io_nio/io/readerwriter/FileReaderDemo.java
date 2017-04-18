package com.java.io_nio.io.readerwriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 字符流 FileReader
 * @author lxh
 *
 */
public class FileReaderDemo {

	private static final String LINE_SEPARTOR = System.getProperty("line.separator");

	public static void main(String[] args){

		FileReader fr = null;
		try {
			fr = new FileReader("E:\\io\\test.txt");
			
			
			/**
			 * 一次读一个。
			 */
			int ch  = 0;
			
			//int len = fr.read();//读 的不是a 是二进制  打印出来是97。读到b 就是98
			//char[] str = null;
			while((ch=fr.read())!=-1){
				//fr.read(str, 0, len);
				System.out.print((char)ch);
			}
			//System.out.println((char)len);
		
		}catch(IOException e){
			System.out.println(e.toString());
		}finally {
			if(fr!=null){
				try {
					fr.close();
				} catch (Exception e) {
					System.out.println(e.toString());
				}
			}
			
		}
	}
}
