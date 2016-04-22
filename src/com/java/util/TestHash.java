package com.java.util;

/**
 * 第二课：
 * 高深的hash算法
 * 
 * @author lxh
 * 
 * 分布式 负债均衡
 * 
 * 循环算法
 * 哈希算法
 * 最少链接算法
 * 响应速度算法
 * 加权法VIP
 *
 *
 */
public class TestHash {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/**
		 *  public native int hashCode(); 本地  c写 的看不了
		 *  
		 */
		Object j = null;
		
		
		
		String d =new String("hello");
		String e =new String("hello");
		System.out.println(d.hashCode());
		System.out.println(e.hashCode());
	}
	
	public void good(String str){
		System.out.println(str);
	}

}
