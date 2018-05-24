package com.czh.test.thread.sleep2wait;

/**
 * <p>
 * Title: ThreadService2.java
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
public class ThreadService2 {
	public int count = 0;

	public void sleepFun() {
		synchronized (this) {
			count = count + 1;
			System.out.println("sleep:" + count);
			try {
				Thread.sleep(2000);
				System.out.println("sleep2:" + count);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void waitFun() {
		synchronized (this) {
			count = count + 1;
			System.out.println("wait:" + count);
			try {
				this.wait(5000);
				System.out.println("wait2:" + count);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public synchronized void testFun(){
		System.out.println("service");
	}
}
