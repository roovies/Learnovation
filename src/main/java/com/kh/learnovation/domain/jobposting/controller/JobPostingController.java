package com.kh.learnovation.domain.jobposting.controller;

import com.kh.learnovation.domain.jobposting.common.JobPostingUtility;
import com.kh.learnovation.domain.jobposting.dto.DetailResp;
import com.kh.learnovation.domain.jobposting.dto.ListReq;
import com.kh.learnovation.domain.jobposting.dto.ListResp;
import com.kh.learnovation.domain.jobposting.dto.PagingRes;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Controller
public class JobPostingController {

    private final JobPostingUtility jut;

    public JobPostingController(JobPostingUtility jobPostingUtility){
        this.jut = jobPostingUtility;
    }
    @GetMapping("/jobposting/list")
    public String showJobPostingList(@ModelAttribute ListReq jobPostingDTO, Model model) {
        // 1. 기본 설정값 지정
        jobPostingDTO.setAuthKey("WNLGFXBZPMX7H2SM3TTQJ2VR1HJ");
        jobPostingDTO.setReturnType("XML");
        jobPostingDTO.setCallTp("L");
        jobPostingDTO.setDisplay(20);
        // 1-1. 기본값을 통한 요청 URL 생성
        StringBuilder sb = new StringBuilder();
        sb.append("http://openapi.work.go.kr/opi/opi/opia/wantedApi.do?"); // 요청 URL정보 (뒤에 쿼리스트링 붙으므로 ?로 마무리)
        sb.append("authKey=" + jobPostingDTO.getAuthKey());
        sb.append("&callTp=" + jobPostingDTO.getCallTp());
        sb.append("&returnType=" + jobPostingDTO.getReturnType());
        sb.append("&display=" + jobPostingDTO.getDisplay());
        // 1-2. 전달 파라미터 확인 후 URL 세팅
        sb = jut.buildUrl(jobPostingDTO, sb);
        System.out.println(sb);

        try {
            // HTTP 연결
            URL url = new URL(sb.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/xml");
            conn.setRequestMethod("GET");
            conn.connect();     // 요청 데이터는 conn 객체에 저장됨

            // XML 파서 초기화
            SAXBuilder builder = new SAXBuilder();

            // HttpURLConnection 객체에서 받아온 XML 데이터를 파싱하여 Document 객체로 변환
            Document document = builder.build(conn.getInputStream());
            
            // XML 객체에서 데이터 추출
            // 해당 과정을 진행하기 위해서는 샘플 데이터를 요청해본 후, 데이터가 어떤 식으로 반환되는지 확인해야 한다.
            Element root = document.getRootElement();
            // 페이징을 위한 필요값 저장
            int total = Integer.parseInt(root.getChildText("total"));
            int currentPage = Integer.parseInt(root.getChildText("startPage"));
            int totalNaviCount = (int) Math.ceil(total / 20);
            PagingRes pagingRes = PagingRes.builder()
                    .total(total)
                    .currentPage(currentPage)
                    .totalNaviCount(totalNaviCount)
                    .build();

            // 데이터 저장
            List<Element> wantedList = root.getChildren("wanted");
            List<ListResp> jobResList = new ArrayList<>();
            for (Element wanted : wantedList){
                String wantedAuthNo = wanted.getChildText("wantedAuthNo");
                String company = wanted.getChildText("company");
                String region = wanted.getChildText("region");
                String title = wanted.getChildText("title");
                String salTpNm = wanted.getChildText("salTpNm");
                String sal = wanted.getChildText("sal");
                String career = wanted.getChildText("career");
                String regDt = wanted.getChildText("regDt");
                String closeDt = wanted.getChildText("closeDt");
                ListResp jobRes = ListResp.builder()
                        .wantedAuthNo(wantedAuthNo)
                        .company(company)
                        .region(region)
                        .title(title)
                        .salTpNm(salTpNm)
                        .sal(sal)
                        .career(career)
                        .regDt(regDt)
                        .closeDt(closeDt)
                        .build();
                jobResList.add(jobRes);
            }
            model.addAttribute("error", "N");
            model.addAttribute("req", jobPostingDTO);
            model.addAttribute("paging", pagingRes);
            model.addAttribute("jobList", jobResList);

        } catch (Exception e) {
            model.addAttribute("req", jobPostingDTO);
            model.addAttribute("error", "Y");
            return "jobposting/JobPostingList";
        }

        return "jobposting/JobPostingList";
    }

    @GetMapping("/jobposting/detail")
    public String showJobPostingDetail(@RequestParam("wantedAuthNo") String wantedAuthNo, Model model){
        ListReq req = new ListReq();
        req.setAuthKey("WNLGFXBZPMX7H2SM3TTQJ2VR1HJ");
        req.setReturnType("XML");
        req.setCallTp("D");
        req.setWantedAuthNo(wantedAuthNo);
        req.setInfoSvc("VALIDATION");

        StringBuilder sb = new StringBuilder();
        sb.append("http://openapi.work.go.kr/opi/opi/opia/wantedApi.do?"); // 요청 URL정보 (뒤에 쿼리스트링 붙으므로 ?로 마무리)
        sb.append("authKey=" + req.getAuthKey());
        sb.append("&wantedAuthNo=" + req.getWantedAuthNo());
        sb.append("&callTp=" + req.getCallTp());
        sb.append("&returnType=" + req.getReturnType());
        sb.append("&infoSvc=" + req.getInfoSvc());
        System.out.println(sb);

        try {
            // HTTP 연결
            URL url = new URL(sb.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/xml");
            conn.setRequestMethod("GET");
            conn.connect();     // 요청 데이터는 conn 객체에 저장됨

            // XML 파서 초기화
            SAXBuilder builder = new SAXBuilder();

            // HttpURLConnection 객체에서 받아온 XML 데이터를 파싱하여 Document 객체로 변환
            Document document = builder.build(conn.getInputStream());

            // XML 객체에서 데이터 추출
            // 해당 과정을 진행하기 위해서는 샘플 데이터를 요청해본 후, 데이터가 어떤 식으로 반환되는지 확인해야 한다.
            Element root = document.getRootElement();
            Element corpInfo = root.getChild("corpInfo");
            System.out.println(corpInfo);
            String corpNm = corpInfo.getChildText("corpNm");
            String reperNm = corpInfo.getChildText("reperNm");
            String totPsncnt = corpInfo.getChildText("totPsncnt");
            String yrSalesAmt = corpInfo.getChildText("yrSalesAmt");
            String indTpCdNm = corpInfo.getChildText("indTpCdNm");
            String busiSize = corpInfo.getChildText("busiSize");
            String corpAddr = corpInfo.getChildText("corpAddr");


            Element wantedInfo = root.getChild("wantedInfo");
            String relJobsNm = wantedInfo.getChildText("relJobsNm");
            String enterTpNm = wantedInfo.getChildText("enterTpNm");
            String empTpNm = wantedInfo.getChildText("empTpNm");
            String jobCont = wantedInfo.getChildText("jobCont");
            String collectPsncnt = wantedInfo.getChildText("collectPsncnt");
            String eduNm = wantedInfo.getChildText("eduNm");
            String salTpNm = wantedInfo.getChildText("salTpNm");
            String selMthd = wantedInfo.getChildText("selMthd");
            String rcptMthd = wantedInfo.getChildText("rcptMthd");
            String submitDoc = wantedInfo.getChildText("submitDoc");
            String workRegion = wantedInfo.getChildText("workRegion");
            String workdayWorkhrCont = wantedInfo.getChildText("workdayWorkhrCont");
            String fourIns = wantedInfo.getChildText("fourIns");
            String retirepay = wantedInfo.getChildText("retirepay");
            String etcWelfare = wantedInfo.getChildText("etcWelfare");
            String dtlRecrContUrl = wantedInfo.getChildText("dtlRecrContUrl");
            DetailResp resp = DetailResp.builder()
                    .corpNm(corpNm)
                    .reperNm(reperNm)
                    .totPsncnt(totPsncnt)
                    .yrSalesAmt(yrSalesAmt)
                    .indTpCdNm(indTpCdNm)
                    .busiSize(busiSize)
                    .corpAddr(corpAddr)
                    .relJobsNm(relJobsNm)
                    .enterTpNm(enterTpNm)
                    .empTpNm(empTpNm)
                    .jobCont(jobCont)
                    .collectPsncnt(collectPsncnt)
                    .eduNm(eduNm)
                    .salTpNm(salTpNm)
                    .selMthd(selMthd)
                    .rcptMthd(rcptMthd)
                    .submitDoc(submitDoc)
                    .workRegion(workRegion)
                    .workdayWorkhrCont(workdayWorkhrCont)
                    .fourIns(fourIns)
                    .retirepay(retirepay)
                    .etcWelfare(etcWelfare)
                    .dtlRecrContUrl(dtlRecrContUrl)
                    .build();
            model.addAttribute("resp", resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "jobposting/jobPostingDetail";
    }
}
