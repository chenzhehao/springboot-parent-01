package com.czh.springboot.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.czh.springboot.redis.RedisContext;
import com.czh.springboot.redis.RedisTool;

/**
 * <p>
 * Title: TestRedis.java
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
 * @date 2018年5月19日
 * @version 1.0
 */
@RestController
public class TestRedis {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Autowired
	private RedisTemplate redisTemplate;

	@RequestMapping("/setRedis")
	public void setRedis(String value, String key) {
		stringRedisTemplate.opsForValue().set(key, value);
	}

	@RequestMapping("/getRedis")
	public String getRedis(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}

	@RequestMapping("/setRedis1")
	public void setRedis1(String value, String key) {
		System.out.println(redisTemplate.opsForValue().get("iy1"));
		redisTemplate.opsForValue().set("iy1", "iy1222");;
	}
	
	@RequestMapping("/getRedis1")
	public String getRedis1(String key) throws Exception {
		System.out.println(redisTemplate.opsForHash().get("a", "b"));	
		return "";
	}
	
	@RequestMapping("/getRedis2")
	public String getRedis2(String key) throws Exception {
		System.out.println(RedisContext.getJedisCluster().get(key));	
		return "";
	}
	
}
