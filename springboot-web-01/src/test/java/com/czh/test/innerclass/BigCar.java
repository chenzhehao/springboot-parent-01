package com.czh.test.innerclass;

/**
 * <p>
 * Title: BigCar.java
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
 * @date 2018年5月11日
 * @version 1.0
 */
public class BigCar extends Car {
//	public BigCar(){
//		System.out.println("BigCar");
//	}
	public BigCar(int i){
		System.out.println("222");
	}
	class Tyre {
		Tyre() {
			System.out.println("BigCar new Tyre()");
		}
	}


}