package com.example.routingDB.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.routingDB.dto.KeyAndQuery;
import com.example.routingDB.dto.UserDbDTO;
import com.example.routingDB.service.ConnectingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ConnectController {

	private final ConnectingService connectingSerivceImpl;

	@PostMapping("/save/dbinfo")
	public ResponseEntity<String> saveDbInfo(@RequestBody UserDbDTO userDbDTO) {
		connectingSerivceImpl.saveDbInfo(userDbDTO);
		return ResponseEntity.ok().body("Database save successfully : " + userDbDTO.getDatabaseName());
	}
	
	@PostMapping("/select/dbinfo")
	public ResponseEntity<UserDbDTO> selectDbInfo(@RequestBody String key){
		UserDbDTO selectedDb = connectingSerivceImpl.selectDbInfo(key);
		return ResponseEntity.ok().body(selectedDb);
	}
	
	@PostMapping("/query")
	public ResponseEntity<Map<String, Object>> query(@RequestBody KeyAndQuery keyAndQuery) {
		Map<String, Object> queryResult = connectingSerivceImpl.query(keyAndQuery);
		return null;
	}
}
