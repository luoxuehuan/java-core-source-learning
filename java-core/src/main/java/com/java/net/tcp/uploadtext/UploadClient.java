package com.java.net.tcp.uploadtext;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class UploadClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		Socket s = new Socket("127.0.0.1",10005);
		
		BufferedReader bufr = new BufferedReader(new FileReader("client.txt"));
		PrintWriter out = new PrintWriter(s.getOutputStream(),true);
		
		String line = null;
		
		/**
		 * 客户端正常读取完毕
		 */
		while((line=bufr.readLine())!=null){
			out.println(line);
		}
		
		/**
		 * 普通解决:
		 * 增加一个结束标记。
		 * 隐患：文件中间有个over咋办？
		 * 改成clinet_over_tag
		 * 或者@#￥#@……%……&
		 * 
		 * 另类解决：
		 * 改成：发2次时间戳！
		 * 1.先发一个时间
		 * 2.作为结束标记
		 * 3.最后再发一个，正式结束。
		 * 
		 * socket内置解决：
		 * s.shutdownOutput();
		 * 
		 */
		out.println("over");
		s.shutdownOutput();
		
		//4.读取服务器返回的socket流，上传成功。
		BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
		
		/**
		 * 阻塞式  服务端未返回数据
		 */
		String str = bufIn.readLine();
		System.out.println(str);
		
		bufr.close();
		s.close();
	}

}
