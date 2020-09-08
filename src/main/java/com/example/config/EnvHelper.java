package com.example.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@Component
public class EnvHelper {

    private static LocalDateTime startDate = LocalDateTime.now();

    @Value("${app.name}")
    private String applicationName;

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    public LocalDateTime getStartDate() {
        return startDate;
    }
}
