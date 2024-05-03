package com.example.routingDB.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.routingDB.dto.QueryDTO;
import com.example.routingDB.routing.DataSourceContextHolder;
import com.example.routingDB.routing.repository.DataSourceRepository;
import com.example.routingDB.service.RoutingService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoutingServiceImpl implements RoutingService {
	
	private final DataSourceRepository dataSourceRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> select(QueryDTO queryDTO) {
		List<Map<String, Object>> resultList = new ArrayList<>();
		String sourceKey = DataSourceContextHolder.getDataSourceKey();
		log.info("찾은  키값 = {}", sourceKey);
		DataSource dataSource = dataSourceRepository.getDataSource(sourceKey);

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(queryDTO.getQuery());
             ResultSet resultSet = preparedStatement.executeQuery()) {

        	
        	int columnCount = resultSet.getMetaData().getColumnCount();
        	
            while (resultSet.next()) {
            	Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = resultSet.getMetaData().getColumnName(i);
                    Object columnValue = resultSet.getObject(i);
                    row.put(columnName, columnValue);
                }
                resultList.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // 예외 처리를 원하는 방식으로 변경 가능
        }
        
        return resultList;
	}
	
}
