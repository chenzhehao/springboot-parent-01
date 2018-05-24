package com.czh.springboot.schedule.websocket;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.czh.springboot.dto.websocket.ServerMessage;
import com.czh.springboot.websocket.SocketSessionRegistry;

/**  
* <p>Title: WebsocketPushMessageTest.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2018</p>  
* <p>Company: www.chenzhehao.com</p>  
* @author chenzhehao  
* @date 2018年4月9日  
* @version 1.0  
*/
@Component
public class WebsocketPushMessageTest {
	@Autowired
    private SimpMessagingTemplate messagingTemplate;

	@Scheduled(cron = "0/10 * * * * ?") // 每5秒执行一次
	public void scheduler() {
		//客户端只要订阅了/topic/subscribeTest主题，调用这个方法即可
		messagingTemplate.convertAndSend("/topic/subscribeTest", new ServerMessage("服务器主动推的数据/topic/subscribeTest"));
	}
    
	@Scheduled(cron = "0/10 * * * * ?") // 每5秒执行一次
	public void scheduler1() {
		//客户端只要订阅了/topic/subscribeTest主题，调用这个方法即可
		messagingTemplate.convertAndSend("/topic/sendTest", new ServerMessage("服务器主动推的数据/topic/sendTest"));
	}
	
	@Scheduled(cron = "0/10 * * * * ?") // 每5秒执行一次
	public void scheduler2() {
		//客户端只要订阅了/topic/subscribeTest主题，调用这个方法即可
		messagingTemplate.convertAndSend("/queue/subscribeTest", new ServerMessage("服务器主动推的数据/queue/subscribeTest"));
	}
	
	@Autowired
	SocketSessionRegistry webAgentSessionRegistry;
	@Scheduled(cron = "0/10 * * * * ?") // 每5秒执行一次
	public void scheduler3() {
		//客户端只要订阅了/topic/subscribeTest主题，调用这个方法即可
		ConcurrentMap<String, Set<String>> sessions = webAgentSessionRegistry.getAllSessionIds();
		for(Map.Entry<String, Set<String>> entry : sessions.entrySet()){
			String userName = entry.getKey();
			String sessionId = entry.getValue().stream().findFirst().get();
			messagingTemplate.convertAndSendToUser(sessionId,"/topic/greetings",new ServerMessage("single send to："+userName+", from:system ,information:1231asd阿萨德的"),createHeaders(sessionId));
		}
	}
	private MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }
	
}
