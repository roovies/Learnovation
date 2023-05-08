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

    private static final Map<String, WebSocketSession> sessionMap = new HashMap<>();
    private static final Map<String, WebSocketSession> anonymous = new HashMap<>();
    private static final Map<String, String> valid = new HashMap<>();
    @Autowired
    private UserService userService;
    //sendMessage
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage msg) throws Exception{
        String abc = msg.getPayload();
        JsonObject jsonObject = (JsonObject) JsonParser.parseString(abc);
        if(jsonObject.get("type").toString().equals("\"create\"")){
            String sender = jsonObject.get("sender").toString().replaceAll("\"","");
            if(sessionMap.get(sender) == null){
                sessionMap.put(sender, anonymous.get(session.getId()));
                valid.put(session.getId(), sender);
            }else{
                String id = sessionMap.get(sender).getId();
                TextMessage textMessage = new TextMessage("새로운 접속");
                sessionMap.get(sender).sendMessage(textMessage);
                sessionMap.get(sender).close();
                anonymous.remove(id);
                sessionMap.remove(sender);
                sessionMap.put(sender, anonymous.get(session.getId()));
                valid.put(session.getId(), sender);
            }
        }
        if(jsonObject.get("type").toString().equals("\"alarm\"")){
            String feeder = jsonObject.get("feeder").toString().replaceAll("\"","");
            TextMessage textMessage = new TextMessage("newAlarm");
            if(sessionMap.get(feeder) != null){
                sessionMap.get(feeder).sendMessage(textMessage);
            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
            anonymous.put(session.getId(), session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        anonymous.remove(session.getId());
        sessionMap.remove(valid.get(session.getId()));
        session.close();
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception){

    }
}
