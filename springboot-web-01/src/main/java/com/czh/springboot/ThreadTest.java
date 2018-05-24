package com.czh.springboot;
/**  
* <p>Title: ThreadTest.java</p>  
* <p>Description:线程池测试 </p>  
* <p>Copyright: Copyright (c) 2018</p>  
* <p>Company: www.chenzhehao.com</p>  
* @author chenzhehao  
* @date 2018年4月2日  
* @version 1.0  
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

public class ThreadTest {
	public static void main(String[] args) {
		// newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		List<Future<String>> list = new ArrayList<Future<String>>();
		for (int i = 0; i < 10; i++) {
			ThreadCallable callable = new ThreadCallable(i);
			Future<String> future = cachedThreadPool.submit(callable);
			list.add(future);
		}
		for (int i = 0; i < list.size(); i++) {
			Future<String> future = list.get(i);
			try {
				System.out.println("future:"+future.get().toString());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		// newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
		for(int i=0;i<10;i++)
        {
            final int index=i;
            fixedThreadPool.execute(new ThreadTestVo(i));
        }
		
		// newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);
//		scheduledThreadPool.scheduleAtFixedRate(command, initialDelay, period, unit)
		// newSingleThreadExecutor
		// 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
		ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
		

	}

	static class ThreadTestVo implements Runnable {
		public int index;
		public ThreadTestVo(int index){
			this.index = index;
		}
		@Override
		public void run() {
			System.out.println("开始处理线程！！！"+index);
			try {
				Thread.sleep(10*index);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("我的线程标识是：" + this.toString());
		}
	}
	
	static class ThreadCallable implements Callable<String>{

		private int i;
		
		public ThreadCallable(int i){
			this.i = i;
		}
		
		@Override
		public String call() throws Exception {
			Random random = new Random();
			Thread.sleep(random.nextInt(1000));
			System.out.println(i);
			return i+"";
		}
		
	}
}
