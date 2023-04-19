package com.kh.learnovation.domain.question.entity;

import com.kh.learnovation.domain.user.entity.TimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(length = 200, nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column
    private Timestamp createdAt;

    @Column
    private Timestamp updatedAt;

    @Column
    private Integer deleted;

    @Column
    private Timestamp deletedAt;

    @Builder
    public Question(Long id, Long userId, String title, String content, Timestamp createdAt, Timestamp updatedAt, Integer deleted, Timestamp deletedAt) {
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
