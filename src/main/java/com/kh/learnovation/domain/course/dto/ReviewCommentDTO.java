package com.kh.learnovation.domain.course.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewCommentDTO {
    private Long id;
    private Long parentId;
    private Long reviewId;
    private Long userId;
    private String content;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Boolean deleted;
    private Timestamp deletedAt;
}
