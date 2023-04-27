package com.kh.learnovation.domain.user.service;

import com.kh.learnovation.domain.user.dto.UserDTO;
import com.kh.learnovation.domain.user.entity.User;
import com.kh.learnovation.domain.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private static final int BLOCK_PAGE_NUM_COUNT = 5; // 블럭에 존재하는 페이지 수
    private static final int PAGE_POST_COUNT = 4; // 한 페이지에 존재하는 게시글 수

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public List<UserDTO> getUserList(Integer pageNum) {

        Page<User> page = userRepository
                .findAll(PageRequest.of(pageNum-1, PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC, "createdAt")));

//        List<User> users = userRepository.findAll();
        List<User> users = page.getContent();
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
    public Integer[] getPageList(Integer curPageNum) {
        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];

        Double usersTotalCount = Double.valueOf(this.getUserCount());

        Integer totalLastPageNum = (int)(Math.ceil((usersTotalCount/PAGE_POST_COUNT)));

        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
                ? curPageNum + BLOCK_PAGE_NUM_COUNT
                : totalLastPageNum;

        curPageNum = (curPageNum<=3) ? 1 : curPageNum-2;

        for(int val=curPageNum, i=0;val<=blockLastPageNum;val++, i++) {
            pageList[i] = val;
        }

        return pageList;
    }

    @Override
    @Transactional
    public List<UserDTO> searchUsers(String keyword) {
        List<User> users = userRepository.findByNameContaining(keyword);
        List<UserDTO> userDTOList = new ArrayList<>();

        if(users.isEmpty()) return userDTOList;

        for(User user : users) {
            userDTOList.add(this.convertEntityToDto(user));
        }
        return userDTOList;
    }

    @Override
    @Transactional
    public UserDTO getPost(Long id) {
        Optional<User> userWrapper = userRepository.findById(id);
        User user = userWrapper.get();

        UserDTO userDTO = UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .nickname(user.getNickname())
                .phoneNumber(user.getPhoneNumber())
                .build();

        return userDTO;
    }

    @Override
    @Transactional
    public void deleteByIdIn(List<Long> ids) {
        for(Long id : ids) {
            userRepository.deleteById(id);
        }
    }

    @Override
    @Transactional
    public Long savePost(UserDTO userDTO) {
        return userRepository.save(userDTO.toEntity()).getId();
    }


    private UserDTO convertEntityToDto(User user) {
        return UserDTO.builder()
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
    }

    @Transactional
    public Long getUserCount() {
        return userRepository.count();
    }


}
