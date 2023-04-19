package com.kh.learnovation.domain.user.service;

import com.kh.learnovation.domain.user.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getUserList();

    void deleteUser(Integer id);
}
