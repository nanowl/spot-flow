/*
package com.kh.project.spotflow.config.ws;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@RequiredArgsConstructor
@EnableWebSocket
@EnableWebSocketMessageBroker

public class WebSocketConfig implements WebSocketConfigurer {
  private final WebSocketHandler webSocketHandler;

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry.addHandler(webSocketHandler, "ws/notification").setAllowedOrigins("*");
  }
}*/
