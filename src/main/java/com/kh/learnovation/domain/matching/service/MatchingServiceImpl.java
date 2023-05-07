package com.kh.learnovation.domain.matching.service;

import com.kh.learnovation.domain.matching.dto.MatchingDTO;
import com.kh.learnovation.domain.matching.entity.Matching;
import com.kh.learnovation.domain.matching.repository.MatchingRepository;
import com.kh.learnovation.domain.notice.dto.NoticeDTO;
import com.kh.learnovation.domain.notice.entity.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class MatchingServiceImpl implements MatchingService{

    @Autowired
    private MatchingRepository mRepository;

    @Override
    @Transactional
    public MatchingDTO insertMatching(MatchingDTO matchingDTO) {
        Matching matching = Matching.builder()
                .id(matchingDTO.getId())
                .title(matchingDTO.getTitle())
                .subject(matchingDTO.getSubject())
                .content(matchingDTO.getContent())
                .createdAt(matchingDTO.getCreatedAt())
                .user(matchingDTO.getUser())
                .status(matchingDTO.getStatus())
                .build();
        matching = mRepository.save(matching);
        matchingDTO.setId(matching.getId());
        return matchingDTO;
    }

    @Override
    @Transactional
    public MatchingDTO selectOneMatching(Long matchingNo) {
        Optional<Matching> matching = mRepository.findById(matchingNo);
        Matching foundMatching = null;
        if(matching.isPresent()){
            foundMatching = matching.get();
        }
        MatchingDTO matchingDTO = MatchingDTO.builder()
                .id(foundMatching.getId())
                .subject(foundMatching.getSubject())
                .user(foundMatching.getUser())
                .content(foundMatching.getContent())
                .createdAt(foundMatching.getCreatedAt())
                .title(foundMatching.getTitle())
                .status(foundMatching.getStatus())
                .build();
        return matchingDTO;
    }


    @Override
    @Transactional
    public MatchingDTO updateMatching(MatchingDTO matchingDTO) {
        Matching matching = Matching.builder()
                .id(matchingDTO.getId())
                .user(matchingDTO.getUser())
                .title(matchingDTO.getTitle())
                .content(matchingDTO.getContent())
                .createdAt(matchingDTO.getCreatedAt())
                .subject(matchingDTO.getSubject())
                .status(matchingDTO.getStatus())
                .build();
        matching = mRepository.save(matching);
        matchingDTO = matchingDTO.builder()
                .id(matching.getId())
                .user(matching.getUser())
                .title(matching.getTitle())
                .content(matching.getContent())
                .createdAt(matching.getCreatedAt())
                .subject(matching.getSubject())
                .status(matching.getStatus())
                .build();
        return matchingDTO;
    }

    @Override
    public Page<Matching> selectAllNotice(Pageable pageable) {
        Page<Matching> pMatching = mRepository.findAllByStatus(0,pageable);
        return pMatching;
    }

    @Override
    public Page<Matching> selectAllNotice(String keyword, Pageable pageable) {
        Page<Matching> pMatching = mRepository.findAllByStatusAndTitleLike(0,keyword,pageable);
        return pMatching;
    }
}
