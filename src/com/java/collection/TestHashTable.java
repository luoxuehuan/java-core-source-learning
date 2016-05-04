package com.java.collection;

import java.util.AbstractMap;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class TestHashTable {

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
	 * 1.hashtable是同步的：
	 * public synchronized V put(K key, V value) 
	 * hashmap 由于非线程安全，效率上可能高于Hashtable
	 * 
	 * 2.
	 * hashmap的key 可以有一个为null,值可以为空
	 * hashtable的key和value都不能为null：
	 * 值不能为空：
	 *  if (value == null) {
            throw new NullPointerException();
        }
	 * 
	 * 
	 * 3.父类不同。
	 * public class HashMap<K,V> extends AbstractMap<K,V>
	 * 
	 * public class Hashtable<K,V> extends Dictionary<K,V>
	 * 
	 * 使用建议：
	 *  需要同步时,用hashtable。反之用hashmap.但是必要的时候hashmap也可以同步。
	 * 
	 */

}
