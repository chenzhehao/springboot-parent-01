package com.czh.springboot.schedule.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.springframework.stereotype.Component;

import com.czh.springboot.service.impl.thread.MapTest;
import com.czh.springboot.service.impl.thread.UserTestService3;

/**
 * <p>
 * Title: UserInfoThread.java
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
 * @date 2018年5月8日
 * @version 1.0
 */
@Component
public class UserInfoThread3 {

	public static void main(String[] args) {
		List<MapTest<Integer, Object>> list = new ArrayList<MapTest<Integer, Object>>();
		for (int i = 0; i < 2000000; i++) {
			MapTest<Integer, Object> map = new MapTest<Integer, Object>();
			map.put(1, "a");
			list.add(map);
		}
		long beginTime = System.currentTimeMillis();
		System.out.println("begin*************time：" + beginTime);

		ConcurrentHashMap<Integer, Object> conMap = new ConcurrentHashMap<Integer, Object>();
		conMap.put(99999999, "0");
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
		for (int i = 0; i <= 19; i++) {
			cachedThreadPool.execute(new UserTestService3(list, conMap, lock));
			// System.out.println("线程" + i + "启动成功*************");
		}

		while (true) {
			// System.out.println("处理完成数量" + conMap.size() + "*************");
			System.out.println("处理完成数量" + conMap.size() + "*************");
			System.out.println("99999999:" + conMap.get(99999999));
			if (conMap.size() - 1 == list.size()) {
				break;
			} else {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		System.out.println("99999999:" + conMap.get(99999999));
		cachedThreadPool.shutdown();
		long endTime = System.currentTimeMillis();
		System.out.println("end*************time：" + endTime);
		System.out.println("用时*************time：" + (endTime - beginTime));
	}
}
