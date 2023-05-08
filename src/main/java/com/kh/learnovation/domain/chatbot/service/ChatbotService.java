package com.kh.learnovation.domain.chatbot.service;

import com.kh.learnovation.domain.chatbot.dto.ChattingDTO;

import java.util.List;

public interface ChatbotService {
    /** 자동 챗봇 응답을 위한 자연어 분석 Service */
    String analyzePattern(String message);

    /** 개설된 채팅방 DB에 저장 Service */
    int saveRoom(String sessionId);

    /** 개설된 채팅방의 대화내역 DB에 저장 Service */
    int saveChatting(ChattingDTO toSaveChattngDTO);

    /** 채팅방의 대화내용 불러오기 Service */
    List<ChattingDTO> findChattingByRoomName(String roomName);

    int deleteRoom(String roomName);
}
