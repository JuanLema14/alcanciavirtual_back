package com.alcanciavirtual.alcanciavirtual_back;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/auth/login")
                    .allowedOrigins("http://localhost:8000")
                        .allowedMethods("*")
                            .exposedHeaders("*");
                            
                            registry.addMapping("/sapi/**")
                            .allowedOrigins("http://localhost:8000")
                            .allowedMethods("*")
                            .exposedHeaders("*");
            }
        };
    }
}
