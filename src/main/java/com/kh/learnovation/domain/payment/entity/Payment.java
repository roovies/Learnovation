package com.kh.learnovation.domain.payment.entity;

import com.kh.learnovation.domain.course.entity.Course;
import com.kh.learnovation.domain.user.entity.User;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "payment")
public class Payment {

    @Id
    @Column(name = "pay_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 구매번호

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course; // 강의번호

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 유저번호

    @Column(name = "payment_at")
    private Timestamp paymentAt;

    @Builder
    public Payment(Long id, Course course, User user, Timestamp paymentAt) {
        this.id = id;
        this.course = course;
        this.user = user;
        this.paymentAt = paymentAt;
    }
}
