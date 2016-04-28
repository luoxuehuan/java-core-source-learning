package com.java.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * Hashmap思考
 * @author lxh
 * 
 * 1.数据结构
 * Hashmap是一个数组和链表的结合体（在数据结构称“链表散列“）
 * 
 * 当我们往hashmap中put元素的时候，先根据key的hash值得到这个元素在数组中的位置（即下标）
 * 然后就可以把这个元素放到对应的位置中了。
 * 如果这个元素所在的位子上已经存放有其他元素了，
 * 那么在同一个位子上的元素将以链表的形式存放，（Java7）新加入的放在链头，最先加入的放在链尾。(Java8)新加入的放在链尾。
 *
 */
public class TestHashMap {

	public static void main(String[] args) {
		Map mp = new HashMap();
		mp.put("h", "dd");
		mp.put("h","cc");
		mp.get("h");
		
		System.out.println(mp.get("h"));
		// TODO Auto-generated method stub
		
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("Spark", 98);
		map.put("Hadoop", 60);
		map.put("Flink", 90);
		map.put("Flume", 69);
		map.put(null, 0);
		map.put("Strom", 91);
		map.put("Spark Streaming", 88);
		map.put("Hive", 70);
		map.put("Mahout", 78);
		map.put("Mahout", 100);
		System.out.println(map);
		map.put("Hive1", 70);
		map.put("Mahout2", 78);
		map.put("Hive3", 70);
		map.put("Mahout4", 78);
		map.put("Hive5", 70);
		map.put("Mahout6", 78);
		map.put("Hive7", 70);
		map.put("Mahout8", 78);
		
		
		
		int hash = 9;
		int index = 9;
		System.out.println(index % hash);
		System.out.println((index - 1) & hash);

	}

}
