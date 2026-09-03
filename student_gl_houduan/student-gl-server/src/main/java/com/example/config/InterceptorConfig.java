package com.example.config;


import com.example.intercrptor.TokenIntercrptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private TokenIntercrptor tokenIntercrptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenIntercrptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/api/login", "/api/signup");
    }
}
