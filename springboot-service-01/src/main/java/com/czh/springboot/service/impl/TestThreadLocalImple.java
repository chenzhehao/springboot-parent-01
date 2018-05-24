package com.czh.springboot.service.impl;

import org.springframework.stereotype.Service;

import com.czh.springboot.service.ITestThreadLocal;
import com.czh.springboot.vo.TestThreadLocalVo;

@Service("testThreadLocal")
public class TestThreadLocalImple implements ITestThreadLocal {

	private static final ThreadLocal<TestThreadLocalVo> value = new ThreadLocal<TestThreadLocalVo>() {
		@Override
		protected TestThreadLocalVo initialValue() {
			return new TestThreadLocalVo();
		}
	};

	/**
	 * 使用ThreadLocal进行变量管理
	 */
	@Override
	public void test(String userName) {
		TestThreadLocalVo testThreadLocalVo = value.get();
		testThreadLocalVo.setUserName(userName);
		testThreadLocalVo.setValue(0);
		for (int i = 0; i < 100; i++) {
			testThreadLocalVo.setValue(testThreadLocalVo.getValue() + i);
			System.out.println(
					"UserName: " + testThreadLocalVo.getUserName() + "    Value: " + testThreadLocalVo.getValue());
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	
	public TestThreadLocalVo testThreadLocalVo2 = new TestThreadLocalVo();
	@Override
	public void test2(String userName) {
		TestThreadLocalVo testThreadLocalVo = testThreadLocalVo2;
		testThreadLocalVo.setUserName(userName);
		testThreadLocalVo.setValue(0);
		for (int i = 0; i < 100; i++) {
			testThreadLocalVo.setValue(testThreadLocalVo.getValue() + i);
			System.out.println(
					"UserName: " + testThreadLocalVo.getUserName() + "    Value: " + testThreadLocalVo.getValue());
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
