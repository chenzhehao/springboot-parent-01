package com.czh.springboot.service.impl.thread;
/**  
* <p>Title: UserTestService.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2018</p>  
* <p>Company: www.chenzhehao.com</p>  
* @author chenzhehao  
* @date 2018年5月8日  
* @version 1.0  
*/

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.springframework.beans.factory.annotation.Autowired;

public class UserTestService3 extends Thread {

	private List<MapTest<Integer, Object>> list;
	private ConcurrentHashMap<Integer, Object> conMap;
	ReentrantReadWriteLock lock;

	public UserTestService3(List<MapTest<Integer, Object>> list, ConcurrentHashMap<Integer, Object> conMap,
			ReentrantReadWriteLock lock) {
		this.list = list;
		this.conMap = conMap;
		this.lock = lock;
	}

	@Autowired
	public void run() {
		int count = 0;
		for (int i = 0; i < list.size(); i++) {
			MapTest<Integer, Object> object = list.get(i);
			if ("a".equals(object.get(1))) {
				object.put(1, "c");
				count++;
				conMap.put(i, object);
				// synchronized (conMap) {
				// // System.out.println("aa:"+conMap.get(99999999).toString());
				// conMap.put(99999999,
				// String.valueOf(Integer.valueOf(conMap.get(99999999).toString())
				// + 1));
				// }

				lock.writeLock().lock();
				try {
					conMap.put(99999999, String.valueOf(Integer.valueOf(conMap.get(99999999).toString()) + 1));
				} finally {
					lock.writeLock().unlock();
					;
				}
			}
		}
		System.out.println(count);
	}
}
