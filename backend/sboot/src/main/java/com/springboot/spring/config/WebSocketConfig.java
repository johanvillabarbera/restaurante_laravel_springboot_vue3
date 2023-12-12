package com.springboot.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

 @Override
 public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/websocket-endpoint")
    .setAllowedOrigins("http://bellidel.eu:8000")
    .withSockJS();
 }
 
 @Override
 public void configureMessageBroker(MessageBrokerRegistry config) {
 config.enableSimpleBroker("/topic");
 config.setApplicationDestinationPrefixes("/app");
 }
}