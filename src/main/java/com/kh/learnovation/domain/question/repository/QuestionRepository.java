package com.kh.learnovation.domain.question.repository;

import com.kh.learnovation.domain.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
