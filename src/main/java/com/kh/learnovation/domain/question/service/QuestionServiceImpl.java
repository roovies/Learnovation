package com.kh.learnovation.domain.question.service;

import com.kh.learnovation.domain.question.dto.QuestionDTO;
import com.kh.learnovation.domain.question.repository.QuestionRepository;
import org.springframework.stereotype.Service;


@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public void savePost(QuestionDTO questionDTO) {
        return questionRepository.save(questionDTO.toEntity()).getId();
    }
}
