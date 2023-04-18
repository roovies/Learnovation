package com.kh.learnovation.domain.course.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseChapterDTO {
    private Long id;
    private Long courseId;     // 강의 고유 ID
    private String title;       // 목차명
    private int chapterOrder;   // 목차 순서
    private List<CourseLessonDTO> lessons; // 강의 목록
}
