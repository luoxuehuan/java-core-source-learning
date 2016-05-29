package com.java.net.tcp.uploadpic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Calendar;

public class UploadTask implements Runnable {

	private Socket s;
	
	public UploadTask(Socket s){
		this.s = s;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String ip  = s.getInetAddress().getHostAddress();
		System.out.println(ip+"..........");
		
		//这里是抛不出去的。
		try {
			//读取数据
			InputStream in = s.getInputStream();
			//将读取的数据存储到一个文件中。
			File dir = new File("c:\\pic");
			if (!dir.exists()) {
				dir.mkdir();
			}
			File file = new File(dir, ip +Calendar.getInstance().getTimeInMillis()+ ".bmp");
			
			//如果文件以及存在。
			if(file.exists()){
				file = new File(dir,ip+".bmp");//重新编号。
			}
			FileOutputStream fos = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = in.read(buf)) != -1) {
				fos.write(buf, 0, len);
			}
			OutputStream out = s.getOutputStream();
			out.write("上传成功".getBytes());
			fos.close();
			s.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
