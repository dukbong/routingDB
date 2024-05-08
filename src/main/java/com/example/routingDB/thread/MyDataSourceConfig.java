package com.example.routingDB.thread;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class MyDataSourceConfig {

	    @Bean
	    public DataSource dataSource() {
	        HikariDataSource dataSource = new HikariDataSource();
	        dataSource.setMaximumPoolSize(10);
			dataSource.setMinimumIdle(2);
			dataSource.setIdleTimeout(60000);
	        dataSource.setMaxLifetime(1800000);

	        MyRoutingDataSource routingDataSource = new MyRoutingDataSource();
	        routingDataSource.setDefaultTargetDataSource(dataSource);
	        return routingDataSource;
	    }
}
