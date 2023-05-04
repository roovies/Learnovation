package com.kh.learnovation.domain.freeboard.service;

import com.kh.learnovation.domain.freeboard.dto.LikeDTO;

public interface LikeService {

    Integer check(LikeDTO likeDTO);

    void save(LikeDTO likeDTO);

    void delete(LikeDTO likeDTO);

//    Integer likeCheck(Long id);
}
