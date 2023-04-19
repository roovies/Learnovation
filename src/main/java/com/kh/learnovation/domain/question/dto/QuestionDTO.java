package com.kh.learnovation.domain.question.dto;

import com.kh.learnovation.domain.question.entity.Question;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class QuestionDTO {
    private Long id;
    private Long userId;
    private String title;
    private String content;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Integer deleted;
    private Timestamp deletedAt;

    public Question toEntity() {
        Question question = Question.builder()
                .id(id)
                .userId(userId)
                .title(title)
                .content(content)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .deleted(deleted)
                .deletedAt(deletedAt)
                .build();
        return question;
    }

    @Builder
    public QuestionDTO(Long id, Long userId, String title, String content, Timestamp createdAt, Timestamp updatedAt, Integer deleted, Timestamp deletedAt) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deleted = deleted;
        this.deletedAt = deletedAt;
    }
}
