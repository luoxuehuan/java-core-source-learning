package com.java.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * Hashmap思考
 * @author lxh
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
