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
public class CourseImageDTO {
    private Long id;
    private Long courseId;
    private String originalImageName;
    private String savedImageName;
    private String savedPath;
    private Long imageSize;
    private Timestamp createAt;
}
