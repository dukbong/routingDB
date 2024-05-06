package com.example.routingDB.entity;

import org.springframework.dao.DuplicateKeyException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "user_db_gen", sequenceName = "user_db_seq", initialValue = 1, allocationSize = 50)
public class UserDB {
	
	@Id
	@GeneratedValue(generator = "user_db_gen")
	@Column(name = "userDB_id")
	private Long id;
	
	private String key;
	
	private String url;
	
	private String driverName;
	
	private String username;
	
	private String password;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userEntity_id")
	private UserEntity userEntity;
	
	@Builder
	public UserDB(Long id, String key, String url, String driverName, String username, String password) {
		this.id = id;
		this.key = key;
		this.url = url;
		this.driverName = driverName;
		this.username = username;
		this.password = password;
	}
	
	// 추가 및 삭제의 경우 외래키를 가진 쪽에서 진행
	public void addDB() {
		boolean containKey = this.userEntity.getUserDBs().stream().anyMatch(i -> i.getKey().equals(this.key));
		if(containKey) {
			throw new DuplicateKeyException("Duplicate DB key");
		}
		this.userEntity.getUserDBs().add(this);
	}
	
}
