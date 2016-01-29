package com.test.bandi.redis.data.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPubSubExample {
	public static final String CHANNEL_NAME = "commonChannel";
	
	private static Logger logger = LoggerFactory.getLogger(JedisPubSubExample.class);
	
	static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(JedisPubSubExample.class.getName());

	public static void main(String[] args) {

		log.trace("trace");
		log.debug("debug");
		log.info("info");
		log.warn("warn");
		log.error("error");
		log.fatal("fatal");
		
		final JedisPoolConfig poolConfig = new JedisPoolConfig();
		final JedisPool jedisPool = new JedisPool(poolConfig, "localhost", 6379, 0);
		final Jedis subscriberJedis = jedisPool.getResource();
		subscriberJedis.auth("password");
		final Subscriber subscriber = new Subscriber();

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					logger.info("Subscribing to \"commonChannel\". This thread will be blocked.");
					subscriberJedis.subscribe(subscriber, CHANNEL_NAME);
					logger.info("Subscription ended.");
				} catch (Exception e) {
					logger.error("Subscribing failed.", e);
				}
			}
		}).start();

		final Jedis publisherJedis = jedisPool.getResource();
		publisherJedis.auth("password");

		new Publisher(publisherJedis, CHANNEL_NAME).publishValues();

		subscriber.unsubscribe();
		jedisPool.returnResource(subscriberJedis);
		jedisPool.returnResource(publisherJedis);
		jedisPool.close();
	}

}
