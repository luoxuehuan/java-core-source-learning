/**
 * 
 */
package cn.com.hadon.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * NumberUtil 测试用例类
 * 
 * @author sunfeng
 * @version 1.0
 */
public class TestNumberUtil {

	/**
	 * Test method for {@link cn.com.hadon.util.NumberUtil#halfUpDouble(double)}
	 * .
	 */
	@Test
	public void testHalfUpDouble() {
		double d = NumberUtil.halfUpDouble(1.2345D);
		assertEquals(1.23, d, 0.0001);
	}

	/**
	 * Test method for
	 * {@link cn.com.hadon.util.NumberUtil#parseStingToDouble(java.lang.String)}
	 * .
	 */
	@Test
	public void testParseStingToDouble() {
		double d = NumberUtil.parseStingToDouble("12.34");
		assertEquals(12.34, d, 0.0001);
	}

	/**
	 * Test Exception method for
	 * {@link cn.com.hadon.util.NumberUtil#parseStingToDouble(java.lang.String)}
	 * .
	 */
	@Test
	public void testParseStingToDoubleNull() {
		double d = NumberUtil.parseStingToDouble(null);
		assertEquals(0, d, 0.0001);
	}

	/**
	 * Test method for
	 * {@link cn.com.hadon.util.NumberUtil#doubleToDecimal(java.lang.Object)}.
	 */
	@Test
	public void testDoubleToDecimal() {
		Object o = NumberUtil.doubleToDecimal("123456789012.123");
		assertEquals(123456789012.123D, Double.parseDouble(o.toString()),
				0.00001);
	}

	/**
	 * Test method for
	 * {@link cn.com.hadon.util.NumberUtil#doubleToDecimal(java.lang.Object)}.
	 */
	@Test
	public void testDoubleToDecimalE() {
		Object o = NumberUtil.doubleToDecimal("1.23E5");
		assertEquals("1.23E+5", o.toString());
	}

	/**
	 * Test Null method for
	 * {@link cn.com.hadon.util.NumberUtil#doubleToDecimal(java.lang.Object)}.
	 */
	@Test
	public void testDoubleToDecimalNull() {
		Object o = NumberUtil.doubleToDecimal("");
		assertEquals("", o.toString());
	}
}
