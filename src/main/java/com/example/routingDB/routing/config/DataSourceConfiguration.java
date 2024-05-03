package com.example.routingDB.routing.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.routingDB.routing.DynamicRoutingDataSource;
import com.example.routingDB.routing.repository.DataSourceRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class DataSourceConfiguration {

	private final DataSourceRepository dataSourceRepository;
	
	@Bean
	public DataSource routingDataSource() {
		return new DynamicRoutingDataSource(dataSourceRepository);
	}
	
}
