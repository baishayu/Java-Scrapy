package com.jd.spider;

import com.jd.spider.base.MasterSchedule;
import com.jd.spider.spider.PageSpider;

public class MyStart extends MasterSchedule{
	
	/**
	 * 启动爬虫的 spider
	 */
	@Override
	protected Class startSpider() {
		
		return PageSpider.class;
	
	}

	
	public static void main(String[] args) {
		new MyStart().start();
	}
	
}
