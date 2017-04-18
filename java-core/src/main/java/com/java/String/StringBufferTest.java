package com.java.String;

public class StringBufferTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		StringBuilder sbd = new StringBuilder();
		/*
		 * java中String、StringBuffer、StringBuilder的区别
		 * 
		 * 1.可变与不可变
		 * 	
		 * 	String不可变。
		 * 		private final char value[];
		 * 	另外的可变。
		 * 		char[] value;
		 * 
		 * 2.是否多线程安全
		 * 
		 * 	A.String不可变为常量。
		 * 
		 * 	B.StringBuffer：A thread-safe, mutable sequence of characters.
		 * 		
		 * 		@Override
    			public synchronized StringBuffer append(String str) {
        			toStringCache = null;
        			super.append(str);
        			return this;
    			}
		 * 	C.StringBuilder:在单线程性能表现比较好。线程不安全。
		 *  	
		 *  	 @Override
    			public StringBuilder append(String str) {
        			super.append(str);
        			return this;
    			}
		 * 		HashMap线程不安全。HashTable线程安全。
		 * 
		 * 
		 * 
		 * 3.StringBuilder与StringBuffer共同点
		 * 		共同抽象父类AbstractStringBuilder。会调用父类方法。
		 * 		只是StringBuffer会在方法上加synchronized关键字，进行同步。
		 */
		calSize();
	}
	
	   private static void calSize() {    
	        System.out.println("Integer: " + Integer.SIZE/8);           // 4    
	        System.out.println("Short: " + Short.SIZE/8);               // 2        
	        System.out.println("Long: " + Long.SIZE/8);                 // 8    
	        System.out.println("Byte: " + Byte.SIZE/8);                 // 1    
	        System.out.println("Character: " + Character.SIZE/8);       // 2    
	        System.out.println("Float: " + Float.SIZE/8);               // 4    
	        System.out.println("Double: " + Double.SIZE/8);             // 8    
	        System.out.println("Boolean: " + Boolean.toString(false));    
	  
	    }
}


