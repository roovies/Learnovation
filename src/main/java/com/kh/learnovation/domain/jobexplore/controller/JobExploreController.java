package com.kh.learnovation.domain.jobexplore.controller;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kh.learnovation.domain.jobexplore.dto.JobExploreDTO;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.*;


@Controller
public class JobExploreController {

    @GetMapping("/explore/list")
    public ModelAndView exploreList(ModelAndView mv,
                                 @RequestParam(value="page", required = false, defaultValue = "1") String strPage){
        // HttpClient 이용해 HttpComponentsClientHttpRequestFactory 객체를 생성
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(5000); // 타임아웃 설정 5초
        factory.setReadTimeout(5000);

        RestTemplate restTemplate = new RestTemplate(factory);

        // url 생성
        try {
            if(0 >= Integer.parseInt(strPage) || Integer.parseInt(strPage) > 7){
                strPage = "1";
            };
        }catch (NumberFormatException e){
            strPage = "1";
        }
        URI url = UriComponentsBuilder.fromHttpUrl("https://career.go.kr/cnet/front/openapi/jobs.json")
                .queryParam("apiKey", "dcbceba3669ac12190c6af8572955b13")
                .queryParam("searchThemeCode", 102421)
                .queryParam("pageIndex", strPage)
                .build().toUri();
        // 헤더 생성
        HttpHeaders headers = new HttpHeaders();
        // 받을 데이터 타입 명시
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));

        // resttemplate api 호출 통신
        ResponseEntity<String> response = restTemplate.exchange(url.toString()
                , HttpMethod.GET, new HttpEntity<>(headers), String.class);
        System.out.println(response.getStatusCode());
        // 통신 성공시
        if(response.getStatusCode().is2xxSuccessful()){
            JsonParser jsonParser = new JsonParser();
            // response body Json 데이터 파싱
            JsonObject jsonObject = (JsonObject) JsonParser.parseString(response.getBody());
            String pageSize = jsonObject.get("pageSize").toString().replaceAll("\"","");
            String totalCount = jsonObject.get("count").toString().replaceAll("\"","");
            String apipage = jsonObject.get("pageIndex").toString().replaceAll("\"","");
            int totalPage = (int)Math.ceil((double)Integer.parseInt(totalCount) / Integer.parseInt(pageSize));
            int page = Integer.parseInt(apipage);
            List<JobExploreDTO> jList = new ArrayList<JobExploreDTO>();
            JsonArray jsonArray = (JsonArray)jsonObject.get("jobs");
            for(int i = 0; i < jsonArray.size(); i++){
                JobExploreDTO job = new JobExploreDTO();
                JsonObject object = (JsonObject)jsonArray.get(i);
                job.setJobId(object.get("job_cd").toString().replaceAll("\"",""));
                if (object.get("social") != null){
                    job.setSocial(object.get("social").toString().replaceAll("\"",""));
                }else{
                    job.setSocial("알 수 없음");
                }
                job.setWork(object.get("work").toString().replaceAll("\"",""));
                if(object.get("wage") != null){
                    job.setSalary(object.get("wage").toString().replaceAll("\"",""));
                }else{
                    job.setSalary("알 수 없음");
                }
                job.setJobName(object.get("job_nm").toString().replaceAll("\"",""));
                if(object.get("wlb") != null){
                    job.setWorkLifeBalance(object.get("wlb").toString().replaceAll("\"",""));
                }else{
                    job.setWorkLifeBalance("알 수 없음");
                }
                jList.add(job);
            }
            int startPage = (page - 1) / 5 * 5 + 1;
            int endPage = startPage + 4 > totalPage ? totalPage : startPage + 4;
            int[] navCount = new int[endPage - startPage + 1];
            for(int i = 0; i < navCount.length; i++ ){
                navCount[i] = startPage + i;
            }
            mv.addObject("totalPage", totalPage)
                    .addObject("page", page)
                    .addObject("jList", jList)
                    .addObject("startPage", startPage)
                    .addObject("endPage", endPage)
                    .addObject("navCount", navCount)
                    .setViewName("jobexplore/list");
            return mv;
        }
        mv.setViewName("redirect:/notice/list");
        return mv;
    }

    @GetMapping(value="/explore/detail")
    public ModelAndView exploreDetail(ModelAndView mv, @RequestParam(value = "jobcode") String jobCode){
        // HttpClient 이용해 HttpComponentsClientHttpRequestFactory 객체를 생성
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(5000); // 타임아웃 설정 5초
        factory.setReadTimeout(5000);

        RestTemplate restTemplate = new RestTemplate(factory);

        // url 생성
        try {
            Integer.parseInt(jobCode);
        }catch (NumberFormatException e){
            // 에러페이지
        }
        URI url = UriComponentsBuilder.fromHttpUrl("https://www.career.go.kr/cnet/front/openapi/job.json")
                .queryParam("apiKey", "dcbceba3669ac12190c6af8572955b13")
                .queryParam("seq", jobCode)
                .build().toUri();
        // 헤더 생성
        HttpHeaders headers = new HttpHeaders();
        // 받을 데이터 타입 명시
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));

        // resttemplate api 호출 통신
        ResponseEntity<String> response = restTemplate.exchange(url.toString()
                , HttpMethod.GET, new HttpEntity<>(headers), String.class);
        System.out.println(response.getStatusCode());
        // 통신 성공시
        if(response.getStatusCode().is2xxSuccessful()){
            List<String> LworkList = new ArrayList<String>();
            List<String> LinterestList = new ArrayList<String>();
            List<String> LforecastList = new ArrayList<String>();
            Map<String, String> LeduChart = new HashMap<String, String>();
            Map<String, String> LmajorChart = new HashMap<String, String>();
            JsonParser jsonParser = new JsonParser();
            // response body Json 데이터 파싱
            JsonObject jsonObject = (JsonObject) JsonParser.parseString(response.getBody());
            JsonArray workList = (JsonArray) jsonObject.get("workList");
            JsonArray interestList = (JsonArray) jsonObject.get("interestList");
            JsonArray forecastList = (JsonArray) jsonObject.get("forecastList");
            JsonArray eduChart = (JsonArray) jsonObject.get("eduChart");
            JsonArray majorChart = (JsonArray) jsonObject.get("majorChart");
            JsonObject baseInfo = (JsonObject) jsonObject.get("baseInfo");
            JsonObject jobReadyList = (JsonObject) jsonObject.get("jobReadyList");
            JsonArray ArCurriculum = (JsonArray) jobReadyList.get("curriculum");
            List<String> LCurriculum = new ArrayList<String>();
            JsonArray ArcertiList = (JsonArray) jsonObject.get("certiList");
            List<Map> certiList = new ArrayList<Map>();
            for(int i  = 0; i < ArcertiList.size(); i++){
                JsonObject jsonObject1 = (JsonObject) ArcertiList.get(i);
                Map<String, String> map = new HashMap<String, String>();
                map.put("certi", jsonObject1.get("certi").toString().replaceAll("\"",""));
                map.put("link", jsonObject1.get("LINK").toString().replaceAll("\"",""));
                certiList.add(map);
            }
            mv.addObject("certiList", certiList);

            for(int i = 0; i < ArCurriculum.size(); i++){
                JsonObject jsonObject1 = (JsonObject) ArCurriculum.get(i);
                LCurriculum.add(jsonObject1.get("curriculum").toString().replaceAll("\"",""));
            }
            mv.addObject("LCurriculum", LCurriculum);

            JsonArray ArCertificate = (JsonArray) jobReadyList.get("certificate");
            List<String> LCertificate = new ArrayList<String>();
            for(int i = 0; i < ArCertificate.size(); i++){
                JsonObject jsonObject1 = (JsonObject) ArCertificate.get(i);
                LCertificate.add(jsonObject1.get("certificate").toString().replaceAll("\"",""));
            }
            mv.addObject("LCertificate", LCertificate);

            JsonArray ArTraining = (JsonArray) jobReadyList.get("training");
            List<String> LTraining = new ArrayList<String>();
            for(int i = 0; i < ArTraining.size(); i++){
                JsonObject jsonObject1 = (JsonObject) ArTraining.get(i);
                LTraining.add(jsonObject1.get("training").toString().replaceAll("\"",""));
            }
            mv.addObject("LTraining", LTraining);

            JsonArray ArRecruit = (JsonArray) jobReadyList.get("recruit");
            List<String> LRecruit = new ArrayList<String>();
            for(int i = 0; i < ArRecruit.size(); i++){
                JsonObject jsonObject1 = (JsonObject) ArRecruit.get(i);
                LRecruit.add(jsonObject1.get("recruit").toString().replaceAll("\"",""));
            }
            mv.addObject("LRecruit", LRecruit);

            String jobName = baseInfo.get("job_nm").toString().replaceAll("\"","");
            mv.addObject("jobName", jobName);
            String salary = baseInfo.get("wage").toString().replaceAll("\"","");
            mv.addObject("salary", salary);
            String salaryDetail = baseInfo.get("wage_source").toString().replaceAll("\"","");
            mv.addObject("salaryDetail", salaryDetail);
            String satisfication = baseInfo.get("satisfication").toString().replaceAll("\"","");
            mv.addObject("satisfication", satisfication);
            String satisficationSource = baseInfo.get("satisfi_source").toString().replaceAll("\"","");
            mv.addObject("satisficationSource", satisficationSource);
            for (int i = 0; i < workList.size(); i++){
                JsonObject jsonObject1 = (JsonObject) workList.get(i);
                LworkList.add(jsonObject1.get("work").toString().replaceAll("\"",""));
            }
            mv.addObject("LworkList", LworkList);

            for (int i = 0; i < interestList.size(); i++){
                JsonObject jsonObject1 = (JsonObject) interestList.get(i);
                LinterestList.add(jsonObject1.get("interest").toString().replaceAll("\"",""));
            }
            mv.addObject("LinterestList", LinterestList);

            for (int i = 0; i < forecastList.size(); i++){
                JsonObject jsonObject1 = (JsonObject) forecastList.get(i);
                LforecastList.add(jsonObject1.get("forecast").toString().replaceAll("\"",""));
            }
            mv.addObject("LforecastList", LforecastList);

            for (int i = 0; i < eduChart.size(); i++){
                JsonObject jsonObject1 = (JsonObject) eduChart.get(i);
                LeduChart.put("chart_name", jsonObject1.get("chart_name").toString().replaceAll("\"",""));
                LeduChart.put("chart_data", jsonObject1.get("chart_data").toString().replaceAll("\"",""));
                LeduChart.put("source", jsonObject1.get("source").toString().replaceAll("\"",""));
            }
            mv.addObject("LeduChart", LeduChart);

            for (int i = 0; i < majorChart.size(); i++){
                JsonObject jsonObject1 = (JsonObject) majorChart.get(i);
                LmajorChart.put("major", jsonObject1.get("major").toString().replaceAll("\"",""));
                LmajorChart.put("major_data", jsonObject1.get("major_data").toString().replaceAll("\"",""));
                LmajorChart.put("source", jsonObject1.get("source").toString().replaceAll("\"",""));
            }
            mv.addObject("LmajorChart", LmajorChart);

            mv.setViewName("jobexplore/detail");
        }
        return mv;
    }
}
