package com.example.routingDB.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QueryDTO {
	
	private String key;
	private String query;
	
	@Builder
	public QueryDTO(String key, String query) {
		super();
		this.key = key;
		this.query = query;
	}
	
}
