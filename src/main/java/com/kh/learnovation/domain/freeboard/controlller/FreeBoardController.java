package com.kh.learnovation.domain.freeboard.controlller;


import com.kh.learnovation.domain.freeboard.dto.FreeBoardDTO;
import com.kh.learnovation.domain.freeboard.service.CommentService;
import com.kh.learnovation.domain.freeboard.service.FreeBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/freeBoard")
public class FreeBoardController {
    private final FreeBoardService freeBoardService;
    private final CommentService commentService;


    @PostMapping("/save")
    public String save(@ModelAttribute FreeBoardDTO freeBoardDTO) throws IOException {
        System.out.println("freeBoardDTO = " + freeBoardDTO);
        FreeBoardService.save(freeBoardDTO);
        return "index";
    }



}
