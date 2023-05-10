package com.kh.learnovation.domain.jobposting.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ListResp {
    /** 목록에 필요한 멤버변수 */
    private String wantedAuthNo;
    private String company;
    private String region;
    private String title;
    private String salTpNm;
    private String sal;
    private String career;
    private String regDt;
    private String closeDt;

    @Builder
    public ListResp(String wantedAuthNo, String company, String region, String title, String salTpNm, String sal, String career, String regDt, String closeDt){
        this.wantedAuthNo = wantedAuthNo;
        this.company = company;
        this.region = region;
        this.title = title;
        this.salTpNm = salTpNm;
        this.sal = sal;
        this.career = career;
        this.regDt = regDt;
        this.closeDt = closeDt;
    }
}
