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
public class CourseDTO {
    private Long id;
    private Long categoryId; // 강의 카테고리
    private Long userId;      // 강사 정보
    private String title;   // 강의 제목
    private int level;      // 강의 난이도
    private int price;      // 강의 가격
    private String content; // 강의 소개글
    private Timestamp createdAt;   // 강의 등록일
    private Timestamp updatedAt;   // 강의 수정일
    private Boolean deleted;        // 강의 삭제여부
    private Timestamp deletedAt;   // 강의 삭제일
}
