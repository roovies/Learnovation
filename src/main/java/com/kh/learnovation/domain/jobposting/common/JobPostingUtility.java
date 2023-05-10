package com.kh.learnovation.domain.jobposting.common;

import com.kh.learnovation.domain.jobposting.dto.ListReq;
import org.springframework.stereotype.Component;

@Component
public class JobPostingUtility {
    public StringBuilder buildUrl(ListReq jobPostingDTO, StringBuilder sb){

        // startPage (시작 페이지)
        if (jobPostingDTO.getStartPage()>1){
            sb.append("&startPage=" + jobPostingDTO.getStartPage());
        } else {
            sb.append("&startPage=1");
        }

        // region (근무지역)
        if (jobPostingDTO.getRegion() != null){
            sb.append("&region=" + jobPostingDTO.getRegion());
        }

        // occupation (직종코드)
        if (jobPostingDTO.getOccupation() != null){
            if (jobPostingDTO.getOccupation().equals("024")){
                sb.append("&occupation=" + jobPostingDTO.getOccupation());
            } else if (jobPostingDTO.getOccupation().equals("026")){
                sb.append("&occupation=" + jobPostingDTO.getOccupation());
            } else{
                sb.append("&occupation=024|026");
            }
        } else {
            sb.append("&occupation=024|026");
        }

        // salTp (임금 형태) - 검색조건 미입력시 관계없음으로 조회됨
        if (jobPostingDTO.getSalTp() != null){
            if (!(jobPostingDTO.getSalTp().equals("null"))){
                sb.append("&salTp=" + jobPostingDTO.getSalTp());
                // salTp를 요청하면 minPay랑 maxPay가 필수 입력임.
                sb.append("&minPay=" + jobPostingDTO.getMinPay());
                if (jobPostingDTO.getMaxPay() != null){
                sb.append("&maxPay=" + jobPostingDTO.getMaxPay());
                }
            }

        }
//        if (jobPostingDTO.getSalTp() != null){
//            sb.append("&salTp=" + jobPostingDTO.getSalTp());
//            // salTp를 요청하면 minPay랑 maxPay가 필수 입력임.
//            sb.append("&minPay=" + jobPostingDTO.getMinPay());
//            if (jobPostingDTO.getMaxPay() != null){
//                sb.append("&maxPay=" + jobPostingDTO.getMaxPay());
//            }
//        }

        // career (경력)
        if (jobPostingDTO.getCareer() != null){
            if (!(jobPostingDTO.getCareer().equals("null"))){
                sb.append("&career=" + jobPostingDTO.getCareer());
                // career값이 E일 경우 경력으로, 경력기간 입력 필요
                if (jobPostingDTO.getCareer().equals("E")){
                    // 최소 경력기간 (입력값 없을 시 0으로 초기화)
                    sb.append("&minCareerM=" + jobPostingDTO.getMinCareerM());
                    // 최대 경력기간 (입력값 없을 시 0으로 초기화)
                    if (jobPostingDTO.getMaxCareerM() != null){
                        sb.append("&maxCareerM=" + jobPostingDTO.getMaxCareerM());
                    }
                }
            }
        }

        // workerCnt (사원수)
        sb.append("&workerCnt=" + jobPostingDTO.getWorkerCnt());
        return sb;
    }
}
