package com.kh.learnovation.domain.course.entity;

import com.kh.learnovation.domain.user.entity.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courses")
@DynamicInsert
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CourseCategory courseCategory; // 강의 카테고리
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;      // 강사 정보
    @Column(name = "title", nullable = false)
    private String title;   // 강의 제목
    @Column(name = "level", nullable = false)
    private int level;      // 강의 난이도
    @Column(name = "price", nullable = false)
    private int price;      // 강의 가격
    @Column(name = "content", nullable = false)
    private String content; // 강의 소개글
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;   // 강의 등록일
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;   // 강의 수정일
    @Column(name = "deleted")
    private Boolean deleted;        // 강의 삭제여부
    @Column(name = "deleted_at")
    private Timestamp deletedAt;   // 강의 삭제일


    // Lazy 처리 연관관계 정의 (OneToMany, OneToOne)
    @OneToMany(mappedBy = "course") // mappedBy는 1:다에서 '다'쪽 엔티티에서 1쪽 엔티티의 필드명을 지정
    private List<CourseChapter> chapters;
    @OneToOne(mappedBy = "course")
    private CourseImage courseImage;

    @Builder
    // DTO -> Entity로 변환하기 때문에 id값은 받을 필요가 없다. => 그런줄 알았는데 써야 할 것 같아서 추가함
    public Course(Long id, CourseCategory courseCategory, User user, String title, int level, int price, String content
                  ) {
        this.id = id;
        this.courseCategory = courseCategory;
        this.user = user;
        this.title = title;
        this.level = level;
        this.price = price;
        this.content = content;
    }
}
