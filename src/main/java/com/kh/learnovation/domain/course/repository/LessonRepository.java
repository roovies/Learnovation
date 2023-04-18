package com.kh.learnovation.domain.course.repository;

import com.kh.learnovation.domain.course.entity.CourseLesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<CourseLesson, Long> {
}
