package com.kh.learnovation.domain.course.repository;

import com.kh.learnovation.domain.course.entity.CourseVideo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<CourseVideo, Long> {
}
