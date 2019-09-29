package com.singlethreadzzz.dim.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
public class TransactionManagementConfig implements TransactionManagementConfigurer {
	
	@Resource(name="mysql")
    private PlatformTransactionManager mysql;

	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return this.mysql;
	}
	
	// 创建事务管理器1
    @Bean(name = "mysql")
    public PlatformTransactionManager txManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

	

}
