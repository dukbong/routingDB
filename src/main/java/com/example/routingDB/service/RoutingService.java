package com.example.routingDB.service;

import java.util.List;
import java.util.Map;

import com.example.routingDB.dto.QueryDTO;

public interface RoutingService {

	List<Map<String, Object>> select(QueryDTO queryDTO);

}
