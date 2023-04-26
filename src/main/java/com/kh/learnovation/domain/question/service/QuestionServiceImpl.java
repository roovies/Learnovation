package com.kh.learnovation.domain.question.service;

import com.kh.learnovation.domain.question.dto.QuestionDTO;
import com.kh.learnovation.domain.question.entity.Question;
import com.kh.learnovation.domain.question.repository.QuestionRepository;
import com.kh.learnovation.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository, UserRepository userRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }




    @Override
    @Transactional
    public Long savePost(QuestionDTO questionDTO) {
        return questionRepository.save(questionDTO.toEntity()).getId();
    }

    @Override
    @Transactional
    public QuestionDTO getPost(Long id) {
        Optional<Question> questionWrapper = questionRepository.findById(id);
        Question question = questionWrapper.get();

        QuestionDTO questionDTO = QuestionDTO.builder()
                .id(question.getId())
                .userId(question.getUserId())
                .title(question.getTitle())
                .content(question.getContent())
                .createdAt(question.getCreatedAt())
                .updatedAt(question.getUpdatedAt())
                .viewCount(question.getViewCount())
                .build();

        return questionDTO;
    }

    @Transactional
    public int updateView(Long id) {
        return questionRepository.updateView(id);
    }

    @Override
    public List<QuestionDTO> questionList() {
        List<Question> questions = questionRepository.findAll();
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for(Question question : questions) {
            QuestionDTO questionDTO = QuestionDTO.builder()
                    .id(question.getId())
                    .userId(question.getUserId())
                    .title(question.getTitle())
                    .content(question.getContent())
                    .createdAt(question.getCreatedAt())
                    .updatedAt(question.getUpdatedAt())
                    .deleted(question.getDeleted())
                    .deletedAt(question.getDeletedAt())
                    .viewCount(question.getViewCount())
                    .build();

            questionDTOList.add(questionDTO);
        }

        return questionDTOList;
    }
}
