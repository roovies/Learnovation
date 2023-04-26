package com.kh.learnovation.domain.question.controller;

import com.kh.learnovation.domain.question.dto.QuestionDTO;
import com.kh.learnovation.domain.question.service.QuestionService;
import com.kh.learnovation.domain.user.dto.UserDTO;
import com.kh.learnovation.domain.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class QuestionController {

    private final QuestionService questionService;

    private final UserService userService;

    public QuestionController(QuestionService questionService, UserService userService) {
        this.questionService = questionService;
        this.userService = userService;
    }

    // 질문 게시글 목록
    @RequestMapping(value = "/question/list", method = RequestMethod.GET)
    public String list(Model model) {

        List<QuestionDTO> questionList = questionService.questionList();
        model.addAttribute("question", questionList);


        return "question/list";
    }

    // 질문 게시글 등록 페이지
    @RequestMapping(value = "/question/registView", method = RequestMethod.GET)
    public String registView() {
        return "question/register";
    }

    // 질문 게시글 등록
    @RequestMapping(value = "/question/regist", method = RequestMethod.POST)
    public String regist(QuestionDTO questionDTO) {
        questionService.savePost(questionDTO);
        return "redirect:/question/list";
    }

    // 질문 게시글 상세 조회
    @RequestMapping(value = "/question/{no}", method = RequestMethod.GET)
    public String detail(@PathVariable("no") Long id, Model model) {
        QuestionDTO questionDTO = questionService.getPost(id);
        questionService.updateView(id); // 게시글 조회수

        model.addAttribute("question", questionDTO);
        return "question/detail";
    }


}
