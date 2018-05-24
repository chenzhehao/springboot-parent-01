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
public class ThreadB implements Runnable{

	public ThreadService servie;

	public ThreadB(ThreadService servie){
		this.servie = servie;
	}
	public ThreadService2 servie2;
	
	public ThreadB(ThreadService2 servie2){
		System.out.println(servie2);
		this.servie2 = servie2;
	}
	
	@Override
	public void run() {
		servie2.waitFun();
	}
	 
}
