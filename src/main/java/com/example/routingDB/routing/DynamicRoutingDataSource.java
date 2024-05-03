package com.example.routingDB.routing;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.example.routingDB.routing.repository.DataSourceRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {
	
	private final DataSourceRepository dataSourceRepository;
	
    public DynamicRoutingDataSource(DataSourceRepository dataSourceRepository) {
        this.dataSourceRepository = dataSourceRepository;
        this.setDefaultTargetDataSource(dataSourceRepository.getDefaultDataSource());
        this.setTargetDataSources(dataSourceRepository.getAllDataSources());
    }

	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceContextHolder.getDataSourceKey();
	}

	@Override
	protected DataSource determineTargetDataSource() {
		String key = DataSourceContextHolder.getDataSourceKey();
		log.info("key = {}", key);
		Assert.hasText(key, "No data source key");
		DataSource targetDataSource = dataSourceRepository.getDataSource(key);
		Assert.notNull(targetDataSource, "No dataSource found for key =>" + key);
		return targetDataSource;
	}
}
