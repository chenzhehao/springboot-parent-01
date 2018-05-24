package com.czh.test.thread.sleep2wait;
/**  
* <p>Title: ThreadService.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2018</p>  
* <p>Company: www.chenzhehao.com</p>  
* @author chenzhehao  
* @date 2018年5月7日  
* @version 1.0  
*/
public class ThreadService {

	public int count = 0;
	
	public synchronized void sleepFun(){
		count = count+1;
		System.out.println("sleep:"+count);
		try {
			Thread.sleep(2000);
			System.out.println("sleep2:"+count);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void waitFun(){
		count = count+1;
		System.out.println("wait:"+count);
		try {
			this.wait(5000);
			System.out.println("wait2:"+count);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
