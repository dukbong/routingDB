package com.example.routingDB.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.routingDB.entity.UserEntity;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

}
