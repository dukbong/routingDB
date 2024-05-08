package com.example.routingDB.serviceImpl;

import org.springframework.stereotype.Service;

import com.example.routingDB.dto.UserDbDTO;
import com.example.routingDB.entity.UserDB;
import com.example.routingDB.entity.UserEntity;
import com.example.routingDB.repository.UserDBRepository;
import com.example.routingDB.repository.UserEntityRepository;
import com.example.routingDB.service.ConnectingService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConnectingServiceImpl implements ConnectingService {
	
	private final UserEntityRepository userEntityRepository;
	private final UserDBRepository userDBRepository;
	
	@Override
	public void saveDbInfo(UserDbDTO userDbDTO) {
		UserEntity userEntity = userEntityRepository.findByUserName(userDbDTO.getCreateUser()).orElseThrow(() -> new IllegalStateException("사용자를 찾을 수 없습니다 - saveDbInfo"));
		UserDB userDB = UserDB.builder().databaseName(userDbDTO.getDatabaseName())
										.url(userDbDTO.getUrl())
										.driverName(userDbDTO.getDriverName())
										.username(userDbDTO.getUserName())
										.password(userDbDTO.getPassword())
										.build();
		
		userDB.addDB(userEntity);
		
		userDBRepository.save(userDB);
	}
	
	@Override
	public void connectDbInfo(String key) {
		
	}
	
}
