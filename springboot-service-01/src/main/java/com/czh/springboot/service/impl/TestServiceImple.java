package com.czh.springboot.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.czh.springboot.dao.UserDao;
import com.czh.springboot.dao.mapper.UserMapper;
import com.czh.springboot.service.ITestService;

@Service("testService")
public class TestServiceImple implements ITestService {

	private final Logger log = LoggerFactory.getLogger(TestServiceImple.class);
	private final Logger logService = LoggerFactory.getLogger(TestServiceImple.class);
	private final Logger logService2 = LoggerFactory.getLogger("SERVICE_LOG");

	@Override
	public String getUser1() {
		return TestServiceImple.a;

	}

	public static String getUser2(int i) {
		return "b";
	}

	public static final String a = "2";

	@Override
	public String test() {
		System.out.println(this);
		
		this.getUser1();
		log.info("test service of info");
		log.error("test service of error");
		return "test service test" ;
	}

	@Override
	public String testLog() {
		logService.info("test service of info in service1");
		logService2.info("test service of info in service2");
		return "test service testLog";
	}

	@Autowired
	private UserMapper userMapper;

	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
	public String getUser() {
		/**
		 * for update为排它锁 lock in share mode
		 * 
		 */
		
		System.out.println(userMapper.getClass().getInterfaces());
		
		System.out.println("带for update更新用户信息begin");
		UserDao userDto = userMapper.getOneForUpdate(16);
		// UserDao userDto = userMapper.getOneLockShare(16);
		System.out.println("带for update更新用户信息：" + userDto.getId() + "====" + userDto.getName());
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// int flag = userMapper.updateUserOfNameById(userDto);
		System.out.println("带for update更新用户信息end");
		return "test service getUser";
	}

	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
	public String setUser(int id) {
		UserDao userDto = userMapper.getOneLockShare(id);
		// UserDao userDto = userMapper.getOne(16);
		System.out.println(id + "更新用户信息：" + userDto.getId() + "====" + userDto.getName());

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		userDto.setId(id);
		userDto.setName("10101010");
		System.out.println(id + "更新用户信息begin：");
		int flag = userMapper.updateUserOfNameById(userDto);
		System.out.println(id + "更新用户信息end：" + flag);

		return "success";
	}

	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
	public String lockUser(int id) {
		userMapper.lockUser();
		System.out.println("lock user");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("unlock user");
//		userMapper.unlockUser();
		return "success";
	}
}
