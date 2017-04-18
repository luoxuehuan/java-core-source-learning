package com.java.util.hdutil;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Properties;
/**
 * web程序获取路径工具类
 * @author zhe
 * @version 1.0
 */
public class PathUtil {

	/**
	 * 获取web-info目录
	 * @return web-info绝对路径
	 */
	public static String getWEBINFDir() {
		String path = null;
		try {
			path = PathUtil.class.getResource("").toURI().getPath();
			path = path.substring(0, path.indexOf("classes"));
			return path;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取WebRoot目录的绝对路径
	 * @return WebRoot目录的绝对路径
	 */
	public static String getWebRootDir() {
		String path = null;
		String folderPath = PathUtil.class.getClassLoader()
				.getResource("").getPath();
		if (folderPath.indexOf("WEB-INF") > 0) {
			path = folderPath.substring(0,
					folderPath.indexOf("WEB-INF/classes"));
		} else if (folderPath.indexOf("build") > 0) {
			path = folderPath.substring(0, folderPath.indexOf("build/classes"));
		}
		if(null!=path && path.indexOf("%20")>=0){
			path = path.replaceAll("%20", " ");
		}
		return path;
	}

	/**
	 * 获取webroot下的某个路径
	 * @param args webroot的分支目录
	 * @return webroot下的某个分钟目录的绝对路径
	 */
	public static String getWebRootDirFilePath(String dir) {
		String path = getWebRootDir() + dir;
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		return path;
	}

	/**
	 * 获取WEB-INF下的某个路径
	 * @param args WEB-INF的分支目录
	 * @return WEB-INF下的某个分钟目录的绝对路径
	 */
	public static String getWebInfoDirFilePath(String dir) {
		String path = getWEBINFDir() + dir;
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		return path;
	}
	/**
	 * 判断当前是否为linux操作系统：返回true时，为linux 系统 ；若返回false，则为windows 系统 
	 * 
	 * @return 是否为linux系统
	 * @see [类、类#方法、类#成员]
	 */
	public static Boolean isLinux() {
		boolean isLinux = true;
		Properties prop = System.getProperties();
		String os = prop.getProperty("os.name");
		if (os.startsWith("win") || os.startsWith("Win")) {
			isLinux = false;
		}
		return isLinux;
	}
}
