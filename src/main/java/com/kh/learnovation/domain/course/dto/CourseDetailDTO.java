package com.kh.learnovation.domain.course.dto;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDetailDTO {
    private Long id;
    private String category; // 강의 카테고리
    private String email;      // 강사 이메일
    private String title;   // 강의 제목
    private int level;      // 강의 난이도
    private int price;      // 강의 가격
    private String content; // 강의 소개글
    private List<CourseChapterDTO> chapters; // 강의에 포함된 목차 목록

//    @Builder
//    public CourseDetailDTO(){
//
//    }
}
