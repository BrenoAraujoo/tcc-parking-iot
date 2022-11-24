package com.tccparkingiot.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class Cors {


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:8080").allowedMethods("GET", "POST", "PUT", "DELETE");
                registry.addMapping("/**").allowedOrigins("http://127.0.0.1:5500/").allowedMethods("GET", "POST", "PUT", "DELETE");
                registry.addMapping("/**").allowedOrigins("http://127.0.0.1:5555/").allowedMethods("GET", "POST", "PUT", "DELETE");

            }
        };
    }
}