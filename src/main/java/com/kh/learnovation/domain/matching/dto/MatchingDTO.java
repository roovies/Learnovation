package com.kh.learnovation.domain.matching.dto;

import com.kh.learnovation.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
@Getter
@Setter
@NoArgsConstructor
public class MatchingDTO {

    private Long id;
    private User user;
    private String title;
    private String content;
    private String subject;
    private Timestamp createdAt;
    private int status;

    @Builder
    public MatchingDTO(long id, User user ,String title, String content, String subject, Timestamp createdAt, int status){
        this.id = id;
        this.user = user;
        this.title = title;
        this.content = content;
        this.subject = subject;
        this.createdAt = createdAt;
        this.status = status;
    }

    @Override
    public String toString() {
        return "MatchingDTO{" +
                "id=" + id +
                ", user=" + user +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", subject='" + subject + '\'' +
                ", createdAt=" + createdAt +
                ", status=" + status +
                '}';
    }
}
