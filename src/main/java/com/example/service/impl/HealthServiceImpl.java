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
public class HealthServiceImpl implements com.example.service.HealthService {

    private EnvHelper envHelper;

    @Override
    public HealthResponse generateHealthResponse() {
        return HealthResponse.builder()
                .appName(envHelper.getApplicationName())
                .appStartTime(DateHelper.getFormattedLocalDateTime(envHelper.getStartDate()))
                .build();
    }
}
