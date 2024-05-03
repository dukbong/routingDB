package com.example.routingDB.routing.repository;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

@Repository
public class DataSourceRepository {
	
	private final Map<String, DataSource> dataSourceMap = new HashMap<>();
	private DataSource defaultDataSource;

	public DataSource getDataSource(String key) {
		return dataSourceMap.get(key);
	}
	
	public void addDataSource(String key, DataSource dataSource) {
		dataSourceMap.put(key, dataSource);
	}
	
	public boolean containsDataSource(String key) {
		return dataSourceMap.containsKey(key);
	}
	
    public DataSource getDefaultDataSource() {
        return defaultDataSource;
    }

    public Map<Object, Object> getAllDataSources() {
        return new HashMap<>(dataSourceMap);
    }
}
