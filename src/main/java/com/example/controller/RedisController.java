package com.example.controller;

import com.example.controller.endpoint.RedisEndpoint;
import com.example.model.Event;
import com.example.model.HealthResponse;
import com.example.repo.EventRepository;
import com.example.service.impl.HealthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@AllArgsConstructor
public class RedisController implements RedisEndpoint {

    private final HealthService healthService;
    private final EventRepository eventRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public ResponseEntity<HealthResponse> health() {
        HealthResponse healthResponse = healthService.generateHealthResponse();
        return new ResponseEntity<>(healthResponse, HttpStatus.OK);
    }

    @Override
    public int feedRedis() {
        int number = 2;
        //generateEvents(number);

        String phoneNumber = "123465";


        Map<String, String> valueMap = new HashMap<>();
        valueMap.put("phoneNumber", phoneNumber);
        valueMap.put("boxMachine", "KRK132");


        redisTemplate.opsForHash().put("Event:1:123", "fdfdff", "new test");
        redisTemplate.opsForHash().putAll("Event:1:"+phoneNumber, valueMap);

        return number;
    }

    private void generateEvents(int n) {
        for (int i = 0; i < n; i++) {
            eventRepository.save(new Event(i, "132", true, "KRK-001", 2));
        }
    }
}
