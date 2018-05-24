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

import org.springframework.beans.factory.annotation.Autowired;

public class UserTestService2 extends Thread {

	private List<Object> list;
	private int numBegin;
	private int numEnd;
	private MapTest<Integer, Object> conMap;

	public UserTestService2(List<Object> list, int numBegin, int numEnd, MapTest<Integer, Object> conMap) {
		this.list = list;
		this.numBegin = numBegin;
		this.numEnd = numEnd;
		this.conMap = conMap;
	}

	@Autowired
	public void run() {
//		System.out.println("开始处理"+numBegin+"到"+numEnd+"的数据*************");
		for (int i = numBegin; i <= numEnd; i++) {
//			System.out.println("开始处理"+i+"的数据*************");
			Object object = list.get(i);
			//添加一些业务逻辑
//			try {
//				Thread.sleep(20);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			conMap.put(i, object);
		}
	}
}
