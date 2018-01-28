package com.storyshell.contentdataservices;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.AbstractMessageBrokerConfiguration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractMessageBrokerConfiguration {

	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/push-notification","/user","/follow","/notifications","/client","/hit","/push");
		config.setApplicationDestinationPrefixes("/app/v1");
		config.setUserDestinationPrefix("/user");
	}

	@Override
	protected SimpUserRegistry createLocalUserRegistry() {
		return null;
	}

	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/push-notification-socket").withSockJS();
	}

}
