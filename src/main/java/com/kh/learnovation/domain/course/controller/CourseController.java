package com.kh.learnovation.domain.course.controller;

import com.kh.learnovation.domain.course.dto.CourseDTO;
import com.kh.learnovation.domain.course.dto.CourseDetailDTO;
import com.kh.learnovation.domain.course.dto.CourseLessonDTO;
import com.kh.learnovation.domain.course.dto.CourseReviewDTO;
import com.kh.learnovation.domain.course.service.CourseService;
import com.kh.learnovation.domain.user.dto.UserDTO;
import com.kh.learnovation.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.PersistenceException;
import java.io.File;
import java.io.IOException;
import java.sql.BatchUpdateException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;


@Controller
@RequestMapping("/course")
public class CourseController {

    private final ResourceLoader resourceLoader;
    private final CourseService courseService;
    private final UserService userService;

    @Autowired
    public CourseController(ResourceLoader resourceLoader, CourseService courseService, UserService userService) {
        this.resourceLoader = resourceLoader;
        this.courseService = courseService;
        this.userService = userService;
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
        CourseDetailDTO detailDTO = courseService.findDetailById(id); // 인강 상세정보
        List<CourseReviewDTO> reviewDTOs = courseService.findReviewByPaging(id, 0); // 수강후기 최근 5개
        Long totalReview = courseService.countReviewByCourseId(id); // 수강후기 총 개수
        String avgRating = courseService.averageRatingByCourseId(id);
        model.addAttribute("detail", detailDTO);
        model.addAttribute("reviews", reviewDTOs);
        model.addAttribute("totalReview", totalReview);
        model.addAttribute("avgRating", avgRating);
        return "course/CourseDetail";
    }

    /**
     * ==============================================================================================================
     * / 나의 학습 페이지 (내 강의 대시보드)
     * /==============================================================================================================
     */
    @GetMapping("/purchased/{id}")
    public String showMyCourseDetail(@PathVariable Long id, Model model){
        /** 로그인 정보를 받고, 해당 회원 id를 토대로 "인강 구매내역 테이블"에 넘어오는 인강 고유 id 값이 있는지 확인한 후, 있으면 findDetailById를 하면 될듯. */
        // userId가 13인 회원 하드코딩으로 세션 설정
        UserDTO tmpUser = UserDTO.builder()
                .id(13L)
                .name("이현준")
                .nickname("고수되고싶다")
                .build();



        CourseDetailDTO detailDTO = courseService.findDetailById(id);
        Optional<CourseReviewDTO> reviewDTO = courseService.findReviewByUserIdAndCourseId(tmpUser.getId(), id);
        model.addAttribute("detail", detailDTO);
        model.addAttribute("loginUser", tmpUser);
        if(reviewDTO.isPresent()){ //작성한 리뷰가 존재할 시
            model.addAttribute("review", reviewDTO.get());
            return "course/CourseDashboard";
        }
        else { //작성한 리뷰가 존재하지 않을 시
            model.addAttribute("review", new CourseReviewDTO());
            return "course/CourseDashboard";
        }
    }

    /**
     * ==============================================================================================================
     * / 수강후기
     * /==============================================================================================================
     */
    // 1. 수강후기 작성
    @PostMapping("/review/register")
    @ResponseBody
    public String registerReview(@RequestBody CourseReviewDTO reviewDTO){
        // 로그인 안되어 있으므로 다음과 같이 userId를 13로 고정떄림
        reviewDTO.setUserId(13L);
        try {
            courseService.createReview(reviewDTO);
            return "success";
        } catch (Exception e){
            return "failed";
        }
    }

    // 2. 수강후기 수정
    @PostMapping("/review/update")
    @ResponseBody
    public String updateReview(@RequestBody CourseReviewDTO reviewDTO){
        // 로그인 안되어 있으므로 다음과 같이 userId를 13로 고정떄림
        reviewDTO.setUserId(13L);
        try {
            courseService.updateReview(reviewDTO);
            return "success";
        } catch (Exception e) {
            return "failed";
        }
    }

    // 3. 수강후기 삭제
    @PostMapping("/review/delete")
    @ResponseBody
    public String deleteReview(@RequestBody CourseReviewDTO reviewDTO){
        // 로그인 안되어 있으므로 다음과 같이 userId를 13로 고정떄림
        reviewDTO.setUserId(13L);
        int result = courseService.deleteReview(reviewDTO.getId(), reviewDTO.getUserId());
        if(result>0){
            return "success";
        } else{
            return "failed";
        }
    }

    // 4. 수강후기 더보기
    @PostMapping("/{courseId}/more")
    @ResponseBody
    public List<CourseReviewDTO> moreReview(@PathVariable Long courseId, @RequestBody Map<String, String> map){
        int page = Integer.parseInt(map.get("page"));
        List<CourseReviewDTO> reviewDTOs = courseService.findReviewByPaging(courseId, page);
        if (!reviewDTOs.isEmpty()){
            System.out.println("@@@@@@@@@@@@@@@@@@@@@");
            System.out.println("비어있다~~");
        }
//        for (CourseReviewDTO reviewDTO : reviewDTOs){
//            System.out.println(reviewDTO.toString());
//        }
        return reviewDTOs;
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
        model.addAttribute("pageList", pageList);

        return "course/CourseList";
    }

    /**
     * ==============================================================================================================
     * / 강의 제목으로 검색
     * /==============================================================================================================
     */

    @GetMapping("/search")
    public String courseSearch(@RequestParam(value = "keyword") String keyword, Model model) {
        List<CourseDetailDTO> courseDetailDTOList = courseService.courseSearch(keyword);
        model.addAttribute("courses", courseDetailDTOList);

        return "course/CourseList";
    }
}
