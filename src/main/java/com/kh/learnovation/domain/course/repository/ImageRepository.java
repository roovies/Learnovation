package com.kh.learnovation.domain.course.repository;

import com.kh.learnovation.domain.course.entity.CourseImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<CourseImage, Long> {
}
