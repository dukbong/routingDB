package com.example.routingDB.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDbDTO {
	
	private String createUser;

	private String databaseName;
	
	private String url;
	
	private String driverName;
	
	private String userName;
	
	private String password;
	
	@Builder
	public UserDbDTO(String createUser, String databaseName, String url, String driverName, String userName, String password) {
		this.createUser = createUser;
		this.databaseName = databaseName;
		this.url = url;
		this.driverName = driverName;
		this.userName = userName;
		this.password = password;
	}
	
}
