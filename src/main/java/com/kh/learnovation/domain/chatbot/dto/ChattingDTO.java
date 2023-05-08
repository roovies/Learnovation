package com.kh.learnovation.domain.chatbot.dto;

import com.kh.learnovation.domain.user.dto.UserDTO;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ChattingDTO {
    private Long id;
    private String roomName;
    private Long senderId;
    private String message;
    private Timestamp createdAt;

    private UserDTO user;

    @Builder
    public ChattingDTO(Long id, String roomName, Long senderId, String message, Timestamp createdAt, UserDTO user){
        this.id = id;
        this.roomName = roomName;
        this.senderId = senderId;
        this.message = message;
        this.createdAt = createdAt;
        this.user = user;
    }
}
