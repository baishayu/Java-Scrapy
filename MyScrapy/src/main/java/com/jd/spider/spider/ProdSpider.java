package com.jd.spider.spider;

import java.io.File;
import java.io.FileWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.jd.spider.base.Spider;
import com.jd.spider.base.net.Request;
import com.jd.spider.base.net.Response;
import com.jd.spider.base.queue.RequestQueue;
import com.jd.spider.item.Product;
import com.jd.spider.itemdeail.ProductItemDeail;

public class ProdSpider extends Spider<Product> {

	@Override
	protected Product process(RequestQueue reqQueue, Response<Product> response) {

		try {
			//1获取 请求的结果 并转换
			//1.1  获取结果
			String html = response.getHTML("utf-8");
			if(html == null)
				return null;
			//1.2 转换成 document对象
			Document prodDoc = Jsoup.parse(html);
			
			//2 处理页面数据值
			//2.1  实例化 一个 pojo对象
			Product prod = new Product();
			//2.2 设置商品的url
			prod.setUrl(response.getUrl());
			//2.3 设置商品的name
			Element nameEle = prodDoc.select(".sku-name").get(0);
			prod.setName(nameEle.ownText());
			
			//3 处理页面的url
			//获取置商品的price的url
			Element prodIdEle = prodDoc.select(".p-price [class^=price]").get(0); 
			String prodId = prodIdEle.attr("class").replace("price J-p-", "");
			String priceUrl = "https://p.3.cn/prices/mgets?type=1&area=1_72_2799_0&pdtk=&pduid=1511068999825466934033&pdpin=&pin=null&pdbp=0&skuIds=J_"+prodId;
			
			//4提交请求      顺延item
			reqQueue.offer(new Request(priceUrl,PriceSpider.class,ProductItemDeail.class,prod));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	
	}
	

}
