package com.java.io_nio.io.inputoutputstream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyDemo {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//nobuffer();//没有用缓冲区。
		bufferu();//使用字节数组 1024做缓存
		
		javabuffer();//使用java提供的缓冲。
	}

	private static void nobuffer() throws IOException {
		FileInputStream fis = new FileInputStream("E:\\io2\\j8.pdf");
		FileOutputStream fos = new FileOutputStream("E:\\io2\\outj8.pdf");
		int len = 0;
		
		while((len =fis.read())!=-1){
			fos.write((char)len);  
		}
		fis.close();
		fos.close();
	}

	private static void bufferu() throws IOException {
		FileInputStream fis = new FileInputStream("E:\\io2\\j8.pdf");
		FileOutputStream fos = new FileOutputStream("E:\\io2\\outj8bytebuf.pdf");
		byte[] bu = new byte[1024];
		int len = 0;
		while((len =fis.read(bu))!=-1){
			//fos.write(bu);//不写。会出问题。abcd 缓存为3  复制2次  abc dbc   编程了abcdbc
			fos.write(bu,0,len);//写了。
			System.out.println(len);
			//要不要刷新fos.flush(); 有close 可以不用flush。
		}
		fis.close();
		fos.close();
	}
	
	
	private static void javabuffer() throws IOException {
		FileInputStream fis = new FileInputStream("E:\\io2\\j8.pdf");
		FileOutputStream fos = new FileOutputStream("E:\\io2\\javabuff.pdf");
		BufferedInputStream bufis = new BufferedInputStream(fis);
		BufferedOutputStream bufos = new BufferedOutputStream(fos);
		
		/*byte[] bu = new byte[1024];
		while(bufis.read(bu)!=-1){
			bufos.write(bu);//不需要写偏移？
			bufos.flush();
		}*/
		
		int ch = 0;
		while((ch = bufis.read())!=-1){
			bufos.write(ch);//不需要写偏移？ 因为这里是不是buf。
		}
		bufis.close();
		bufos.close();
	}
}
