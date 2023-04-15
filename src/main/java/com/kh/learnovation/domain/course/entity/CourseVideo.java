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
    @OneToOne
    @JoinColumn(name="lesson_id", nullable = false)
    private CourseLesson courseLesson;
    @Column(name="original_video_name", nullable = false)
    private String originalVideoName;
    @Column(name="saved_video_name", nullable = false)
    private String savedVideoName;
    @Column(name="saved_path", nullable = false)
    private String savedPath;
    @Column(name="video_size", nullable = false)
    private int videoSize;
    @CreationTimestamp
    @Column(name="created_at", nullable = false)
    private Timestamp createdAt;
}
