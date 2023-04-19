package com.kh.learnovation.domain.notice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NoticeController {
    @GetMapping("/notice/write")
    public ModelAndView noticeWriteView(ModelAndView mv){
        mv.setViewName("notice/write");
        return mv;
    }
}
