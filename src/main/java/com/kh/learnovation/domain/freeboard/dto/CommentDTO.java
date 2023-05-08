package com.kh.learnovation.domain.freeboard.dto;

import com.kh.learnovation.domain.freeboard.entity.CommentEntity;
import com.kh.learnovation.domain.freeboard.entity.FreeBoardEntity;
import com.kh.learnovation.domain.user.entity.User;
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
    private User user;
    private FreeBoardEntity freeBoardEntity;
    private String commentContents;
    private Timestamp commentCreatedTime;


    @Builder
    public CommentDTO(long id, User user, String commentContents, FreeBoardEntity freeBoardEntity, Timestamp commentCreatedTime){
        this.id = id;
        this.user = user;
        this.commentContents = commentContents;
        this.freeBoardEntity = freeBoardEntity;
        this.commentCreatedTime = commentCreatedTime;
    }



}