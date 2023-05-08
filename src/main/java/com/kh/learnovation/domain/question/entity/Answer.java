package com.kh.learnovation.domain.question.entity;


import lombok.AccessLevel;
import lombok.Cleanup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "qna_comments")
public class Answer {

    @Id
    @Column(name = "answer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "qna_id")
    private Long questionId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "answer_content")
    private String content;

    @Column(name = "answer_created_at")
    private Timestamp createdAt;

    @Column(name = "answer_updated_at")
    private Timestamp updatedAt;



}
