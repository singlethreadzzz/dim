package com.singlethreadzzz.dim.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

@Configuration
public class ShiroConfig {
	
	@Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

}
