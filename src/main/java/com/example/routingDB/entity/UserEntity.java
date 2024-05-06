package com.example.routingDB.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.util.Assert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "user_entity_gen", sequenceName = "user_entity_seq", initialValue = 1, allocationSize = 50)
public class UserEntity {
	
	@Id
	@GeneratedValue(generator = "user_entity_gen")
	@Column(name = "userEntity_id")
	private Long id;
	
	private String userName;
	
	private String userPassword;
	
	@OneToMany(mappedBy = "userEntity")
	private List<UserDB> userDBs = new ArrayList<>();
	
	@Builder
	public UserEntity(Long id, String userName, String userPassword) {
		super();
		this.id = id;
		this.userName = userName;
		this.userPassword = userPassword;
	}
	
}
