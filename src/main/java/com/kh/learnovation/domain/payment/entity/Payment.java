package com.kh.learnovation.domain.payment.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "payments")
public class Payment {

    @Id
    @Column(name = "pay_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 구매번호

    @Column(name = "course_id", nullable = false)
    private Long courseId; // 강의번호

    @Column(name = "user_id", nullable = false)
    private Long userId; // 유저번호

    @Column(name = "payment_at")
    private Timestamp paymentAt;


}
