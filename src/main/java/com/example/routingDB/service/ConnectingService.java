package com.example.routingDB.service;

import com.example.routingDB.dto.DBInfoDTO;

public interface ConnectingService {

	void connect(DBInfoDTO dbInfoDTO);

	void select(String key);

}
