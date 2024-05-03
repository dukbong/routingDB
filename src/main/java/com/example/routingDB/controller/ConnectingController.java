package com.example.routingDB.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.routingDB.dto.DBInfoDTO;
import com.example.routingDB.routing.DataSourceContextHolder;
import com.example.routingDB.service.ConnectingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ConnectingController {
	
	private final ConnectingService connectingServiceImpl;
	
	@PostMapping("/connection")
	public ResponseEntity<String> connection(@RequestBody DBInfoDTO dbInfoDTO) {
		connectingServiceImpl.connect(dbInfoDTO);
		return ResponseEntity.ok().body(String.format("DB[%s]에 정상 접근하였습니다.", dbInfoDTO.getKey()));
	}
	
	@GetMapping("/driver/select")
	public ResponseEntity<String> driverSelect(@RequestParam(value = "key") String key){
		connectingServiceImpl.select(key);
		return ResponseEntity.ok().body(String.format("DB[%s]를 선택하였습니다. [Thread = %s]", key, DataSourceContextHolder.getDataSourceKey()));
	}
	

}
