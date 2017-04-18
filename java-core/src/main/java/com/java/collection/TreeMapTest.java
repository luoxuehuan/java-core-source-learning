package com.java.collection;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapTest {

	public static void main(String[] args) {
	
		
		
		Map<String, String> map = new HashMap<String, String>(); 
		map.put("a", "aaa"); 
		map.put("b", "bbb"); 
		map.put("c", "ccc"); 
		map.put("d", "ddd"); 
		Iterator<String> iterator = map.keySet().iterator(); 
		while (iterator.hasNext()) { 
		Object key = iterator.next(); 
		System.out.println("HashMap.get(key) is :" + map.get(key)); 
		} 
		
		
		// 定义HashTable,用来测试 
		Hashtable<String, String> tab = new Hashtable<String, String>(); 
		tab.put("a", "aaa"); 
		tab.put("b", "bbb"); 
		tab.put("c", "ccc"); 
		tab.put("d", "ddd"); 
		Iterator<String> iterator_1 = tab.keySet().iterator(); 
		while (iterator_1.hasNext()) { 
		Object key = iterator_1.next(); 
		System.out.println("HashTable.get(key) is :" + tab.get(key)); 
		} 
		
		
		TreeMap<String, String> tmp = new TreeMap<String, String>(); 
		tmp.put("a", "aaa"); 
		tmp.put("b", "bbb"); 
		tmp.put("d", "ccc"); 
		tmp.put("c", "cdc"); 
		Iterator<String> iterator_2 = tmp.keySet().iterator(); 
		while (iterator_2.hasNext()) { 
		Object key = iterator_2.next(); 
		System.out.println("TreeMap.get(key) is :" + tmp.get(key)); 
		} 
		} 
	}


//	输出结果：
//	HashMap.get(key) is :aaa
//	HashMap.get(key) is :bbb
//	HashMap.get(key) is :ccc
//	HashMap.get(key) is :ddd
//	HashTable.get(key) is :bbb
//	HashTable.get(key) is :aaa
//	HashTable.get(key) is :ddd
//	HashTable.get(key) is :ccc

//  TreeMap是有序的：
//	TreeMap.get(key) is :aaa
//	TreeMap.get(key) is :bbb
//	TreeMap.get(key) is :cdc
//	TreeMap.get(key) is :ccc

	
	/**
	 * 一、实现
	 * 【参考博文】：http://blog.csdn.net/chenssy/article/details/26668941
	 * 
	 * 
	 */
	
	
	
	
	/**
	 * 二、区别
	 * 【参考】http://dwz.cn/3hx72v
	 * HashMap:底层是哈希表数据结构。线程不同步。
	 * TreeMap:底层是二叉树（红黑树）数据结构，线程不同步，可用于给Map集合中的键进行排序。
	 * 
	 * 
	 * 三、使用注意：
	 * HashMap通常比TreeMap快一点(树和哈希表的数据结构使然)，建议多使用HashMap，
	 * TreeMap： 在需要排序的时候使用，在一个map放入很多数据，需要按照什么规则排序显示的时候。
	 * 
	 * 
	 */


