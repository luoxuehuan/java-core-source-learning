package com.java.util.hdutil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密
 * 
 * @author lexond
 * @version 1.0
 */
public class MD5 {

	/**
	 * 将字符串进行MD5加密
	 * 
	 * @param str
	 *            需要加密的字符串
	 * @return 返回加密的字符串
	 */
	public static String md5Digest(String str) {

		MessageDigest md;
		try {

			// 利用MD5码进行加密
			md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte[] digesta = md.digest();

			StringBuffer sb = new StringBuffer();
			for (int n = 0; n < digesta.length; n++) {
				String stmp = (Integer.toHexString(digesta[n] & 0XFF));
				sb.append(stmp);
			}

			return sb.toString().toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

}