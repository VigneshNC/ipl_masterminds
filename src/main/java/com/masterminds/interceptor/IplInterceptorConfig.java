package com.masterminds.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class IplInterceptorConfig implements WebMvcConfigurer {
	
	@Autowired
	IplInterceptor iplInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(iplInterceptor).addPathPatterns("/**").excludePathPatterns("/js/**").excludePathPatterns("/img/**");
	}

}
