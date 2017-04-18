package com.java.util.hdutil;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 遍历包下所有类
 * 
 * @author sunfeng
 *
 */
public class PackageUtil {

	private static final String FILE_TYPE = "file";

	private static final String JAR_TYPE = "jar";

	/**
	 * 获取指定包名下的所有类
	 * 
	 * @param packageName
	 *            包名
	 * @return 类
	 */
	public static List<Class<?>> getClass(String packageName) {
		return getClass(packageName, true);
	}

	/**
	 * 获取指定包名下的所有类
	 * 
	 * @param packageName
	 *            包名
	 * @param recursive
	 *            是否迭代子包，true迭代，false不迭代
	 * @return 类
	 */
	public static List<Class<?>> getClass(String packageName, boolean recursive) {

		List<Class<?>> classList = new ArrayList<Class<?>>();

		// 替换包名
		String packageDirName = packageName.replace('.', '/');

		// 定义一个枚举的集合 并进行循环来处理这个目录下的things
		Enumeration<URL> dirs = null;
		try {
			// 得到路径下的资源
			dirs = Thread.currentThread().getContextClassLoader()
					.getResources(packageDirName);
			while (dirs.hasMoreElements()) {
				// 获取下一个元素
				URL url = dirs.nextElement();
				// 得到协议的名称
				String protocol = url.getProtocol();
				// 如果是以文件的形式保存在服务器上
				if (FILE_TYPE.equals(protocol)) {
					// 获取包的物理路径
					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
					getClassByFile(filePath, packageName, recursive, classList);
				}
				if (JAR_TYPE.equals(protocol)) {// 如果是jar包文件
					JarFile jar = ((JarURLConnection) url.openConnection())
							.getJarFile();
					getClassByJar(jar, packageDirName, packageName, recursive,
							classList);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return classList;
	}

	/**
	 * 文件形式中的所有类
	 * 
	 * @param packagePath
	 *            包路径
	 * @param packageName
	 *            包名
	 * @param recursive
	 *            是否循环迭代子包
	 * @param classList
	 *            获取到的类集合
	 */
	private static void getClassByFile(String packagePath, String packageName,
			final boolean recursive, List<Class<?>> classList) {
		// 得到包的目录生成File
		File dir = new File(packagePath);
		// 如果不存在或者也不是目录就直接返回
		if (!dir.exists() || !dir.isDirectory()) {
			return;
		}
		// 如果存在 就获取包下的所有文件 包括目录
		File[] dirfiles = dir.listFiles(new FileFilter() {
			// 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
			public boolean accept(File file) {
				return (recursive && file.isDirectory())
						|| (file.getName().endsWith(".class"));
			}
		});
		// 循环所有文件
		for (File file : dirfiles) {
			// 如果是目录 则继续扫描
			if (file.isDirectory()) {
				getClassByFile(file.getAbsolutePath(),
						packageName + "." + file.getName(), recursive,
						classList);
			} else {
				// 如果是java类文件 去掉后面的.class 只留下类名
				String className = file.getName().substring(0,
						file.getName().length() - 6);
				try {
					// 添加到集合中去
					// classes.add(Class.forName(packageName + '.' +
					// className));
					// 这里用forName有一些不好，会触发static方法，没有使用classLoader的load干净
					classList.add(Thread.currentThread()
							.getContextClassLoader()
							.loadClass(packageName + '.' + className));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * jar包形式获取所有类
	 * 
	 * @param jar
	 *            jar文件
	 * @param packageDirName
	 *            包路径名
	 * @param packageName
	 *            包名
	 * @param recursive
	 *            是否循环迭代子包
	 * @param classList
	 *            获取到的类集合
	 */
	private static void getClassByJar(JarFile jar, String packageDirName,
			String packageName, boolean recursive, List<Class<?>> classList) {

		// 从此jar包 得到一个枚举类
		Enumeration<JarEntry> entries = jar.entries();
		// 同样的进行循环迭代
		while (entries.hasMoreElements()) {
			// 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
			JarEntry entry = entries.nextElement();
			String name = entry.getName();
			// 如果是以/开头的
			if (name.charAt(0) == '/') {
				// 获取后面的字符串
				name = name.substring(1);
			}
			// 如果前半部分和定义的包名相同
			if (name.startsWith(packageDirName)) {
				int idx = name.lastIndexOf('/');
				// 如果以"/"结尾 是一个包
				if (idx != -1) {
					// 获取包名 把"/"替换成"."
					packageName = name.substring(0, idx).replace('/', '.');
				}
				// 如果可以迭代下去 并且是一个包
				if ((idx != -1) || recursive) {
					// 如果是一个.class文件 而且不是目录
					if (name.endsWith(".class") && !entry.isDirectory()) {
						// 去掉后面的".class" 获取真正的类名
						String className = name.substring(
								packageName.length() + 1, name.length() - 6);
						try {
							// 添加到classes
							// classList.add(Class.forName(packageName + '.'
							// + className));
							classList.add(Thread.currentThread()
									.getContextClassLoader()
									.loadClass(packageName + '.' + className));
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
}
