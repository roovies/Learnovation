package com.kh.learnovation.domain.freeboard.dto;

import com.kh.learnovation.domain.freeboard.entity.LikeEntity;
import com.kh.learnovation.domain.freeboard.entity.ReportEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자
public class ReportDTO {

    private Long id;
    private Long userId;
    private Long freeBoardId;
    private String reportTitle;
    private String reportContent;

    public static ReportDTO toReportDTO(ReportEntity reportEntity, Long freeBoardId) {
        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setUserId(reportEntity.getUser().getId());
        reportDTO.setUserId(reportEntity.getUser().getId());
        reportDTO.setFreeBoardId(freeBoardId);
        return reportDTO;
    }

}
