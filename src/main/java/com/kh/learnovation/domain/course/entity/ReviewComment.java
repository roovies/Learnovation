package com.kh.learnovation.domain.course.entity;

import com.kh.learnovation.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="review_comments")
public class ReviewComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="parent_id")
    private ReviewComment parentComment;
    @ManyToOne
    @JoinColumn(name="review_id", nullable = false)
    private CourseReview courseReview;
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;
    @Column(name="content", nullable = false, length = 500)
    private String content;
    @CreationTimestamp
    @Column(name="created_at", nullable = false, updatable = false)
    private Timestamp createdAt;
    @UpdateTimestamp
    @Column(name="updated_at", nullable = false)
    private Timestamp updatedAt;
    @Column(name="deleted", nullable = false)
    private Boolean deleted;
    @Column(name="deleted_at")
    private Timestamp deletedAt;

}
