package com.kh.learnovation.domain.question.service;

import com.kh.learnovation.domain.question.dto.QuestionDTO;
import com.kh.learnovation.domain.question.entity.Question;

public interface QuestionService {

    void savePost(QuestionDTO questionDTO);
}
