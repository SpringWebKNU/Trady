package com.example.trady.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 이미지 파일 경로 등록
        // 이미지 파일 경로 등록
        registry.addResourceHandler("/images/uploads/**")
                .addResourceLocations("file:/C:/Trady/src/main/resources/static/images/uploads/")  // 절대 경로 사용
                .setCacheControl(CacheControl.maxAge(0, TimeUnit.SECONDS).cachePublic());  // 캐시를 아예 사용하지 않음

    }
}
