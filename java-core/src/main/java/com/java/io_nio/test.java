package com.java.io_nio;

public class test {

	public static void main(String[] args) {
		// TOD3O Auto-generated method stub
		Integer i01 = 59;
		int i02 = 59;
		Integer i03 =Integer.valueOf(59);
		Integer i04 = new Integer(59);
		System.out.println(i02==i04);//自动拆箱，比较的是值！
		System.out.println(i02==i03);//比较的是值。
		System.out.println(i01==i02);//比较的是值。
		System.out.println(i01==i04);
		System.out.println(i03==i04);
	}

}
