package com.czh.test.transienttest;

import java.io.Serializable;

/**
 * <p>
 * Title: UserVo.java
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
 * @date 2018年5月9日
 * @version 1.0
 */
public class UserBo implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5882518858343516237L;
	private String name;
	private int age;
	private transient String password;
	public static String address = "上海";

	public static String getAddress() {
		return address;
	}

	public static void setAddress(String address) {
		UserBo.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
