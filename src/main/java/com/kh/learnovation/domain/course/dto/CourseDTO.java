package com.kh.learnovation.domain.course.dto;

import com.kh.learnovation.domain.course.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
    private Long id;
    private String category; // 강의 카테고리
    private String email;      // 강사 이메일
    private String title;   // 강의 제목
    private int level;      // 강의 난이도
    private int price;      // 강의 가격
    private String content; // 강의 소개글
    private Timestamp createdAt;   // 강의 등록일
    private Timestamp updatedAt;   // 강의 수정일
    private Boolean deleted;        // 강의 삭제여부
    private Timestamp deletedAt;   // 강의 삭제일
    private List<CourseChapterDTO> chapters; // 강의에 포함된 목차 목록

}