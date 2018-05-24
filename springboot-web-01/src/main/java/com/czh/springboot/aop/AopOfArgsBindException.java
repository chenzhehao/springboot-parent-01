package com.czh.springboot.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
 * <p>
 * Title: AopOfArgsBindException.java
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
@Aspect
@Component
public class AopOfArgsBindException implements Ordered {

	@Pointcut(value = "execution(* com.czh.springboot.web.controller..*.*(..)) && args(..,bindingResult)",argNames="..,bindingResult")
	public void beforeCheck(BindingResult bindingResult) {
	}

	/**
	 * // 可以使用@Pointcut定义，也可以直接定义,如下两种都可以
	 * pointcut中只能用&& 不能用and
	 * 参数注意：函数里都要有
	 * 
	 * 可以混合使用
	 * @Before(value = "pointcut1(param) && pointcut2(secure)"
	 * 
	 * <p>Title: checkArgsException</p>  
	 * <p>Description: </p>  
	 * @param bindingResult
	 * @throws Exception 
	 */
	@Before("beforeCheck(bindingResult)") 
//	@Before("execution(* com.czh.springboot.web.controller..*.*(..)) and args(..,bindingResult)")
	public void checkArgsException(BindingResult bindingResult) throws Exception {
		System.out.println("***************************@Before***************************");
		System.out.println("参数bindingResult=" + bindingResult);
		if (bindingResult.hasErrors()) {
			StringBuffer messages = new StringBuffer();
			for (ObjectError error : bindingResult.getAllErrors()) {
				messages.append(error.getDefaultMessage().toString() + ",");
			}
			messages.deleteCharAt(messages.length() - 1);
			System.out.println("异常信息messages=" + messages);
			throw new Exception(messages.toString());
		}
	}

	@AfterReturning(pointcut = "execution(* com.czh.springboot.web.controller..*.*(..)) and args(time, name)", returning = "returnValue")
	public void afterFun(String time, String name , Object returnValue) {
		System.out.println("***************************@AfterReturning***************************");
		System.out.println("参数time=" + time);
		System.out.println("参数name=" + name);
		System.out.println("返回value=" + returnValue);
	}

	@Override
	public int getOrder() {
		return 9000;
	}

}
