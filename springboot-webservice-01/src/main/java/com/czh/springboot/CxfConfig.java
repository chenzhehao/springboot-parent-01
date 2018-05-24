package com.czh.springboot;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.czh.springboot.webservice.TestWebService;
import com.czh.springboot.webservice.TestWebService2;

/**
 * <p>
 * Title: CxfConfig.java
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
 * @date 2018年4月12日
 * @version 1.0
 */

@Configuration
public class CxfConfig {

	/**
	 * ServletRegistrationBean用来注册新的servlet
	 * <p>Title: dispatcherServletForWebService</p>  
	 * <p>Description: 名字不要定义为dispatcherServlet，springboot默认的servlet名字为dispatcherServlet，一样时会覆盖掉</p>  
	 * @return
	 */
	@Bean
	public ServletRegistrationBean dispatcherServletForWebService() {
		return new ServletRegistrationBean(new CXFServlet(), "/demo/*");
	}

	@Bean(name = Bus.DEFAULT_BUS_ID)
	public SpringBus springBus() {
		return new SpringBus();
	}

	@Bean
	public TestWebService demoService() {
		return new TestWebService();
	}

	@Bean
	public Endpoint endpoint() {
		EndpointImpl endpoint = new EndpointImpl(springBus(), demoService());
		endpoint.publish("/api");
		return endpoint;
	}

	@Bean
	public Endpoint endpoint2() {
		EndpointImpl endpoint = new EndpointImpl(springBus(), new TestWebService2());
		endpoint.publish("/api2");
		return endpoint;
	}
}