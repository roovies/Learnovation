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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
public class JobExploreController {

    @GetMapping("/explore/list")
    public ModelAndView tetteest(ModelAndView mv,
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
}
