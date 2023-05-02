package com.kh.learnovation.domain.freeboard.service;


import com.kh.learnovation.domain.freeboard.dto.FreeBoardDTO;
import com.kh.learnovation.domain.freeboard.entity.FreeBoardEntity;
import com.kh.learnovation.domain.freeboard.entity.FreeBoardFileEntity;
import com.kh.learnovation.domain.freeboard.repository.FreeBoardFileRepository;
import com.kh.learnovation.domain.freeboard.repository.FreeBoardRepository;
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
    private final FreeBoardFileRepository freeBoardFileRepository;


    @Override
    public void save(FreeBoardDTO freeBoardDTO) throws IOException {
        // 파일 첨부 여부에 따라 로직 분리
        if (freeBoardDTO.getFreeBoardFile().isEmpty()) {
            // 첨부 파일 없음.
            FreeBoardEntity freeBoardEntity = FreeBoardEntity.toSaveEntity(freeBoardDTO);
            freeBoardRepository.save(freeBoardEntity);
        } else {
            // 첨부 파일 있음.
                /*
                    1. DTO에 담긴 파일을 꺼냄
                    2. 파일의 이름 가져옴
                    3. 서버 저장용 이름을 만듦
                    // 내사진.jpg => 839798375892_내사진.jpg
                    4. 저장 경로 설정
                    5. 해당 경로에 파일 저장
                    6. board_table에 해당 데이터 save 처리
                    7. board_file_table에 해당 데이터 save 처리
                 */
            File file = new File("");
            String rootPath = String.valueOf(file.getAbsoluteFile());
            String savePath = rootPath + "\\src\\main\\resources\\static\\uploadFiles\\";
            File folder = new File(savePath);
            // 저장할 폴더가 없을 경우 생성
            if (!folder.exists()) {
                folder.mkdirs();
            }

            MultipartFile freeBoardFile = freeBoardDTO.getFreeBoardFile(); // 1.
            String originalFilename = freeBoardFile.getOriginalFilename(); // 2.
            String storedFileName = System.currentTimeMillis() + "_" + originalFilename; // 3.
            String filePath = savePath + "/" + storedFileName; // 4. C:/springboot_img/9802398403948_내사진.jpg
            //            String savePath = "/Users/사용자이름/springboot_img/" + storedFileName; // C:/springboot_img/9802398403948_내사진.jpg
            freeBoardFile.transferTo(new File(filePath));  //5
            FreeBoardEntity freeBoardEntity = FreeBoardEntity.toSaveFileEntity(freeBoardDTO);
            Long savedId = freeBoardRepository.save(freeBoardEntity).getId();
            FreeBoardEntity freeBoard = freeBoardRepository.findById(savedId).get();

            FreeBoardFileEntity freeBoardFileEntity = FreeBoardFileEntity.toBoardFileEntity(freeBoard, originalFilename, storedFileName);
            freeBoardFileRepository.save(freeBoardFileEntity);
        }

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
            FreeBoardDTO freeBoardDTO = FreeBoardDTO.toBoardDTO(freeBoardEntity);
            return freeBoardDTO;
        } else {
            return null;
        }
    }
    @Override
    public FreeBoardDTO update(FreeBoardDTO freeBoardDTO) {
        FreeBoardEntity freeBoardEntity = FreeBoardEntity.toUpdateEntity(freeBoardDTO);
        freeBoardRepository.save(freeBoardEntity);
        return findById(freeBoardDTO.getId());
    }
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

        System.out.println("freeBoardEntities.getContent() = " + freeBoardEntities.getContent()); // 요청 페이지에 해당하는 글
        System.out.println("freeBoardEntities.getTotalElements() = " + freeBoardEntities.getTotalElements()); // 전체 글갯수
        System.out.println("freeBoardEntities.getNumber() = " + freeBoardEntities.getNumber()); // DB로 요청한 페이지 번호
        System.out.println("freeBoardEntities.getTotalPages() = " + freeBoardEntities.getTotalPages()); // 전체 페이지 갯수
        System.out.println("freeBoardEntities.getSize() = " + freeBoardEntities.getSize()); // 한 페이지에 보여지는 글 갯수
        System.out.println("freeBoardEntities.hasPrevious() = " + freeBoardEntities.hasPrevious()); // 이전 페이지 존재 여부
        System.out.println("freeBoardEntities.isFirst() = " + freeBoardEntities.isFirst()); // 첫 페이지 여부
        System.out.println("freeBoardEntities.isLast() = " + freeBoardEntities.isLast()); // 마지막 페이지 여부

        // 목록: id, writer, title, hits, createdTime
        Page<FreeBoardDTO> freeBoardDTOS = freeBoardEntities.map(freeBoard -> new FreeBoardDTO(freeBoard.getId(), freeBoard.getFreeBoardTitle(), freeBoard.getFreeBoardHits(), freeBoard.getCreatedTime()));
        return freeBoardDTOS;
    }
    @Override
    public Page<FreeBoardDTO> freeBoardSearchList(Pageable pageable, String searchKeyword){
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 20;
        Page<FreeBoardEntity> freeBoardSearchEntities = freeBoardRepository.findByFreeBoardTitleContaining(searchKeyword, PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
        Page<FreeBoardDTO> freeBoardSearchDTOS = freeBoardSearchEntities.map(freeBoardSearch -> new FreeBoardDTO(freeBoardSearch.getId(), freeBoardSearch.getFreeBoardTitle(), freeBoardSearch.getFreeBoardHits(), freeBoardSearch.getCreatedTime()));
        return freeBoardSearchDTOS;
    }

}
