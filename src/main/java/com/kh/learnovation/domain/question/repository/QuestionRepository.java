package com.kh.learnovation.domain.question.repository;

import com.kh.learnovation.domain.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Modifying
    @Query("update Question p set p.viewCount = p.viewCount + 1 where p.id = :id")
    int updateView(@Param("id") Long id);

    List<Question> findByTitleContaining(String keyword);
}
