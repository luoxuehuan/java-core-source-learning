package com.java.util.hdutil;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * 属性文件解析类
 * 
 * @author sunfeng
 * @version 1.0
 */
public class PropertiesOperate {

	/**
	 * 属性文件集合
	 */
	private static Map<Object, Properties> propMap = new HashMap<Object, Properties>();

	/**
	 * 根据属性文件名获取属性文件（属性文件存放在类路径下）
	 * <p>
	 * <b>重要：如果每次调用时都需要重新读取配置文件的话，则需要先清空属性文件Map，方法为：PropertiesOperate.
	 * clearPropMap()</b>
	 * </p>
	 * 
	 * @param propFileName
	 *            属性文件名,****.properties文件名（如果在src下面那么直接传递属性文件名就可以，如果不是在src下
	 *            则传递来的参数是包含路径的属性文件名）
	 * @return 属性文件对象
	 */
	public static Properties getProperties(String propFileName) {
		if (propFileName == null) {
			return null;
		}
		if (propMap.containsKey(propFileName)) {
			return propMap.get(propFileName);
		}
		Properties props = new Properties();
		try {
			// 获取类路径下的属性文件按
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			props.load(loader.getResourceAsStream(propFileName));
			propMap.put(propFileName, props);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return props;
	}

	/**
	 * 根据属性文件名获取属性文件（属性文件放在指定的路径下,不在类路径目录下） *
	 * <p>
	 * <b>重要：如果每次调用时都需要重新读取配置文件的话，则需要先清空属性文件Map，方法为：PropertiesOperate.
	 * clearPropMap()</b>
	 * </p>
	 * 
	 * @param path
	 *            属性文件路径
	 * @param propFileName
	 *            属性文件名,****.properties文件名
	 * @return 属性文件对象
	 */
	public static Properties getProperties(String path, String propFileName) {
		if (propFileName == null || path == null) {
			return null;
		}
		if (propMap.containsKey(propFileName)) {
			return propMap.get(propFileName);
		}
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(path + "/" + propFileName));
			propMap.put(propFileName, props);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return props;
	}

	/**
	 * 获取指定 Properties文件里的内容
	 * <p>
	 * <b>重要：如果每次调用时都需要重新读取配置文件的话，则需要先清空属性文件Map，方法为：PropertiesOperate.
	 * clearPropMap()</b>
	 * </p>
	 * 
	 * @param propName
	 *            ****.properties文件名，属性文件名,****.properties文件名（
	 *            如果在src下面那么直接传递属性文件名就可以，如果不是在src下，则传递来的参数是包含路径的属性文件名）
	 * @param key
	 *            properties文件里面的key
	 * @return 返回内容
	 */
	public static String getEntryValue(String propName, String key) {

		Properties prop = getProperties(propName);
		if (prop != null) {
			return prop.getProperty(key);
		} else {
			return null;
		}
	}

	/**
	 * 获取指定 Properties文件里的内容（属性文件放在指定的路径下,不在类路径目录下）
	 * <p>
	 * <b>重要：如果每次调用时都需要重新读取配置文件的话，则需要先清空属性文件Map，方法为：PropertiesOperate.
	 * clearPropMap()</b>
	 * </p>
	 * 
	 * @param path
	 *            属性文件路径
	 * @param propName
	 *            ****.properties文件名
	 * @param key
	 *            properties文件里面的key
	 * @return 返回内容
	 */
	public static String getEntryValue(String path, String propName, String key) {

		Properties prop = getProperties(path, propName);
		if (prop != null) {
			return prop.getProperty(key);
		} else {
			return null;
		}
	}

	/**
	 * 解析属性文件,得到属性文件中所有的键值对内容
	 * <p>
	 * <b>重要：如果每次调用时都需要重新读取配置文件的话，则需要先清空属性文件Map，方法为：PropertiesOperate.
	 * clearPropMap()</b>
	 * </p>
	 * 
	 * @param propName
	 *            属性文件名，****.properties文件名（如果在src下面那么直接传递属性文件名就可以，
	 *            如果不是在src下，则传递来的参数是包含路径的属性文件名）
	 * @return Map<String, String>
	 */
	public static Map<String, String> parseProperties(String propName) {
		Map<String, String> map = new HashMap<String, String>();
		Properties properties = getProperties(propName);
		for (Iterator<Map.Entry<Object, Object>> it = properties.entrySet()
				.iterator(); it.hasNext();) {
			Map.Entry<Object, Object> entry = it.next();
			Object key = entry.getKey();
			Object value = entry.getValue();
			map.put(key.toString(), value.toString());
		}
		return map;
	}

	/**
	 * 解析属性文件，得到属性文件中所有的键值对内容（属性文件放在指定的路径下,不在类路径目录下）
	 * <p>
	 * <b>重要：如果每次调用时都需要重新读取配置文件的话，则需要先清空属性文件Map，方法为：PropertiesOperate.
	 * clearPropMap()</b>
	 * </p>
	 * 
	 * @param path
	 *            属性文件路径
	 * @param propName
	 *            属性文件名
	 * @return Map<String, String>
	 */
	public static Map<String, String> parseProperties(String path,
			String propName) {
		Map<String, String> map = new HashMap<String, String>();
		Properties properties = getProperties(path, propName);
		for (Iterator<Map.Entry<Object, Object>> it = properties.entrySet()
				.iterator(); it.hasNext();) {
			Map.Entry<Object, Object> entry = it.next();
			Object key = entry.getKey();
			Object value = entry.getValue();
			map.put(key.toString(), value.toString());
		}
		return map;
	}

	/**
	 * 清空存放属性文件缓存的Map
	 */
	public static void clearPropMap() {
		propMap.clear();
	}

}
