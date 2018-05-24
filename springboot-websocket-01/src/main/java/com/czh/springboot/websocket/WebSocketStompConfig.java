package com.czh.springboot.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**  
* <p>Title: WebSocketStompConfig.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2018</p>  
* <p>Company: www.chenzhehao.com</p>  
* @author chenzhehao  
* @date 2018年4月9日  
* @version 1.0  
*/
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketStompConfig extends AbstractWebSocketMessageBrokerConfigurer {

	/**
     * 注册stomp的端点
     */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// 允许使用socketJs方式访问，访问点为webSocketServer，允许跨域
        // 在网页上我们就可以通过这个链接
        // http://localhost:8080/webSocketServer
        // 来和服务器的WebSocket连接
//        registry.addEndpoint("/webSocketServer").setAllowedOrigins("*").withSockJS();
        registry.addEndpoint("/webSocketServer").withSockJS();
	}

	/**
     * 配置信息代理
     */
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		// 订阅Broker名称
		// 全局使用的消息前缀（客户端订阅路径上会体现出来）
        registry.enableSimpleBroker("/queue", "/topic");
        // 客户端向服务端发送时的主题上面需要加"/app"作为前缀；
        registry.setApplicationDestinationPrefixes("/app");
        // 点对点使用的订阅前缀（客户端订阅路径上会体现出来），不设置的话，默认也是/user/
        registry.setUserDestinationPrefix("/user/");
	}
	
	
	@Bean
    public SocketSessionRegistry SocketSessionRegistry(){
        return new SocketSessionRegistry();
    }
    @Bean
    public STOMPConnectEventListener STOMPConnectEventListener(){
        return new STOMPConnectEventListener();
    }

}
