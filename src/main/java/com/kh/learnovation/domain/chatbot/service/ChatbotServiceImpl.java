package com.kh.learnovation.domain.chatbot.service;

import com.kh.learnovation.domain.chatbot.entity.Chatbot;
import com.kh.learnovation.domain.chatbot.repository.ChatbotRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ChatbotServiceImpl implements ChatbotService{

    private final ChatbotRepository chatbotRepository;
    public ChatbotServiceImpl(ChatbotRepository chatbotRepository){
        this.chatbotRepository = chatbotRepository;
    }

    private List<String> helloPatterns = new ArrayList<>(Arrays.asList("안녕", "반갑", "하이", "오하요", "헬로", "hello", "hi", "방가", "반가워", "하잉", "ㅎㅇ", "ㅎ2", "하2"));
    private List<String> lostPatterns = new ArrayList<>(Arrays.asList("잃어", "분실", "까먹", "찾", "잊어", "복구"));
    private List<String> contactPatterns = new ArrayList<>(Arrays.asList("상담", "1:1", "1대1", "일대일", "연결", "문의", "대화", "필요", "신청", "해주", "도와", "서비스", "연락", "해야", "어떻게"));

    @Override
    public String analyzePattern(String message) {
        System.out.println("클라이언트 메시지 : " + message);
        int matchCount = 0;
        for (String pattern : contactPatterns){
            if (message.contains(pattern)){
                Optional<Chatbot> chatbot = chatbotRepository.findById(2L);
                return chatbot.get().getResponse();
            }
        }
        for (String pattern : helloPatterns){
            if(message.contains(pattern)){
                Optional<Chatbot> chatbot = chatbotRepository.findById(1L);
                return chatbot.get().getResponse();
            }
        }
        return "<p>죄송합니다. 저는 홈페이지와 관련된 사항만 안내드릴 수 있어요. 😢<br>다른 문의사항은 없으실까요?</p>";
    }
}
