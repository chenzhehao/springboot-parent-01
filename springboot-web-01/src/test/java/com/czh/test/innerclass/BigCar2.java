package com.czh.test.innerclass;

/**
 * <p>
 * Title: BigCar2.java
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
public class BigCar2 extends BigCar {
	public BigCar2() {
		super(1);	
		System.out.println("BigCar2");
	}
//	public BigCar2(int i) {
//		super(i);
//		// TODO Auto-generated constructor stub
//	}

	public static void main(String[] args) {
		new BigCar2();// 子类创建时，会先自动调用父类的默认构造方法，父类如果还有父类，会在向上先调用其父来的构造方法
	}
}
