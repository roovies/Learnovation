package com.kh.learnovation.domain.course.dto;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CourseDetailDTO {
    private Long id;
    private String category; // 강의 카테고리
    private int level;      // 강의 난이도
    private String title;   // 강의 제목
    private String nickname;      // 강사 닉네임
    private int price;      // 강의 가격
    private String content; // 강의 소개글
    private String savedPath; // 썸네일 저장 폴더
    private String savedImageName; // 저장된 썸네일 파일명
    private List<CourseChapterDTO> chapters; // 강의에 포함된 목차 목록
    private Long totalLessonCount;
    private String totalVideoTime;

    @Builder
    public CourseDetailDTO(Long id, String category, int level, String title, String nickname, int price,
                           String content, String savedPath, String savedImageName, List<CourseChapterDTO> chapters){
        this.id = id;
        this.category = category;
        this.level = level;
        this.title = title;
        this.nickname = nickname;
        this.price = price;
        this.content = content;
        this.savedPath = savedPath;
        this.savedImageName = savedImageName;
        this.chapters = chapters;

    }
}
