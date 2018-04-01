package com.jd.spider.base;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

import com.jd.spider.base.net.Request;

//Schedule implements  Runnable

public class SlaveSchedule implements Schedule{
	
	public void start() {
	
		schedule();
		//threadPool.submit(this);
		
	}

	//private Map<Class<Spider>,Spider> spiderMap = new HashMap<Class<Spider>,Spider>();
	
	//public void run(){
		
	//	schedule();
	//}
	
	public void schedule() {
		 while(true){
			 try{
				 	
				 	//获取请求对象
				 	Request request = requestQueue.take();
					
					//获取请求对象中 的 任务 提交任务
					Class<Spider> spiderClass = request.getSpider();
					
					//反射调用spider对象
					if(spiderClass == null)
						throw new RuntimeException();
						
						//构建spider实例  并保证单例
//						if(!spiderMap.containsKey(spiderClass)){
							Spider spider = spiderClass.newInstance();
//							spiderMap.put(spiderClass, spider);
//						}
						
						//获取spider对象
//						Spider spider = spiderMap.get(spiderClass);
						
						//设置请求对象
						spider.setRequest(request);
						
						//提交任务
						threadPool.submit(spider);
						
						//threadPool.submit(this);

			 }catch(Exception e){
				 e.printStackTrace();
			 }
		 }	 
	}

	
}
