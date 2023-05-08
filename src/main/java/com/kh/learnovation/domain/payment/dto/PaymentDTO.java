package com.kh.learnovation.domain.payment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PaymentDTO {

    private Long id;
    private Long courseId;
    private Long userId;
    private String status;
    private int amount;


}
