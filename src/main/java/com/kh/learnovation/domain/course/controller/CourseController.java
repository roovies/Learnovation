package com.kh.learnovation.domain.course.controller;

import com.kh.learnovation.domain.course.dto.CourseDTO;
import com.kh.learnovation.domain.course.dto.CourseLessonDTO;
import com.kh.learnovation.domain.course.dto.CourseVideoDTO;
import com.kh.learnovation.domain.course.service.CourseService;
import com.kh.learnovation.domain.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.PersistenceException;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.BatchUpdateException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/course")
public class CourseController {

    private final ResourceLoader resourceLoader;
    private final CourseService courseService;

    @Autowired
    public CourseController(ResourceLoader resourceLoader, CourseService courseService) {
        this.resourceLoader = resourceLoader;
        this.courseService = courseService;
    }


 /**==============================================================================================================
 / 강의 등록관련 (후에 manager domain으로 옮겨야 함)
 /==============================================================================================================*/
    @GetMapping("/register")
    public String showCourseRegistration() {
        return "/course/CourseRegister";
    }
    @PostMapping("/register")
    public String registerCourse(@ModelAttribute CourseDTO courseDto, String priceStr,
                                 @RequestParam("chapter") String[] chapters, @ModelAttribute CourseLessonDTO courseLessonDto,
                                 @RequestParam("videoFile") MultipartFile[] videoFiles
    ) {
        try {
            if(courseDto.getTitle()==null || courseDto.getContent()==null){
                return "에러페이지";
            }
            //가격 콤마 제거
            int price = Integer.parseInt(priceStr.replace(",", ""));
            courseDto.setPrice(price);
            courseService.createCourse(courseDto, chapters, courseLessonDto, videoFiles);
        } catch (NumberFormatException e) {
            System.out.println("가격 입력 오류 발생 (공백 또는 int형으로 변환되지 않는 문자가 입력됨)");
            return "에러페이지";
        } catch (PersistenceException | BatchUpdateException e) {
            System.out.println("강의 등록 실패");
            return "에러페이지";
        } catch (IllegalStateException | IOException e) {
            System.out.println("첨부파일 등록 실패");
            return "에러페이지";
        } catch (NoSuchElementException message) { // 이메일이 존재하지 않을 경우
            System.out.println(message);
        }
        return "성공페이지";
    }
    // 이메일로 회원여부 확인 ajax
    @PostMapping("/register/checkemail")
    @ResponseBody
    public UserDTO checkUserViaEmail(@RequestBody Map<String, String> data){
        String email = data.get("email");
        UserDTO userDto = courseService.findUserByEmail(email);
        return userDto;
    }
}
