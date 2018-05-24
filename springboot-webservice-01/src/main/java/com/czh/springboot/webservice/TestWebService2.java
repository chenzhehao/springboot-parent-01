package com.czh.springboot.webservice;

import javax.jws.WebService;

/**
 * <p>
 * Title: TestWebService2.java
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
 * @date 2018年4月12日
 * @version 1.0
 */
@WebService
public class TestWebService2 {
	static{
		System.out.println("测试是否单例模式TestWebService2");
	}
	/**
	 * <p>Title: testFun2</p>  
	 * <p>Description: </p>  
	 * @param key
	 * @return
	 */
	public String testFun2(int key) {
		return "key:" + key;
	}

	/**
	 * 
	 * <p>Title: testFun3</p>  
	 * <p>Description: </p>  
	 * @param s
	 * @param key
	 * @return
	 */
	public String testFun3(String s, int key) {
		return "s:" + s + " key:" + key;
	}
}
