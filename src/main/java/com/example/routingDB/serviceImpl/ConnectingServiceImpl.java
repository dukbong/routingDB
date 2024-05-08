package com.example.routingDB.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.routingDB.dto.KeyAndQuery;
import com.example.routingDB.dto.UserDbDTO;
import com.example.routingDB.entity.UserDB;
import com.example.routingDB.entity.UserEntity;
import com.example.routingDB.repository.UserDBRepository;
import com.example.routingDB.repository.UserEntityRepository;
import com.example.routingDB.service.ConnectingService;
import com.example.routingDB.thread.ConnectionManager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConnectingServiceImpl implements ConnectingService {

	private final UserEntityRepository userEntityRepository;
	private final UserDBRepository userDBRepository;
	private final ConnectionManager connectionManager;

	@Override
	public void saveDbInfo(UserDbDTO userDbDTO) {
		UserEntity userEntity = userEntityRepository.findByUserName(userDbDTO.getCreateUser())
				.orElseThrow(() -> new IllegalStateException("사용자를 찾을 수 없습니다 - saveDbInfo"));
		UserDB userDB = UserDB.builder().databaseName(userDbDTO.getDatabaseName()).url(userDbDTO.getUrl())
				.driverName(userDbDTO.getDriverName()).username(userDbDTO.getUserName())
				.password(userDbDTO.getPassword()).build();

		userDB.addDB(userEntity);

		userDBRepository.save(userDB);
	}

	@Override
	public UserDbDTO selectDbInfo(String key) {
		UserDB findDb = userDBRepository.findByDatabaseName(key)
				.orElseThrow(() -> new IllegalArgumentException("데이터 베이스를 찾을 수 없습니다."));
		UserDbDTO userDbDTO = UserDbDTO.builder().createUser(findDb.getUserEntity().getUserName())
				.databaseName(findDb.getDatabaseName()).driverName(findDb.getDriverName())
				.userName(findDb.getUsername()).password(findDb.getPassword()).build();
		connectionManager.getJdbcTemplate(userDbDTO);
		return userDbDTO;
	}

	@Override
	public Map<String, Object> query(KeyAndQuery keyAndQuery) {
		UserDB userDB = userDBRepository.findByDatabaseName(keyAndQuery.getKey())
				.orElseThrow(() -> new IllegalArgumentException("데이터베이스를 찾을 수 없습니다."));

		UserDbDTO userDbDTO = UserDbDTO.builder().createUser(userDB.getUserEntity().getUserName())
				.databaseName(userDB.getDatabaseName()).driverName(userDB.getDriverName())
				.userName(userDB.getUsername()).password(userDB.getPassword()).build();
		JdbcTemplate jdbcTemplate = connectionManager.getJdbcTemplate(userDbDTO);
	    List<Map<String, Object>> result = jdbcTemplate.queryForList(keyAndQuery.getQuery());
		if (result.size() == 1) {
			return result.get(0);
		} else {
			Map<String, Object> response = new HashMap<>();
			response.put("results", result);
			return response;
		}
	}

}
