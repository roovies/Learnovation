package com.kh.learnovation.domain.freeboard.service;

import com.kh.learnovation.domain.freeboard.dto.CommentDTO;
import com.kh.learnovation.domain.freeboard.entity.CommentEntity;
import com.kh.learnovation.domain.freeboard.entity.FreeBoardEntity;
import com.kh.learnovation.domain.freeboard.repository.CommentRepository;
import com.kh.learnovation.domain.freeboard.repository.FreeBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final FreeBoardRepository freeBoardRepository;

//    @Override
//    public Long save(CommentDTO commentDTO) {
//        /* 부모엔티티(BoardEntity) 조회 */
//        Optional<FreeBoardEntity> optionalFreeBoardEntity = freeBoardRepository.findById(commentDTO.getFreeBoardId());
//        if (optionalFreeBoardEntity.isPresent()) {
//            FreeBoardEntity freeBoardEntity = optionalFreeBoardEntity.get();
//            CommentEntity commentEntity = CommentEntity.toSaveEntity(commentDTO, freeBoardEntity);
//            return commentRepository.save(commentEntity).getId();
//        } else {
//            return null;
//        }
//    }
//
    @Override
    public List<CommentDTO> findAll(Long freeBoardId) {
        FreeBoardEntity freeBoardEntity = freeBoardRepository.findById(freeBoardId).get();
        List<CommentEntity> commentEntityList = commentRepository.findAllByFreeBoardEntityOrderByIdDesc(freeBoardEntity);
        /* EntityList -> DTOList */
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (CommentEntity commentEntity: commentEntityList) {
            CommentDTO commentDTO = CommentDTO.builder().id(commentEntity.getId()).userId(commentEntity.getUser().getId())
                    .nickname(commentEntity.getUser().getNickname()).commentContents(commentEntity.getCommentContents())
                    .freeBoardId(commentEntity.getFreeBoardEntity().getId()).commentCreatedTime(commentEntity.getCommentCreatedTime()).build();
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }

//    @Override
//    public CommentDTO update(CommentDTO commentDTO) {
//        FreeBoardEntity freeBoardEntity = freeBoardRepository.findById(commentDTO.getFreeBoardId()).get();
//        CommentEntity commentEntity = CommentEntity.toUpdateEntity(commentDTO, freeBoardEntity);
//        CommentEntity updateCommentEntity =  commentRepository.save(commentEntity);
//        return  CommentDTO.toCommentDTO(updateCommentEntity , freeBoardEntity.getId());
//    }
//
//    @Override
//    public void delete(CommentDTO commentDTO) {
//        FreeBoardEntity freeBoardEntity = freeBoardRepository.findById(commentDTO.getFreeBoardId()).get();
//        CommentEntity commentEntity = CommentEntity.toDeleteEntity(commentDTO, freeBoardEntity);
//        commentRepository.delete(commentEntity);
//    }
}