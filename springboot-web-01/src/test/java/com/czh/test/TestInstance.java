package com.czh.test;

import com.czh.springboot.bo.TestInstanceBo;

/**  
* <p>Title: TestInstance.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2018</p>  
* <p>Company: www.chenzhehao.com</p>  
* @author chenzhehao  
* @date 2018年4月8日  
* @version 1.0  
*/
public class TestInstance {
	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 10; i++) {
			TestInstanceBo instanceBo = TestInstanceBo.getInstance();
			TestInstanceBo instanceBo1 = new TestInstanceBo();
			System.out.println(instanceBo);
			System.out.println(instanceBo1);
		}
		
	}
}
