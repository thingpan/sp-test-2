package com.sp.file.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Value("${download.file-path}")
	private String filePath;
	@Value("${download.resource-url}")
	private String resourceUrl;
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(resourceUrl)//리소스와 연결될 URL path를 지정합니다.
		.addResourceLocations(filePath);//실제 리소스가 존재하는 외부 경로를 지정합니다
	}
}