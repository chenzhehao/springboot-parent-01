package com.czh.test.thread.sleep2wait;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * Title: TestThread.java
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2018
 * </p>
 * <p>
 * Company: www.chenzhehao.com
 * </p>
 * 
 * @author chenzhehao
 * @date 2018年5月7日
 * @version 1.0
 */
public class TestThread {

	public static void main(String[] args) {
		int a = -16;
		System.out.println(a >> 4);
		System.out.println(a >>> 4);
		System.out.println(a << 4);
		
//		ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
//		map.put("a", "1");
//		map.put("b", "2");
		
		
		
		//线程使用同一个对象时，sleep会一直持有锁，wait或释放锁，到时间后重新获取锁
		
		
		
//		ThreadService service = new ThreadService();
//		Thread sleepThread = new Thread(new ThreadA(service));
//		Thread waitThread = new Thread(new ThreadB(service));
//		waitThread.start();
//		sleepThread.start();
		
		ThreadService2 service2 = new ThreadService2();
		Thread sleepThread2 = new Thread(new ThreadA(service2));
		
		sleepThread2.start();
//		for (int i = 0; i < 1000; i++) {
//			System.out.println(i);
//		}
		Thread waitThread2 = new Thread(new ThreadB(service2));
		waitThread2.start();
		
		Thread thread3 =new Thread(){
			public ThreadService2 service = service2;
			@Autowired
			public void run(){
				for (int i = 0; i < 100; i++) {
					System.out.println(i);
					try {
//						wait(10);
//						service.waitFun();
						service.testFun();
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		thread3.start();
		
	}

}
