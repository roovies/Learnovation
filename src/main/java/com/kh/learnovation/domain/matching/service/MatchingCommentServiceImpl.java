package com.kh.learnovation.domain.matching.service;

import com.kh.learnovation.domain.matching.dto.MatchingCommnetDTO;
import com.kh.learnovation.domain.matching.dto.MatchingDTO;
import com.kh.learnovation.domain.matching.entity.Matching;
import com.kh.learnovation.domain.matching.entity.MatchingComment;
import com.kh.learnovation.domain.matching.repository.MatchingCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchingCommentServiceImpl implements MatchingCommentService{
    @Autowired
    MatchingCommentRepository mCRepository;
    @Override
    public void insertComment(MatchingCommnetDTO matchingCommnetDTO) {
        MatchingComment matchingComment = MatchingComment.builder()
                .user(matchingCommnetDTO.getUser())
                .content(matchingCommnetDTO.getContent())
                .matching(matchingCommnetDTO.getMatching())
                .build();
        mCRepository.save(matchingComment);
    }

    @Override
    public List<MatchingComment> selectList(Matching matching) {
        List<MatchingComment> cList = mCRepository.findByMatchingOrderByCreatedAtDesc(matching);
        return cList;
    }

    @Override
    public void deleteComment(MatchingCommnetDTO matchingCommnetDTO) {
        MatchingComment matchingComment = MatchingComment.builder().id(matchingCommnetDTO.getId()).build();
        mCRepository.delete(matchingComment);
    }

    @Override
    public void updateComment(MatchingCommnetDTO matchingCommnetDTO) {
        MatchingComment matchingComment = MatchingComment.builder().id(matchingCommnetDTO.getId()).build();
        Optional<MatchingComment> optionalMatchingComment = mCRepository.findById(matchingComment.getId());
        if(optionalMatchingComment.isPresent()){
            matchingComment = optionalMatchingComment.get();
        }
        matchingComment.setContent(matchingCommnetDTO.getContent());
        mCRepository.save(matchingComment);
    }
}
