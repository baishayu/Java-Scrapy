
package com.jd.spider.spider;

import java.util.ArrayList;
import java.util.Map;

import com.google.gson.Gson;
import com.jd.spider.base.Spider;
import com.jd.spider.base.net.Response;
import com.jd.spider.base.queue.RequestQueue;
import com.jd.spider.item.Product;

public class PriceSpider extends Spider<Product> {

	
	@Override
	protected Product process(RequestQueue reqQueue, Response<Product> response) {
		try {
			String priceJson = response.getJSON("utf-8");
			
			if(priceJson==null)
				return null;

			Gson gson = new Gson();
			
			ArrayList arrayList = gson.fromJson(priceJson, ArrayList.class);
			
			Map<String,String> map = (Map<String,String>) arrayList.get(0);
			
			String price =  map.get("p");

			Product t = response.getT();
			
			t.setPrice(price);
			//返回 一个  item处理器实例   
			return t;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
