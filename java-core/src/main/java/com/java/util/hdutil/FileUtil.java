package com.java.util.hdutil;

import java.io.File;

/**
 * 文件操作工具类
 * 
 * @author zhe
 * @version 1.0
 */
public class FileUtil {
	/**
	 *递归 删除某一目录下的所有文件和文件夹
	 * 
	 * @param dir
	 *            目录 或文件
	 */
	public static void deleteFile(File dir) {
		if (!dir.exists()) {
			return;
		}
		if (dir.isDirectory()) {
			for (File file : dir.listFiles()) {
				deleteFile(file);
			}
			dir.delete();
		} else if (dir.isFile()) {
			dir.delete();
		}
	}

	public static void main(String[] args) {
		// FileUtil.deleteFile(new File("C:\\Users\\zhe\\Desktop\\test"));
		File dir = new File("C:\\Users\\zhe\\Desktop\\test");
		for (String name : dir.list()) {
			System.out.println(name);
		}
	}
}
