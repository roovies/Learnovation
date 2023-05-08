package com.kh.learnovation.domain.freeboard.service;

import com.kh.learnovation.domain.freeboard.dto.LikeDTO;
import com.kh.learnovation.domain.user.dto.UserDTO;

import java.util.Optional;

public interface LikeService {

    LikeDTO check(LikeDTO likeDTO);

    void save(LikeDTO likeDTO);

    void delete(Long id);

    Integer findLike(Long id, Optional<UserDTO> optionalUserDTO);


//    Integer likeCheck(Long id);
}
