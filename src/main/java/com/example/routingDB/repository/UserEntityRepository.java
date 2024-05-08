package com.example.routingDB.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.routingDB.entity.UserEntity;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
	Optional<UserEntity> findByUserName(String userName);
}
