package com.czh.springboot.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>
 * Title: ExceptionForGlobalHandler.java
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
@RestControllerAdvice
public class ExceptionForGlobalHandler {
	
	@ExceptionHandler(Exception.class)
	public Object myBankException(final HttpServletRequest request, final HttpServletResponse response,
			final Exception ex) {
		
//		return ex.getMessage();
		return ex.getCause().getMessage();
		
	}
}
