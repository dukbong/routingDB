package com.example.routingDB.routing.repository;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.example.routingDB.dto.UserDBDTO;
import com.example.routingDB.entity.UserDB;
import com.example.routingDB.repository.UserDBRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class DataSourceRepository {
	
	private final Map<String, DataSource> dataSourceMap = new HashMap<>();
	private DataSource defaultDataSource;
	
	// DB에서 관리
	private final UserDBRepository userDBRepository;

	public DataSource getDataSource(String key) {
		return dataSourceMap.get(key);
	}
	
	public void addDataSource(String key, DataSource dataSource) {
		dataSourceMap.put(key, dataSource);
	}
	
	// DB에 저장하기
	public void addDataSource(UserDBDTO userDBDTO) {
		userDBRepository.save(userDBDTO.convertEntity());
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
