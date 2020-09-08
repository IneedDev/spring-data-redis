package com.example.controller;

import com.example.controller.endpoint.RedisEndpoint;
import com.example.logger.Logger;
import com.example.model.HealthResponse;
import com.example.service.impl.HealthServiceImpl;
import com.example.service.impl.RedisServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/redis")
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
        return new ResponseEntity<>(healthResponse, HttpStatus.OK);
    }

    @Override
    public void feedRedis(int version, int volume) {
        Logger.logDataStartUploadToRedis();
        redisService.generateRedisData(version, volume);
        Logger.logDataStopUploadToRedis();
    }

    @Override
    public List<String> getDataFromRedis(String pattern) {
        Logger.logDataStartGetKeysByPatternToRedis(pattern);
        List<String> list = redisService.getDataFromRedisByKeyMatchingPattern(pattern);
        Logger.logDataStopGetKeysByPatternToRedis(list.size(), pattern);
        return list;
    }

    @Override
    public void flushDatabase() {
        Logger.logDataStartFlushDbToRedis();
        redisService.flushDatabase();
        Logger.logDataStopFlushDbToRedis();
    }
}
