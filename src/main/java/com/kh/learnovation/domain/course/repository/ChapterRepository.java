package com.kh.learnovation.domain.course.repository;

import com.kh.learnovation.domain.course.entity.CourseChapter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChapterRepository extends JpaRepository<CourseChapter, Long> {
}
