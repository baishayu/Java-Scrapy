package com.jd.spider.spider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.jd.spider.base.Spider;
import com.jd.spider.base.net.Request;
import com.jd.spider.base.net.Response;
import com.jd.spider.base.queue.RequestQueue;


/**
 * spider 
 * 
 * 采用的多线程  多例的方式  设计的
 * 
 * @author wanglei
 *
 */


public class PageSpider extends Spider<Object> {
	
	/*
	 *因为是多例的  又要多线程 共享 所以声明生  static类型
	 */
	private static Integer page = 1;
	
	private String start_url = "https://search.jd.com/Search?"
			+ "keyword=%E6%89%8B%E6%9C%BA&enc=utf-8"
			+ "&qrst=1"
			+ "&rt=1"
			+ "&stop=1"
			+ "&vt=2"	
			+ "&wq=%E6%89%8B%E6%9C%BA"
			+ "&cid2=653"
			+ "&cid3=655"
			+ "&page=";
	
	/**
	 * 1   有数据 封装数据到pojo  return
	 * 2   有url封装成请求对象交给 队列 
	 * 3   有图片下载器下载
	 */
	
	static{
		System.out.println("加载了");
	}

	@Override
	protected Object process(RequestQueue reqQueue, Response<Object> response) {
		try {
			if(page>100)
				return null;
			//提交分页请求,并也  由  PageSpider类 来处理本次请求
			synchronized (PageSpider.class) {
				reqQueue.offer(new Request(start_url+(2*page-1),PageSpider.class));
				page++;
			}
			
			//处理本次请求
			String html = response.getHTML("utf-8");
			
			if (html==null)
				return null;
			
		    Document prodListdoc = Jsoup.parse(html);		

			//获取商品列表元素
			Elements prodListElems = prodListdoc.select(".p-img a");
			//获取商品  连接
			for (Element prodListElem : prodListElems) {
				
				String prodUrl = prodListElem.attr("href");
				
				if(!prodUrl.startsWith("http"))
					prodUrl = "http:"+prodUrl;
				
				//构造请求 并将 请求添加到 请求队列里
				/**
				 * 1   封装url
				 * 2   处理该请求的spider实例
				 * 3 item是否顺延  
				 * 
				 */
				reqQueue.offer(new Request(prodUrl,ProdSpider.class));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}
