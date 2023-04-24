package com.kh.learnovation.domain.course.service;

import com.kh.learnovation.domain.course.dto.CourseDTO;
import com.kh.learnovation.domain.course.dto.CourseDetailDTO;
import com.kh.learnovation.domain.course.dto.CourseLessonDTO;
import com.kh.learnovation.domain.course.entity.Course;
import com.kh.learnovation.domain.course.entity.CourseImage;
import com.kh.learnovation.domain.course.entity.CourseLesson;
import com.kh.learnovation.domain.course.entity.CourseVideo;
import com.kh.learnovation.domain.user.dto.UserDTO;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.PersistenceException;
import java.io.IOException;
import java.sql.BatchUpdateException;
import java.util.List;

public interface CourseService {
    /** 이메일로 유저 찾기 Service */
    UserDTO findUserByEmail(String email);

    /** 강의 등록 Service */
    void createCourse(CourseDTO courseDto, String[] chapters, CourseLessonDTO courseLessonDto, MultipartFile imageFile, MultipartFile[] videoFiles) throws IOException, PersistenceException, BatchUpdateException;

    /** 썸네일 업로드 Service */
    CourseImage createImage(MultipartFile imageFile, Course savedCourse) throws IOException;

    /**동영상 업로드 Service */
    List<CourseVideo> createVideos(MultipartFile[] videoFiles, List<CourseLesson> savedLessons) throws IOException;

    /** SummerNote 이미지 업로드 Service */
    String createImages(MultipartFile file) throws IOException;

    CourseDetailDTO findCourseById(Long id);
}
