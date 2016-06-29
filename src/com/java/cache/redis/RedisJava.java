package com.java.cache.redis;

import redis.clients.jedis.Jedis;

public class RedisJava {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		// Connecting to Redis server on localhost
		Jedis jedis = new Jedis("10.137.12.12", 6379);

		System.out.println("Connection to server sucessfully");
		// check whether server is running or not
		System.out.println("Server is running: " + jedis.ping());

		jedis.set("tch", "sb");
		// Get the stored data and print it
		System.out.println("tch:: " + jedis.get("tch"));
		
		
	}

	
}
