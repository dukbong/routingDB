package com.example.routingDB.thread;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.routingDB.dto.UserDbDTO;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Service
public class ConnectionManager {

    private final Map<String, JdbcTemplate> jdbcTemplateCache = new ConcurrentHashMap<>();

    public JdbcTemplate getJdbcTemplate(UserDbDTO userDbDTO) {
        // 캐시에 데이터베이스 이름으로 저장된 JdbcTemplate이 있는지 확인
        return jdbcTemplateCache.computeIfAbsent(userDbDTO.getDatabaseName(), key -> {
            // 새로 생성할 경우 HikariDataSource를 사용하여 데이터베이스 연결을 생성
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(userDbDTO.getUrl());
            config.setUsername(userDbDTO.getUserName());
            config.setPassword(userDbDTO.getPassword());
            config.setDriverClassName(userDbDTO.getDriverName());

            HikariDataSource dataSource = new HikariDataSource(config);
            return new JdbcTemplate(dataSource);
        });
    }
}