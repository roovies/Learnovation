package com.kh.learnovation.domain.course.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class CourseVideoDTO {
    private Long id;
    private Long lessonId;
    private String originalVideoName;
    private String savedVideoName;
    private String savedPath;
    private Long videoSize;
    private String videoTime;
    private Timestamp createdAt;

    @Builder
    public CourseVideoDTO(Long id, Long lessonId, String originalVideoName, String savedVideoName,
                          String savedPath, Long videoSize,String videoTime, Timestamp createdAt){
        this.id = id;
        this.originalVideoName = originalVideoName;
        this.savedVideoName = savedVideoName;
        this.savedPath = savedPath;
        this.videoSize = videoSize;
        this.videoTime = videoTime;
        this.createdAt = createdAt;
    }
}
