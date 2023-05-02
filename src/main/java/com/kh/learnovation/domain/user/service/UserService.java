package com.kh.learnovation.domain.user.service;

import com.kh.learnovation.domain.user.dto.UserDTO;
import com.kh.learnovation.domain.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDTO> getUserList(Integer pageNum);

    Integer[] getPageList(Integer pageNum);

    List<UserDTO> searchUsers(String keyword);

    UserDTO getPost(Long id);

    void deleteByIdIn(List<Long> selectedIds);

    Long savePost(UserDTO userDTO);

    void pwUpdate(UserDTO userDTO);

    User 회원찾기(String email);

    void 회원가입(User user);
    void 회원수정(User user);

    /** user이메일로 user Entity 가져오기 */
    Optional<User> findUserByEmail(String email);

}
