package com.kh.learnovation.domain.course.dto;

import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class CourseChapterDTO {
    private Long id;
    private Long courseId;     // 강의 고유 ID
    private String title;       // 목차명
    private int chapterOrder;   // 목차 순서
    private List<CourseLessonDTO> lessons; // 강의 목록

    @Builder
    public CourseChapterDTO(Long id, Long courseId, String title,int chapterOrder, List<CourseLessonDTO> lessons){
        this.id = id;
        this.courseId = courseId;
        this.title = title;
        this.chapterOrder = chapterOrder;
        this.lessons = lessons;
    }
}
