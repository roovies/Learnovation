package com.kh.learnovation.domain.chatbot.service;

import com.kh.learnovation.domain.chatbot.dto.ChattingDTO;
import com.kh.learnovation.domain.chatbot.entity.Chatbot;
import com.kh.learnovation.domain.chatbot.entity.Chatting;
import com.kh.learnovation.domain.chatbot.entity.Room;
import com.kh.learnovation.domain.chatbot.repository.ChatbotRepository;
import com.kh.learnovation.domain.chatbot.repository.ChattingRepository;
import com.kh.learnovation.domain.chatbot.repository.RoomRepository;
import com.kh.learnovation.domain.user.dto.UserDTO;
import com.kh.learnovation.domain.user.entity.User;
import com.kh.learnovation.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChatbotServiceImpl implements ChatbotService {

    private final ChatbotRepository chatbotRepository;
    private final RoomRepository roomRepository;
    private final ChattingRepository chattingRepository;
    private final UserRepository userRepository;

    public ChatbotServiceImpl(ChatbotRepository chatbotRepository, RoomRepository roomRepository, ChattingRepository chattingRepository, UserRepository userRepository) {
        this.chatbotRepository = chatbotRepository;
        this.roomRepository = roomRepository;
        this.chattingRepository = chattingRepository;
        this.userRepository = userRepository;
    }

    private final List<String> helloPatterns = new ArrayList<>(
            Arrays.asList("안녕", "반갑", "하이", "오하요", "헬로", "hello",
            "hi", "방가", "반가워", "하잉", "ㅎㅇ", "ㅎ2", "하2")
    );
    private final List<String> askPatterns = new ArrayList<>(
            Arrays.asList("상담", "문의", "1:1", "일대일", "1대1")
    );
    private final List<String> askSecondPatterns = new ArrayList<>(
            Arrays.asList("연결", "대화", "필요", "신청", "해주", "도와", "서비스", "연락", "해야", "어떻게", "해줘", "ㄱ", "원해", "원합", "원하", "가능", "알려")
    );
    private final List<String> lostPatterns = new ArrayList<>(
            Arrays.asList("아이디", "ID", "id", "아디", "이메일", "로그인", "비밀번호", "패스워드", "비번", "PW", "pw", "password", "PASSWORD", "계정")
    );
    private final List<String> lostSecondPatterns = new ArrayList<>(
            Arrays.asList("잃어", "분실", "까먹", "찾", "잊어", "복구", "몰라", "모르", "알려", "궁금")
    );
    private final List<String> coursePatterns = new ArrayList<>(
            Arrays.asList("인강", "강의", "수강")
    );
    private final List<String> recommendPatterns = new ArrayList<>(
            Arrays.asList("추천", "어떤", "인기")
    );
    private final List<String> courseGuidePatterns = new ArrayList<>(
            Arrays.asList("어떻게", "방법", "는 법", "는법", "어디서", "이용", "안내", "살 수", "살수", "려면", "가이드")
    );
    private final List<String> refundPatterns = new ArrayList<>(
            Arrays.asList("환불", "결제", "취소")
    );
    private final List<String> noticePatterns = new ArrayList<>(
            Arrays.asList("이벤트", "혜택", "공지", "규칙", "이용", "이용방법", "안내", "가이드", "규정")
    );
    @Override
    public String analyzePattern(String message) {
        /** 인사 */
        for (String pattern : helloPatterns) {
            if (message.contains(pattern)) {
                Optional<Chatbot> chatbot = chatbotRepository.findById(1L);
                return chatbot.get().getResponse();
            }
        }

        /** 상담연결 문의 */
        for (String firstPattern : askPatterns) {
            if (message.contains(firstPattern)) {
                for (String secondPattern : askSecondPatterns) {
                    if (message.contains(secondPattern)) {
                        Optional<Chatbot> chatbot = chatbotRepository.findById(2L);
                        return chatbot.get().getResponse();
                    }
                }
            }
        }

        /** 아이디/비밀번호 찾기 */
        for (String firstPattern : lostPatterns){
            if(message.contains(firstPattern)){
                for (String secondPattern : lostSecondPatterns){
                    if (message.contains(secondPattern)){
                        Optional<Chatbot> chatbot = chatbotRepository.findById(3L);
                        return chatbot.get().getResponse();
                    }
                }
            }
        }

        /** 인강 관련 */
        for (String firstPattern : coursePatterns){
            if (message.contains(firstPattern)){
                // 1. 인강 추천 관련
                for (String recommendPattern : recommendPatterns){
                    if (message.contains(recommendPattern)){
                        Optional<Chatbot> chatbot = chatbotRepository.findById(4L);
                        return chatbot.get().getResponse();
                    }
                }
                // 2. 인강 가이드 관련
                for (String courseGuidePattern : courseGuidePatterns){
                    if (message.contains(courseGuidePattern)){
                        Optional<Chatbot> chatbot = chatbotRepository.findById(5L);
                        return chatbot.get().getResponse();
                    }
                }
                // 3. 인강 환불 관련
                for (String refundPattern : refundPatterns){
                    if (message.contains(refundPattern)){
                        Optional<Chatbot> chatbot = chatbotRepository.findById(6L);
                        return chatbot.get().getResponse();
                    }
                }
            }
        }

        /** 이용안내, 공지사항, 이벤트 관련 */
        for (String noticePattern : noticePatterns){
            if (message.contains(noticePattern)){
                Optional<Chatbot> chatbot = chatbotRepository.findById(7L);
                return chatbot.get().getResponse();
            }
        }


            return "<p>죄송합니다. 아직 학습하지 못한 말이에요. 😢<br><b>키워드</b>를 중심으로 다시 한 번 문의해주세요.</p>";
    }

    @Override
    public int saveRoom(String sessionId) {
        Room room = new Room();
        room.setName(sessionId);
        Optional<Room> savedRoom = Optional.of(roomRepository.save(room));
        if (savedRoom.isPresent()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int saveChatting(ChattingDTO toSaveChattngDTO) {
        Optional<Room> foundRoom = roomRepository.findByName(toSaveChattngDTO.getRoomName());
        if (foundRoom.isPresent()) {
            Chatting toSaveChatting = Chatting.builder()
                    .room(foundRoom.get())
                    .senderId(toSaveChattngDTO.getSenderId())
                    .message(toSaveChattngDTO.getMessage())
                    .build();
            Optional<Chatting> savedChatting = Optional.of(chattingRepository.save(toSaveChatting));
            if (savedChatting.isPresent()) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    @Override
    public List<ChattingDTO> findChattingByRoomName(String roomName) {
        Optional<Room> foundRoom = roomRepository.findByName(roomName);
        List<ChattingDTO> chattingDTOs = new ArrayList<>();
        if (foundRoom.isPresent()) {
            List<Chatting> foundChattings = chattingRepository.findByRoomId(foundRoom.get().getId());
            if (foundChattings.get(0).getSenderId() != 0L) {
                Optional<User> foundUser = userRepository.findById(foundChattings.get(0).getSenderId());
                chattingDTOs = foundChattings.stream()
                        .map(chatting -> {
                            ChattingDTO chattingDTO = ChattingDTO.builder()
                                    .id(chatting.getId())
                                    .roomName(chatting.getRoom().getName())
                                    .senderId(chatting.getSenderId())
                                    .message(chatting.getMessage())
                                    .createdAt(chatting.getCreatedAt())
                                    .user(UserDTO.builder()
                                            .id(foundUser.get().getId())
                                            .email(foundUser.get().getEmail())
                                            .name(foundUser.get().getName())
                                            .nickname(foundUser.get().getNickname())
                                            .phoneNumber(foundUser.get().getPhoneNumber())
                                            .createdAt(foundUser.get().getCreatedAt())
                                            .build())
                                    .build();
                            return chattingDTO;
                        }).collect(Collectors.toList());
            } else {
                chattingDTOs = foundChattings.stream()
                        .map(chatting -> {
                            ChattingDTO chattingDTO = ChattingDTO.builder()
                                    .id(chatting.getId())
                                    .roomName(chatting.getRoom().getName())
                                    .senderId(chatting.getSenderId())
                                    .message(chatting.getMessage())
                                    .createdAt(chatting.getCreatedAt())
                                    .build();
                            return chattingDTO;
                        }).collect(Collectors.toList());
            }
        }
        return chattingDTOs;
    }

    @Override
    @Transactional
    public int deleteRoom(String roomName) {
        int result = roomRepository.deleteByName(roomName);
        return result;
    }
}
