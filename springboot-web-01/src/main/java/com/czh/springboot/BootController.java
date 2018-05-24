package com.czh.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author chenzhehao
 *
 *@SpringBootApplication
 *使用了@SpringBootApplication注解的话，系统会去入口类的同级包以及下级包中去扫描实体类
 *
 *@ComponentScan(basePackages={"com.czh.springboot"})
 *手动配置需要扫描的包路径
 *
 *@EnableScheduling
 *增加支持定时任务的注解
 *
 *
 */
@RestController
//@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.czh.springboot"})
@EnableScheduling//增加支持定时任务的注解
@EnableTransactionManagement//启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
public class BootController extends SpringBootServletInitializer {

	/**
	 * 重建BootController，部署到javaEE容器，可部署在tomcat中
	 */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BootController.class);
    }
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(BootController.class, args);
	}

	@RequestMapping("/")
	public String home() {
		return "hello world,spring boot";
	}

	
}
