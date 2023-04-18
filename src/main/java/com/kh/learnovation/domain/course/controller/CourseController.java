package com.kh.learnovation.domain.course.controller;

import com.kh.learnovation.domain.course.dto.CourseDTO;
import com.kh.learnovation.domain.course.dto.CourseLessonDTO;
import com.kh.learnovation.domain.course.dto.CourseVideoDTO;
import com.kh.learnovation.domain.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/course")
public class CourseController {

    private final ResourceLoader resourceLoader;
    private final CourseService courseService;
    @Autowired
    public CourseController(ResourceLoader resourceLoader, CourseService courseService){
        this.resourceLoader = resourceLoader;
        this.courseService = courseService;
    }

    /**=================================================================================================================
    / 강의 등록관련 (후에 manager domain으로 옮겨야 함)
    /=================================================================================================================*/
    @GetMapping("/register")
    public String showCourseRegistration() {
        return "/course/CourseRegister";
    }

    @PostMapping("/register")
    public String registerCourse(@ModelAttribute CourseDTO courseDto, String priceStr,
                                 @RequestParam("chapter") String[] chapters, @ModelAttribute CourseLessonDTO courseLessonDto,
                                 @RequestParam("videoFile") MultipartFile[] videoFiles
    ) throws IllegalStateException, IOException {
        //가격 콤마 제거
        int price = Integer.parseInt(priceStr.replace(",", ""));
        courseDto.setPrice(price);
        String result = courseService.createCourse(courseDto, chapters, courseLessonDto, videoFiles);
        return "";
    }
}
