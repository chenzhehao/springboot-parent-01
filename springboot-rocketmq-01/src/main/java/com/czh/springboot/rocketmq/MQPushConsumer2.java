package com.czh.springboot.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

/**  
* <p>Title: MQPushConsumer.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2018</p>  
* <p>Company: www.chenzhehao.com</p>  
* @author chenzhehao  
* @date 2018年4月25日  
* @version 1.0  
*/
@Component
public class MQPushConsumer2 implements MessageListenerConcurrently {

	private static final Logger LOGGER = LoggerFactory.getLogger(MQPushConsumer2.class);

	@Value("${spring.rocketmq.namesrvAddr}")
	private String namesrvAddr;

	private final DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("TestRocketMQPushConsumer2");

	/**
	 * 初始化
	 *
	 * @throws MQClientException
	 */
	@PostConstruct
	public void start() {
		try {
			LOGGER.info("MQ：启动消费者1");

			consumer.setNamesrvAddr(namesrvAddr);
			// 从消息队列头开始消费
			consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
			// 集群消费模式
			consumer.setMessageModel(MessageModel.CLUSTERING);
			// 订阅主题
			consumer.subscribe("TopicTest", "TagTest0||TagTest1");
			// 注册消息监听器
			consumer.registerMessageListener(this);
			consumer.setVipChannelEnabled(false);  
			// 启动消费端
			consumer.start();
			
		} catch (MQClientException e) {
			LOGGER.error("MQ：启动消费者失败：{}-{}", e.getResponseCode(), e.getErrorMessage());
			throw new RuntimeException(e.getMessage(), e);
		}

	}

	/**
	 * 消费消息
	 * 
	 * @param msgs
	 * @param context
	 * @return
	 */
	@Override
	public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
		int index = 0;
		try {
			LOGGER.info("MQ：消费者接收新信息1:"+msgs.size());
			for (; index < msgs.size(); index++) {
				MessageExt msg = msgs.get(index);

				String messageBody = new String(msg.getBody(), RemotingHelper.DEFAULT_CHARSET);

				LOGGER.info("MQ：消费者接收新信息1: {} {} {} {} {}", msg.getMsgId(), msg.getTopic(), msg.getTags(), msg.getKeys(),
						messageBody);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			if (index < msgs.size()) {
				context.setAckIndex(index + 1);
			}
		}
		return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
	}

	@PreDestroy
	public void stop() {
		if (consumer != null) {
			consumer.shutdown();
			LOGGER.error("MQ：关闭消费者");
		}
	}

}