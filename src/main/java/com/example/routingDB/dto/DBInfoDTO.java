package com.example.routingDB.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DBInfoDTO {
	
	private String key;
	private String driverName;
	private String url;
	private String username;
	private String password;
	
	@Builder
	public DBInfoDTO(String key, String driverName, String url, String username, String password) {
		this.key = key;
		this.driverName = driverName;
		this.url = url;
		this.username = username;
		this.password = password;
	}
}
