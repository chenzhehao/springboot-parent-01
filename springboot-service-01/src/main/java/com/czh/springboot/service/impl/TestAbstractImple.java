package com.czh.springboot.service.impl;

import com.czh.springboot.service.TestAbstract;

/**  
* <p>Title: TestAbstractImple.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2018</p>  
* <p>Company: www.chenzhehao.com</p>  
* @author chenzhehao  
* @date 2018年5月15日  
* @version 1.0  
*/
public class TestAbstractImple extends TestAbstract {

	@Override
	public void get1() {
		System.out.println(this.a);
	}

}
