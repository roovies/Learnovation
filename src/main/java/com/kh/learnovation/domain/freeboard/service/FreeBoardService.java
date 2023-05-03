package com.kh.learnovation.domain.freeboard.service;

import com.kh.learnovation.domain.freeboard.dto.FreeBoardDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface FreeBoardService {

    FreeBoardDTO insertFreeBoard(FreeBoardDTO freeBoardDTO) throws IOException;

    List<FreeBoardDTO> findAll();

    void updateHits(Long id);

    FreeBoardDTO findById(Long id);

//    FreeBoardDTO update(FreeBoardDTO freeBoardDTO);

    void delete(Long id);

    Page<FreeBoardDTO> freeBoardList(Pageable pageable);

    Page<FreeBoardDTO> freeBoardSearchList(Pageable pageable, String searchKeyword);
}
