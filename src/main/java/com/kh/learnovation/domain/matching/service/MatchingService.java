package com.kh.learnovation.domain.matching.service;

import com.kh.learnovation.domain.matching.dto.MatchingCommnetDTO;
import com.kh.learnovation.domain.matching.dto.MatchingDTO;
import com.kh.learnovation.domain.matching.entity.Matching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MatchingService {

    public MatchingDTO insertMatching(MatchingDTO matchingDTO);

    public MatchingDTO selectOneMatching(Long matchingNo);

    MatchingDTO updateMatching(MatchingDTO matchingDTO);

    Page<Matching> selectAllNotice(Pageable pageable);

    Page<Matching> selectAllNotice(String keyword, Pageable pageable);

}
