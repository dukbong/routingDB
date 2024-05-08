package com.example.routingDB.service;

import java.util.Map;

import com.example.routingDB.dto.KeyAndQuery;
import com.example.routingDB.dto.UserDbDTO;

public interface ConnectingService {

	void saveDbInfo(UserDbDTO userDbDTO);

	UserDbDTO selectDbInfo(String key);

	Map<String, Object> query(KeyAndQuery keyAndQuery);

}
