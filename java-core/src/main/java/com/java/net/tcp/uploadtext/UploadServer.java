package com.java.net.tcp.uploadtext;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class UploadServer {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		
		ServerSocket ss = new ServerSocket(10005);
		
		Socket s = ss.accept();
		
		BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
		
		BufferedWriter bufw = new BufferedWriter(new FileWriter("server.txt"));
		
		String line  = null;
		
		
		/**
		 * 写完了数据，但没拿到结束标记。造成阻塞。
		 * 
		 * 错误原因：阻塞式方法缺少结束标记
		 */
		while((line=bufIn.readLine())!=null){
			if("over".equals(line)){
				break;
			}
			bufw.write(line);
			bufw.newLine();
			/*
			 * 缓存区没刷新怎么会有数据呢？默认换从去 8k 8092字节。
			 * 源文件11k---- 生成的文件只有8k
			 * 
			 * 
			 */
			bufw.flush();
		}
		
		/**
		 *知道源和目的就行了。
		 */
		PrintWriter out = new PrintWriter(s.getOutputStream(),true);
		
		out.println("上传成");
		
		s.close();
		ss.close();
		
	}

}
