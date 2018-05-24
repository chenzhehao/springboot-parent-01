package com.czh.springboot.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;

import com.czh.springboot.dto.websocket.ClientMessage;
import com.czh.springboot.dto.websocket.ServerMessage;

/**
 * <p>
 * Title: WebSocketAction.java
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
 * @date 2018年4月9日
 * @version 1.0
 */
@Controller
public class WebSocketAction {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 
	 * @param message
	 * @return
	 * @MessageMapping 可以作为发送消息、也可以作为订阅消息（订阅时没有订阅的响应）
	 */
	@MessageMapping("/sendTest")
	@SendTo("/topic/subscribeTest")
	public ServerMessage sendDemo(ClientMessage message) {
		logger.info("接收到了信息" + message.getName());
		return new ServerMessage("你发送的消息为:" + message.getName());
	}

	/**
	 * 
	 * @return
	 * @SubscribeMapping 只能作为订阅消息使用，可以实现请求-回应模式（订阅时有一次性响应，订阅方式为发送消息的前缀）
	 */
	@SubscribeMapping("/subscribeTest")
	public ServerMessage sub() {
		logger.info("XXX用户订阅了我。。。");
		return new ServerMessage("感谢你订阅了我。。。");
	}

	@Autowired
    private SimpMessagingTemplate messagingTemplate;
	
	@MessageMapping("/sendUser")
	@SendToUser("/sendUser")
	public ServerMessage sendUser(ClientMessage message) {
		return new ServerMessage("你发送的消息为:" + message.getName());
	}
	
	@Autowired
	SocketSessionRegistry webAgentSessionRegistry;
	/**
     * 同样的发送消息   只不过是ws版本  http请求不能访问
     * 根据用户key发送消息
     * @param message
     * @return
     * @throws Exception
     */
    @MessageMapping("/msg/hellosingle")
    public void greeting2(ClientMessage message) throws Exception {
    	logger.info("hellosingle用户订阅了我。。。");
        //这里没做校验
        String sessionId=webAgentSessionRegistry.getSessionIds(message.getId()).stream().findFirst().get();
        messagingTemplate.convertAndSendToUser(sessionId,"/topic/greetings",new ServerMessage("single send to："+message.getId()+", from:" + message.getName() + ",information:"+message.getInformation()),createHeaders(sessionId));
    }
    private MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }

}
