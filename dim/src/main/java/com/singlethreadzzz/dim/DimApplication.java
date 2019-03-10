package com.singlethreadzzz.dim;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.singlethreadzzz.dim.mapper")
@SpringBootApplication
public class DimApplication {

	public static void main(String[] args) {
		SpringApplication.run(DimApplication.class, args);
	}

}
