package com.example.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class HealthResponse {
    private String appStartTime;
    private String appName;
    private String logLevel;
    private long freeMemory;
    private long totalMemory;
    private int restConnectTimeout;
    private int restReadTimeout;
    private int restWriteTimeout;
    private Map<String, String> tomcatConfig;
}
