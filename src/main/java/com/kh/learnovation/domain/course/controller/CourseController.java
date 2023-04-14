package com.kh.learnovation.domain.course.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
public class CourseController {

    @GetMapping("/course/register")
    public String showCourseRegister(){
        return "/course/CourseRegister";
    }
}
