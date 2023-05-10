package com.kh.learnovation.domain.payment.service;

import com.kh.learnovation.domain.course.entity.Course;
import com.kh.learnovation.domain.payment.entity.Payment;
import com.kh.learnovation.domain.payment.repository.PaymentRepository;
import com.kh.learnovation.domain.user.entity.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }


    @Override
    @Transactional
    public void savePayment(Long courseId, Long buyerId) {
        Payment payment = Payment.builder()
                .course(Course.builder()
                        .id(courseId)
                        .build())
                .user(User.builder()
                        .id(buyerId)
                        .build())
                .build();
        paymentRepository.save(payment);
    }
}
