package com.jd.spider.base;

import com.jd.spider.base.net.Request;

public abstract class MasterSchedule extends SlaveSchedule{
	
	public void start() {
		
		//启动爬虫的请求
		Request req = new Request();
	
		Class sp = startSpider();
		
		req.setSpider(sp);
		
		requestQueue.offer(req);
		
		//从请求队列中以轮询的方式  获取 请求
		
		schedule();
	}
	
	protected abstract Class startSpider();
	
}
