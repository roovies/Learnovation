package com.kh.learnovation.domain.freeboard.service;

import com.kh.learnovation.domain.freeboard.dto.LikeDTO;
import com.kh.learnovation.domain.freeboard.entity.FreeBoardEntity;
import com.kh.learnovation.domain.freeboard.entity.LikeEntity;
import com.kh.learnovation.domain.freeboard.repository.FreeBoardRepository;
import com.kh.learnovation.domain.freeboard.repository.LikeRepository;
import com.kh.learnovation.domain.user.entity.User;
import com.kh.learnovation.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final FreeBoardRepository freeBoardRepository;



    @Override
    public Integer check(LikeDTO likeDTO) {
        Optional<User> optionalUser = userRepository.findById(likeDTO.getUserId());
        if (optionalUser.isPresent()){
            Optional<FreeBoardEntity> optionalFreeBoardEntity = freeBoardRepository.findById(likeDTO.getFreeBoardId());
            if (optionalFreeBoardEntity.isPresent()) {
                FreeBoardEntity freeBoardEntity = optionalFreeBoardEntity.get();
                    User userEntity = optionalUser.get();
                    LikeEntity likeEntity = LikeEntity.toSaveEntity(userEntity, freeBoardEntity);
                    Optional<LikeEntity> optionalLikeEntity = likeRepository.findByUserAndFreeBoard(likeEntity.getUser(), likeEntity.getFreeBoardEntity()); // 동작 안하면 다음엔 get해서 ID값을 넣어보자
                    if (optionalLikeEntity.isPresent()){
                        likeEntity = optionalLikeEntity.get();
                        likeDTO = LikeDTO.toLikeDTO(likeEntity);
                        return 1;

                    }else {
                        return 0;
                    }
                } else {
                    return 100;
                }
            } else {
                return 7;
            }
        }


    @Override
    public void save(LikeDTO likeDTO) {
        Optional<FreeBoardEntity> optionalFreeBoardEntity = freeBoardRepository.findById(likeDTO.getFreeBoardId());
        if (optionalFreeBoardEntity.isPresent()) {
            FreeBoardEntity freeBoardEntity = optionalFreeBoardEntity.get();
            Optional<User> optionalUser = userRepository.findById(likeDTO.getUserId());
            if (optionalUser.isPresent()) {
                User userEntity = optionalUser.get();
                LikeEntity likeEntity = LikeEntity.toSaveEntity(userEntity, freeBoardEntity);
                likeRepository.save(likeEntity);

            } else {
            }
        } else {
        }
    }
    @Override
    public void delete(LikeDTO likeDTO) {
        Optional<FreeBoardEntity> optionalFreeBoardEntity = freeBoardRepository.findById(likeDTO.getFreeBoardId());
        if (optionalFreeBoardEntity.isPresent()) {
            FreeBoardEntity freeBoardEntity = optionalFreeBoardEntity.get();
            Optional<User> optionalUser = userRepository.findById(likeDTO.getUserId());
            if (optionalUser.isPresent()) {
                User userEntity = optionalUser.get();
                LikeEntity likeEntity = LikeEntity.toSaveEntity(userEntity, freeBoardEntity);
                likeRepository.delete(likeEntity);

            } else {
            }
        } else {
        }


    }
//    @Override
//    public Integer likeCheck(Long id) {
//        Optional<FreeBoardEntity> optionalFreeBoardEntity = freeBoardRepository.findById(id);
//        if (optionalFreeBoardEntity.isPresent()) {
//            FreeBoardEntity freeBoardEntity = optionalFreeBoardEntity.get();
//            Optional<User> optionalUser = userRepository.findById(user.getUserId());
//            if (optionalUser.isPresent()) {
//                User userEntity = optionalUser.get();
//                LikeEntity likeEntity = LikeEntity.toSaveEntity(userEntity, freeBoardEntity);
//                Optional<LikeEntity> optionalLikeEntity = likeRepository.findByUserAndFreeBoard(likeEntity.getUser(), likeEntity.getFreeBoardEntity()); // 동작 안하면 다음엔 get해서 ID값을 넣어보자
//                if (optionalLikeEntity.isPresent()){
//                    return 1;
//                }else {
//                    return 0;
//                }
//            } else {
//                return null;
//            }
//        } else {
//            return null;
//        }
//
//
//    }
}