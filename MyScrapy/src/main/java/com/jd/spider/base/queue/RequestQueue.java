package com.jd.spider.base.queue;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.jd.spider.base.net.Request;
import com.jd.spider.utils.JedisUtils;

import redis.clients.jedis.Jedis;

public class RequestQueue {

	private static volatile RequestQueue queue = new RequestQueue();
	
	private RequestQueue(){}
	
	public static RequestQueue getInstance(){
		return queue;
	}
	
	public <Item> void offer(Request<Item> request){
			
		Jedis jedis = JedisUtils.getJedis();
		System.err.println(request.getUrl());
		//对Request序列化
		String reqJson = JSON.toJSONString(request);
		
		jedis.lpush("req:queue",reqJson);
		jedis.close();
	}

	public <Item> Request<Item> take(){
			
		Jedis jedis = JedisUtils.getJedis();
		
		List<String> jsons = jedis.brpop(0, "req:queue");
		
		String reqJson =  jsons.get(1);
		
		Request<Item> request = JSON.parseObject(reqJson, new TypeReference<Request<Item>>(){});
		
		jedis.close();
		
		return request;
	}
	
	
	
	
}
