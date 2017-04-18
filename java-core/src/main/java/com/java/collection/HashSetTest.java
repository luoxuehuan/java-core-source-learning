package com.java.collection;

import java.util.AbstractSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class HashSetTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map set =  (Map) new HashSet();
		Map mp = new HashMap();
	}
	
	/**
	 * 一、HashSet的源码解析：
	 * 【参考文档】http://www.cnblogs.com/xwdreamer/archive/2012/06/03/2532999.html
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
	
	
	
	
	

	
	/**
	 * 
	 * 二、与其他相比：
	 * 
	 * 与HashMap的不同点：
	 * 
	 * Set的集合里不允许对象有重复的值
	 * 
	 * 
	 * 
	 * 1.
	 * HashSet实现了Set接口，它不允许集合中有重复的值
	 * HashMap实现了Map接口，Map接口对键值对进行映射。
	 * Map中不允许重复的键。
	 * Map接口有两个基本的实现，HashMap和TreeMap。
	 * TreeMap保存了对象的排列次序，而HashMap则不能。
	 * 
	 * ---HashSet
	 * public class HashSet<E> extends AbstractSet<E>
	 * 
	 * 
	 * 	如果已存在e,map.put(e, PRESENT)返回的不是null,则不改变hashset，并返回false。
	 *  public boolean add(E e) { return map.put(e, PRESENT)==null;}
	 * 
	 * 
	 * 
	 * 2.HashMap储存键值对	HashSet仅仅存储对象
	 * 
	 *  // Dummy value to associate with an Object in the backing Map
    	private static final Object PRESENT = new Object();
	 * map.put(e, PRESENT)
	 * 
	 * 3.使用put()方法将元素放入map中	使用add()方法将元素放入set中
	 * public V put(K key, V value)
	 *  public boolean add(E e)
	 * 
	 * 4.HashMap中使用键对象来计算hashcode值	
	 * HashSet使用成员对象来计算hashcode值，对于两个对象来说hashcode可能相同，
	 * 
	 * ---源码解析：
	 * ---使用成员对象e来计算hash值。
	 * ---return map.put(e, PRESENT)==null;
	 * ---putVal(hash(key), key, value, false, true);
	 * 
	 * ---HashSet把成员对象当做HashMap的Key存储。而对象为固定的PRESENT。【value使用一个static final的Object对象标识】
	 * 
	 * 所以equals()方法用来判断对象的相等性，如果两个对象不同的话，那么返回false
	 * 
	 * 
	 * 5.
	 * HashMap比较快，因为是使用唯一的键来获取对象	
	 * HashSet较HashMap来说比较慢
	 */
}
