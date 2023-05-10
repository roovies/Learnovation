package com.kh.learnovation.domain.question.entity;

import com.kh.learnovation.domain.user.entity.TimeEntity;
import com.kh.learnovation.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "qna_posts")
public class Question {

    @Id
    @Column(name = "question_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(name = "question_title", length = 200, nullable = false)
    private String title;

    @Column(name = "question_content",nullable = false)
    private String content;

    @Column(name = "question_created_at")
    private Timestamp createdAt;

    @Column(name = "question_updated_at")
    private Timestamp updatedAt;

    @Column(name = "question_deleted")
    private Long deleted;

    @Column(name = "question_deleted_at")
    private Timestamp deletedAt;

    @Column(name = "question_view_count")
    private Long viewCount;

    @Builder
    public Question(Long id, Long userId, String title, String content, Timestamp createdAt, Timestamp updatedAt, Long deleted, Timestamp deletedAt, Long viewCount) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deleted = deleted;
        this.deletedAt = deletedAt;
        this.viewCount = viewCount;
    }
}
