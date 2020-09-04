package com.example.service.impl;

import com.example.DateHelper;
import com.example.config.EnvHelper;
import com.example.model.HealthResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class HealthService implements com.example.service.HealthService {

    private EnvHelper envHelper;

    @Override
    public HealthResponse generateHealthResponse() {
        return HealthResponse.builder()
                .logLevel(envHelper.getLogLevel())
                .appName(envHelper.getApplicationName())
                .appStartTime(DateHelper.getFormattedLocalDateTime(envHelper.getStartDate()))
                .tomcatConfig(getTomcatConfig())
                .freeMemory(Runtime.getRuntime().freeMemory())
                .totalMemory(Runtime.getRuntime().totalMemory())
                .build();
    }

    private Map<String, String> getTomcatConfig() {
        Map<String, String> tomcatConfig = new HashMap<>();
        tomcatConfig.put("ConnectionTimeout", envHelper.getConnectionTimeout().toString());
        tomcatConfig.put("AcceptCount", envHelper.getAcceptCount().toString());
        tomcatConfig.put("MinSpareThreads", envHelper.getMinSpareThreads().toString());
        tomcatConfig.put("MaxConnections", envHelper.getMaxConnections().toString());
        tomcatConfig.put("KeepAliveTimeout", envHelper.getKeepAliveTimeout().toString());
        tomcatConfig.put("MaxKeepAliveRequests", envHelper.getMaxKeepAliveRequests().toString());
        tomcatConfig.put("MaxThreads", envHelper.getMaxThreads().toString());
        tomcatConfig.put("Port", envHelper.getTomcatPort().toString());
        tomcatConfig.put("IpAddress", envHelper.getLocalAddress());
        return tomcatConfig;
    }
}
