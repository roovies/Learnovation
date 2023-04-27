package com.kh.learnovation.domain.question.service;

import com.kh.learnovation.domain.question.dto.QuestionDTO;

import java.util.List;

public interface QuestionService {

    // 게시글 작성
    Long savePost(QuestionDTO questionDTO);

    // 게시글 상세 조회
    QuestionDTO getPost(Long id);

    // 게시글 조회수
    int updateView(Long id);

    // 게시글 목록
    List<QuestionDTO> questionList(Integer pageNum);

    Integer[] getPageList(Integer pageNum);

    List<QuestionDTO> searchQuestion(String keyword);
}
