package com.java.util;
/**
 * 第一课： 
 * java 基本数据类型 和string 的常量池
 * 
 * boolean 全部缓存
 * byte全部缓存
 * character <==127缓存
 * short
 * long
 * 
 * double 没有缓存
 * float 没有缓存
 * @author lxh
 *
 */
public class TestInt {

	public static void main(String[] args) {
		Integer a = new Integer(5);
		Integer b = new Integer(5);
		a = Integer.reverse(a);
		
		/*Integer e = new Integer("ae");
		System.out.println(e);*/
		/**
		 *	Integer a = 5;
		 *	Integer b = 5;
		 * 	IntegerCache 
		 * -128 到127 是从缓存里拿的
		 */
	 	Integer c = 128;
	 	Integer d = 128;
	 

	}

}
