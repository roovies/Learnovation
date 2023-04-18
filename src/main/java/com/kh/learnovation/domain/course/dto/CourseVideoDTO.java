package com.kh.learnovation.domain.course.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseVideoDTO {
    private Long id;
    private Long lessonId;
    private String originalVideoName;
    private String savedVideoName;
    private String savedPath;
    private Long videoSize;
    private Timestamp createdAt;
}
