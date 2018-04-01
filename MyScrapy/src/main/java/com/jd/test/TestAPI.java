package com.jd.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.jd.spider.item.Product;

public class TestAPI {

	//@Test
	//public void test() throws InstantiationException, IllegalAccessException{
		
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		
		Map params = new HashMap();
		params.put("name", "zhansgan");
		params.put("age", 12);
		Map headers = new HashMap();
		headers.put("User-Agent","Mozilla");
		
		Product prod = new Product();
		prod.setName("手机");
		
		//Request<Product> request = new Request<Product>
		//	("http：//www.jd.com","POST",params, headers,"com.jd.spider","com.jd.itemDetail",prod);
		
		//request.setSpClazz(PageSpider.class);
		
		//request.setProd(prod);
		
		
		/*
		//String json = JSON.toJSONString(request);
		//System.out.println(json);
		
		//Request<Product> request2 = JSON.parseObject(json, new TypeReference<Request<Product>>(){});
		
		Map<String, String> headers2 = request2.getHeaders();
		System.out.println(headers2);
		
		
		System.out.println(request2);
		
		//Class spClazz = request2.getSpClazz();
		
		//Spider spider = (Spider) spClazz.newInstance();
	//	System.out.println(spider);
		
		//new Thread(spider).start();
		
		
		Product product = request2.getProd();
		
		System.out.println(product);
		
		Product t = request2.getT();
		
		System.out.println(t);
	*/
	}
	
	
	@Test
	public void test(){
		
		Model<User> user = new Model<User>();
				
		
		String json = JSON.toJSONString(user);
		
		
		
		
		
		//JSON.parseObject(json, new TypeReference<Model<>>(){});
		
	}
	
	
	
	
	
}
