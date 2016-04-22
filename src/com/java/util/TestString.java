package com.java.util;

public class TestString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringBuffer a =null;
		StringBuilder b =null;
		String c = null;
		
		
	 	/**
	 	 * true
	 	 * 
	 	 * string pool
	 	 * 
	 	 */
	 	String str ="hello";
	 	String str1 = "hello";
	 	/**
	 	 * string pool缓存
	 	 * 没找到 建 一个
	 	 * 
	 	 * 找到  返回 hello
	 	 * 
	 	 * 第一次遇到的 时候缓存
	 	 * 
	 	 * integer 是启动 就缓存
	 	 */
	 	System.out.println(str == str1);
	 	
	 	
	 	/**
	 	 * false
	 	 * 
	 	 * new 是在内存空间申请了一块空间
	 	 */
	 	String str3 = new String("hello2");
	 	/**
	 	 * 仍然 是在 string pool中
	 	 */
	 	String str4 = "hello2";
	 	
	 	
	 	
	 	/**
	 	 * false
	 	 * 
	 	 * new 是在内存空间申请了一块空间
	 	 */
	 	String str5 = new String("hello2");
	 	/**
	 	 * new 也是在内存空间申请了一块空间
	 	 */
	 	String str6 = new String("hello2");
	 	
		
	
		System.out.println(a.equals(b));
		
		/**
		 * jdk 中大量 采用位运算 
		 * 大大 提高效率
		 * == 比较stack 栈  存 这指向内存空间的地址
		 * equal 比较heap  堆 内存空间
		 * 1.heap是堆，stack是栈。
		 * 2.stack的空间由操作系统自动分配和释放，heap的空间是手动申请和释放的，heap常用new关键字来分配。
		 * 3.stack空间有限，heap的空间是很大的自由区。
		 * 在Java中，若只是声明一个对象，则先在栈内存中为其分配地址空间，若再new一下，实例化它，则在堆内存中为其分配地址。
		 * 4.举例：数据类型 变量名；这样定义的东西在栈区。
		 * 如：Object a =null; 只在栈内存中分配空间new 数据类型();或者malloc(长度); 
		 * 这样定义的东西就在堆区如：Object b =new Object(); 则在堆内存中分配空间
		 * 
		 * 作业：digist
		 */
		
		
	}

}
