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
@Table(name="course_images")
public class CourseImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="course_id", nullable = false)
    private Course course;
    @Column(name="original_image_name", nullable = false)
    private String originalImageName;
    @Column(name="saved_image_name", nullable = false)
    private String savedImageName;
    @Column(name="saved_path", nullable = false)
    private String savedPath;
    @Column(name="image_size", nullable = false)
    private int imageSize;
    @CreationTimestamp
    @Column(name="created_at", nullable = false)
    private Timestamp createAt;
}
