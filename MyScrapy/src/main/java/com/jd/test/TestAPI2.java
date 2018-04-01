package com.jd.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class TestAPI2 {

	/**
	 * @param args
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static void main(String[] args) {
	/*
		Address address = new  Address("河南", "郑州");
		User user = new User("张三", "12", address);
		String json = JSON.toJSONString(user);
		
		System.out.println(json);

		User u = JSON.parseObject(json,User.class);
		System.out.println(u);
		*/
		
		JSONObject obj = new JSONObject();
		obj.put("name", "张三");
		obj.put("age", 11);
		
		User object = JSON.parseObject(obj.toJSONString(), User.class);
		
		System.out.println(object);
		
		
		
		
		User u1 = new User();
		
		User u2 = new User();
		
		
		System.out.println(u1.getClass()==u2.getClass());
		
		
		
		
	}	
	
	
}
