package com.example.routingDB.service;

import com.example.routingDB.dto.UserDbDTO;

public interface ConnectingService {

	void saveDbInfo(UserDbDTO userDbDTO);

	void connectDbInfo(String key);

}
