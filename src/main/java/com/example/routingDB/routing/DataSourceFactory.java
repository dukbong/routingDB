package com.example.routingDB.routing;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.example.routingDB.routing.enums.DriverClassNameEnum;

public class DataSourceFactory {
	
	public static DataSource createDataSource(String DriverName, String url, String username, String password) {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(DriverClassNameEnum.searchDriver(DriverName));
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}

}
