package com.jd.spider.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.jd.spider.base.net.Request;
import com.jd.spider.base.net.Response;
import com.jd.spider.base.queue.RequestQueue;

public abstract class Spider<T> implements Runnable{
	
	private Request<T> request = null;
	
	public Request<T> getRequest() {
		return request;
	}

	public void setRequest(Request<T> request) {
		this.request = request;
	}

	
	public void run() {
		
		//1 发出请求 并 获得 爬虫的响应  结果
		CloseableHttpResponse resop = null;
		try {
			
			if("GET".equals(request.getMethod())){
				 resop = this.doGet();
			}
			
			if("POST".equals(request.getMethod())){
				resop = this.doPost();
			}
		
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("请求失败"+request.getUrl());
		}
		
		//2 对响应结果再封装 构造自己的 response
		//2.1   实例化 自定义response 对CloseableHttpResponse再封装
		Response response = new Response(resop);
		//2.2   回显 请求的url 给response
		response.setUrl(request.getUrl());

		//2.3  设置顺延的实例给response
		try {
			//2.3.1 获取  request中的 model
			JSONObject model = (JSONObject) request.getT();

			if(model!=null){
				//2.3.2获取  子类的泛型 类型   也是 model的类型
				ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
				Type type2 = type.getActualTypeArguments()[0];
				String typeName = type2.getTypeName();
				//2.3.3加载 泛型的字节码类
				Class clazz = Class.forName(typeName);
				//2.3.4使用 fastJSON 将 model装换成  pojo  并顺延到  response 对象
				response.setT(JSON.parseObject(model.toJSONString(),clazz));
			}
		
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		//3 对爬虫 结果处理,并返回 构造出来的实例对象
		try {
			T t = process(RequestQueue.getInstance(),response);
		
			//4 反射 实例化 处理实例对象的  类
			Class itemDeailClazz = request.getItemdeail();
		
			if(itemDeailClazz !=null){
				ItemDeail<T> itemDeail = (ItemDeail<T>) itemDeailClazz.newInstance();
				//5 调用处理 结果的方法 对结果处理
				itemDeail.deailItem(t);
			}
			
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public CloseableHttpResponse doPost() throws Exception{
		String url = request.getUrl();
		Map<String,String> headers = request.getHeaders();
		Map<String,String> params = request.getParams();
		if(url == null)
			return null;
		
		//1 设置请求行 和 url
		HttpPost httpPost = new HttpPost(url);
		
		//2 设置请求头
		if(headers!=null)
			for (Map.Entry<String, String> header : headers.entrySet()) {
				httpPost.setHeader(header.getKey(), header.getValue());
			}
		
		//3 设置 请求体
		ArrayList<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
		if(parameters!=null){
			for (Map.Entry<String, String> entry: params.entrySet()) {
				parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}
		
		httpPost.setEntity(new UrlEncodedFormEntity(parameters));
		
		//4 获取一个httpclient对象， 用来执行http请求
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		//5执行请求 得到返回对象
		return httpClient.execute(httpPost);
	
	
	
	}
	
	public CloseableHttpResponse doGet() throws Exception{
		String url = request.getUrl();
		Map<String,String> headers = request.getHeaders();
		Map<String,String> params = request.getParams();

		if(url==null)
			return null;
		
		//1 设置请求行 和 url
		HttpGet httpGet = new HttpGet(url);
		
		//2 设置请求头
		if(headers!=null)
			for (Map.Entry<String, String> header : headers.entrySet()) {
				httpGet.setHeader(header.getKey(), header.getValue());
			}
		
		//3获取一个httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		//4执行请求,并放回结果
		return  httpClient.execute(httpGet);
		
	}
		
	
	abstract protected T process(RequestQueue reqQueue ,Response<T> response) throws Exception;
	
}
