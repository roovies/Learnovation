package com.kh.learnovation.domain.course.entity;

import com.kh.learnovation.domain.user.entity.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
@Table(name="course_reviews", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "course_id"})})
@DynamicInsert
@DynamicUpdate
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

    @Builder
    public CourseReview(Long id, User user, Course course, String content, BigDecimal rating, Timestamp createdAt, Timestamp updatedAt){
        this.id = id;
        this.user = user;
        this.course = course;
        this.content = content;
        this.rating = rating;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
