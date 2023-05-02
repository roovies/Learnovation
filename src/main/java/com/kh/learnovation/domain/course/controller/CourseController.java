package com.kh.learnovation.domain.course.controller;

import com.kh.learnovation.domain.course.dto.CourseDTO;
import com.kh.learnovation.domain.course.dto.CourseDetailDTO;
import com.kh.learnovation.domain.course.dto.CourseLessonDTO;
import com.kh.learnovation.domain.course.service.CourseService;
import com.kh.learnovation.domain.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.PersistenceException;
import java.io.File;
import java.io.IOException;
import java.sql.BatchUpdateException;
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


    /**
     * ==============================================================================================================
     * / 강의 등록관련 (후에 manager domain으로 옮겨야 함)
     * /==============================================================================================================
     */
    @GetMapping("/register")
    public String showCourseRegistration() {
        return "/course/CourseRegister";
    }

    // 이메일로 회원여부 확인 ajax
    @PostMapping("/register/checkemail")
    @ResponseBody
    public UserDTO checkUserViaEmail(@RequestBody Map<String, String> data) {
        String email = data.get("email");
        UserDTO userDto = courseService.findUserByEmail(email);
        return userDto;
    }

    @PostMapping("/register")
    public String registerCourse(@ModelAttribute CourseDTO courseDto, String priceStr,
                                 @RequestParam("chapter") String[] chapters, @ModelAttribute CourseLessonDTO courseLessonDto,
                                 @RequestParam("thumbnail") MultipartFile imageFile,
                                 @RequestParam("videoFile") MultipartFile[] videoFiles
    ) {
        try {
            if (courseDto.getTitle() == null || courseDto.getContent() == null) {
                return "에러페이지";
            }
            //가격 콤마 제거
            int price = Integer.parseInt(priceStr.replace(",", ""));
            courseDto.setPrice(price);
            // 인강 등록 (게시물+목차+개별강의+강의별 동영상)
            courseService.createCourse(courseDto, chapters, courseLessonDto,imageFile, videoFiles);
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
            System.out.println("이메일이 존재하지 않음");
            return "에러페이지";
        }
        return "성공페이지";
    }

    // SummerNote 이미지 업로드
    @PostMapping("/register/uploadImage")
    @ResponseBody
    public String summerNoteFileUpload(@RequestParam("file") MultipartFile file) {
        String url = "savedError";
        try {
            url = courseService.createImages(file);
        } catch (IOException e) {
            System.out.println("첨부파일 등록 실패");
            return "error";
        }
        return url;
    }

    /**
     * ==============================================================================================================
     * / 강의 상세 페이지
     * /==============================================================================================================
     */
    @GetMapping("/{id}")
    public String showCourseDetail(@PathVariable Long id, Model model){
        System.out.println(id);
        CourseDetailDTO detailDTO = courseService.findDetailById(id);
        model.addAttribute("detail", detailDTO);
        return "course/CourseDetail";
    }

    /**
     * ==============================================================================================================
     * / 강의 리스트
     * /==============================================================================================================
     */
    @GetMapping("/list")
    public String courseList(
            Model model
            , @RequestParam(value = "page", defaultValue = "1") Integer pageNum
    ) {

        List<CourseDetailDTO> courseList = courseService.getCourseList(pageNum);
        Integer[] pageList = courseService.getPageList(pageNum);

        model.addAttribute("courses", courseList);
        model.addAttribute("page", pageList);

        return "course/CourseList";
    }
}
