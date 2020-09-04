package com.example.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HealthResponse {
    private String appStartTime;
    private String appName;
}
