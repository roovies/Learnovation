package com.kh.learnovation.domain.course.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="course_lessons")
public class CourseLesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="chapter_id")
    private CourseChapter courseChapter;
    @Column(name="title", nullable = false)
    private String title;
    @Column(name="lesson_order", nullable = false)
    private int lessonOrder;

    // Lazy 처리 연관관계 (OneToOne)
    @OneToOne(mappedBy = "courseLesson", fetch = FetchType.LAZY)
    private CourseVideo video;


    @Builder
    public CourseLesson(CourseChapter courseChapter, String title, int lessonOrder){
        this.courseChapter = courseChapter;
        this.title = title;
        this.lessonOrder = lessonOrder;
    }
}
