package com.czh.springboot.rocketmq;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**  
* <p>Title: RocketMqController.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2018</p>  
* <p>Company: www.chenzhehao.com</p>  
* @author chenzhehao  
* @date 2018年4月25日  
* @version 1.0  
*/
@RestController
public class RocketMqController  {

	@Resource
    private MQProducer mqProducer;
	
	@RequestMapping("testRocket")
	public void testRocketMq(){
		for (int i = 0; i < 3; i++) {
            mqProducer.sendMessage("Hello RocketMQ " + i, "TopicTest",
                    "TagTest"+(i%2), "Key" + i);
        }
        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
	
	@RequestMapping("testRocket2")
	public void testRocketMq2(){
		for (int i = 0; i < 3; i++) {
            mqProducer.sendMessageQueueSelector("Hello RocketMQ " + i, "TopicTest",
                    "TagTest"+(i%2), "Key" + i, i);
        }
        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}

}
