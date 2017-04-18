package com.java.io_nio.io.readerwriter;

import java.io.FileWriter;
import java.io.IOException;

/**
 * 字符流 FileWrite
 * @author lxh
 *
 */
public class FileWriterDemo {

	private static final String LINE_SEPARTOR = System.getProperty("line.separator");

	public static void main(String[] args){
		
		/**
		 * IOE异常处理。
		 * 1.在try外面声明fw
		 * 2.在try里面进行对象初始化
		 * 3.在finally里面处理,必须先判断fw不为空。再close
		 */
		
		FileWriter fw = null;
		//创建一个可以往文件中写入字符数据的字符输出流对象。
		
		
	
		/*
		 * 调用Writer对象的write(string)方法，写入数据。
		 * 
		 * 数据被写入到临时存储缓冲区中。
		 * 那该怎么办？去爹那找方法abstract  void flush()  刷新该流的缓冲。 
		 * 
		 * write写入后,要进行flush刷新，将数据直接写到目的地。
		 */
		
		try {
			/*
			 * 既然是往一个文件写入
			 * 那么在创建对象时，就必须明确改文件（用于存储数据的目的地）。
			 * 
			 * 如果文件不存在，则会自动创建。
			 * 如果文件存在，则会被覆盖。
			 * 
			 * IOException怎么解决？
			 * 
			 * 如果构造函数加入true 可以实现对文件进行续写
			 */
			fw = new FileWriter("E:\\io\\test.txt", true);
			fw.write("abcde\r\nhhhhhhh");//如果用记事本txt解析【\n】解析不出来
			/**
			 * 这种的比较标准。拿系统的换行。
			 */
			fw.write("abcde" + LINE_SEPARTOR + "nhhhhhhh");//如果用记事本txt解析【\n】解析不出来
			fw.append("ddd");
			//fw.flush();//刷新
		}catch(IOException e){
			System.out.println(e.toString());
		}finally {
			/**
			 * 一定要判断不等于空
			 */
			if(fw!=null){
				try {
					/*
					 * 因为用了windows的资源，需要关闭。
					 * 
					 * 如果直接调用close  。必须先刷新！（会自动刷新！flush()）。比如关闭word的时候提醒需要先保存。
					 * 关闭之后就不能再flush了。
					 */
					fw.close();
				} catch (Exception e) {
					System.out.println(e.toString());
				}
			}
			
		}
	}
}
