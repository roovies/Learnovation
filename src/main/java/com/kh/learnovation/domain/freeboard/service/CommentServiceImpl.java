package com.kh.learnovation.domain.freeboard.service;

import com.kh.learnovation.domain.freeboard.dto.CommentDTO;
import com.kh.learnovation.domain.freeboard.dto.FreeBoardDTO;
import com.kh.learnovation.domain.freeboard.entity.CommentEntity;
import com.kh.learnovation.domain.freeboard.entity.FreeBoardEntity;
import com.kh.learnovation.domain.freeboard.repository.CommentRepository;
import com.kh.learnovation.domain.freeboard.repository.FreeBoardRepository;
import com.kh.learnovation.domain.user.dto.UserDTO;
import com.kh.learnovation.domain.user.entity.User;
import com.kh.learnovation.domain.user.repository.UserRepository;
import com.kh.learnovation.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final FreeBoardRepository freeBoardRepository;

    @Override
    public CommentDTO write(CommentDTO commentDTO) {
        User user = userRepository.findByEmail(commentDTO.getEmail()).get();
        FreeBoardEntity freeBoardEntity = freeBoardRepository.findById(commentDTO.getFreeBoardId()).get();
        CommentEntity commentEntity = CommentEntity.builder().user(user).commentContents(commentDTO.getCommentContents())
                .freeBoardEntity(freeBoardEntity).commentCreatedTime(commentDTO.getCommentCreatedTime()).build();
        commentEntity = commentRepository.save(commentEntity);
        commentDTO = CommentDTO.builder().id(commentEntity.getId()).userId(commentEntity.getUser().getId())
                .nickname(commentEntity.getUser().getNickname()).email(commentEntity.getUser().getEmail())
                .commentContents(commentEntity.getCommentContents()).freeBoardId(commentEntity.getFreeBoardEntity().getId())
                .commentCreatedTime(commentEntity.getCommentCreatedTime())
                .build();
            return commentDTO;
        }



    @Override
    public List<CommentDTO> findAll(Long freeBoardId) {
        FreeBoardEntity freeBoardEntity = freeBoardRepository.findById(freeBoardId).get();
        List<CommentEntity> commentEntityList = commentRepository.findAllByFreeBoardEntityOrderByIdDesc(freeBoardEntity);
        /* EntityList -> DTOList */
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (CommentEntity commentEntity: commentEntityList) {
            CommentDTO commentDTO = CommentDTO.builder().id(commentEntity.getId()).userId(commentEntity.getUser().getId())
                    .nickname(commentEntity.getUser().getNickname()).email(commentEntity.getUser().getEmail()).commentContents(commentEntity.getCommentContents())
                    .freeBoardId(commentEntity.getFreeBoardEntity().getId()).commentCreatedTime(commentEntity.getCommentCreatedTime()).build();
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }

    @Override
    public CommentDTO update(CommentDTO commentDTO) {
        User user = userRepository.findById(commentDTO.getUserId()).get();
        FreeBoardEntity freeBoardEntity = freeBoardRepository.findById(commentDTO.getFreeBoardId()).get();
        CommentEntity commentEntity = CommentEntity.builder().user(user).commentContents(commentDTO.getCommentContents())
                .freeBoardEntity(freeBoardEntity).commentCreatedTime(commentDTO.getCommentCreatedTime()).build();
        CommentEntity updateCommentEntity =  commentRepository.save(commentEntity);
        commentDTO.builder().id(updateCommentEntity.getId()).userId(updateCommentEntity.getUser().getId())
                .nickname(updateCommentEntity.getUser().getNickname()).email(updateCommentEntity.getUser().getEmail())
                .commentContents(updateCommentEntity.getCommentContents()).freeBoardId(updateCommentEntity.getFreeBoardEntity().getId())
                .commentCreatedTime(updateCommentEntity.getCommentCreatedTime()).build();
        return commentDTO;
    }
//    @Override
//    public void update(CommentDTO commentDTO) {
//        User user = userRepository.findById(commentDTO.getUserId()).get();
//        FreeBoardEntity freeBoardEntity = freeBoardRepository.findById(commentDTO.getFreeBoardId()).get();
//        CommentEntity commentEntity = CommentEntity.builder().user(user).commentContents(commentDTO.getCommentContents())
//                .freeBoardEntity(freeBoardEntity).commentCreatedTime(commentDTO.getCommentCreatedTime()).build();
//        CommentEntity updateCommentEntity =  commentRepository.save(commentEntity);
//        commentDTO.builder().id(updateCommentEntity.getId()).userId(updateCommentEntity.getUser().getId())
//                .nickname(updateCommentEntity.getUser().getNickname()).email(updateCommentEntity.getUser().getEmail())
//                .commentContents(updateCommentEntity.getCommentContents()).freeBoardId(updateCommentEntity.getFreeBoardEntity().getId())
//                .commentCreatedTime(updateCommentEntity.getCommentCreatedTime()).build();
//
//    }
//
//    @Override
//    public void delete(CommentDTO commentDTO) {
//        FreeBoardEntity freeBoardEntity = freeBoardRepository.findById(commentDTO.getFreeBoardId()).get();
//        CommentEntity commentEntity = CommentEntity.toDeleteEntity(commentDTO, freeBoardEntity);
//        commentRepository.delete(commentEntity);
//    }
}