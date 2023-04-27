package com.kh.learnovation.domain.freeboard.dto;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자
public class LikeDTO {
    private Long id;
    private String likeWriter;
    private Long freeBoardId;

}
