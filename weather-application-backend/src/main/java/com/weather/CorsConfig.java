package com.weather;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
    	  registry.addMapping("/**") // Allow requests from any origin
          .allowedMethods("*") // Allow all HTTP methods
          .allowedOrigins("*") // Allow all origins
          .allowedHeaders("*"); // Allow all headers
    }
}
