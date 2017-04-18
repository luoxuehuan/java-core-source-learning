package com.java.io_nio.io.readerwriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 字符流 FileReader
 * @author lxh
 *
 */
public class FileReaderDemo2 {

	private static final String LINE_SEPARTOR = System.getProperty("line.separator");

	public static void main(String[] args){

		FileReader fr = null;
		try {
			fr = new FileReader("E:\\io\\test.txt");
			
			/**
			 * 数据为abcde
			 * 
			 * 如果缓冲区 长度不够的话。。。。
			 * 第一次读取 原来为abc ch =3
			 * 
			 * 第二次会变成  dec    ab被de取代。 ch =2
			 *    
			 * 第三次还是dec ch = -1因为读取完毕了。
			 * 
			 * 输出dec
			 */
			char[] buf = new char[10];
			/*
			 * 讲读到的字符存储到数组中
			 */
			//int ch  = fr.read(buf);
			//System.out.println(buf);
			
			//int ch2  = fr.read(buf);
			//System.out.println(buf);
			
			int len = 0;
			
			/**
			 * read 一次读 1个。
			 * 
			 * 磁盘 寻道的 是时候 取 肯定 是1 个 1个取的。
			 * 
			 * 但是取 1个 取 1个 取 1个 放在 杯子（数组）里。
			 * 然后一起倒出来。
			 * 
			 * 如果原文件字符个数为9  buf 为 5.读2此就搞定了！
			 * buf为10 ，读一次就搞定了！
			 */
			while((len = fr.read(buf))!=-1){
				System.out.println("啊啊"+new String(buf,0,len));
			}
			
			/**
			 * 读到  几个  打印几个！！！
			 */
			//System.out.println( new String(buf,0,ch));
		
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
