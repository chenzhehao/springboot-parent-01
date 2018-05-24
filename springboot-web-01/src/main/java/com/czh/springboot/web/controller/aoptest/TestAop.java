package com.czh.springboot.web.controller.aoptest;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.czh.springboot.to.aop.UserTo;
import com.czh.springboot.to.aop.UserTo2;

/**
 * <p>
 * Title: TestAop.java
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
@RestController
public class TestAop {

	@RequestMapping("aop1")
	public Object aop1(@Valid UserTo user, BindingResult bindingResult) {
		return "success欠去玩儿哈送发票人情味儿";
	}
	
	@RequestMapping("aop11")
	public Object aop11(@Valid UserTo2 user, BindingResult bindingResult) {
		return "success阿斯顿发黄色的房间里去玩儿";
	}

	//这种aop无法切到，参数只能是对象才可以，参数是对象时可以添加bindingResult，否则无法添加
	@RequestMapping("aop10")
	public Object aop10(@Valid @NotNull String time) {
		return "success";
	}
	
	@RequestMapping("aop2")
	public Object aop2(String time, String name) {
		return "success";
	}
	
	
}
