package com.example.routingDB.dto;

import com.example.routingDB.entity.UserDB;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserDBDTO {
	
	private String key;
	
	private String url;
	
	private String driverName;
	
	private String username;
	
	private String password;
	
	@Builder
	public UserDBDTO(String key, String url, String driverName, String username, String password) {
		this.key = key;
		this.url = url;
		this.driverName = driverName;
		this.username = username;
		this.password = password;
	}
	
	public UserDB convertEntity() {
		return UserDB.builder()
					 .key(this.key)
					 .url(this.key)
					 .driverName(this.driverName)
					 .username(this.username)
					 .password(this.password)
					 .build();
	}

}
