package com.graduationproject.backend.backendwebsiteshoe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

/**
 * Configuration websockets.
 *
 * @author Mai Ngoc Dinh.
 */
@Configuration
public class SocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {

  @Override
  protected boolean sameOriginDisabled() {
    return true;
  }

  /**
   * Message security metadata source registry.
   *
   * @param messages messages
   */
  @Override
  protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
    messages
        .simpDestMatchers("/secured/**", "/secured/**/**").authenticated()
        .anyMessage().authenticated();
  }
}
