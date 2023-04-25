package com.graduationproject.backend.backendwebsiteshoe.config;

import com.graduationproject.backend.backendwebsiteshoe.service.AuthChannelInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Configuration websockets.
 *
 * @author Mai Ngoc Dinh.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfig
    implements WebSocketMessageBrokerConfigurer {

  @Autowired
  private AuthChannelInterceptor channelInterceptor;

  /**
   * Register STOMP endpoints mapping each to a specific URL and (optionally)
   * enabling and configuring SockJS fallback options.
   *
   * @param registry registry
   */
  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
//    registry.addEndpoint("/chat");
    registry.addEndpoint("/chat")
        .setAllowedOriginPatterns("http://localhost:5173/")
        .withSockJS();
  }

  /**
   * Configure message broker options.
   *
   * @param config config
   */
  @Override
  public void configureMessageBroker(MessageBrokerRegistry config) {
    config.enableSimpleBroker("/topic/");
    config.setApplicationDestinationPrefixes("/app");
  }

  @Override
  public void configureClientInboundChannel(ChannelRegistration registration) {

    // Add our interceptor for authentication/authorization
    registration.interceptors(channelInterceptor);

  }
}
