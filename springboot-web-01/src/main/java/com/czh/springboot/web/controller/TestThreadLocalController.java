package com.czh.springboot.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.czh.springboot.service.ITestThreadLocal;

/**
 * <p>
 * Title: TestThreadLocalController.java
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
 * @date 2018年3月27日
 * @version 1.0
 */
@RestController
public class TestThreadLocalController {

	@Autowired
	ITestThreadLocal testThreadLocal;

	@RequestMapping(value = "testThreadLocal")
	public Object testThreadLocal() {
		System.out.println("********* 1 *******");
		for (int i = 0; i < 30; i++) {
			new Thread(new MyThread(testThreadLocal,"chenzhehao"+i)).start();
		}
		return "000000";
	}

	static class MyThread implements Runnable {
		ITestThreadLocal testThreadLocal;
		String userName;

		public MyThread(ITestThreadLocal testThreadLocal,String userName) {
			this.testThreadLocal = testThreadLocal;
			this.userName = userName;
		}

		public void run() {
			System.out.println("********* 2 *******");
			testThreadLocal.test2(userName);
		}

	}
}
