package com.czh.test;

import com.czh.springboot.vo.TestFanXingVo;

/**  
* <p>Title: TestFanXing.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2018</p>  
* <p>Company: www.chenzhehao.com</p>  
* @author chenzhehao  
* @date 2018年4月8日  
* @version 1.0  
*/
public class TestFanXing {

	public static void main(String[] args) {
		TestFanXingVo vo = new TestFanXingVo();
		TestFanXingA a = new TestFanXingA();
		a.setS("11");
		System.out.println(vo.getString(a, "getS", null));
	}
	
}
