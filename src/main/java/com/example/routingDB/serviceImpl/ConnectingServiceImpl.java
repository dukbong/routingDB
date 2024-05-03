package com.example.routingDB.serviceImpl;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.example.routingDB.dto.DBInfoDTO;
import com.example.routingDB.routing.DataSourceContextHolder;
import com.example.routingDB.routing.DataSourceFactory;
import com.example.routingDB.routing.repository.DataSourceRepository;
import com.example.routingDB.service.ConnectingService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConnectingServiceImpl implements ConnectingService {

	private final DataSourceRepository dataSourceRepository;
	
	@Override
	public void connect(DBInfoDTO dbInfoDTO) {
		Assert.hasText(dbInfoDTO.getKey(), "연결한 DB의 키값은 필수 사항입니다.");
		Assert.hasText(dbInfoDTO.getDriverName(), "드라이버 이름은 필수 사항입니다.");
		Assert.hasText(dbInfoDTO.getUrl(), "접속 URL은 필수 사항입니다.");
		Assert.hasText(dbInfoDTO.getUsername(), "접속 ID는 필수 사항입니다.");
		DataSource dataSource = DataSourceFactory.createDataSource(dbInfoDTO.getDriverName(), dbInfoDTO.getUrl(), dbInfoDTO.getUsername(), dbInfoDTO.getPassword());
		dataSourceRepository.addDataSource(dbInfoDTO.getKey(), dataSource);
	}

	@Override
	public void select(String key) {
		Assert.hasText(key, "DB를 선택하기 위해선 키값이 필수 사항입니다.");
		if (!dataSourceRepository.containsDataSource(key)) {
            throw new IllegalArgumentException("No data source found for key: " + key);
        }
		log.info("key = {}", key);
		DataSourceContextHolder.setDataSourceKey(key);
	}

}
