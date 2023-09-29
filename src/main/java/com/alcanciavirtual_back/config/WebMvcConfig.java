package com.alcanciavirtual_back.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.alcanciavirtual.alcanciavirtual_back.security.JWTInterceptorPolicy;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final JWTInterceptorPolicy jwtInterceptor;

    public WebMvcConfig(JWTInterceptorPolicy jwtInterceptor) {
        this.jwtInterceptor = jwtInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/sapi/auth/fetch");
    }
}
