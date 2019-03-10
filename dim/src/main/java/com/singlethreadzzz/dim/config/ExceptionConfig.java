package com.singlethreadzzz.dim.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.singlethreadzzz.dim.handler.MyExceptionResolver;

@Configuration
public class ExceptionConfig {
	
    @Bean
    public MyExceptionResolver MyExceptionResolver() {
        return new MyExceptionResolver();
    }

}
