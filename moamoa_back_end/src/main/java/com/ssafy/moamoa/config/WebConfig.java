package com.ssafy.moamoa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.moamoa.interceptor.LogInterceptor;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

	private final LogInterceptor logInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(logInterceptor).addPathPatterns("/**");
	}

	// @Override
	// public void addCorsMappings(CorsRegistry registry) {
	// 	registry.addMapping("/**")
	// 		// .allowedOrigins("*")
	// 		.allowedOrigins("http://localhost:3000")
	// 		.allowedMethods("GET", "POST", "PUT", "DELETE");
	// }
}