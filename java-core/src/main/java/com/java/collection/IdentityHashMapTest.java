package com.java.collection;

import java.util.IdentityHashMap;

public class IdentityHashMapTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 IdentityHashMap ihm = new IdentityHashMap();  
	        //下面两行代码向IdentityHashMap对象添加两个key-value对  
		 
		 	/*String yuwen1 = new String("语文");
		 	String yuwen2 = new String("语文");
		 
	        ihm.put(yuwen1,89);  
	        ihm.put(yuwen2,78);  
	        //下面两行代码只会向IdentityHashMap对象添加一个key-value对  
	        ihm.put("java",93);  
	        ihm.put("java",98);  
	        
	        System.out.println(ihm.get(yuwen2));
	        System.out.println(ihm);  */
		 
		
	        //下面两行代码向IdentityHashMap对象添加两个key-value对  
	        ihm.put(new String("语文"),89);
	        ihm.put(new String("语文"),78);  
	        //下面两行代码只会向IdentityHashMap对象添加一个key-value对  
	        ihm.put("java",93);  
	        ihm.put("java",98);  
	        
	        System.out.println(ihm.get(new String("语文")));
	        System.out.println(ihm);  
	        
	        
	        
	}

}
