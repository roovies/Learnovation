package com.kh.learnovation.domain.jobexplore.controller;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
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

    private boolean validNullJson(JsonElement jsonElement, String key){
        if(jsonElement != null){
            if(!jsonElement.isJsonNull()){
                JsonObject jsonObject = (JsonObject)jsonElement;
                if(jsonObject.get(key) != null){
                    if(!jsonObject.get(key).isJsonNull()){
                        return true;
                    }
                }
            }

        }
        return false;
    }

    private boolean validNullJson(JsonArray jsonArray){
        if(jsonArray != null){
            if(jsonArray.size() > 0){
                return true;
            }
        }
        return false;
    }

    private boolean validNullJson(JsonObject jsonObject, String key){
        if(jsonObject != null){
            if(jsonObject.get(key) != null){
                if(!jsonObject.get(key).isJsonNull()){
                    return true;
                }
            }
        }
        return false;
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
            if(validNullJson(ArcertiList)){
                for(int i  = 0; i < ArcertiList.size(); i++){
                    Map<String, String> map = new HashMap<String, String>();
                    if(validNullJson(ArcertiList.get(i), "certi")){
                        JsonObject jsonObject1 = (JsonObject) ArcertiList.get(i);
                        map.put("certi", jsonObject1.get("certi").toString().replaceAll("\"",""));
                    }
                    if(validNullJson(ArcertiList.get(i), "LINK" )){
                        JsonObject jsonObject1 = (JsonObject) ArcertiList.get(i);
                        map.put("link", jsonObject1.get("LINK").toString().replaceAll("\"",""));
                    }
                    if(map.isEmpty()){
                        map.put("certi", "자료없음");
                    }
                    certiList.add(map);
                }
            }else{
                Map<String, String> map = new HashMap<String, String>();
                map.put("certi", "자료없음");
            }
            mv.addObject("certiList", certiList);
            if(validNullJson(ArCurriculum)){
                for(int i = 0; i < ArCurriculum.size(); i++){
                    if(validNullJson(ArCurriculum.get(i), "curriculum")){
                        JsonObject jsonObject1 = (JsonObject) ArCurriculum.get(i);
                        LCurriculum.add(jsonObject1.get("curriculum").toString().replaceAll("\"",""));
                    }
                }
            }
            if(LCurriculum.isEmpty()){
                LCurriculum.add("자료없음");
            }
            mv.addObject("LCurriculum", LCurriculum);

            JsonArray ArCertificate = (JsonArray) jobReadyList.get("certificate");
            List<String> LCertificate = new ArrayList<String>();
            if(validNullJson(ArCertificate)){
                for(int i = 0; i < ArCertificate.size(); i++){
                    if(validNullJson(ArCertificate.get(i),"certificate")){
                        JsonObject jsonObject1 = (JsonObject) ArCertificate.get(i);
                        LCertificate.add(jsonObject1.get("certificate").toString().replaceAll("\"",""));
                    }
                }
            }
            if(LCertificate.isEmpty()){
                LCertificate.add("자료없음");
            }

            mv.addObject("LCertificate", LCertificate);

            JsonArray ArTraining = (JsonArray) jobReadyList.get("training");
            List<String> LTraining = new ArrayList<String>();
            if(validNullJson(ArTraining)){
                for(int i = 0; i < ArTraining.size(); i++){
                    if(validNullJson(ArTraining.get(i),"training")){
                        JsonObject jsonObject1 = (JsonObject) ArTraining.get(i);
                        LTraining.add(jsonObject1.get("training").toString().replaceAll("\"",""));
                    }
                }
            }
            if(LTraining.isEmpty()){
                LTraining.add("자료없음");
            }

            mv.addObject("LTraining", LTraining);

            JsonArray ArRecruit = (JsonArray) jobReadyList.get("recruit");
            List<String> LRecruit = new ArrayList<String>();
            if(validNullJson(ArRecruit)){
                for(int i = 0; i < ArRecruit.size(); i++){
                    if(validNullJson(ArRecruit.get(i), "recruit")) {
                        JsonObject jsonObject1 = (JsonObject) ArRecruit.get(i);
                        LRecruit.add(jsonObject1.get("recruit").toString().replaceAll("\"", ""));
                    }
                }
            }
            if(LRecruit.isEmpty()){
                LRecruit.add("자료없음");
            }

            mv.addObject("LRecruit", LRecruit);

            String jobName = "";
            if(validNullJson(baseInfo, "job_nm")){
                jobName = baseInfo.get("job_nm").toString().replaceAll("\"","");
                mv.addObject("jobName", jobName);
            }else{
                mv.addObject("jobName", "자료없음");
            }


            String salary = "";
            if(validNullJson(baseInfo, "wage")){
                salary = baseInfo.get("wage").toString().replaceAll("\"","");
                mv.addObject("salary", salary);
            }else{
                mv.addObject("salary", "자료없음");
            }


            String salaryDetail = "";
            if(validNullJson(baseInfo, "wage_source")){
                salaryDetail = baseInfo.get("wage_source").toString().replaceAll("\"","");
                mv.addObject("salaryDetail", salaryDetail);
            }else{
                mv.addObject("salaryDetail", "자료없음");
            }


            String satisfication = "";
            if(validNullJson(baseInfo, "satisfication")){
                satisfication = baseInfo.get("satisfication").toString().replaceAll("\"","");
                mv.addObject("satisfication", satisfication);
            }else{
                mv.addObject("satisfication", "자료없음");
            }

            String satisficationSource = "";
            if(validNullJson(baseInfo, "satisfi_source")){
                satisficationSource = baseInfo.get("satisfi_source").toString().replaceAll("\"","");
                mv.addObject("satisficationSource", satisficationSource);
            }else{
                mv.addObject("satisficationSource", "자료없음");
            }




            if(validNullJson(workList)){
                for (int i = 0; i < workList.size(); i++){
                    if(validNullJson(workList.get(i), "work")){
                        JsonObject jsonObject1 = (JsonObject) workList.get(i);
                        LworkList.add(jsonObject1.get("work").toString().replaceAll("\"",""));
                    }
                }
            }
            if(LworkList.isEmpty()){
                LworkList.add("자료없음");
            }

            mv.addObject("LworkList", LworkList);

            if(validNullJson(interestList)){
                for (int i = 0; i < interestList.size(); i++){
                    if(validNullJson(interestList.get(i), "interest")){
                        JsonObject jsonObject1 = (JsonObject) interestList.get(i);
                        LinterestList.add(jsonObject1.get("interest").toString().replaceAll("\"",""));
                    }
                }
            }
            if(LinterestList.isEmpty()){
                LinterestList.add("자료없음");
            }

            mv.addObject("LinterestList", LinterestList);

            if(validNullJson(forecastList)){
                for (int i = 0; i < forecastList.size(); i++){
                    if (validNullJson(forecastList.get(i), "forecast")) {
                        JsonObject jsonObject1 = (JsonObject) forecastList.get(i);
                        LforecastList.add(jsonObject1.get("forecast").toString().replaceAll("\"",""));
                    }
                }
            }
            if(LforecastList.isEmpty()){
                LforecastList.add("자료없음");
            }

            mv.addObject("LforecastList", LforecastList);

            if(validNullJson(eduChart)){
                for (int i = 0; i < eduChart.size(); i++){
                    JsonObject jsonObject1 = (JsonObject) eduChart.get(i);
                    if(validNullJson(jsonObject1, "chart_name") && validNullJson(jsonObject1, "chart_data")){
                        String edu = jsonObject1.get("chart_name").toString().replaceAll("\"","");
                        String data = jsonObject1.get("chart_data").toString().replaceAll("\"","");
                        String[] edus = edu.split(",");
                        String[] datas = data.split(",");
                        StringBuilder sb = new StringBuilder();
                        for(int j = 0; j < edus.length; j++){
                            if(j == edus.length - 1){
                                sb.append(edus[j] + "(" + datas[j] + "%)");
                            }else{
                                sb.append(edus[j] + "(" + datas[j] + "%),");
                            }
                        }
                        LeduChart.put("chart_name", sb.toString());
                        LeduChart.put("chart_data", data);
                    }
                    if(validNullJson(jsonObject1, "source"))
                    LeduChart.put("source", jsonObject1.get("source").toString().replaceAll("\"",""));
                }
            }

            mv.addObject("LeduChart", LeduChart);

            if(validNullJson(majorChart)){
                for (int i = 0; i < majorChart.size(); i++){
                    JsonObject jsonObject1 = (JsonObject) majorChart.get(i);
                    if(validNullJson(jsonObject1, "major_data") && validNullJson(jsonObject1, "major")){
                        String data = jsonObject1.get("major_data").toString().replaceAll("\"","");
                        String major = jsonObject1.get("major").toString().replaceAll("\"","");
                        String[] majors = major.split(",");
                        String[] datas = data.split(",");
                        StringBuilder sb = new StringBuilder();
                        for(int j = 0; j < majors.length; j++){
                            if(j == majors.length - 1){
                                sb.append(majors[j] + "(" + datas[j] + "%)");
                            }else{
                                sb.append(majors[j] + "(" + datas[j] + "%),");
                            }
                        }
                        LmajorChart.put("major", sb.toString());
                        LmajorChart.put("major_data", data);
                    }
                    if(validNullJson(jsonObject1, "source"))
                    LmajorChart.put("source", jsonObject1.get("source").toString().replaceAll("\"",""));
                }
            }

            mv.addObject("LmajorChart", LmajorChart);
            mv.setViewName("jobexplore/detail");
        }
        return mv;
    }
}
