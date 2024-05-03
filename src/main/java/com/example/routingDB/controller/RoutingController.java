package com.example.routingDB.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.routingDB.dto.QueryDTO;
import com.example.routingDB.routing.DataSourceContextHolder;
import com.example.routingDB.service.RoutingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RoutingController {

	private final RoutingService routingServiceImpl;
	
	@PostMapping("/routing/select")
	public ResponseEntity<List<Map<String, Object>>> select(@RequestBody QueryDTO queryDTO) {
		try {
			List<Map<String, Object>> result = routingServiceImpl.select(queryDTO);
			return ResponseEntity.ok().body(result);
		} finally {
			DataSourceContextHolder.clear();
		}
	}
}
