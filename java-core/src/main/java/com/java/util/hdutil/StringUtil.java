package com.java.util.hdutil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.UUID;

/**
 * 用于对字符串做的一些操作
 * 
 * @author lexond && sunfneg
 * @version 1.0
 */
public class StringUtil {

	/**
	 * 将字符串按照指定编码方式编码
	 * 
	 * @param source
	 *            需要编码的字符串
	 * @param oldEncode
	 *            原始编码
	 * @param newEncode
	 *            新编码
	 * @return 转码后的字符串
	 */
	public static String encodeString(String source, String oldEncode,
			String newEncode) {
		if (isNull(source)) {
			return source;
		}
		try {
			String newString = new String(source.getBytes(oldEncode), newEncode);
			return newString;
		} catch (UnsupportedEncodingException e) {
			return source;
		}
	}

	/**
	 * 将字符串按照指定编码方式编码，此方法的原始编码是iso-8859-1，新编码是utf-8
	 * 
	 * @param source
	 *            需要编码的字符串
	 * @return 编码后的字符串
	 */
	public static String encodeString(String source) {

		String oldEncode = "iso-8859-1";
		String newEncode = "utf-8";

		return encodeString(source, oldEncode, newEncode);
	}

	/**
	 * 将字节流转化为UTF-8字符格式
	 * 
	 * @param is
	 *            字节流
	 * @return 字符串格式的字节流
	 */
	public static String getStringFromInputStream(InputStream is) {
		if (is == null) {
			return null;
		}
		String encode = "utf-8";
		String content = null;
		try {
			byte[] contentByte = new byte[is.available()];
			is.read(contentByte);
			content = new String(contentByte, encode);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("将InputStream转化成String失败!");
		}

		return content;
	}

	/**
	 * 根据level级别,返回空格
	 * 
	 * @param levelStr
	 *            level级别
	 * @return 对应的空格
	 */
	public static String getBlankStrByLevel(String levelStr) {
		final String BLANK_HTML = "&nbsp;";
		StringBuffer sb = new StringBuffer();
		int level = Integer.parseInt(levelStr);
		for (int i = 0; i < level; i++) {
			sb.append(BLANK_HTML);
			sb.append(BLANK_HTML);
			sb.append(BLANK_HTML);
			sb.append(BLANK_HTML);
		}
		return sb.toString();
	}

	/**
	 * 分隔字符串，根据指定的分隔符分割字符串
	 * 
	 * @param str
	 *            需要分割的字符串
	 * @param split
	 *            字符串的分隔符号
	 * @return 返回分隔的字符
	 */
	public static String[] splitString(String str, String split) {
		return str.split(split);
	}

	/**
	 * 分隔字符串(默认分隔符为&)
	 * 
	 * @param str
	 *            需要分割的字符串
	 * @return 返回分隔的字符
	 */
	public static String[] splitString(String str) {

		// 默认使用的分割符号
		final String splitStirng = "&";
		return str.split(splitStirng);
	}

	/**
	 * 得到对象对应的字符串结构
	 * 
	 * @param o
	 *            对象
	 * @return 如果对象为NULL，则返回空字符串
	 */
	public static String getStringFromObject(Object o) {

		if (o == null)
			return "";

		return o.toString();

	}

	/**
	 * 判断字符是否是数字 true--是 false--否 ，0-9
	 * 
	 * @param c
	 *            字符
	 * @return 如果是数字则返回true，如果不是则返回false
	 */
	public static boolean isDigital(char c) {
		if (Character.isDigit(c)) {
			return true;
		}
		return false;
	}

	/**
	 * 去除字符串中的空格(全角和半角)
	 * 
	 * @param str
	 *            字符串
	 * @return 去除空格后的字符串
	 */
	public static String trim(String str) {
		return str.replaceAll("\\s", "").replaceAll("　", "");
	}

	/**
	 * 判断对象是否为空
	 * 
	 * @param o
	 *            对象
	 * @return 如果为空则返回true，如果不为空则返回false
	 */
	public static boolean isNull(Object o) {

		if (o != null && !o.toString().equals("")) {
			return false;
		}

		return true;
	}

	/**
	 * 生成唯一编码，利用时间戳生成编码（形如"CODE_20110101141212"）
	 * 
	 * @param code
	 *            编码
	 * @return 生成的编码，编码格式CODE_20110101141212
	 */
	public static String getCode(String code) {
		String nowTime = TimeUtil.formatDate(new Date(), "yyyyMMddHHmmss");
		if (StringUtil.isNull(code)) {
			code = "CODE";
		}
		return code + "_" + nowTime;
	}

	/**
	 * 生成UUID编码
	 * 
	 * @return UUID编码
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		// 去掉“-”符号
		return uuid.replaceAll("-", "");
	}

	/**
	 * 将CLOB转成字符串
	 * 
	 * @param object
	 *            需要转化的流对象
	 * @return 转换后的字符串
	 */
	public static String clobToString(Object object) {
		if (StringUtil.isNull(object)) {
			return "";
		}
		Reader reader = (Reader) object;
		BufferedReader br = new BufferedReader(reader);
		StringBuffer sb = new StringBuffer();
		String s = null;
		try {
			s = br.readLine();
			while (s != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
				sb.append(s);
				s = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String reString = sb.toString();
		return reString;
	}

}
