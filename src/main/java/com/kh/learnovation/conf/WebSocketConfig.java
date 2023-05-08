package com.kh.learnovation.conf;

import com.kh.learnovation.domain.chatbot.handler.ChatbotHandler;
import com.kh.learnovation.domain.alarm.AlarmHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@RequiredArgsConstructor
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    private final ChatbotHandler chatbotHandler;

    private final AlarmHandler alarmHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatbotHandler, "ws/chatbot").setAllowedOrigins("*");
        registry.addHandler(alarmHandler, "ws/alarm").setAllowedOrigins("*");
    }
}
