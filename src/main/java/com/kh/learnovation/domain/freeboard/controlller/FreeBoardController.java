package com.kh.learnovation.domain.freeboard.controlller;


import com.kh.learnovation.domain.freeboard.dto.CommentDTO;
import com.kh.learnovation.domain.freeboard.dto.FreeBoardDTO;
import com.kh.learnovation.domain.freeboard.service.CommentService;
import com.kh.learnovation.domain.freeboard.service.FreeBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/freeBoard")
public class FreeBoardController {
    private final FreeBoardService freeBoardService;
    private final CommentService commentService;


    @GetMapping("/save")
    public String saveForm() {
        return "/freeBoard/save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute FreeBoardDTO freeBoardDTO) throws IOException {
        System.out.println("freeBoardDTO = " + freeBoardDTO);
        freeBoardService.save(freeBoardDTO);
        return "freeBoard/index";
    }

    @GetMapping("/")
    public String findAll(Model model) {
        // DB에서 전체 게시글 데이터를 가져와서 list.html에 보여준다.
        List<FreeBoardDTO> freeBoardDTOList = freeBoardService.findAll();
        model.addAttribute("freeBoardDTOList", freeBoardDTOList);
        return "/freeBoard/FreeList";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model,
                           @PageableDefault(page=1) Pageable pageable) {
        /*
            해당 게시글의 조회수를 하나 올리고
            게시글 데이터를 가져와서 detail.html에 출력
         */
        freeBoardService.updateHits(id);
        FreeBoardDTO freeBoardDTO = freeBoardService.findById(id);
        /* 댓글 목록 가져오기 */
        List<CommentDTO> commentDTOList = commentService.findAll(id);
        model.addAttribute("commentList", commentDTOList);
        model.addAttribute("freeBoard", freeBoardDTO);
        model.addAttribute("page", pageable.getPageNumber());
        return "/freeBoard/FreeDetail";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        FreeBoardDTO freeBoardDTO = freeBoardService.findById(id);
        model.addAttribute("freeBoardUpdate", freeBoardDTO);
        return "/freeBoard/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute FreeBoardDTO freeBoardDTO, Model model) {
        FreeBoardDTO freeBoard = freeBoardService.update(freeBoardDTO);
        model.addAttribute("freeBoard", freeBoard);
        return "/freeBoard/detail";
//        return "redirect:/board/" + boardDTO.getId();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        freeBoardService.delete(id);
        return "redirect:/freeBoard/";
    }

    // /board/paging?page=1
    @GetMapping("/paging")
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model, String searchKeyword) {

        Page<FreeBoardDTO> list = null;
        if (searchKeyword == null){
            list = freeBoardService.freeBoardList(pageable);
        }else{
            list = freeBoardService.freeBoardSearchList(pageable, searchKeyword);
        }
        int blockLimit = 5;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
        int endPage = ((startPage + blockLimit - 1) < list.getTotalPages()) ? startPage + blockLimit - 1 : list.getTotalPages();
        // page 갯수 20개
        // 현재 사용자가 3페이지
        // 1 2 3
        // 현재 사용자가 7페이지
        // 7 8 9
        // 보여지는 페이지 갯수 3개
        // 총 페이지 갯수 8개
        model.addAttribute("list", list);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "/freeBoard/paging";


    }


}
