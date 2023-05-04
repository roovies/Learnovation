package com.kh.learnovation.domain.freeboard.service;


import com.kh.learnovation.domain.admin.entity.Admin;
import com.kh.learnovation.domain.freeboard.dto.FreeBoardDTO;
import com.kh.learnovation.domain.freeboard.entity.FreeBoardEntity;
import com.kh.learnovation.domain.freeboard.entity.FreeBoardFileEntity;
import com.kh.learnovation.domain.freeboard.repository.FreeBoardFileRepository;
import com.kh.learnovation.domain.freeboard.repository.FreeBoardRepository;
import com.kh.learnovation.domain.notice.dto.NoticeDTO;
import com.kh.learnovation.domain.notice.entity.Notice;
import com.kh.learnovation.domain.user.entity.User;
import com.kh.learnovation.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class FreeBoardServiceImpl implements FreeBoardService {

    private final FreeBoardRepository freeBoardRepository;
    private final UserRepository userRepository;
    private final FreeBoardFileRepository freeBoardFileRepository;


    @Override
    @Transactional
    public FreeBoardDTO insertFreeBoard(FreeBoardDTO freeBoardDTO) throws IOException {
        User user = userRepository.findById(freeBoardDTO.getUserId()).get();
        System.out.println(user.toString());
        FreeBoardEntity freeBoardEntity = FreeBoardEntity.builder()
                .user(user)
                .freeBoardTitle(freeBoardDTO.getFreeBoardTitle())
                .freeBoardContents(freeBoardDTO.getFreeBoardContents())
                .createdAt(freeBoardDTO.getFreeBoardCreatedTime())
                .updatedAt(freeBoardDTO.getFreeBoardUpdatedTime())
                .status(freeBoardDTO.getStatus())
                .subject(freeBoardDTO.getSubject())
                .build();
        freeBoardEntity = freeBoardRepository.save(freeBoardEntity);
        freeBoardDTO = FreeBoardDTO.builder()
                .id(freeBoardEntity.getId())
                .userId(freeBoardEntity.getUser().getId())
                .nickname(freeBoardEntity.getUser().getNickname())
                .freeBoardTitle(freeBoardEntity.getFreeBoardTitle())
                .freeBoardContents(freeBoardEntity.getFreeBoardContents())
                .freeBoardCreatedTime(freeBoardEntity.getCreatedAt())
                .freeBoardUpdatedTime(freeBoardEntity.getUpdatedAt())
                .status(freeBoardEntity.getStatus())
                .subject(freeBoardEntity.getSubject())
                .build();

        return freeBoardDTO;

        }


    @Override
    @Transactional
    public List<FreeBoardDTO> findAll() {
        List<FreeBoardEntity> freeBoardEntityList = freeBoardRepository.findAll();
        List<FreeBoardDTO> freeBoardDTOList = new ArrayList<>();
        for (FreeBoardEntity freeBoardEntity: freeBoardEntityList) {
            freeBoardDTOList.add(FreeBoardDTO.toBoardDTO(freeBoardEntity));
        }
        return freeBoardDTOList;
    }
    @Override
    @Transactional
    public void updateHits(Long id) {
        freeBoardRepository.updateHits(id);
    }

    @Override
    @Transactional
    public FreeBoardDTO findById(Long id) {
        Optional<FreeBoardEntity> optionalFreeBoardEntity = freeBoardRepository.findById(id);
        if (optionalFreeBoardEntity.isPresent()) {
            FreeBoardEntity freeBoardEntity = optionalFreeBoardEntity.get();
            FreeBoardDTO freeBoardDTO = FreeBoardDTO.builder()
                    .id(freeBoardEntity.getId())
                    .userId(freeBoardEntity.getUser().getId())
                    .nickname(freeBoardEntity.getUser().getNickname())
                    .freeBoardTitle(freeBoardEntity.getFreeBoardTitle())
                    .freeBoardContents(freeBoardEntity.getFreeBoardContents())
                    .freeBoardHits(freeBoardEntity.getFreeBoardHits())
                    .freeBoardCreatedTime(freeBoardEntity.getCreatedAt())
                    .freeBoardUpdatedTime(freeBoardEntity.getUpdatedAt())
//                  .status(freeBoard.getStatus())
                    .subject(freeBoardEntity.getSubject())
                    .build();
            return freeBoardDTO;
        } else {
            return null;
        }
    }






//    @Override
//    public FreeBoardDTO update(FreeBoardDTO freeBoardDTO) {
//        FreeBoardEntity freeBoardEntity = FreeBoardEntity.toUpdateEntity(freeBoardDTO);
//        freeBoardRepository.save(freeBoardEntity);
//        return findById(freeBoardDTO.getId());
//    }
    @Override
    public void delete(Long id) {
        freeBoardRepository.deleteById(id);
    }
    @Override
    public Page<FreeBoardDTO> freeBoardList(Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 20; // 한 페이지에 보여줄 글 갯수
        // 한페이지당 3개씩 글을 보여주고 정렬 기준은 id 기준으로 내림차순 정렬
        // page 위치에 있는 값은 0부터 시작
        Page<FreeBoardEntity> freeBoardEntities =
                freeBoardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
        System.out.println(freeBoardEntities);

        System.out.println("freeBoardEntities.getContent() = " + freeBoardEntities.getContent()); // 요청 페이지에 해당하는 글
        System.out.println("freeBoardEntities.getTotalElements() = " + freeBoardEntities.getTotalElements()); // 전체 글갯수
        System.out.println("freeBoardEntities.getNumber() = " + freeBoardEntities.getNumber()); // DB로 요청한 페이지 번호
        System.out.println("freeBoardEntities.getTotalPages() = " + freeBoardEntities.getTotalPages()); // 전체 페이지 갯수
        System.out.println("freeBoardEntities.getSize() = " + freeBoardEntities.getSize()); // 한 페이지에 보여지는 글 갯수
        System.out.println("freeBoardEntities.hasPrevious() = " + freeBoardEntities.hasPrevious()); // 이전 페이지 존재 여부
        System.out.println("freeBoardEntities.isFirst() = " + freeBoardEntities.isFirst()); // 첫 페이지 여부
        System.out.println("freeBoardEntities.isLast() = " + freeBoardEntities.isLast()); // 마지막 페이지 여부

        // 목록: id, writer, title, hits, createdTime
        Page<FreeBoardDTO> freeBoardDTOS = freeBoardEntities.map(freeBoard -> FreeBoardDTO.builder().id(freeBoard.getId())
                .userId(freeBoard.getUser().getId())
                .nickname(freeBoard.getUser().getNickname())
                .freeBoardTitle(freeBoard.getFreeBoardTitle())
                .freeBoardContents(freeBoard.getFreeBoardContents())
                .freeBoardCreatedTime(freeBoard.getCreatedAt())
                .freeBoardUpdatedTime(freeBoard.getUpdatedAt())
//                .status(freeBoard.getStatus())
                .subject(freeBoard.getSubject())
                .build());
        return freeBoardDTOS;
    }
    @Override
    public Page<FreeBoardDTO> freeBoardSearchList(Pageable pageable, String searchKeyword){
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 20;
        Page<FreeBoardEntity> freeBoardSearchEntities = freeBoardRepository.findByFreeBoardTitleContaining(searchKeyword, PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
        Page<FreeBoardDTO> freeBoardSearchDTOS = freeBoardSearchEntities.map(freeBoardSearch ->  FreeBoardDTO.builder().id(freeBoardSearch.getId())
                .userId(freeBoardSearch.getUser().getId())
                .nickname(freeBoardSearch.getUser().getNickname())
                .freeBoardTitle(freeBoardSearch.getFreeBoardTitle())
                .freeBoardContents(freeBoardSearch.getFreeBoardContents())
                .freeBoardCreatedTime(freeBoardSearch.getCreatedAt())
                .freeBoardUpdatedTime(freeBoardSearch.getUpdatedAt())
//                .status(freeBoardSearch.getStatus())
                .subject(freeBoardSearch.getSubject())
                .build());
        return freeBoardSearchDTOS;
    }



}
