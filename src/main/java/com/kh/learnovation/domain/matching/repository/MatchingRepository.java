package com.kh.learnovation.domain.matching.repository;

import com.kh.learnovation.domain.matching.entity.Matching;
import com.kh.learnovation.domain.notice.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchingRepository extends JpaRepository<Matching, Long> {
    Page<Matching> findAllByStatus(int status, Pageable pageable);

    Page<Matching> findAllByStatusAndTitleLike(int status, String title, Pageable pageable);
}
