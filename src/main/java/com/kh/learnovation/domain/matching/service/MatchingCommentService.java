package com.kh.learnovation.domain.matching.service;

import com.kh.learnovation.domain.matching.dto.MatchingCommnetDTO;
import com.kh.learnovation.domain.matching.dto.MatchingDTO;
import com.kh.learnovation.domain.matching.entity.Matching;
import com.kh.learnovation.domain.matching.entity.MatchingComment;

import java.util.List;

public interface MatchingCommentService {
    void insertComment(MatchingCommnetDTO matchingCommnetDTO);

    List<MatchingComment> selectList(Matching matching);


    void deleteComment(MatchingCommnetDTO matchingCommnetDTO);

    void updateComment(MatchingCommnetDTO matchingCommnetDTO);
}
