package com.example.routingDB.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.routingDB.entity.UserDB;

public interface UserDBRepository extends JpaRepository<UserDB, Long> {
	Optional<UserDB> findByDatabaseNameAndUserEntity_UserName(String databaseName, String userName);
	Optional<UserDB> findByDatabaseName(String databaseName);
}
