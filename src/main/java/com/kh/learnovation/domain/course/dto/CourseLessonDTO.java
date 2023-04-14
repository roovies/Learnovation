package com.kh.learnovation.domain.course.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseLessonDTO {
    private Long id;
    private Long chapterId;
    private String title;
    private int lessonOrder;
}
