package com.kh.learnovation.domain.question.controller;

import com.kh.learnovation.domain.question.dto.QuestionDTO;
import com.kh.learnovation.domain.question.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class QuestionController {

    private QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @RequestMapping(value = "/question/list", method = RequestMethod.GET)
    public String list() {
        return "question/list";
    }

    @RequestMapping(value = "/question/registView", method = RequestMethod.GET)
    public String registView() {
        return "question/regist";
    }

    @RequestMapping(value = "/question/regist", method = RequestMethod.POST)
    public String regist(QuestionDTO questionDTO) {
        questionService.savePost(questionDTO);
        return "redirect:/question/list";
    }


}
