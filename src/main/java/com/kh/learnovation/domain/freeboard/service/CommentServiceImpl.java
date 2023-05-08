package com.kh.learnovation.domain.freeboard.service;

import com.kh.learnovation.domain.freeboard.dto.CommentDTO;
import com.kh.learnovation.domain.freeboard.dto.FreeBoardDTO;
import com.kh.learnovation.domain.freeboard.entity.CommentEntity;
import com.kh.learnovation.domain.freeboard.entity.FreeBoardEntity;
import com.kh.learnovation.domain.freeboard.repository.CommentRepository;
import com.kh.learnovation.domain.freeboard.repository.FreeBoardRepository;
import com.kh.learnovation.domain.matching.entity.MatchingComment;
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
    public void insertComment(CommentDTO commentDTO) {
        CommentEntity commentEntity = CommentEntity.builder()
                .user(commentDTO.getUser())
                .commentContents(commentDTO.getCommentContents())
                .freeBoardEntity(commentDTO.getFreeBoardEntity())
                .build();
        commentRepository.save(commentEntity);

    }


//    @Override
//    public List<CommentDTO> findAll(Long freeBoardId) {
//        FreeBoardEntity freeBoardEntity = freeBoardRepository.findById(freeBoardId).get();
//        List<CommentEntity> commentEntityList = commentRepository.findAllByFreeBoardEntityOrderByIdDesc(freeBoardEntity);
//        /* EntityList -> DTOList */
//        List<CommentDTO> commentDTOList = new ArrayList<>();
//        for (CommentEntity commentEntity: commentEntityList) {
//            CommentDTO commentDTO = CommentDTO.builder().id(commentEntity.getId()).userId(commentEntity.getUser().getId())
//                    .nickname(commentEntity.getUser().getNickname()).email(commentEntity.getUser().getEmail()).commentContents(commentEntity.getCommentContents())
//                    .freeBoardId(commentEntity.getFreeBoardEntity().getId()).commentCreatedTime(commentEntity.getCreatedAt()).build();
//            commentDTOList.add(commentDTO);
//        }
//        return commentDTOList;
//    }

    @Override
    public List<CommentEntity> selectList(FreeBoardEntity freeBoardEntity) {
        List<CommentEntity> cList = commentRepository.findByFreeBoardEntityOrderByCreatedAtDesc(freeBoardEntity);
        return cList;
    }

    @Override
    public void deleteComment(CommentDTO commentDTO) {
        CommentEntity commentEntity = CommentEntity.builder().id(commentDTO.getId()).build();
        commentRepository.delete(commentEntity);

    }

    @Override
    public void updateComment(CommentDTO commentDTO) {
        CommentEntity commentEntity = CommentEntity.builder().id(commentDTO.getId()).build();
        Optional<CommentEntity> optionalCommentEntity = commentRepository.findById(commentEntity.getId());
        if(optionalCommentEntity.isPresent()){
            commentEntity = optionalCommentEntity.get();
        }
        commentEntity.setCommentContents(commentDTO.getCommentContents());
        commentRepository.save(commentEntity);
    }


}