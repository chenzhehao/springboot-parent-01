package com.czh.springboot.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import redis.clients.jedis.Jedis;

/**  
* <p>Title: RedisTool.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2018</p>  
* <p>Company: www.chenzhehao.com</p>  
* @author chenzhehao  
* @date 2018年4月8日  
* @version 1.0  
*/
public class RedisTool {
	@Autowired
    private static StringRedisTemplate stringRedisTemplate;
	public static String getStringValue(String key){
		
		return key;
	}
	
	public static String getString(String key) throws Exception{
		Jedis jedis = null;
        try {
            jedis = RedisContext.getJedis();
            String json = jedis.get(key);
            return json;
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
	}
}
