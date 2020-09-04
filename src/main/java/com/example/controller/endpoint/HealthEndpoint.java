package com.example.controller.endpoint;

import com.example.model.HealthResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.ZonedDateTime;

@Api
public interface HealthEndpoint {

    @ApiOperation(value = "Health endpoint", httpMethod = "GET", response = ZonedDateTime.class)
    @GetMapping("/health")
    ResponseEntity<HealthResponse> health();
}
