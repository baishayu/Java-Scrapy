package com.jd.spider.spider;

import com.jd.spider.base.Spider;
import com.jd.spider.base.net.Response;
import com.jd.spider.base.queue.RequestQueue;
import com.jd.spider.item.Order;

public class OrderSpider extends Spider<Order>{

	@Override
	protected Order process(RequestQueue reqQueue, Response<Order> response) {

		//

		try {
			response.getHTML("utf-8");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
