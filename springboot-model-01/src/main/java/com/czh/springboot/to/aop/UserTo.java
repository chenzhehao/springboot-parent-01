package com.czh.springboot.to.aop;

import javax.validation.constraints.NotNull;

import com.czh.springboot.common.annotation.TestAnnatation;

/**
 * <p>
 * Title: UserTo.java
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
 * @date 2018年5月22日
 * @version 1.0
 */
public class UserTo {

	@NotNull
	@TestAnnatation(testArg="100",testArg3=300)
	public String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
