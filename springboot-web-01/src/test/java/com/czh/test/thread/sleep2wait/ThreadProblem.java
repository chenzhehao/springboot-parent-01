package com.czh.test.thread.sleep2wait;

/**
 * @Title: ThreadProblem.java
 * @Description:
 * @Copyright: Copyright (c) 2018
 * @Company: www.chenzhehao.com
 * @author chenzhehao
 * @date 2018年6月12日
 * @version 1.0
 */
public class ThreadProblem extends Thread {

	int a;
	
	public ThreadProblem(int a){
		this.a = a;
	}
	
	@Override
	public void run() {
		CommonService.num = a;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(CommonService.num != a){
			System.out.println("ThreadProblem.a: "+a+"  CommonService.num: "+CommonService.num);
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			ThreadProblem t = new ThreadProblem(i);
			t.start();
		}
	}

}

class CommonService {
	public static int num = 0;
}