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
public class CommentService {
    private final CommentRepository commentRepository;
    private final FreeBoardRepository freeBoardRepository;

    public Long save(CommentDTO commentDTO) {
        /* 부모엔티티(BoardEntity) 조회 */
        Optional<FreeBoardEntity> optionalFreeBoardEntity = freeBoardRepository.findById(commentDTO.getFreeBoardId());
        if (optionalFreeBoardEntity.isPresent()) {
            FreeBoardEntity freeBoardEntity = optionalFreeBoardEntity.get();
            CommentEntity commentEntity = CommentEntity.toSaveEntity(commentDTO, freeBoardEntity);
            return commentRepository.save(commentEntity).getId();
        } else {
            return null;
        }
    }

    public List<CommentDTO> findAll(Long freeBoardId) {
        FreeBoardEntity freeBoardEntity = freeBoardRepository.findById(freeBoardId).get();
        List<CommentEntity> commentEntityList = commentRepository.findAllByBoardEntityOrderByIdDesc(freeBoardEntity);
        /* EntityList -> DTOList */
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (CommentEntity commentEntity: commentEntityList) {
            CommentDTO commentDTO = CommentDTO.toCommentDTO(commentEntity, freeBoardId);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }

}