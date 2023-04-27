package com.kh.learnovation.domain.freeboard.service;

import com.kh.learnovation.domain.freeboard.dto.LikeDTO;
import com.kh.learnovation.domain.freeboard.entity.CommentEntity;
import com.kh.learnovation.domain.freeboard.entity.FreeBoardEntity;
import com.kh.learnovation.domain.freeboard.entity.LikeEntity;
import com.kh.learnovation.domain.freeboard.repository.FreeBoardRepository;
import com.kh.learnovation.domain.freeboard.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
//    private final MemberRepository memberRepository;
    private final FreeBoardRepository freeBoardRepository;


    public Long save(LikeDTO likeDTO) throws Exception {
        Optional<FreeBoardEntity> optionalFreeBoardEntity = freeBoardRepository.findById(likeDTO.getFreeBoardId());
        if (optionalFreeBoardEntity.isPresent()) {
            FreeBoardEntity freeBoardEntity = optionalFreeBoardEntity.get();
            LikeEntity likeEntity = LikeEntity.toSaveEntity(likeDTO, freeBoardEntity);
            return likeRepository.save(likeEntity).getId();
        } else {
            return null;
        }
    }

    }




//    @Transactional
//    public void delete(HeartRequestDTO heartRequestDTO) {
//
//        Member member = memberRepository.findById(heartRequestDTO.getMemberId())
//                .orElseThrow(() -> new NotFoundException("Could not found member id : " + heartRequestDTO.getMemberId()));
//
//        Board board = boardRepository.findById(heartRequestDTO.getBoardId())
//                .orElseThrow(() -> new NotFoundException("Could not found board id : " + heartRequestDTO.getBoardId()));
//
//        Heart heart = heartRepository.findByMemberAndBoard(member, board)
//                .orElseThrow(() -> new NotFoundException("Could not found heart id"));
//
//        heartRepository.delete(heart);
//}
