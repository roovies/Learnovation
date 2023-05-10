package com.kh.learnovation.domain.jobposting.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ListReq {
    private String authKey;     // 인증키
    private String callTp;      // 호출하는 페이지 타입 (L:목록, D: 상세)
    private String returnType;  // 반드시 XML로 지정
    private int startPage;      // 기본값1, 검색 시작위치를 지정 (최대 1000까지 가능)
    private int display;        // 기본값10, 출력 개수 지정 (최대 100까지 가능)
    private String region;      // 근무지역 코드 (확인해야 함)
    private String occupation;  // 직종 코드 (024, 026)
    private String salTp;       // 임금 형태 (Y: 연봉, 미입력시 관계없음)
    private String minPay;         // 최소 급여 (~만원 이상: 연봉 검색값 입력 시 천단위 구분을 위한 콤마 입력X)
    private String maxPay;         // 최대 급여 (위와 동일)
    private String career;      // 경력 (N: 신입, E: 경력, Z: 관계X)
    private String minCareerM;     // 경력 최소개월 수
    private String maxCareerM;     // 경력 최대개월 수
    private String workerCnt;   // 사원 수 (W5: 5인미만, W9: 5~10인, W10: 10~30인, W30: 30~50인, W50: 50~100인, W100: 100인 이상)
    private String wantedAuthNo;
    private String infoSvc;

}
