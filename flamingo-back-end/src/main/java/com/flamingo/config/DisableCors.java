package com.flamingo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class DisableCors {
    
    @Bean
	public WebMvcConfigurer cors(){
		return new WebMvcConfigurer() {


			public void addCorsMappings(CorsRegistry registry){
				registry.addMapping("/**").allowedOrigins("http://localhost:4200/");
			}
		};
	}
}
