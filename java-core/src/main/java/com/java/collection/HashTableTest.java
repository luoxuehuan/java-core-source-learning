package com.java.collection;

import java.util.AbstractMap;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class HashTableTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dictionary table = new Hashtable();
		Map map = new HashMap();
		for (int i = 6 ; i-- > 0 ;){
			System.out.println(i);
		}
		
		for (int i = 6 ; i > 0 ; i--){
			System.out.println(i);
		}
	}
	
	/**
	 * 区别：
	 * 
	 * 【1】.HashTable是同步的：
	 * 
	 * ---HashTable：
	 * public synchronized V put(K key, V value) 
	 * ---HashMap：
	 * public V put(K key, V value)
	 * 
	 * HashMap 由于非线程安全，效率上可能高于Hashtable
	 * 
	 * 【2】.
	 * hashmap的key 可以有一个为null,值可以为空
	 * hashtable的key和value都不能为null：
	 * 值不能为空：
	 * ---HashTable
	 * if (value == null) { throw new NullPointerException();}
	 * ---HashMap
	 * 
	 * 
	 * 
	 * 【3】.父类不同。
	 * 
	 * ---HashMap
	 * public class HashMap<K,V> extends AbstractMap<K,V>
	 * ---Hashtable
	 * public class Hashtable<K,V> extends Dictionary<K,V>
	 * 
	 * 【4】.使用建议：
	 *  需要同步时,用hashtable。反之用hashmap.但是必要的时候hashmap也可以同步。
	 *  Hashtable和HashMap采用的hash/rehash算法都大概一样，所以性能不会有很大的差异
	 * 
	 */

}
