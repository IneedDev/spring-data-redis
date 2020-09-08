package com.example.service.impl;

import com.example.utils.DateHelper;
import com.example.config.EnvHelper;
import com.example.model.HealthResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
@AllArgsConstructor
public class HealthServiceImpl implements com.example.service.HealthService {

    private EnvHelper envHelper;
    private RedisTemplate redisTemplate;

    @Override
    public HealthResponse generateHealthResponse() {
        Properties properties = redisTemplate.getConnectionFactory().getConnection().serverCommands().info();
        return HealthResponse.builder()
                .appName(envHelper.getApplicationName())
                .appStartTime(DateHelper.getFormattedLocalDateTime(envHelper.getStartDate()))
                .redisHost(envHelper.getRedisHost())
                .redisPort(envHelper.getRedisPort())
//                .info(redisTemplate.getConnectionFactory().getConnection().serverCommands().info())
                .total_system_memory(properties.getProperty("total_system_memory"))
                .used_memory_human(properties.getProperty("used_memory_human"))
                .redis_version(properties.getProperty("redis_version"))
                .redis_mode(properties.getProperty("redis_mode"))
                .aof_enabled(properties.getProperty("aof_enabled"))
                .loading(properties.getProperty("loading"))
                .build();
    }
}
