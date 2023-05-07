package com.kh.learnovation.domain.matching.repository;


import com.kh.learnovation.domain.matching.entity.Matching;
import com.kh.learnovation.domain.matching.entity.MatchingComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchingCommentRepository extends JpaRepository<MatchingComment, Long> {

    List<MatchingComment> findByMatchingOrderByCreatedAt(Matching matching);
}
