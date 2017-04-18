package com.java.util.hdutil;

import java.math.BigDecimal;

/**
 * 
 * 用于格式化数字
 * 
 * @author lexond
 * @version 1.0
 */
public class NumberUtil {

	/**
	 * 保留两位有效数字
	 * 
	 * @param value
	 *            值
	 * @return 保留后的值
	 */
	public static double halfUpDouble(double value) {

		BigDecimal d = new BigDecimal(value);
		d = d.setScale(2, BigDecimal.ROUND_HALF_UP);
		return d.doubleValue();

	}

	/**
	 * 将字符串转化成double类型
	 * 
	 * @param str
	 *            字符串表示的值
	 * @return str代表的double值，如果发生错误返回0
	 */
	public static double parseStingToDouble(String str) {

		try {
			return Double.parseDouble(str);
		} catch (Exception e) {
			return 0;
		}

	}

	/**
	 * 将double转成BigDecimal，防止出现科学计算法
	 * 
	 * @param value
	 *            值
	 * @return 保留后的值
	 */
	public static Object doubleToDecimal(Object value) {
		if (StringUtil.isNull(value)) {
			return "";
		}
		if (value.toString().contains("E")) {
			BigDecimal d = new BigDecimal(value.toString());
			return d;
		} else {
			return value;
		}
	}
}
