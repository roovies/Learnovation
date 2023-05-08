package com.kh.learnovation.domain.chatbot.handler;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kh.learnovation.domain.chatbot.dto.ChattingDTO;
import com.kh.learnovation.domain.chatbot.entity.Chatting;
import com.kh.learnovation.domain.chatbot.service.ChatbotService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ChatbotHandler extends TextWebSocketHandler {

    private final ChatbotService chatbotService;

    public ChatbotHandler(ChatbotService chatbotService){
        this.chatbotService = chatbotService;
    }

    private Map<String, WebSocketSession> chattingRoom = new HashMap<>();
    private WebSocketSession adminSession;

    // message
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage msg) throws Exception {
        String payload = msg.getPayload();
        System.out.println("클라이언트가 전송한 메시지: " + payload);
        JsonObject jsonObject = (JsonObject) JsonParser.parseString(payload);
        String type = jsonObject.get("type").getAsString();

        if (type.equals("ADMIN")){ // 채팅서버에 접속한 사람이 관리자면 관리자 세션을 저장
            adminSession = session;
            String jsonMsg = "{\"type\": \"SUCCESS\", \"senderId\": \"53\"}";
            TextMessage replyMsg = new TextMessage(jsonMsg);
            adminSession.sendMessage(replyMsg);
        } else if(type.equals("CLIENT_ENTER")){ // Client 채팅서버 접속 시 Client세션 저장 및 세션ID를 통해 room 개설
            if(!chattingRoom.containsKey(session.getId())){
                Long userId = jsonObject.get("senderId").getAsLong();
                String name = jsonObject.get("name").getAsString();
                String message = jsonObject.get("message").getAsString();
                int saveRoomResult = chatbotService.saveRoom(session.getId());
                if (saveRoomResult>0){
                    chattingRoom.put(session.getId(), session);
                    ChattingDTO toSaveChattingDTO = ChattingDTO.builder()
                            .roomName(session.getId())
                            .senderId(userId)
                            .message(message)
                            .build();
                    int saveChattingResult = chatbotService.saveChatting(toSaveChattingDTO);
                    if (saveChattingResult>0){
                        //상담원 연결 시 현재 사용자 세션 정보를 저장
                        String jsonMsg = "{\"type\": \"CLIENT_ENTER\", \"sessionId\": \"" + session.getId() + "\", \"senderId\": \"" + String.valueOf(userId) + "\", \"message\": \"" + message + "\", \"name\": \"" + name + "\"}";
                        TextMessage replyMsg = new TextMessage(jsonMsg);
                        adminSession.sendMessage(replyMsg);
                    } else{
                        System.out.println("INITIAL CLIENT SENDING MESSAGE IS FAILED");
                    }
                } else{
                    System.out.println("ROOM CREATE IS FAILED");
                }
            } else{
                Long userId = jsonObject.get("senderId").getAsLong();
                String name = jsonObject.get("name").getAsString();
                String message = jsonObject.get("message").getAsString();
                ChattingDTO toSaveChattingDTO = ChattingDTO.builder()
                        .roomName(session.getId())
                        .senderId(userId)
                        .message(message)
                        .build();
                int saveChattingResult = chatbotService.saveChatting(toSaveChattingDTO);
                if (saveChattingResult>0){
                    //상담원 연결 시 현재 사용자 세션 정보를 저장
                    String jsonMsg = "{\"type\": \"CLIENT_ENTER\", \"sessionId\": \"" + session.getId() + "\", \"senderId\": \"" + String.valueOf(userId) + "\", \"message\": \"" + message + "\", \"name\": \"" + name + "\"}";
                    TextMessage replyMsg = new TextMessage(jsonMsg);
                    adminSession.sendMessage(replyMsg);
                } else{
                    System.out.println("INITIAL CLIENT SENDING MESSAGE IS FAILED");
                }
            }
        }

        if(type.equals("TALK")){ // Client -> ADMIN으로 메시지 전송
            String message = jsonObject.get("message").getAsString();
            Long userId = jsonObject.get("senderId").getAsLong();
            String name = jsonObject.get("name").getAsString();
            if (adminSession != null){ // ADMIN 세션이 존재해야 전송할 수 있음
                if(message.equals("!종료")){ // 상담 중 "!종료" 메시지로 상담을 종료할 경우
                    ChattingDTO toSaveChattingDTO = ChattingDTO.builder()
                            .roomName(session.getId())
                            .senderId(userId)
                            .message("<p>클라이언트가 [!종료] 명령어를 통해 상담을 종료했습니다.</p>")
                            .build();
                    int result = chatbotService.saveChatting(toSaveChattingDTO);
                    if(result>0){
                        // 1. Client의 종료 처리를 위해 Client에게 메시지 전송
                        String jsonMsg = "{\"type\": \"CONNECTION_FIN\", \"sessionId\": null, \"senderId\": \"chatbot\", \"message\": \"<p>상담이 종료되었습니다.<br>다시 상담을 원하시면 요청해주세요.</p>\"}";
                        TextMessage replyMsg = new TextMessage(jsonMsg);
                        session.sendMessage(replyMsg);
                        // 2. Admin이 Client 종료를 처리하기 위해 Admin에게 메시지 전송
                        jsonMsg = "{\"type\": \"CLIENT_MSG\", \"sessionId\": \"" + session.getId() + "\", \"senderId\": \"" + String.valueOf(userId) + "\", \"message\": \"<p>클라이언트가 [!종료] 명령어를 통해 상담을 종료했습니다.</p>\", \"name\": \"" + name + "\"}";
                        replyMsg = new TextMessage(jsonMsg);
                        adminSession.sendMessage(replyMsg);
                    } else{
                        System.out.println("CLIENT SENDING MESSAGE IS FAILED");
                    }
                    //=======================================================================
                    // 삭제 로직은 여기가 아닌, 소켓 소멸 시 삭제하는걸로 @@@@@@@@@@@@
                    // 채팅룸에서 세션 삭제
//                    chattingRoom.remove(session.getId());
                    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                } else{
                    ChattingDTO toSaveChattingDTO = ChattingDTO.builder()
                            .roomName(session.getId())
                            .senderId(userId)
                            .message(message)
                            .build();
                    int result = chatbotService.saveChatting(toSaveChattingDTO);
                    if(result>0){
                        String jsonMsg = "{\"type\": \"CLIENT_MSG\", \"sessionId\": \"" + session.getId() + "\", \"senderId\": \"" + String.valueOf(userId) + "\", \"message\": \"" + message + "\", \"name\": \"" + name + "\"}";
                        TextMessage replyMsg = new TextMessage(jsonMsg);
                        adminSession.sendMessage(replyMsg);
                    }
                    else {
                        System.out.println("CLIENT SENDING MESSAGE IS FAILED");
                    }
                }
            } else{
                String jsonMsg = "{\"type\": \"CONNECTION_FIN\", \"sessionId\": null, \"senderId\": \"chatbot\", \"message\": \"<p>죄송합니다. 😅</p><p>지금은 상담 가능한 시간이 아닙니다.<br></p><br><p><b>\uD83D\uDCCC 1:1 문의 운영 시간 안내</b></p><p> - 평일 : 10:00 ~ 18:00</p><p> - 점심 : 13:00 ~ 14:00</p><p>주말, 공휴일은 휴무입니다.</p>\"}";
                TextMessage replyMsg = new TextMessage(jsonMsg);
                session.sendMessage(replyMsg);
            }
        } else if (type.equals("ADMIN_MSG")){ // ADMIN -> Client로 메시지 전송
            String sessionId = jsonObject.get("sessionId").getAsString();
            String message = jsonObject.get("message").getAsString();
            WebSocketSession receiverSession = chattingRoom.get(sessionId);
            if (receiverSession != null){
                // DB에 저장
                ChattingDTO toSaveChattingDTO = ChattingDTO.builder()
                        .roomName(sessionId)
                        .senderId(53L)
                        .message(message)
                        .build();
                int savedChattingResult = chatbotService.saveChatting(toSaveChattingDTO);
                if(savedChattingResult>0){
                    // Client에게 전송
                    String jsonMsg = "{\"type\": \"ADMIN_MSG\", \"senderId\": \"admin\", \"message\": \"" + message + "\"}";
                    TextMessage replyMsg = new TextMessage(jsonMsg);
                    receiverSession.sendMessage(replyMsg);
                } else {
                    System.out.println("ADMIN SENDING MESSAGE IS FAILED");
                }
            } else{
                String jsonMsg = "{\"type\": \"CLOSED_SESSION\", \"senderId\": \"admin\", \"message\": \"존재하지 않는 세션입니다.\"}";
                TextMessage replyMsg = new TextMessage(jsonMsg);
                adminSession.sendMessage(replyMsg);
                System.out.println("[" + sessionId + "] THERE IS NOT SESSION.");
            }
        }

        if (type.equals("AUTO")){ // type : AUTO => 챗봇 자동응답
            String message = jsonObject.get("message").getAsString();
            String response = chatbotService.analyzePattern(message);
            if (response.contains("산업안전보건법")) {
                if (adminSession == null){ // 관리자 세션이 존재하지 않는 경우 (상담 시간 외)
                    String jsonMsg = "{\"type\": \"CONNECTION_FIN\", \"sessionId\": null, \"senderId\": \"chatbot\", \"message\": \"<p>죄송합니다. 😅</p><p>지금은 상담 가능한 시간이 아닙니다.<br></p><br><p><b>\uD83D\uDCCC 1:1 문의 운영 시간 안내</b></p><p> - 평일 : 10:00 ~ 18:00</p><p> - 점심 : 13:00 ~ 14:00</p><p>주말, 공휴일은 휴무입니다.</p>\"}";
                    TextMessage replyMsg = new TextMessage(jsonMsg);
                    session.sendMessage(replyMsg);
                } else{ // 관리자 세션이 존재하는 경우 (상담 가능 시간)
                    //상담원 연결 시 type을 TALK로 설정한 후, sessionId도 설정해줌
                    String jsonMsg = "{\"type\": \"CONNECTION_REQ\", \"sessionId\": \"" + session.getId() + "\", \"senderId\": \"chatbot\", \"message\": \"" + response +"\"}";
                    TextMessage replyMsg = new TextMessage(jsonMsg);
                    session.sendMessage(replyMsg);
                    jsonMsg = "{\"type\": \"ADMIN_MSG\", \"senderId\": \"admin\", \"message\": \"<p>상담 종료를 원하시면 <b>[!종료]</b>를 입력해주세요.👋</p>\"}";
                    replyMsg = new TextMessage(jsonMsg);
                    session.sendMessage(replyMsg);
                }

            } else{ // 상담요청이 아닌 그냥 챗봇 요청일 경우 type을 AUTO로 설정해서 응답
                String jsonMsg = "{\"type\": \"AUTO\", \"sessionId\": null, \"senderId\": \"chatbot\", \"message\": \"" + response +"\"}";
                TextMessage replyMsg = new TextMessage(jsonMsg);
                session.sendMessage(replyMsg);
            }
        }
    }

    // connection established
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println(session + "클라이언트 접속");
    }

    // connection closed
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        if (adminSession == null){
            chattingRoom.remove(session.getId());
            System.out.println(session + "클라이언트 접속 해제");
        }
        else {
            if(session.getId().equals(adminSession.getId())){
                adminSession = null;
                System.out.println(session + "관리자 접속 해제");
            } else {
                chattingRoom.remove(session.getId());
                System.out.println(session + "클라이언트 접속 해제");
                String message = "[CODE:SC9999] 클라이언트의 연결이 종료되었습니다.";
                // DB에 저장
                ChattingDTO toSaveChattingDTO = ChattingDTO.builder()
                        .roomName(session.getId())
                        .senderId(53L)
                        .message(message)
                        .build();
                int savedChattingResult = chatbotService.saveChatting(toSaveChattingDTO);
                if(savedChattingResult>0){
                    String jsonMsg = "{\"type\": \"SOCKET_CLOSE\", \"sessionId\": \"" + session.getId() + "\", \"senderId\": \"admin\", \"message\": \"" + message + "\"}";
                    TextMessage replyMsg = new TextMessage(jsonMsg);
                    adminSession.sendMessage(replyMsg);
                } else {
                    System.out.println("SOCKET CLOSE MESSAGE IS FAILED");
                }
            }
        }
        session.close();
    }
}
