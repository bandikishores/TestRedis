package com.test.bandi.redis.data.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class JedisExample {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("localhost", 6379);
	    jedis.auth("password");
	    System.out.println("Connected to Redis");
	    System.out.println(jedis.isConnected());
	    
	    System.out.println(jedis.get("test"));
	    System.out.println("Values present in set skishore : " + jedis.smembers("skishore"));
	    System.out.println("Values present in list kishore : " + jedis.lrange("kishore", 0, -1));
	    
	    jedis.disconnect();
	    System.out.println(jedis.isConnected());
	}

}
