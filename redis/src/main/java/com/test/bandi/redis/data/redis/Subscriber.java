package com.test.bandi.redis.data.redis;

import org.apache.log4j.Logger;

import redis.clients.jedis.JedisPubSub;

public class Subscriber extends JedisPubSub {
	
	final static Logger logger = Logger.getLogger(Subscriber.class);

	
	 @Override
	    public void onMessage(String channel, String message) {
		 logger.debug("Message received. Channel: {}, Msg: {}");
		 	//logger.debug("Message received. Channel: {}, Msg: {}", channel, message);
	    }

	    @Override
	    public void onPMessage(String pattern, String channel, String message) {

	    }

	    @Override
	    public void onSubscribe(String channel, int subscribedChannels) {

	    }

	    @Override
	    public void onUnsubscribe(String channel, int subscribedChannels) {

	    }

	    @Override
	    public void onPUnsubscribe(String pattern, int subscribedChannels) {

	    }

	    @Override
	    public void onPSubscribe(String pattern, int subscribedChannels) {

	    }
}
