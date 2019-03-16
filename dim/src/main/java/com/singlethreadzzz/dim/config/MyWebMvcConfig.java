package com.singlethreadzzz.dim.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.singlethreadzzz.dim.interceptor.UserInfoInterceptor;

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {
	
//	@Autowired
//	private LoginInterceptor loginInterceptor;
//	@Autowired
//	private PathInterceptor pathInterceptor;
	
	@Autowired
	private UserInfoInterceptor userInfoInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		//添加拦截器示例
//		List<String> excludePathPatternsList = new ArrayList<String>();
//		excludePathPatternsList.add("/static/**");
//		excludePathPatternsList.add("/login/**");
//		excludePathPatternsList.add("/error/**");
//		excludePathPatternsList.add("/logout/**");
//		registry.addInterceptor(loginInterceptor)
//        		.excludePathPatterns(excludePathPatternsList)
//				.addPathPatterns("/**");
		
//		List<String> excludeLoginsList = new ArrayList<String>();
//		excludeLoginsList.add("/static/**");
//		excludeLoginsList.add("/login/**");
//		excludeLoginsList.add("/error/**");
//		excludeLoginsList.add("/logout/**");
//		//添加登录径拦截器
//		registry.addInterceptor(loginInterceptor)
//		        .excludePathPatterns(excludeLoginsList)
//				.addPathPatterns("/**");
		
//		//添加路径拦截器
//		List<String> excludePathList = new ArrayList<String>();
//		excludePathList.add("/static/**");
//		registry.addInterceptor(pathInterceptor)
//				.excludePathPatterns(excludePathList)
//				.addPathPatterns("/**");
		
		//添加用户信息拦截器
		List<String> excludeUserList = new ArrayList<String>();
		excludeUserList.add("/static/**");
		excludeUserList.add("/login");
		excludeUserList.add("/logout");
		excludeUserList.add("/error");
		registry.addInterceptor(userInfoInterceptor)
				.excludePathPatterns(excludeUserList)
				.addPathPatterns("/**");
	}

}
