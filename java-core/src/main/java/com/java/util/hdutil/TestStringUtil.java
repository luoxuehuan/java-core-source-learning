package com.java.util.hdutil;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.junit.Ignore;
import org.junit.Test;

/**
 * StringUtil测试用例
 * 
 * @author sunfeng
 * @version 1.0
 */
public class TestStringUtil {

	/**
	 * EncodeString（String,String,String）测试用例，正常执行测试
	 */
	@Test
	public void testEncodeStringThreeParams() {
		String result = StringUtil.encodeString("我爱中国", "iso-8859-1", "utf-8");
		assertEquals("????", result);
	}

	/**
	 * EncodeString（String,String,String）测试用例，异常测试
	 */
	@Test
	public void testEncodeStringThreeParamsException() {
		String result = StringUtil.encodeString("我爱中国", "iso-8859-1", "utf28");
		assertEquals("我爱中国", result);
	}

	/**
	 * EncodeString（String,String,String）测试用例，Null测试
	 */
	@Test
	public void testEncodeStringThreeParamsNull() {
		String result = StringUtil.encodeString(null, "iso-8859-1", "utf-8");
		assertNull(result);
	}

	/**
	 * EncodeString（String,String,String）测试用例，空字符串测试
	 */
	@Test
	public void testEncodeStringThreeParamsBlank() {
		String result = StringUtil.encodeString("", "iso-8859-1", "utf-8");
		assertEquals("", result);
	}

	/**
	 * EncodeString(String)测试用例，底层调用的事EncodeString（String,String,String）
	 * 所以具体的测试见EncodeString（String,String,String）
	 */
	@Test
	public void testEncodeString() {
		String result = StringUtil.encodeString("我爱中国");
		assertEquals("????", result);
	}

	/**
	 * GetStringFromInputStream 测试用例，输入流为Null的测试
	 */
	@Test
	public void testGetStringFromInputStreamNull() {
		InputStream inputStream = null;
		String result = StringUtil.getStringFromInputStream(inputStream);
		assertNull(result);
	}

	/**
	 * GetStringFromInputStream 测试用例，正常执行测试
	 */
	@Test
	public void testGetStringFromInputStream() {

		try {
			InputStream inputStream = new FileInputStream("D:\\test.txt");
			String result = StringUtil.getStringFromInputStream(inputStream);
			assertEquals("123", result);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * GetBlankStrByLevel测试用例，正常执行测试
	 */
	@Test
	public void testGetBlankStrByLevel() {
		String result = StringUtil.getBlankStrByLevel("2");
		assertEquals("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;", result);
	}

	/**
	 * GetBlankStrByLevel测试用例，异常执行测试
	 */
	@Test(expected = Exception.class)
	public void testGetBlankStrByLevelException() {
		StringUtil.getBlankStrByLevel("");
	}

	/**
	 * SplitString 测试用例，正常执行
	 */
	@Test
	public void testSplitStringTwoParams() {
		String[] result = StringUtil.splitString("a,b,c", ",");
		assertArrayEquals(new String[] { "a", "b", "c" }, result);
	}

	/**
	 * SplitString 测试用例，异常执行
	 */
	@Test(expected = Exception.class)
	public void testSplitStringTwoParamsException() {
		StringUtil.splitString(null, ",");
	}

	/**
	 * SplitString测试用例，正常执行测试，异常情况请参见testSplitStringTwoParamsException
	 */
	@Test
	public void testSplitString() {
		String[] result = StringUtil.splitString("a&b&c");
		assertArrayEquals(new String[] { "a", "b", "c" }, result);
	}

	/**
	 * GetStringFromObject 测试用例，null情况测试
	 */
	@Test
	public void testGetStringFromObjectNull() {
		String result = StringUtil.getStringFromObject(null);
		assertEquals("", result);
	}

	@Test
	@Ignore
	public void testIsDigital() {
	}

	/**
	 * trim 测试用例，正常执行
	 */
	@Test
	public void testTrim() {
		String result = StringUtil.trim(" test ");
		assertEquals("test", result);
	}

	/**
	 * trim 测试用例，异常和null测试
	 */
	@Test(expected = Exception.class)
	public void testTrimNullAndException() {
		StringUtil.trim(null);
	}

	/**
	 * IsNull 测试用例，正常执行
	 */
	@Test
	public void testIsNull() {
		boolean result = StringUtil.isNull("123");
		assertEquals(false, result);
	}

	/**
	 * IsNull 测试用例，null执行
	 */
	@Test
	public void testIsNullNull() {
		boolean result = StringUtil.isNull(null);
		assertEquals(true, result);
	}

	/**
	 * IsNull 测试用例，""执行
	 */
	@Test
	public void testIsNullblank() {
		boolean result = StringUtil.isNull("");
		assertEquals(true, result);
	}

	@Test
	@Ignore
	public void testGetCode() {
	}

	/**
	 * ClobToString 测试用例 null测试
	 */
	@Test
	public void testClobToStringNull() {
		String result = StringUtil.clobToString(null);
		assertEquals("", result);
	}

	/**
	 * ClobToString 测试用例 异常测试
	 */
	@Test(expected = Exception.class)
	public void testClobToStringException() {
		StringUtil.clobToString(new Object());
	}

}
