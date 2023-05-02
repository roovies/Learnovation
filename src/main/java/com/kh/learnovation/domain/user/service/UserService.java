package com.kh.learnovation.domain.user.service;

import com.kh.learnovation.domain.user.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getUserList(Integer pageNum);

    Integer[] getPageList(Integer pageNum);

    List<UserDTO> searchUsers(String keyword);

    UserDTO getPost(Long id);

    void deleteByIdIn(List<Long> selectedIds);

    Long savePost(UserDTO userDTO);

    void pwUpdate(UserDTO userDTO);
}
