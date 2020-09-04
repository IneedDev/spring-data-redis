package com.example.controller;

import com.example.config.EnvHelper;
import com.example.controller.endpoint.HealthEndpoint;
import com.example.model.HealthResponse;
import com.example.service.impl.ConsumerService;
import com.example.service.impl.HealthService;
import com.example.service.impl.Producer;
import com.github.jcustenborder.kafka.connect.redis.RedisSession;
import com.github.jcustenborder.kafka.connect.redis.RedisSinkConnector;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class Health implements HealthEndpoint {

    private final ConsumerService consumerService;
    private final HealthService healthService;
    private final EnvHelper envHelper;
    private final Producer producer;


    @Override
    public ResponseEntity<HealthResponse> health() {
        HealthResponse healthResponse = healthService.generateHealthResponse();
        return new ResponseEntity<>(healthResponse, HttpStatus.OK);
    }

}
