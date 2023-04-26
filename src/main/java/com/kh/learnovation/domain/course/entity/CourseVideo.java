package com.kh.learnovation.domain.course.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="course_videos")
public class CourseVideo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="lesson_id")
    private CourseLesson courseLesson;
    @Column(name="original_video_name", nullable = false)
    private String originalVideoName;
    @Column(name="saved_video_name", nullable = false)
    private String savedVideoName;
    @Column(name="saved_path", nullable = false)
    private String savedPath;
    @Column(name="video_size", nullable = false)
    private Long videoSize;
    @Column(name="video_time", nullable = false)
    private int videoTime;
    @CreationTimestamp
    @Column(name="created_at", nullable = false)
    private Timestamp createdAt;
}
