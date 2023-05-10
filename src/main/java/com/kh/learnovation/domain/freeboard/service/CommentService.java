package com.kh.learnovation.domain.freeboard.service;

import com.kh.learnovation.domain.freeboard.dto.CommentDTO;
import com.kh.learnovation.domain.freeboard.entity.CommentEntity;
import com.kh.learnovation.domain.freeboard.entity.FreeBoardEntity;

import java.util.List;

public interface CommentService {
//
//    List<CommentDTO> findAll(Long freeBoardId);

    List<CommentEntity> selectList(FreeBoardEntity freeBoardEntity);

    void deleteComment(CommentDTO commentDTO);

    void updateComment(CommentDTO commentDTO);


    void insertComment(CommentDTO commentDTO);
}
