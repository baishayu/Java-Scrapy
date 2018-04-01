package com.jd.spider.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.jd.spider.base.queue.RequestQueue;

public interface Schedule {

	 ExecutorService threadPool = Executors.newFixedThreadPool(15);

	 RequestQueue requestQueue = RequestQueue.getInstance();
	 
	 void schedule();

}
