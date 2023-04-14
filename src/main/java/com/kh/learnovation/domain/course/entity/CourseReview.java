package com.kh.learnovation.domain.course.entity;

import com.kh.learnovation.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="course_reviews")
public class CourseReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name="course_id", nullable = false)
    private Course course;
    @Column(name="content", nullable = false)
    private String content;
    @Column(name="rating", nullable = false, precision = 2, scale = 1)
    private BigDecimal rating;
    @CreationTimestamp
    @Column(name="created_at", nullable = false)
    private Timestamp createdAt;
    @UpdateTimestamp
    @Column(name="updated_at", nullable = false)
    private Timestamp updatedAt;
    @Column(name="deleted", nullable = false)
    private Boolean deleted;
    @Column(name="deleted_at")
    private Timestamp deletedAt;   // 강의 삭제일

}
