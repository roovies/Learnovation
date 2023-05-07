package com.kh.learnovation.domain.freeboard.service;

import com.kh.learnovation.domain.freeboard.dto.CommentDTO;

import java.util.List;

public interface CommentService {

    CommentDTO write(CommentDTO commentDTO);
//
    List<CommentDTO> findAll(Long freeBoardId);

    CommentDTO update(CommentDTO commentDTO);
//
//    void delete(CommentDTO commentDTO);

}
