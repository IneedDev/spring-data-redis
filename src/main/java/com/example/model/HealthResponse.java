package com.example.model;

import lombok.Builder;
import lombok.Data;

import java.util.Properties;

@Data
@Builder
public class HealthResponse {
    private String appStartTime;
    private String appName;
    private String redisHost;
    private int redisPort;
    private String total_system_memory;
    private String redis_version;
    private String redis_mode;
    private String used_memory_human;

    private String aof_enabled;
    private String loading;

    private Properties info;


}
