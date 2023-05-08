package com.kh.learnovation.domain.chatbot.controller;

import com.kh.learnovation.domain.chatbot.dto.ChattingDTO;
import com.kh.learnovation.domain.chatbot.service.ChatbotService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class ChatbotController {
    private final ChatbotService chatbotService;
    public ChatbotController(ChatbotService chatbotService){
        this.chatbotService = chatbotService;
    }

    @GetMapping("/admin/chatting")
    public String showChattingList(){
        return "admin/Chatting";
    }

    @PostMapping("/admin/chatting/list")
    @ResponseBody
    public List<ChattingDTO> showMessageList(@RequestBody Map<String, String> map){
        String roomName = map.get("sessionId");
        List<ChattingDTO> chattingDTOs = chatbotService.findChattingByRoomName(roomName);
        return chattingDTOs;
    }

    @PostMapping("/admin/chatting/delete")
    @ResponseBody
    public String deleteSession(@RequestBody Map<String, String> map){
        String roomName = map.get("sessionId");
        int result = chatbotService.deleteRoom(roomName);
        if (result>0){
            return "success";
        } else{
            return "fail";
        }
    }
}
