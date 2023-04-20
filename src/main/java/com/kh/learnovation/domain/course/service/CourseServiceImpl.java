package com.kh.learnovation.domain.course.service;

import com.kh.learnovation.domain.course.dto.CourseDTO;
import com.kh.learnovation.domain.course.dto.CourseLessonDTO;
import com.kh.learnovation.domain.course.entity.*;
import com.kh.learnovation.domain.course.repository.*;
import com.kh.learnovation.domain.user.dto.UserDTO;
import com.kh.learnovation.domain.user.entity.User;
import com.kh.learnovation.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.sql.BatchUpdateException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CourseServiceImpl implements CourseService {
    private final ResourceLoader resourceLoader;
    private final CourseRepository courseRepository;
    private final ImageRepository imageRepository;
    private final CategoryRepository categoryRepository;
    private final ChapterRepository chapterRepository;
    private final LessonRepository lessonRepository;
    private final VideoRepository videoRepository;
    private final UserRepository userRepository;

    @Autowired
    public CourseServiceImpl(ResourceLoader resourceLoader, CourseRepository courseRepository,
                             ImageRepository imageRepository,
                             CategoryRepository categoryRepository, ChapterRepository chapterRepository,
                             LessonRepository lessonRepository, VideoRepository videoRepository,
                             UserRepository userRepository) {
        this.resourceLoader = resourceLoader;
        this.courseRepository = courseRepository;
        this.imageRepository = imageRepository;
        this.categoryRepository = categoryRepository;
        this.chapterRepository = chapterRepository;
        this.lessonRepository = lessonRepository;
        this.videoRepository = videoRepository;
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        Optional<User> foundUser = userRepository.findByEmail(email);
        if (foundUser.isPresent()) {
            UserDTO userResp = UserDTO.builder()
                    .id(foundUser.get().getId())
                    .socialId(foundUser.get().getSocialId())
                    .socialProvider(foundUser.get().getSocialProvider())
                    .email(foundUser.get().getEmail())
                    .name(foundUser.get().getName())
                    .nickname(foundUser.get().getNickname())
                    .phoneNumber(foundUser.get().getPhoneNumber())
                    .profileImage(foundUser.get().getProfileImage())
                    .createdAt(foundUser.get().getCreatedAt())
                    .updatedAt(foundUser.get().getUpdatedAt())
                    .status(foundUser.get().getStatus())
                    .deletedAt(foundUser.get().getDeletedAt())
                    .build();
            return userResp;
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public void createCourse(CourseDTO courseDto,
                             String[] chapters,
                             CourseLessonDTO courseLessonDto,
                             MultipartFile imageFile,
                             MultipartFile[] videoFiles
    ) throws IOException, PersistenceException, BatchUpdateException {
        /** 글 처리 (카테고리정보, 회원정보, 글제목, 난이도, 가격, 글내용, 생성일, 수정일, 삭제여부, 삭제일) */
        // 1. DTO에 있는 카테고리명을 통해 CourseCategory 객체 불러오기
        CourseCategory foundCategory = categoryRepository.findByName(courseDto.getCategory());
        // 2. DTO에 있는 이메일을 통해 User 객체 불러오기
        Optional<User> foundUser = Optional.ofNullable(userRepository.findByEmail(courseDto.getEmail()))
                .orElseThrow(() -> new NoSuchElementException("[" + courseDto.getEmail() + "] 이메일이 존재하지 않습니다."));

        // 3. DTO -> Entity 변환
        Course toSaveCourse = Course.builder()
                .courseCategory(foundCategory)
                .user(foundUser.get())
                .title(courseDto.getTitle())
                .level(courseDto.getLevel())
                .price(courseDto.getPrice())
                .content(courseDto.getContent())
                .build();
        // 4. 변환된 Entity를 통해 DB 저장
        Course savedCourse = courseRepository.save(toSaveCourse);

        /** 썸네일 이미지 처리 */
        CourseImage uploadedImage = createImage(imageFile, savedCourse);
        CourseImage savedImage = imageRepository.save(uploadedImage);


        /** 목차 목록 처리 (게시물과 1:N 관계) */
        // 1. 목차 목록을 담을 List 생성
        List<CourseChapter> toSaveChapters = new ArrayList<>();
        // 2. 전달된 목차만큼 for문 반복하여 chapter 객체에 저장후, List에 저장
        for (int i = 0; i < chapters.length; i++) {
            CourseChapter toSaveChapter = CourseChapter.builder()
                    .course(savedCourse)
                    .title(chapters[i])
                    .chapterOrder(i)
                    .build();
            toSaveChapters.add(toSaveChapter);
        }
        // 3. 목차들이 저장된 목록 Entity를 DB에 저장
        List<CourseChapter> savedChapters = chapterRepository.saveAll(toSaveChapters);

        /** 강의 목록 처리 (목차와 1:N 관계) */
        // 2차원배열로 표현하여, 행(앞의 중괄호) 값을 목차로 표현
        List<CourseLesson> toSaveLessons = new ArrayList<>();
        String[][] lessons = courseLessonDto.getLesson();
        int order = 0;
        for (int i = 0; i < lessons.length; i++) {
            for (int j = 0; j < lessons[i].length; j++) {
                CourseLesson toSaveLesson = CourseLesson.builder()
                        .courseChapter(savedChapters.get(i))
                        .title(lessons[i][j])
                        .lessonOrder(order)
                        .build();
                toSaveLessons.add(toSaveLesson);
                order++;
            }
        }
        List<CourseLesson> savedLessons = lessonRepository.saveAll(toSaveLessons);

        /** 동영상 목록 처리 - (강의와 1:1 관계) */
        // 1. 동영상 서버에 저장
        List<CourseVideo> uploadedVideos = createVideos(videoFiles, savedLessons);
        List<CourseVideo> savedVideos = videoRepository.saveAll(uploadedVideos);
    }

    @Override
    public CourseImage createImage(MultipartFile imageFile, Course savedCourse) throws IOException {
        // 1. 파일 저장 디렉터리 경로 구하기
        Resource resource = resourceLoader.getResource("file:src/main/resources/static/course/upload/thumbnails");
        String realPath = resource.getFile().getAbsolutePath();
        // 2. 저장할 디렉터리에 날짜별로 디렉터리 생성
        String today = new SimpleDateFormat("yyMMdd").format(new Date()); //230417
        String savedFolder = realPath + File.separator + today; // File.separator는 파일시스템 구분자. 윈도우(\), 유닉스계열(/)
        File folder = new File(savedFolder);
        if (!folder.exists())
            folder.mkdirs();
        // 3. 업로드 요청한 썸네일 이미지 서버에 저장
        CourseImage toSaveImage = new CourseImage();
        String originalImageName = imageFile.getOriginalFilename();
        if(!originalImageName.isEmpty()){
            String toSaveImageName = UUID.randomUUID() + originalImageName.substring(originalImageName.lastIndexOf('.'));
            toSaveImage.setCourse(savedCourse);
            toSaveImage.setOriginalImageName(originalImageName);
            toSaveImage.setSavedImageName(toSaveImageName);
            toSaveImage.setSavedPath(today);
            toSaveImage.setImageSize(imageFile.getSize());
            imageFile.transferTo(new File(folder, toSaveImageName));
        }
        return toSaveImage;
    }

    @Override
    @Transactional
    public List<CourseVideo> createVideos(MultipartFile[] videoFiles, List<CourseLesson> savedLessons
    ) throws IOException {
        // 1. 파일 저장할 디렉터리 경로 구하기
        Resource resource = resourceLoader.getResource("file:src/main/resources/static/course/upload/videos");
        String realPath = resource.getFile().getAbsolutePath();
        // 2. 해당 경로에 날짜별로 디렉터리 생성
        String today = new SimpleDateFormat("yyMMdd").format(new Date()); //230417
        String savedFolder = realPath + File.separator + today; // File.separator는 파일시스템 구분자. 윈도우(\), 유닉스계열(/)
        File folder = new File(savedFolder);
        if (!folder.exists())
            folder.mkdirs();
        // 3. 업로드 되는 파일 목록 저장
        List<CourseVideo> toSaveVideos = new ArrayList<CourseVideo>();
        int k = 0;
        for (MultipartFile videoFile : videoFiles) {
            CourseVideo toSaveVideo = new CourseVideo();
            String originalVideoName = videoFile.getOriginalFilename(); // 원본 파일명이 있으면 업로드된 파일이 남아있다는 것
            if (!originalVideoName.isEmpty()) {
                String toSaveVideoName = UUID.randomUUID() + originalVideoName.substring(originalVideoName.lastIndexOf('.'));
                toSaveVideo.setCourseLesson(savedLessons.get(k));
                toSaveVideo.setOriginalVideoName(originalVideoName);
                toSaveVideo.setSavedVideoName(toSaveVideoName);
                toSaveVideo.setSavedPath(today);
//                toSaveVideo.setSavedPath(savedFolder);
                toSaveVideo.setVideoSize(videoFile.getSize());
                videoFile.transferTo(new File(folder, toSaveVideoName));
                k++;
            }
            toSaveVideos.add(toSaveVideo);
        }
        return toSaveVideos;
    }

    @Override
    public String createImages(MultipartFile file) throws IOException {
        // 1. 업로드 경로 구하기
        Resource resource = resourceLoader.getResource("file:src/main/resources/static/course/upload/images");
        String realPath = resource.getFile().getAbsolutePath();
        // 2. 날짜별 디렉터리 생성
        String today = new SimpleDateFormat("yyMMdd").format(new Date());
        String savedFolder = realPath + File.separator + today;
        File folder = new File(savedFolder);
        if (!folder.exists())
            folder.mkdirs();
        // 3. 업로드 요청된 이미지들 서버에 저장
        String originalImageName = file.getOriginalFilename();
        String returnUrl = "savedError";
        String savedImageName = "";
        if (!originalImageName.isEmpty()) {
            savedImageName = UUID.randomUUID() + originalImageName.substring(originalImageName.lastIndexOf('.'));
            file.transferTo(new File(folder, savedImageName));
            returnUrl = "/course/upload/images/" + today + "/" + savedImageName;
        }
        return returnUrl;
    }
}
