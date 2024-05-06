package com.example.routingDB.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserEntityDTO {
	
	private String userName;
	
	private String userPassword;

	@Builder
	public UserEntityDTO(String userName, String userPassword) {
		this.userName = userName;
		this.userPassword = userPassword;
	}
	
}
