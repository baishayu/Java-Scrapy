package com.jd.spider.base.net;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

public class Response<T> {
	
	private CloseableHttpResponse response;
	
	private T t;
	
	public T getT() {
		return t;
	}

	public void setT(T t) {
		System.out.println(t);
		this.t = t;
	}

	//请求url  相当于请求行
	private String url = null;
	
	public Response(CloseableHttpResponse response){
		this.response = response;
	}
	
	public CloseableHttpResponse getResponse() {
		return response;
	}

	public void setResponse(CloseableHttpResponse response) {
		this.response = response;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHTML(String encode) throws Exception{
		
		if(response==null)
			return null;
		
		//获取返回值
		HttpEntity entity = response.getEntity();
		String html = EntityUtils.toString(entity,encode);
		
		return html;
	}
	
	
	public String getJSON(String encode) throws Exception{

		if(response==null)
			return null;
		
		//获取返回值
		HttpEntity entity = response.getEntity();
		return EntityUtils.toString(entity,encode);
	
	}

}
