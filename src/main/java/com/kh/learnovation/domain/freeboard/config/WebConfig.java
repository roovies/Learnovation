package com.kh.learnovation.domain.freeboard.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private String resourcePath = "/upload/**"; // view 에서 접근할 경로
    private String savePath = "file:///C:\\Users\\user1\\IdeaProjects\\src\\main\\resources\\static\\uploadFiles\\"; // 실제 파일 저장 경로(win)
//    private String savePath = "file:///Users/사용자이름/springboot_img/"; // 실제 파일 저장 경로(mac)

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(resourcePath)
                .addResourceLocations(savePath);
    }
}