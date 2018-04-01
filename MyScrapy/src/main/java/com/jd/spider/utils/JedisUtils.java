package com.jd.spider.utils;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisUtils {

	private static final GenericObjectPoolConfig poolConfig;
	private static final JedisPool jedisPool ;
	
	static{
		poolConfig = new GenericObjectPoolConfig();
		poolConfig.setMaxIdle(50);
		poolConfig.setMinIdle(10);
		poolConfig.setMaxTotal(60);
		jedisPool = new JedisPool(poolConfig, "192.168.162.128", 6379);
	}
	
	public static Jedis getJedis(){
		return jedisPool.getResource();
	}
	
	public static void close(Jedis jedis){
		jedis.close();
	}
}
