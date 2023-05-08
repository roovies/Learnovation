package com.kh.learnovation.domain.freeboard.service;

import com.kh.learnovation.domain.freeboard.dto.LikeDTO;
import com.kh.learnovation.domain.freeboard.entity.FreeBoardEntity;
import com.kh.learnovation.domain.freeboard.entity.LikeEntity;
import com.kh.learnovation.domain.freeboard.repository.FreeBoardRepository;
import com.kh.learnovation.domain.freeboard.repository.LikeRepository;
import com.kh.learnovation.domain.user.dto.UserDTO;
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
    public LikeDTO check(LikeDTO likeDTO) {
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
                        return likeDTO;

                    }else {
                        return likeDTO;
                    }
                } else {
                    return null;
                }
            } else {
                return null;
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
    public void delete(Long id) {
        Optional<LikeEntity> optionalLikeEntity = likeRepository.findById(id);
        if (optionalLikeEntity.isPresent()) {
            likeRepository.deleteById(id);
        } else {
            // 엔티티가 존재하지 않는 경우, 예외 처리 또는 로그 등의 작업을 수행할 수 있습니다.
            System.out.println("왜 ㅇㄻㅇ리ㅏㄴ런ㅁ일머리ㅓㄴㅇ리ㅏ멀ㄴㅁ런미ㅏ러");
        }
    }

    @Override
    public Integer findLike(Long id, Optional<UserDTO> optionalUserDTO) {
        if(optionalUserDTO.isPresent()){
            UserDTO userDTO =optionalUserDTO.get();
            Optional<User> optionalUser =userRepository.findById(userDTO.getId());
            FreeBoardEntity freeBoardEntity=freeBoardRepository.findById(id).get();
                LikeEntity likeEntity = LikeEntity.toSaveEntity(optionalUser.get(), freeBoardEntity);
                Optional<LikeEntity> optionalLikeEntity =likeRepository.findByUserAndFreeBoard(optionalUser.get(), freeBoardEntity);
                if (optionalLikeEntity.isPresent()){
                    return 1;
                }else {
                    return 0;
                }
        }
        return 10;
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
