package com.example.controller;

import com.example.controller.endpoint.RedisEndpoint;
import com.example.logger.Logger;
import com.example.model.HealthResponse;
import com.example.service.impl.HealthServiceImpl;
import com.example.service.impl.RedisServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/rest/user")
@NoArgsConstructor
public class RedisController implements RedisEndpoint {

    @Autowired
    private HealthServiceImpl healthService;

    @Autowired
    private RedisServiceImpl redisService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public ResponseEntity<HealthResponse> health() {
        HealthResponse healthResponse = healthService.generateHealthResponse();
        System.out.println("dupa");
        return new ResponseEntity<>(healthResponse, HttpStatus.OK);
    }

    @Override
    public double feedRedis() {
        StopWatch crunchifyWatch = new StopWatch();
        crunchifyWatch.start();
        Logger.logDataStartUploadToRedis();
        redisService.generateRedisData();
        Logger.logDataStopUploadToRedis();
        crunchifyWatch.stop();
        System.out.println("\n1. prettyPrint Result: " + crunchifyWatch.prettyPrint());
        return crunchifyWatch.getTotalTimeSeconds();
    }

    @Override
    public List<String> getDataFromRedis(String pattern) {
        System.out.println("getAllData");
        return redisService.getDataFromRedis(pattern);
    }

    @Override
    public void flushDatabase() {
        Logger.logDataStartFlushDbToRedis();
        redisService.flushDatabase();
        Logger.logDataStopFlushDbToRedis();
    }
}
