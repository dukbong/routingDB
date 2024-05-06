package com.example.routingDB.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.routingDB.entity.UserDB;

public interface UserDBRepository extends JpaRepository<UserDB, Long> {

}
