package com.java.net.tcp.uploadpic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class UploadPicServer {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket ss =  new ServerSocket();
		while(true){
			//获取客户端。
			Socket s =ss.accept();
			
			/**
			 * 这里可以多个人同时读文件。
			 */
			new Thread(new UploadTask(s)).start();
		}
		
/*		Socket s =ss.accept();
		
		String ip  = s.getInetAddress().getHostAddress();
		System.out.println(ip+"..........");
		
		//读取数据
		InputStream in = s.getInputStream();
		
		//将读取的数据存储到一个文件中。
		File dir = new File("c:\\pic");
		if(!dir.exists()){
			dir.mkdir();
		}
		
		File file  = new File(dir,ip+".bmp");
		FileOutputStream fos = new FileOutputStream(file);
		
		byte[] buf = new byte[1024];
		
		int len = 0;
		while((len=in.read(buf))!=-1){
			fos.write(buf, 0, len);
		}
		
		OutputStream out = s.getOutputStream();
		out.write("上传成功".getBytes());
		
		fos.close();
		s.close();
		ss.close();*/
		
		
	}

}
