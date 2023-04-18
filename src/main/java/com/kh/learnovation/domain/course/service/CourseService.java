package com.kh.learnovation.domain.course.service;

import com.kh.learnovation.domain.course.dto.CourseDTO;
import com.kh.learnovation.domain.course.dto.CourseLessonDTO;
import com.kh.learnovation.domain.course.entity.CourseLesson;
import com.kh.learnovation.domain.course.entity.CourseVideo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CourseService {
    /** 강의 등록 Service */
    String createCourse(CourseDTO courseDto, String[] chapters, CourseLessonDTO courseLessonDto, MultipartFile[] videoFiles) throws IOException;

    /**동영상 업로드 Service*/
    List<CourseVideo> createVideos(MultipartFile[] videoFiles, List<CourseLesson> savedLessons) throws IOException;
}
