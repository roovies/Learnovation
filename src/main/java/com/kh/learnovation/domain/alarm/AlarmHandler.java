package com.kh.learnovation.domain.alarm;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kh.learnovation.domain.user.dto.UserDTO;
import com.kh.learnovation.domain.user.service.UserService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Component
public class AlarmHandler extends TextWebSocketHandler {

    public static final Map<String, WebSocketSession> sessionMap = new HashMap<>();

    @Autowired
    private UserService userService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage msg) throws Exception{
        String abc = msg.getPayload();
        JsonObject jsonObject = (JsonObject) JsonParser.parseString(abc);
        for (Map.Entry<String, WebSocketSession> arg : sessionMap.entrySet()) {
            if(session.getId() != arg.getKey()){
                arg.getValue().sendMessage(new TextMessage(msg.getPayload()));
            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Optional<UserDTO> userDTO = userService.getCurrentUser();
        if (userDTO.isPresent()){
            sessionMap.put(userDTO.get().getId().toString(), session);
        }else{
            sessionMap.put(session.getId(), session);
        }
        System.out.println(session + "클라이언트 접속");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

        sessionMap.remove(session.getId());
        System.out.println(session + "클라이언트 접속 해제");
        session.close();
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception){

    }
}
