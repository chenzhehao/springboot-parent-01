package com.czh.springboot.webservice;

import javax.jws.WebService;

/**
 * <p>
 * Title: TestWebService.java
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
public class TestWebService {

	static{
		System.out.println("测试是否单例模式TestWebService");
	}
	public String testFun(int key) {
		return "key:" + key;
	}
}
