package com.czh.springboot.bo;
/**  
* <p>Title: TestInstanceBo.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2018</p>  
* <p>Company: www.chenzhehao.com</p>  
* @author chenzhehao  
* @date 2018年4月8日  
* @version 1.0  
*/
public class TestInstanceBo {
	public static TestInstanceBo instance;
	public static TestInstanceBo getInstance(){
		if(instance == null) {
			instance = new TestInstanceBo();
		}
		return instance;
	}
	static{
		System.out.println("2222");
	}
}
