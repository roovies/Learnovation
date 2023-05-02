package com.kh.learnovation.domain.course.dto;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CourseReviewDTO {
    private Long id;
    private Long userId;
    private String userNickname;
    private Long courseId;
    private String content;
    private BigDecimal rating;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @Builder
    public CourseReviewDTO(Long id, Long userId, String userNickname, Long courseId, String content, BigDecimal rating, Timestamp createdAt, Timestamp updatedAt){
        this.id = id;
        this.userId = userId;
        this.userNickname = userNickname;
        this.courseId = courseId;
        this.content = content;
        this.rating = rating;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
