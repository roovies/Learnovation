package com.kh.learnovation.domain.course.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseReviewDTO {
    private Long id;
    private Long userId;
    private Long courseId;
    private String content;
    private BigDecimal rating;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Boolean deleted;
    private Timestamp deletedAt;   // 강의 삭제일
}
