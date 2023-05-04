package com.kh.learnovation.domain.chatbot.conf;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kh.learnovation.domain.chatbot.service.ChatbotService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChatHandler extends TextWebSocketHandler {

    private final ChatbotService chatbotService;

    public ChatHandler(ChatbotService chatbotService){
        this.chatbotService = chatbotService;
    }
//    private static List<WebSocketSession> list = new ArrayList<>();
    // message
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage msg) throws Exception {
        String payload = msg.getPayload();
        JsonObject jsonObject = (JsonObject) JsonParser.parseString(payload);
        String type = jsonObject.get("type").getAsString();
        if(type.equals("TALK")){
            String message = jsonObject.get("message").getAsString();
            String response = chatbotService.analyzePattern(message);
            if (response.contains("산업안전보건법")){
                TextMessage replyMsg = new TextMessage(response);
                session.sendMessage(replyMsg);
            }
            else{
                TextMessage replyMsg = new TextMessage(response);
                session.sendMessage(replyMsg);
            }
        }
        else {
            System.out.println("---접속LOG---");
        }
//        if(type == 1){
//            String content = jsonObject.get("content").getAsString();
//            String response = chatbotService.analyzePattern(content);
//            TextMessage replyMsg = new TextMessage(response);
//            session.sendMessage(replyMsg);
//        }
//        TextMessage replyMsg = new TextMessage("반갑다.");
//        session.sendMessage(replyMsg);
    }

    // connection established
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        list.add(session);
        System.out.println(session + "클라이언트 접속");
    }

    // connection closed
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//        list.remove(session);
        System.out.println(session + "클라이언트 접속 해제");
        session.close();
    }
}
