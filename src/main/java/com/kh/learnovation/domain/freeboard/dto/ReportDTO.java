package com.kh.learnovation.domain.freeboard.dto;


import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자
public class ReportDTO {

    private Long id;
    private Long userId;
    private String name;
    private Long freeBoardId;
    private String reportReason;
    private String reportContent;


    @Builder
    public ReportDTO(long id, Long userId, String name, Long freeBoardId, String reportReason, String reportContent) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.freeBoardId = freeBoardId;
        this.reportReason = reportReason;
        this.reportContent = reportContent;
    }

}
