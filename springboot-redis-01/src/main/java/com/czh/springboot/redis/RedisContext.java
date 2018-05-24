package com.czh.springboot.redis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.util.Pool;

/**
 * <p>
 * Title: RedisContext.java
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
 * @date 2018年4月8日
 * @version 1.0
 */

@Component
public class RedisContext {

	private static String redisHost;

	@Value("${spring.redis.host}")
	public void setRedisHost(String redisHost) {
		RedisContext.redisHost = redisHost;
	}

	private static int redisPort;

	@Value("${spring.redis.port}")
	public void setRedisPort(int redisPort) {
		RedisContext.redisPort = redisPort;
	}

	private static String redisPassword;

	@Value("${spring.redis.password}")
	public void setRedisPassword(String redisPassword) {
		RedisContext.redisPassword = redisPassword;
	}

	private static int timeout;

	@Value("${spring.redis.timeout}")
	public void setTimeout(int timeout) {
		RedisContext.timeout = timeout;
	}

	private static int maxActive;

	@Value("${spring.redis.pool.max-active}")
	public void setMaxActive(int maxActive) {
		RedisContext.maxActive = maxActive;
	}

	private static int maxWait;

	@Value("${spring.redis.pool.max-wait}")
	public void setMaxWait(int maxWait) {
		RedisContext.maxWait = maxWait;
	}

	private static int maxIdle;

	@Value("${spring.redis.pool.max-idle}")
	public void setMaxIdle(int maxIdle) {
		RedisContext.maxIdle = maxIdle;
	}

	private static int minIdle;

	@Value("${spring.redis.pool.min-idle}")
	public void setMinIdle(int minIdle) {
		RedisContext.minIdle = minIdle;
	}

	private static int database;

	@Value("${spring.redis.database}")
	public void setDatabase(int database) {
		RedisContext.database = database;
	}

	private static Set<HostAndPort> nodes;

	@Value("${spring.redis.cluster.nodes}")
	public void setNodes(String nodes1) {
		HostAndPort node1 = new HostAndPort("192.168.57.3", 6801);
		HostAndPort node2 = new HostAndPort("192.168.57.3", 6802);
		HostAndPort node3 = new HostAndPort("192.168.57.3", 6803);
		HostAndPort node4 = new HostAndPort("192.168.57.3", 6804);
		HostAndPort node5 = new HostAndPort("192.168.57.3", 6805);
		HostAndPort node6 = new HostAndPort("192.168.57.3", 6806);
		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
		nodes.add(node1);
		nodes.add(node2);
		nodes.add(node3);
		nodes.add(node4);
		nodes.add(node5);
		nodes.add(node6);
		RedisContext.nodes = nodes;
	}

	public static Jedis getJedis() {
		return getPool().getResource();
	}

	public static JedisCluster getJedisCluster() {
		return getInstance().jedisConfigCluster;
	}

	private static Pool<Jedis> getPool() {
		return getInstance()._jedisPool;
	}

	private static RedisContext getInstance() {
		return SingletonFactory.instance;
	}

	private Pool<Jedis> _jedisPool;
	private JedisCluster jedisConfigCluster;

	public RedisContext(int flag) {
		init();
	}

	public RedisContext() {
		System.out.println("123123");
	}

	public void init() {

		String sentinelstr = "";
		String[] sentinelArray = sentinelstr.split(",");
		Set<String> sentinels1 = new HashSet<String>(Arrays.asList(sentinelArray));

		JedisPoolConfig jedisConfig = new JedisPoolConfig();
		jedisConfig.setMaxIdle(maxIdle);
		jedisConfig.setMinIdle(minIdle);
		jedisConfig.setMaxTotal(maxActive);
		jedisConfig.setMaxWaitMillis(maxWait);
		jedisConfig.setTestOnBorrow(false);

		// _jedisPool = new JedisSentinelPool("", sentinels1, jedisConfig,
		// redisPassword);

		// _jedisPool = new JedisPool(jedisConfig, redisHost, redisPort,
		// timeout, redisPassword, database);

		List list = new ArrayList();
		jedisConfigCluster = new JedisCluster(nodes, jedisConfig);

	}

	private static class SingletonFactory {
		private static RedisContext instance = new RedisContext(1);
	}

}
