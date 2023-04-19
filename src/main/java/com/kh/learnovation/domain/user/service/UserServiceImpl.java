package com.kh.learnovation.domain.user.service;

import com.kh.learnovation.domain.user.dto.UserDTO;
import com.kh.learnovation.domain.user.entity.User;
import com.kh.learnovation.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public List<UserDTO> getUserList() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();

        for(User user : users) {
            UserDTO userDTO = UserDTO.builder()
                    .id(user.getId())
                    .socialId(user.getSocialId())
                    .socialProvider(user.getSocialProvider())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .name(user.getName())
                    .nickname(user.getNickname())
                    .phoneNumber(user.getPhoneNumber())
                    .profileImage(user.getProfileImage())
                    .createdAt(user.getCreatedAt())
                    .updatedAt(user.getUpdatedAt())
                    .status(user.getStatus())
                    .deletedAt(user.getDeletedAt())
                    .build();

            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    @Override
    @Transactional
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
