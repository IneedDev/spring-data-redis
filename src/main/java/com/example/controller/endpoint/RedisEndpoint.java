package com.example.controller.endpoint;

import com.example.model.HealthResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.ZonedDateTime;
import java.util.List;

@Api
public interface RedisEndpoint {

    @ApiOperation(value = "Health endpoint", httpMethod = "GET")
    @GetMapping("/health")
    ResponseEntity<HealthResponse> health();

    @ApiOperation(value = "Redis feed endpoint", httpMethod = "GET")
    @GetMapping("/feed/{version}/{volume}")
    void feedRedis(@PathVariable int version, @PathVariable int volume);

    @ApiOperation(value = "Redis get all data by scan", httpMethod = "GET")
    @GetMapping("/getAllData/{pattern}")
    List<String> getDataFromRedis(@PathVariable String pattern);

    @ApiOperation(value = "Redis flush database", httpMethod = "GET")
    @GetMapping("/flushDb")
    void flushDatabase();
}
