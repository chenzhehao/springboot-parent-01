package com.czh.springboot.schedule.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Component;

import com.czh.springboot.service.impl.thread.MapTest;
import com.czh.springboot.service.impl.thread.UserTestService2;

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
public class UserInfoThread2 {

	public static void main(String[] args) {
		List<Object> list = new ArrayList<Object>();
		for (int i = 0; i < 2000000; i++) {
			String s = i + "";
			list.add(s);
		}
		long beginTime = System.currentTimeMillis();
		System.out.println("begin*************time："+beginTime);

		int num = list.size() % 20 == 0 ? list.size() / 20 : list.size() / 19;
		MapTest<Integer, Object> conMap = new MapTest<Integer, Object>();
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		for (int i = 0; i <= 19; i++) {
			int numBegin = i * num;
			int numEnd = i == 19 ? (list.size() - 1) : (i + 1) * num - 1;
			cachedThreadPool.execute(new UserTestService2(list, numBegin, numEnd, conMap));
			System.out.println("线程" + i + "启动成功*************");
		}

		while (true) {
			System.out.println("处理完成数量" + conMap.size() + "*************");
			if (conMap.size() == list.size()) {
				System.out.println();
				break;
			} 
		}
		cachedThreadPool.shutdown();
		long endTime = System.currentTimeMillis();
		System.out.println("end*************time："+endTime);
		System.out.println("用时*************time："+(endTime-beginTime));
	}
}
