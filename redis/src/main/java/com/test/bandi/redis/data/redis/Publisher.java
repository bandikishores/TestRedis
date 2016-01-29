package com.test.bandi.redis.data.redis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import redis.clients.jedis.Jedis;

public class Publisher {
	 private final Jedis publisherJedis;

	    private final String channel;

	    public Publisher(Jedis publisherJedis, String channel) {
	        this.publisherJedis = publisherJedis;
	        this.channel = channel;
	    }

	    public void publishValues() {
	        System.out.println("Type your message (quit for terminate)");
	        try {
	            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	            while (true) {
	                String line = reader.readLine();

	                if (!"quit".equals(line)) {
	                    publisherJedis.publish(channel, line);
	                } else {
	                    break;
	                }
	            }

	        } catch (IOException e) {
	            System.out.println("IO failure while reading input, e");
	        }
	    }
}
