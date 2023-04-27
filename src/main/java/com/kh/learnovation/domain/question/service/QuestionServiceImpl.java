package com.kh.learnovation.domain.question.service;

import com.kh.learnovation.domain.question.dto.QuestionDTO;
import com.kh.learnovation.domain.question.entity.Question;
import com.kh.learnovation.domain.question.repository.QuestionRepository;
import com.kh.learnovation.domain.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private static final int BLOCK_PAGE_NUM_COUNT = 5; // 블럭에 존재하는 페이지 수
    private static final int PAGE_POST_COUNT = 4; // 한 페이지에 존재하는 게시글 수

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
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
    @Transactional
    public List<QuestionDTO> questionList(Integer pageNum) {

        Page<Question> page = questionRepository
                .findAll(PageRequest.of(pageNum-1, PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC, "createdAt")));

//        List<Question> questions = questionRepository.findAll();
        List<Question> questions = page.getContent();
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

    @Override
    @Transactional
    public Integer[] getPageList(Integer curPageNum) {
        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];

        Double questionsTotalCount = Double.valueOf(this.getQuestionCount());

        Integer totalLastPageNum = (int)(Math.ceil((questionsTotalCount/PAGE_POST_COUNT)));

        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
                ? curPageNum + BLOCK_PAGE_NUM_COUNT
                : totalLastPageNum;

        curPageNum = (curPageNum<=3) ? 1 : curPageNum-2;

        for(int val=curPageNum, i=0;val<=blockLastPageNum;val++, i++) {
            pageList[i] = val;
        }

        return pageList;
    }

    @Override
    public List<QuestionDTO> searchQuestion(String keyword) {
        List<Question> questions = questionRepository.findByTitleContaining(keyword);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        if(questions.isEmpty()) return questionDTOList;

        for(Question question : questions) {
            questionDTOList.add(this.convertEntityToDto(question));
        }
        return questionDTOList;
    }

    private QuestionDTO convertEntityToDto(Question question) {
        return QuestionDTO.builder()
                .id(question.getId())
                .userId(question.getUserId())
                .title(question.getTitle())
                .content(question.getTitle())
                .createdAt(question.getCreatedAt())
                .updatedAt(question.getUpdatedAt())
                .deleted(question.getDeleted())
                .deletedAt(question.getDeletedAt())
                .viewCount(question.getViewCount())
                .build();
    }

    public Long getQuestionCount() {
        return questionRepository.count();
    }
}
