package com.czh.test;

import com.czh.springboot.redis.RedisTool;

/**  
* <p>Title: TestRedis.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2018</p>  
* <p>Company: www.chenzhehao.com</p>  
* @author chenzhehao  
* @date 2018年4月8日  
* @version 1.0  
*/
public class TestRedis {
	
	public static void main(String[] args) throws Exception {
		System.out.println(RedisTool.getString("aa"));
	}

}
