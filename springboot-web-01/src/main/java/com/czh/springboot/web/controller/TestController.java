package com.czh.springboot.web.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.czh.springboot.redis.RedisTool;
import com.czh.springboot.service.ITestService;
import com.czh.springboot.service.impl.TestEmailService;

@RestController
public class TestController {

	@Autowired
	ITestService testService;
	
	@Autowired
	TestEmailService testEmailService;

	@RequestMapping("/test")
	public String test() {
		return testService.test();
	}

	@RequestMapping("/testLog")
	public String testLog() {
		return testService.testLog();
	}

	@RequestMapping("/getUser")
	public String getUser() {
		return testService.getUser();
	}
	
	@RequestMapping("/setUser")
	public String setUser(int id) {
		return testService.setUser(id);
	}
	
	@RequestMapping("/lockUser")
	public String lockUser(int id) {
		return testService.lockUser(id);
	}

	
	@RequestMapping("/sendEmail")
	public String sendEmail() {
		return testEmailService.sendEmail();
	}
	@RequestMapping("/sendEmail1")
	public String sendEmail1() throws MessagingException {
		return testEmailService.sendEmail1();
	}
	
	
}
