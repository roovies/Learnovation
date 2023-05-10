package com.kh.learnovation.domain.payment.controller;

import com.kh.learnovation.domain.course.dto.CourseDetailDTO;
import com.kh.learnovation.domain.course.service.CourseService;
import com.kh.learnovation.domain.payment.dto.PaymentDTO;
import com.kh.learnovation.domain.payment.service.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    private final CourseService courseService;

    private final PaymentService paymentService;

    public PaymentController(CourseService courseService, PaymentService paymentService) {
        this.courseService = courseService;
        this.paymentService = paymentService;
    }

    // 결제 화면 View
    @GetMapping("/paymentView/{id}")
    public String paymentView(@PathVariable Long id, Model model) {
        CourseDetailDTO courseDetailDTO = courseService.findDetailById(id);

        model.addAttribute("course", courseDetailDTO);

        return "payment/payment2";
    }

    // 결제 및 검증 완료후
    @PostMapping("/pay")
    public String coursePayment(@RequestBody Map<String, String> map) {

        Long courseId = Long.parseLong(map.get("courseId"));
        Long buyerId = Long.parseLong(map.get("buyerId"));

        paymentService.savePayment(courseId, buyerId);


        return "course/CourseList";
    }

}
