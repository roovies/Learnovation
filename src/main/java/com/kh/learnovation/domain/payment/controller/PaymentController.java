//package com.kh.learnovation.domain.payment.controller;
//
//import com.kh.learnovation.domain.payment.entity.Payment;
//import com.kh.learnovation.domain.payment.service.PaymentService;
//import org.apache.tomcat.util.json.JSONParser;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.BufferedReader;
//
//@RestController
//public class PaymentController {
//
//    @Autowired
//    private PaymentService paymentService;
//
//    @PostMapping("/payments/complete")
//    public String paymentComplete(HttpServletRequest request) throws Exception {
//
//        BufferedReader in = request.getReader();
//        String inputLine;
//        StringBuffer stringBuffer = new StringBuffer();
//        while ((inputLine = in.readLine()) != null) {
//            stringBuffer.append(inputLine);
//        }
//        in.close();
//
//        JSONParser jsonParser = new JSONParser();
//        JSONObject jsonObject = (JSONObject) jsonParser.parse(stringBuffer.toString());
//
//        //결제 결과 파싱
//        JSONObject response = (JSONObject) jsonObject.get("response");
//        String impUid = (String) response.get("imp_uid");
//        String merchantUid = (String) response.get("merchant_uid");
//        String status = (String) response.get("status");
//
//        //DB에서 결제 정보 조회
//        Payment payment = paymentService.getPaymentByMerchantUid(merchantUid);
//
//        //결제 요청한 금액과 결제 완료한 금액이 일치하는지 확인
//        if(payment.getAmount() == Integer.parseInt(response.get("amount").toString())) {
//            //결제 완료
//            payment.setImpUid(impUid);
//            payment.setStatus(status);
//            paymentService.updatePayment(payment);
//            return "success";
//        } else {
//            //결제 실패
//            return "failure";
//        }
//    }
//
//    @PostMapping("/prepare")
//    public String preparePayment(@ModelAttribute Payment payment, Model model) {
//
//        // Payment 엔티티를 DB에 저장
//        paymentService.savePayment(payment);
//
//        // 아임포트 API를 호출하여 결제 정보를 생성하고 반환된 URL을 가져옴
//        String paymentUrl = iamportApiPrepare(payment);
//
//        // 반환된 URL로 redirect
//        return "redirect:" + paymentUrl;
//    }
//
//    // 아임포트 API를 호출하여 결제 정보를 생성하고 반환된 URL을 반환하는 메서드
//    private String iamportApiPrepare(Payment payment) {
//        String paymentUrl = "";
//
//        // 아임포트 API를 호출하여 결제 정보 생성
//        // impUid를 반환받아 payment 객체의 impUid 필드에 설정
//        // paymentUrl을 반환받아 반환
//        return paymentUrl;
//    }
//
//}
