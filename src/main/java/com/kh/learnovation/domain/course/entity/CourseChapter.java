package com.kh.learnovation.domain.course.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="course_chapters")
public class CourseChapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="course_id")
    private Course course;      // 강의 정보
    @Column(name="title", nullable = false)
    private String title;       // 목차명
    @Column(name="chapter_order", nullable = false)
    private int chapterOrder;   // 목차 순서
}
