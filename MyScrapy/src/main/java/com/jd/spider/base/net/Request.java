package com.jd.spider.base.net;

import java.util.Map;

public class Request<T> {
	
	//请求url  相当于请求行
	private String url = null;

	//请求方式   默认GET
	private String method = "GET";
	
	//请求参数       相当于post的请求体
	private Map<String,String> params = null;
	
	//请求头
	private Map<String,String> headers = null;
	
	//该请求的  爬取器
	//private String spider = null;
	private Class spider = null;
	
	
	//请求请求结果的 处理器
	//private String itemdeail = null;
	private Class itemdeail = null;
	
	//要顺延的  实体类
	private T t = null;
	

	public Request() {}

	public Request(String url, String method, Map<String, String> params, Map<String, String> headers, Class spider,
			Class itemdeail, T t) {
		super();
		this.url = url;
		this.method = method;
		this.params = params;
		this.headers = headers;
		this.spider = spider;
		this.itemdeail = itemdeail;
		this.t = t;
	}

	public Request(String url, Class spider) {
		this.url = url;
		this.spider = spider;
	}

	public Request(String url, Class spider, T t) {
		this.url = url;
		this.t = t;
		this.spider = spider;
	
	}
	
	public Request(String url, Class spider, Class itemdeail, T t) {
		this.url = url;
		this.spider = spider;
		this.itemdeail = itemdeail;
		this.t = t;
	}

	@Override
	public String toString() {
		return "Request [url=" + url + ", method=" + method + ", params=" + params + ", headers=" + headers
				+ ", spider=" + spider + ", itemdeail=" + itemdeail + ", t=" + t + "]";
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	
	public Class getSpider() {
		return spider;
	}

	public void setSpider(Class spider) {
		this.spider = spider;
	}

	public Class getItemdeail() {
		return itemdeail;
	}

	public void setItemdeail(Class itemdeail) {
		this.itemdeail = itemdeail;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}
	
	
}
