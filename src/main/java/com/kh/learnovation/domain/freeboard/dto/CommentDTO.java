package com.kh.learnovation.domain.freeboard.dto;

import com.kh.learnovation.domain.freeboard.entity.CommentEntity;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자
public class CommentDTO {
    private Long id;
    private Long userId;
    private String nickname;
    private String email;
    private String commentContents;
    private Long freeBoardId;
    private Timestamp commentCreatedTime;


    @Builder
    public CommentDTO(long id, Long userId, String nickname,String email, String commentContents, Long freeBoardId, Timestamp commentCreatedTime){
        this.id = id;
        this.userId = userId;
        this.nickname = nickname;
        this.email= email;
        this.commentContents = commentContents;
        this.freeBoardId = freeBoardId;
        this.commentCreatedTime = commentCreatedTime;
    }



}