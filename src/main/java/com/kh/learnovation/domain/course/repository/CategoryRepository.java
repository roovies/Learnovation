package com.kh.learnovation.domain.course.repository;

import com.kh.learnovation.domain.course.entity.CourseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CourseCategory, Long> {
    CourseCategory findByName(String category);
}
