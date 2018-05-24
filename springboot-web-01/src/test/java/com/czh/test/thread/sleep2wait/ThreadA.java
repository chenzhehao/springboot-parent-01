package com.czh.test.thread.sleep2wait;
/**  
* <p>Title: ThreadA.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2018</p>  
* <p>Company: www.chenzhehao.com</p>  
* @author chenzhehao  
* @date 2018年5月7日  
* @version 1.0  
*/
public class ThreadA implements Runnable{

	public ThreadService servie;
	public ThreadService2 servie2;

	public ThreadA(ThreadService servie){
		this.servie = servie;
	}
	
	public ThreadA(ThreadService2 servie2){
		this.servie2 = servie2;
	}
	
	
	@Override
	public void run() {
		servie2.sleepFun();
	}
	
	
}
