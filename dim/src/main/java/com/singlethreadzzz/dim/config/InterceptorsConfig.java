package com.singlethreadzzz.dim.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.singlethreadzzz.dim.interceptor.LoginInterceptor;
import com.singlethreadzzz.dim.interceptor.PathInterceptor;

//@Configuration
public class InterceptorsConfig implements WebMvcConfigurer {
	
//	@Autowired
//	private LoginInterceptor loginInterceptor;
	@Autowired
	private PathInterceptor pathInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		//添加拦截器示例
//		List<String> excludePathPatternsList = new ArrayList<String>();
//		excludePathPatternsList.add("/js/**");
//		excludePathPatternsList.add("/css/**");
//		excludePathPatternsList.add("/icon/**");
//		excludePathPatternsList.add("/image/**");
//		excludePathPatternsList.add("/error/**");
//		registry.addInterceptor(loginInterceptor)
//        		.excludePathPatterns(excludePathPatternsList)
//				.addPathPatterns("/**");
		
//		List<String> excludePathPatternsList = new ArrayList<String>();
//		excludePathPatternsList.add("/login");
//		excludePathPatternsList.add("/logout");
//		excludePathPatternsList.add("/error/*");
		//添加登录径拦截器
//		registry.addInterceptor(loginInterceptor)
//		        .excludePathPatterns(excludePathPatternsList)
//				.addPathPatterns("/**");
		//添加路径拦截器
		List<String> excludePathPatternsList = new ArrayList<String>();
		excludePathPatternsList.add("/dim/static/**");
		registry.addInterceptor(pathInterceptor)
				.excludePathPatterns(excludePathPatternsList)
				.addPathPatterns("/**");
				
		
	}

}
